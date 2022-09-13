const { createApp } = Vue

  createApp({
    data() {
      return {
        cards:{},
        trhuDate:""
      }
    },
    created(){
        this.getData()
    },
    methods:{
        getData(){
            axios.get('/api/clients/current') //trae el cliente 1 (Melba)
            .then((response)=> {
              this.cards=response.data.cards
              console.log(this.cards)
            })
        },
        
    },
  }).mount('#app')