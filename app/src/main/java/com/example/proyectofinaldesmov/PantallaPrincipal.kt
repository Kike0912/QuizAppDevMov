package com.example.proyectofinaldesmov

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun PantallaPrincipal(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .verticalScroll(enabled = true, state = rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("¡Prepárate para demostrar tus habilidades!", style = MaterialTheme.typography.headlineLarge)

        QuizItem(
            titulo = "Domina Git y controla tu código",
            descripcion = "Conviértete en un maestro del versionado con preguntas que pondrán a prueba tu conocimiento.",
            imagenId = R.drawable.git, // tu imagen para Git
            onIniciar = { navController.navigate("categoria1") }
        )
        QuizItem(
            titulo = "Explora el mundo de las APIs REST",
            descripcion = "Aprende a construir y entender APIs como un verdadero desarrollador backend.",
            imagenId = R.drawable.api, // imagen para API REST
            onIniciar = { navController.navigate("categoria2") }
        )
        QuizItem(
            titulo = "SQL para héroes de datos",
            descripcion = "Consulta, filtra y domina bases de datos con preguntas desafiantes y divertidas.",
            imagenId = R.drawable.sql, // imagen para SQL
            onIniciar = { navController.navigate("categoria3") }
        )
        QuizItem(
            titulo = "Kotlin: Tu nuevo mejor amigo",
            descripcion = "Sumérgete en el lenguaje que está revolucionando el desarrollo Android y más.",
            imagenId = R.drawable.kotlin, // imagen para Kotlin
            onIniciar = { navController.navigate("categoria4") }
        )
        QuizItem(
            titulo = "Laravel PHP: Código elegante y potente",
            descripcion = "Pon a prueba tus conocimientos sobre el framework que hace magia con PHP.",
            imagenId = R.drawable.laravel, // imagen para Laravel PHP
            onIniciar = { navController.navigate("categoria5") }
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { /* TODO: Implementar acción de cerrar sesión */ },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Cerrar sesión", color = Color.White)
        }
    }
}

@Composable
fun QuizItem(
    titulo: String,
    descripcion: String,
    imagenId: Int,
    onIniciar: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, Color.Gray),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(titulo, style = MaterialTheme.typography.headlineMedium)

            Spacer(modifier = Modifier.height(12.dp))

            Image(
                painter = painterResource(id = imagenId),
                contentDescription = "Imagen de $titulo",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(RoundedCornerShape(12.dp))
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(descripcion, style = MaterialTheme.typography.bodyMedium)

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = onIniciar) {
                Text("Iniciar partida")
            }
        }
    }
}

