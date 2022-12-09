<template>
  <div id="app">
    <nav>
      <div class="nav-wrapper red darken-1">
        <a href="#" class="brand-logo center">Cliente</a>
      </div>
    </nav>
    <div class="container">
      <ul>
        <li v-for="(erro, index) of errors" :key="index">
          campo <b>{{ erro.field }}</b> - {{ erro.defaultMessage }}
        </li>
      </ul>
      <form @submit.prevent="salvar">
        <label>Nome</label>
        <input type="text" placeholder="Nome" v-model="cliente.nome">
        <label>Data de nascimento</label>
        <input type="date" placeholder="dd-mm-yyyy" v-model="cliente.dataNascimento">
        <label>Cpf</label>
        <input type="text" placeholder="000.000.000-00" v-model="cliente.cpf">
        <label>E-mail</label>
        <input type="email" placeholder="email@gamil.com" v-model="cliente.email">
        <button class="waves-effect waves-light btn-small">Salvar<i class="material-icons left">save</i></button>
      </form>
      <table>
        <thead>
          <tr>
            <th>Nome</th>
            <th>Data de nasc.</th>
            <th>Cpf</th>
            <th>E-mail</th>
            <th>Opções</th>
          </tr>
        </thead>
        <tbody v-for="cliente of clientes" :key="cliente.id">
          <tr>
            <td>{{ cliente.nome }}</td>
            <td>{{ cliente.dataNascimento }}</td>
            <td>{{ cliente.cpf }}</td>
            <td>{{ cliente.email }}</td>
            <td>
              <button @click="editar(cliente)" class="waves-effect btn-small blue darken-1"><i
                  class="material-icons">create</i></button>
              <button @click="excluir(cliente)" class="waves-effect btn-small red darken-1"><i class="material-icons">delete_sweep</i></button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>

import Cliente from './services/clientes'

export default {
  data() {
    return {
      cliente: {
        id: '',
        nome: '',
        dataNascimento: '',
        cpf: '',
        email: ''
      },
      clientes: [],
      errors: []
    }
  },

  mounted() {
    this.listar()
  },

  methods: {

    listar() {
      Cliente.listar().then(resposta => {
        this.clientes = resposta.data
      })
    },

    salvar() {
      if (!this.cliente.id) {
        Cliente.salvar(this.cliente).then(() => {
          this.cliente = {}
          alert('Cliente cadastrado com sucesso!')
          this.listar()
          this.errors = []
        }).catch(e => {
          this.errors = e.response.data.errors
        })
      } else {
        Cliente.alterar(this.cliente).then(() => {
          this.cliente = {}
          alert('Cliente editado com sucesso!')
          this.listar()
          this.errors = []
        }).catch(e => {
          this.errors = e.response.data.errors
        })
      }
    },

    editar(cliente) {
      this.cliente = cliente
    },

    excluir(cliente) {
      Cliente.excluir(cliente).then(() => {
        this.listar();
        this.errors = []
      }).catch(e => {
        this.errors = e.response.data.errors
      })
    }
  }

}

</script>

<style>

</style>
