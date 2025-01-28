package com.example.semana01

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Backpack
import androidx.compose.material.icons.filled.Cookie
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Routes(val titulo: String, val icono: ImageVector, val ruta:String) {
    object  PantallaLogin: Routes("Login", Icons.Filled.Person, "login")
    object  PantallaUsuarios: Routes("Usuarios", Icons.Filled.VerifiedUser, "usuario")
    object  PantallaRecuperar: Routes("Recuperar Pass", Icons.Filled.Backpack, "recuperar")
    object  PantallaRecetas: Routes("Recetas", Icons.Filled.Cookie, "recetas")
}