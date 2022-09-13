const { createApp } = Vue

createApp({
  data() {
    return {
      emailInput: "",
      passwordInput: "",
      mensaje: "",
      registerFirstName: "",
      registerLastName: "",
      registerEmail: "",
      registerPassword: "",
      error: "",
      mensajeRegister: "",
      usuario: "",
      mjeNoAutenticado: "",
      termsChecked: "",
    }
  },
  created() {
    axios.get('/api/clients/current') //trae el cliente 1 (Melba)
      .then((response) => {
        this.usuario = "autenticado"
        console.log(this.usuario)
      }).catch((error) => {
        this.rutaBtnMyAccounts = "#log-in"
        this.usuario = "visitante"
        console.log(this.usuario)
      },

      )
  },
  methods: {
    signIn() {
      axios.post('/api/login', `email=${this.emailInput}&password=${this.passwordInput}`, { headers: { 'content-type': 'application/x-www-form-urlencoded' } })
        .then(response => {
          axios.get('/api/clients/current') //trae el cliente 1 (Melba)
            .then((response) => {
              window.location.href = "/web/accounts.html"
            }).catch((error) => {
              window.location.href = "/admin/manager.html"
            })

        }).catch((error) => {
          this.error = "Wrong email or password"
        })

    },
    register() {
      if (this.termsChecked == true) {
        axios.post('/api/clients', `firstName=${this.registerFirstName}&lastName=${this.registerLastName}&email=${this.registerEmail}&password=${this.registerPassword}`, { headers: { 'content-type': 'application/x-www-form-urlencoded' } })
          .then(response => {
            console.log('registered')
            window.location.href = "http://localhost:8080/index.html#log-in"
          })
          .catch((error) => {
            this.mensajeRegister = error.response.data
            console.log(this.mensajeRegister)
          })
      }
      this.mensajeRegister = "You must agree to the terms and services to register"
      console.log(this.termsChecked)
    },

    clickMyAccount() {
      this.mjeNoAutenticado = "To access your accounts you must log in!"
    },
    cerrarSesion() {
      axios.post('/api/logout')
        .then(response => {
          window.location.href = "../index.html"
        })

    },

  },
}).mount('#app')