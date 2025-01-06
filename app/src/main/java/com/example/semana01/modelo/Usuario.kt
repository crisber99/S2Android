package com.example.semana01.modelo

data class Usuario(val email: String, val pass: String) {
    companion object{
        private val listaUsuario = mutableListOf<Usuario>()

        fun agregarUsuario(usuario: Usuario){
            listaUsuario.add(usuario)
        }

        fun obtUsuarioMail(email: String): Usuario?{
            return listaUsuario.find { it.email.equals(email, ignoreCase = true) }
        }

        fun listarUsuarios(): List<Usuario>{
            return listaUsuario
        }

    }

}