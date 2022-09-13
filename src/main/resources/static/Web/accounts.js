const { createApp } = Vue

  createApp({
    data() {
      return {
        message: 'Hello Vue!',
        client:{},
        accounts:{},
        loans:{},
      }
    },
    created(){
        this.getData()
    },
    methods:{
        getData(){
            axios.get('/api/clients/current')
            .then((response)=> {
              this.client=response.data
              this.accounts=this.client.accounts
              this.loans=this.client.loans
              console.log(this.loans)
            })
        },
        cerrarSesion(){
          axios.post('/api/logout')
          .then(response => {
            window.location.href="../index.html"
          })
          
      },
        
    },
  }).mount('#app')