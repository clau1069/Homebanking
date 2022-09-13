const { createApp } = Vue

createApp({
  data() {
    return {
      arrayClientes: [],
      message: 'Hello Vue!',
      respuesta: {},
      objetocliente: {
        firstName: "",
        lastName: "",
        email: "",
      },
      showShowClients: 'no',
      showAddClient: 'no',
      showAddLoan: 'no',
      newLoanObject: {
        loanName: "",
        maxAmount: "",
        percentage: '',
        paymentsList: [],

      },

    }
  },
  created() {
    this.getData()
    console.log("hola")
  },
  methods: {
    getData() {
      axios.get('/rest/clients')
        .then((response) => {
          this.arrayClientes = response.data._embedded.clients
          console.log(this.arrayClientes);
          this.respuesta = response;

        })
    },
    añadirUsuario() {
      console.log(this.objetocliente)
      if (this.objetocliente.firstName && this.objetocliente.lastName && this.objetocliente.email) {
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
    addToPaymentsList(number) {

      if (this.newLoanObject.paymentsList.includes(number)) {
        indexofNumber = this.newLoanObject.paymentsList.indexOf(number)
        this.newLoanObject.paymentsList.splice(indexofNumber, 1)
      } else {
        this.newLoanObject.paymentsList.push(number)
      }

      console.log(this.newLoanObject.paymentsList)

    },
    addLoan() {/* 
      let array = this.newLoanObject.loanPaymentsList.join(", ")
      this.newLoanObject.loanPaymentsList = array */
      console.log(this.newLoanObject)
      axios.post('/api/loans/create', this.newLoanObject)
        .then((response) => {
          console.log("se creó")
        })
    }
  },
  computed: {
  }
}).mount('#app')

