const { createApp } = Vue

  createApp({
    data() {
      return {
        arrayClientes:[],
        message: 'Hello Vue!',
        respuesta:{},
        objetocliente:{
          firstName:"",
          lastName:"",
          email:"",
        },
        
      }
    },
    created(){
      this.getData()
      console.log("hola")
    },
    methods:{
      getData(){
        axios.get('/rest/clients')
      .then((response) =>{
        this.arrayClientes= response.data._embedded.clients
        console.log(this.arrayClientes);
        this.respuesta = response;
        
      })
      },
      añadirUsuario(){
        console.log(this.objetocliente)
        if(this.objetocliente.firstName && this.objetocliente.lastName && this.objetocliente.email){
          /* axios.post(('/rest/clients'),(this.objetocliente)).then((response)=>{
            this.getData()
          }) */
          axios({
            method: 'post',
            url: '/rest/clients',
            data: this.objetocliente
          });
        }
      this.getData()
      },
    },
    computed:{
    }
  }).mount('#app')

