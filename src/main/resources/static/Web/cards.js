const { createApp } = Vue

createApp({
  data() {
    return {
      actualYear: "",
      cards: {},
      aparecerModal: "no",
      aparecerDivConfirm: "si",
      aparecerDivSuccess: "no",
      aparecerDivError: "no",
      errorPetición: "",
      numberCardToRemove: "",
      cardToRemove: "",
    }
  },
  created() {
    console.log(this.message)
    this.getData()
    this.actualYear = new Date(Date.now()).getFullYear().toString().slice(2, 4);
  },
  methods: {
    getData() {
      axios.get('/api/clients/current') //trae el cliente 1 (Melba)
        .then((response) => {
          this.cards = response.data.cards
          console.log(this.cards)
        })
    },
    cerrarSesion(e) {
      e.preventDefault()
      axios.post('/api/logout')
        .then(response => {
          window.location.href = "../index.html"
        })

    },
    searchInfoCardToremove() {
      this.cardToRemove = this.cards.filter(card => card.number == this.numberCardToRemove)[0]
      console.log(this.cardToRemove)
    },
    removeCard() {
      axios.patch(`/api/clients/current/cards?number=${this.numberCardToRemove}`, { headers: { 'accept': 'application/xml' } })
        .then(response => {

          this.aparecerDivSuccess = 'si'
          this.aparecerDivConfirm = 'no'
        })
        .catch(error => {
          this.aparecerDivError = 'si'
          this.aparecerDivConfirm = 'no'
          this.errorPetición = error.response.data
        })

    },
    cancelTransaction(e) {
      e.preventDefault()
      this.aparecerModal = "no"
      this.aparecerDivSuccess = "no"
      this.aparecerDivError = "no"
    },
    reload() {
      window.location.reload()
    }

  },
}).mount('#app')