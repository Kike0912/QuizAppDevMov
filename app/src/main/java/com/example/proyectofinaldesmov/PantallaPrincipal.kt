package com.example.proyectofinaldesmov

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@Composable

fun PantallaPrincipal(navController: NavController) {

    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        visible = true
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.background,
                        MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)
                    )
                )
            )
    ) {
        AnimatedVisibility(
            visible = visible,
            enter = fadeIn() + slideInVertically(initialOffsetY = { it / 2 }),
            exit = fadeOut()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp)
                    .verticalScroll(enabled = true, state = rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "¡Prepárate para demostrar tus conocimientos!",
                    style = MaterialTheme.typography.headlineLarge.copy(fontSize = 27.sp),
                    color = MaterialTheme.colorScheme.onBackground,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 16.dp)
                )

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
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red,
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(50),
                    modifier = Modifier.width(250.dp).height(50.dp)
                        .shadow(8.dp, RoundedCornerShape(50))
                ) {
                    Text(
                        style = MaterialTheme.typography.headlineSmall.copy(fontSize = 20.sp),
                        text = "Cerrar sesión"
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
            }
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
    val scale = remember { Animatable(0.9f) }

    LaunchedEffect(Unit) {
        scale.animateTo(
            1f,
            animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing)
        )
    }

    Card(
        shape = RoundedCornerShape(16.dp),
        onClick = onIniciar,
        modifier = Modifier.fillMaxWidth()
            .padding(horizontal = 8.dp)
            .graphicsLayer(scaleX = scale.value, scaleY = scale.value)
            .shadow(6.dp, RoundedCornerShape(16.dp)),
        //.clickable(onClick = onIniciar), // Hacer clic en toda la tarjeta
        border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            //.background(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                titulo,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineSmall.copy(fontSize = 20.sp),
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(12.dp))

            Image(
                painter = painterResource(id = imagenId),
                contentDescription = "Imagen de $titulo",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .clip(RoundedCornerShape(12.dp))
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                descripcion,
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 16.sp),
                color = Color.DarkGray,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
