package com.example.proyectofinaldesmov

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Navegacion() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "inicio") {
        composable("inicio") {
            PantallaPrincipal(navController)
        }
        composable("categoria1") {
            Categoria1(navController)
        }
        composable("categoria2") {
            Categoria2(navController)
        }
        composable("categoria3") {
            Categoria3(navController)
        }
        composable("categoria4") {
            Categoria4(navController)
        }
        composable("categoria5") {
            Categoria5(navController)
        }
    }
}
