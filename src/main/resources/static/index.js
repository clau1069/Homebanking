const { createApp } = Vue

  createApp({
    data() {
      return {
        emailInput:"",
        passwordInput:"",
        mensaje:"",
        registerFirstName:"",
        registerLastName:"",
        registerEmail:"",
        registerPassword:"",
        error:"",
        mensajeRegister:"",
        usuario:"",
        mjeNoAutenticado:"",
      }
    },
    created(){
      axios.get('/api/clients/current') //trae el cliente 1 (Melba)
      .then((response)=> {
        this.usuario="autenticado"
        console.log(this.usuario)
      }).catch((error)=>{
        this.rutaBtnMyAccounts="#log-in"
        this.usuario="visitante"
        console.log(this.usuario)
      },
      
      )
    },
    methods:{
        signIn(){
            axios.post('/api/login',`email=${this.emailInput}&password=${this.passwordInput}`,{headers:{'content-type':'application/x-www-form-urlencoded'}})
            .then(response => {
              window.location.href="/web/accounts.html"
            }).catch((error)=> {
              
              if (error.response) {
                // The request was made and the server responded with a status code
                // that falls out of the range of 2xx
                this.mensaje= error.response.data.error
                console.log(this.mensaje)
                console.log(error.response.data);
                console.log(error.response.status);
                console.log(error.response.headers);
              } else if (error.request) {
                // The request was made but no response was received
                // `error.request` is an instance of XMLHttpRequest in the browser and an instance of
                // http.ClientRequest in node.js
                console.log(error.request);
              } else {
                // Something happened in setting up the request that triggered an Error
                console.log('Error', error.message);
              }
              console.log(error.response);
              this.error=error.response.data.message
            })
            
        },
        register(){
          axios.post('/api/clients',`firstName=${this.registerFirstName}&lastName=${this.registerLastName}&email=${this.registerEmail}&password=${this.registerPassword}`,{headers:{'content-type':'application/x-www-form-urlencoded'}})
          .then(response => console.log('registered'))
          .catch( (error)=> {
            this.mensajeRegister=error.response.data
            console.log(this.mensajeRegister)
          })
          
        },
        clickMyAccount(){
          this.mjeNoAutenticado="To access your accounts you must log in!"
        },
        cerrarSesion(){
          axios.post('/api/logout')
          .then(response => {
            window.location.href="../index.html"
          })
          
      },
        
    },
  }).mount('#app')