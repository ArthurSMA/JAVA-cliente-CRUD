import { http } from './config'

export default {

    listar:() => {
        return http.get('clientes')
    },

    salvar:(cliente) => {
        return http.post('clientes', cliente)
    },

    alterar:(cliente) => {
        return http.put('clientes', cliente)
    },

    excluir:(cliente) => {
        return http.delete('clientes', { data: cliente })
    }
}
