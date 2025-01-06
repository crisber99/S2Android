package com.example.semana01


import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.semana01.modelo.Usuario

@Preview(showBackground = true, name = "Prueba Usuario")
@Composable
fun PantallaUsuarios() {
    Box(
        modifier = Modifier.fillMaxSize()
            .background(color = Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Lista de Usuarios", fontSize = 20.sp, color = Color.Black)
            val lstusuario = Usuario.listarUsuarios()

            if (lstusuario != null) {
                Log.d("Proy", "Se listará")
                Lista(lstusuario)
            } else {
                Log.d("Proy", "No se listará")
            }
        }
    }
}

@Composable
fun Lista(lstUser: List<Usuario>){
    Log.d("Proy", "Entró en Lista")
    LazyColumn {
        items(lstUser) {
            lst ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(text = lst.email)
                Text(text = lst.pass)
            }
            Log.d("Proy", "Se lista $lst")
        }
    }
}

@Composable
fun DiseñoLista(){

}