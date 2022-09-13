const { createApp } = Vue

  createApp({
    data() {
      return {
        message: 'Hello Vue!',
        cliente1:{},
        cuentas:{},
        cuentaSeleccionada:{},
        transacciones:[],
        nombreCuenta:"",
        balanceCuenta:0,
      }
    },
    created(){
        this.getData()
    },
    methods:{
        getData(){
          axios.get('/api/clients/current') //trae el cliente 1 (Melba)
          .then((response)=> {
              this.client=response.data
              this.accounts=response.data.accounts
              console.log(this.client)
              this.encontrarCuentaSeleccionada()
          })
        },
        encontrarCuentaSeleccionada() {
          params = new URLSearchParams(window.location.search)
          const idURL = params.get('id')
          this.cuentaSeleccionada= this.accounts.find(cuenta=>cuenta.id==idURL)
        },
        
    },
  }).mount('#app')