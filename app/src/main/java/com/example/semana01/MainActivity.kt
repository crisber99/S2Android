package com.example.semana01

//noinspection UsingMaterialAndMaterial3Libraries
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
                    .background(color = Color.Gray),
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
    NavHost(
        navController = navController,
        startDestination = Routes.PantallaLogin.ruta
    ) {
        composable(Routes.PantallaLogin.ruta) { PantallaLogin() }
        composable(Routes.PantallaUsuarios.ruta) { PantallaUsuarios() }
        composable(Routes.PantallaRecuperar.ruta) { PantallaRecuperar() }
    }
}