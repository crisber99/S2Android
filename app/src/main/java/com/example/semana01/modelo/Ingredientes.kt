package com.example.semana01.modelo

data class Ingredientes(val nomIngrediente: String) {
    companion object{
        private val listaIngrediente = mutableListOf<Ingredientes>()
    }
}