const { createApp } = Vue

createApp({
  data() {
    return {
      message: 'Hello Vue!',
      client: {},
      accounts: {},
      enabledClientAccounts: {},
      loans: {},
      mjeErrorCreateAccount: "",
      lengthDeAccounts: 0,
      aparecerModal: "no",
      aparecerDivConfirm: "si",
      aparecerDivSuccess: "no",
      aparecerDivError: "no",
      errorPetición: "",
    }
  },
  created() {
    this.getData()
  },
  methods: {
    getData() {
      axios.get('/api/clients/current',) //trae el cliente 1 (Melba)
        .then((response) => {
          this.client = response.data
          this.loans = this.client.loans
          this.accounts = this.client.accounts
          this.enabledClientAccounts = this.accounts.filter(account => account.status == true)
          this.lengthDeAccounts = Object.keys(this.enabledClientAccounts).length
          console.log(this.enabledClientAccounts)
        })

      axios.get('/api/clients/current', { headers: { 'accept': 'application/xml' } })
    },
    cerrarSesion() {
      axios.post('/api/logout')
        .then(response => {
          window.location.href = "../index.html"
        })

    },
    createNewAccount(type) {
      axios.post(`/api/clients/current/accounts?accountType=${type}`).then(response => {
        console.log("cuenta creada")

        this.aparecerDivSuccess = 'si'
        /* window.location.href="/web/accounts.html#Accounts" */
})
        .catch((error) => {
          this.aparecerDivError = 'si'
          this.mjeErrorCreateAccount = error.response.data
        })
    },
    goToAcocunt(id) {
      window.location.href = `/web/account.html?id=${id}`
    },
    removeAccount(id) {
      axios.patch(`/api/clients/current/accounts?id=${id}`)
        .then(response => window.location.reload())
        .catch()
    },
    cancelTransaction() {
      this.aparecerModal = "no"
      this.aparecerDivSuccess = "no"
      this.aparecerDivError = "no"
    },
    reload() {
      window.location.reload()
    },
  },
}).mount('#app')