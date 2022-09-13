const { createApp } = Vue

  createApp({
    data() {
      return {
        type:"",
        color:"",
      }
    },
    created(){
    },
    methods:{
        createCard(e){
            e.preventDefault()
            axios.post(`/api/clients/current/cards?type=${this.type}&color=${this.color}`,{headers:{'content-type':'application/x-www-form-urlencoded'}})
            .then(response => {
              console.log("se creó la card"+this.type+this.color)
              window.alert("Card Created!")
            }).catch(error=>{
              if(this.type==""||this.color==""){
                window.alert("Please select the type and color")
              }else{
                window.alert(error.response.data)
              
              }
                
            })
        }

    },
  }).mount('#app')
  