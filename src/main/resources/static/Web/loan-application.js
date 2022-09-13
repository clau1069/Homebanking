const { createApp } = Vue

createApp({
  data() {
    return {
      showType: "si",
      showPayments: "no",
      showAmount: "no",
      showAccounts: "no",
      aparecerModal: "no",
      aparecerDivConfirm: "si",
      aparecerDivSuccess: "no",
      aparecerDivError: "no",
      errorPetición: "",
      loans: [],
      loanSelected: "vacio",
      loanPayments: [],
      accounts: {},
      loanApplication: {
        idLoan: "",
        amount: "",
        payments: "",
        numberAccount: ""
      },
      clientLoans: [],
      loanName: ""
    }
  },
  created() {
    this.getData()
  },
  methods: {
    getData() {
      axios.get('/api/loans') //trae el cliente 1 (Melba)
        .then((response) => {
          this.loans = response.data
          console.log(this.loans[0].name)

        })
      axios.get('/api/clients/current')
        .then((response) => {
          this.accounts = response.data.accounts
          this.clientLoans = response.data.loans
        })
    },
    cerrarSesion(e) {
      e.preventDefault()
      axios.post('/api/logout')
        .then(response => {
          window.location.href = "../index.html"
        })

    },
    asignToLoanApplication() {
      this.loanSelected = this.loans.filter(loan => loan.name == this.loanName)[0]
      this.loanApplication.idLoan = this.loanSelected.id
      this.loanPayments = this.loanSelected.payments
      console.log(this.loanApplication)
    },
    asignPaymentSelected() {
      console.log(this.loanApplication.payments)
    },
    createLoan() {
      axios.post('/api/loans', this.loanApplication)
        .then(response => {
          this.aparecerDivSuccess = "si"
          console.log("creado")
        })
        .catch((error) => {
          console.log("no se creó")
          console.log(error)
          this.errorPetición = error.response.data
          this.aparecerDivConfirm = "no"
          this.aparecerDivError = "si"
        })
    },
    cancelTransaction(e) {
      e.preventDefault()
      this.aparecerModal = "no"
      this.aparecerDivSuccess = "no"
      this.aparecerDivError = "no"
    }

  },
}).mount('#app')