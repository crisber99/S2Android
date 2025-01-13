package com.example.semana01

//noinspection UsingMaterialAndMaterial3Libraries
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigation
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.semana01.ui.theme.Semana01Theme


class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Semana01Theme {
                val navigatonController = rememberNavController()

                Scaffold (
                    bottomBar = { MenuBottomNavigation(navController = navigatonController)}
                ){
                    NavigationGraph(navController = navigatonController)
                }

            }
        }
    }
}


@Composable
    fun MenuBottomNavigation(navController: NavController) {
    val pantallas = listOf(
        Routes.PantallaLogin,
        Routes.PantallaUsuarios,
        Routes.PantallaRecuperar
    )

    BottomNavigation {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        pantallas.forEach { pantalla ->
            BottomNavigationItem(
                modifier = Modifier
                    .background(color = Color.Yellow),
                selected = currentRoute == pantalla.ruta,
                onClick = { navController.navigate(pantalla.ruta) },
                icon = { Icon(pantalla.icono, contentDescription = pantalla.titulo) },
                label = { Text(text = pantalla.titulo) }
            )

        }
    }
}


@Composable
fun NavigationGraph(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xfffeffb0))
    )
    {
        NavHost(
            navController = navController,
            startDestination = Routes.PantallaLogin.ruta
        ) {
            composable(Routes.PantallaLogin.ruta) { PantallaLogin() }
            composable(Routes.PantallaUsuarios.ruta) { PantallaUsuarios() }
            composable(Routes.PantallaRecuperar.ruta) { PantallaRecuperar() }
        }
    }
}

@Preview(showBackground = true, name = "Prueba Menú")
@Composable
fun PreView(){
    val navigatonController = rememberNavController()
    MenuBottomNavigation(navController = navigatonController)
    //NavigationGraph(navController = navigatonController)
}

fun seguridadNula() {
    var nombre: String? = null
    Log.d("SeguridadNula", nombre?: "El valor es nulo")

    nombre = "Kotlin"
    nombre?.let { Log.d("SeguridadNula", "Nombre no es nulo: $it") }
}


fun imprimirMensaje(mensaje: String): Unit {
    Log.d("UnitEjemplo", mensaje)
}

// El tipo Unit es opcional, así que podrías escribir:
fun imprimirOtroMensaje(mensaje: String) {
    Log.d("UnitEjemplo", mensaje)
}




fun verificarTipo(obj: Any) {
    if (obj is String) {
        Log.d("VerificaciónTipo", "El objeto es un String con longitud: ${obj.length}")
    } else {
        Log.d("VerificaciónTipo", "El objeto no es un String")
    }


    val obj: Any = 42
    val texto: String? = obj as? String
    println(texto)
}

fun verificarNoEsTipo(obj: Any) {
    if (obj !is Int) {
        Log.d("VerificaciónNoEsTipo", "El objeto no es un entero")
    } else {
        Log.d("VerificaciónNoEsTipo", "El objeto es un entero")
    }
}

fun procesarDato(dato: Any) {
    when (dato) {
        is String -> println("Es un String con valor: $dato")
        is Int -> println("Es un Int con valor: $dato")
        else -> println("Tipo desconocido")
    }
}

fun ejemploVal() {
    val mensaje = "Hola, Kotlin"
    Log.d("ValEjemplo", mensaje)
}

fun ejemploVar() {
    var contador = 0
    contador += 1
    Log.d("VarEjemplo", "El contador es: $contador")
}

fun usarOperadores() {
    val a = 10
    val b = 5

    val suma = a + b
    Log.d("Operadores", "Suma: $suma")

    val esMayor = a > b
    Log.d("Operadores", "¿Es a mayor que b?: $esMayor")

    val resultadoElvis = b ?: 0
    Log.d("Operadores", "Resultado Elvis: $resultadoElvis")
}

fun condicionalBasico(edad: Int) {
    if (edad >= 18) {
        Log.d("Condicional", "Eres mayor de edad")
    } else {
        Log.d("Condicional", "Eres menor de edad")
    }
}
//
//
/*
import android.util.Log

fun calcularDescuento(precio: Double) {
    val descuento: Double
    val precioFinal: Double

    // Aplicar operadores y condicionales
    if (precio >= 1000) {
        descuento = precio * 0.20
        precioFinal = precio - descuento
    } else if (precio in 500.0..999.99) {
        descuento = precio * 0.10
        precioFinal = precio - descuento
    } else {
        descuento = 0.0
        precioFinal = precio
    }

    // Mostrar resultados en Log.d
    Log.d("CalculadoraDescuento", "Precio original: $$precio")
    Log.d("CalculadoraDescuento", "Descuento aplicado: $$descuento")
    Log.d("CalculadoraDescuento", "Precio final: $$precioFinal")
}

// Llamar a la función
fun main() {
    calcularDescuento(1200.0)  // Caso: aplica 20% de descuento
    calcularDescuento(800.0)   // Caso: aplica 10% de descuento
    calcularDescuento(400.0)   // Caso: sin descuento
}
* */


fun obtenerMensaje(dia: String): String {
    return when (dia) {
        "Lunes" -> "Inicio de semana"
        "Viernes" -> "Fin de semana cercano"
        "Sábado", "Domingo" -> "Es fin de semana"
        else -> "Día normal"
    }
}

fun usarWhen(dia: String) {
    when (dia) {
        "Lunes" -> Log.d("When", "Inicio de semana")
        "Viernes" -> Log.d("When", "Fin de semana cercano")
        "Sábado", "Domingo" -> Log.d("When", "Es fin de semana")
        else -> Log.d("When", "Día normal")
    }
}


fun cicloForBasico() {
    for (i in 1..100) {
        Log.d("CicloFor", "Número: $i")
    }
}
