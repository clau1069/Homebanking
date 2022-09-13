const { createApp } = Vue

  createApp({
    data() {
      return {
        inputAmount:0,
        inputDescription:"-",
        accountOrigin:"-",
        accountDestiny:"-",
        usuario:"",
        cuentas:{},
        destinyOfTransfer: 0,
        errorPetición:0,
        aparecerModal: "no",
        aparecerDivConfirm:"si",
        aparecerDivSuccess:"no",
        aparecerDivError:"",
      }
    },
    created(){
      axios.get('/api/clients/current')
      .then((response)=> {
        this.usuario=response.data
        this.cuentas=this.usuario.accounts
        
      })
    },
    methods:{
        signIn(){
            axios.post('/api/transactions',`amount=${this.inputAmount}&description=${this.inputDescription}&numberOrigin=${this.numberOrigin}&numberDestiny=${this.numberDestiny}`,{headers:{'content-type':'application/x-www-form-urlencoded'}})
            .then(response => {
              window.location.href="/web/accounts.html"
            })
            
        },
        cerrarSesion(){
            axios.post('/api/logout')
            .then(response => {
              window.location.href="../index.html"
            })
            
        },
        asignvalueOwn(){
          this.destinyOfTransfer="own"
        },
        asignValueAnother(){
          this.destinyOfTransfer="another"
        },
        createTransaction(e){
          this.errorPetición=""
          e.preventDefault()
          axios.post('/api/transactions', `amount=${this.inputAmount}&description=${this.inputDescription}&numberOrigin=${this.accountOrigin}&numberDestiny=${this.accountDestiny}`,{headers:{'content-type':'application/x-www-form-urlencoded'}})
          .then(response=>{
            console.log(response)
            console.log("se hizo la transacción")
            this.aparecerDivSuccess="si"
            this.aparecerDivConfirm="no"
          })
          .catch(error=>{
            this.errorPetición=error.response.data
            console.log("ocurrió un error")
            this.aparecerDivConfirm="no"
            this.aparecerDivError="si"
          })
        },
        sendMoney(e){
          this.errorPetición=""
          e.preventDefault()
          this.aparecerModal="si"
          this.aparecerDivConfirm="si"
        },
        cancelTransaction(e){
          e.preventDefault()
          this.aparecerModal="no"
          this.aparecerDivSuccess="no"
          this.aparecerDivError="no"
        }
        
    },
  }).mount('#app')