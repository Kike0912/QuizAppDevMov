package com.example.proyectofinaldesmov

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.delay

@Composable
fun Categoria2(navController: NavController) {
    val preguntas = remember { obtenerPreguntasCategoria2() }
    var indiceActual by remember { mutableStateOf(0) }
    var aciertos by remember { mutableStateOf(0) }
    var respuestaSeleccionada by remember { mutableStateOf<Int?>(null) }
    var colorFondo by remember { mutableStateOf(Color.White) }
    var mostrarFinal by remember { mutableStateOf(false) }

    val pregunta = preguntas.getOrNull(indiceActual)

    LaunchedEffect(respuestaSeleccionada) {
        if (respuestaSeleccionada != null) {
            delay(1000)
            if (indiceActual == preguntas.lastIndex) {
                mostrarFinal = true
            } else {
                indiceActual++
                respuestaSeleccionada = null
                colorFondo = Color.White
            }
        }
    }

    if (mostrarFinal) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("¡Juego terminado!", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(16.dp))
            Text("Aciertos: $aciertos de ${preguntas.size}", style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(32.dp))
            Button(onClick = { navController.navigate("inicio") }) {
                Text("Volver al menú")
            }
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colorFondo)
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Pregunta ${indiceActual + 1} / ${preguntas.size}",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(8.dp)
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = pregunta?.texto ?: "No hay preguntas",
                style = MaterialTheme.typography.headlineSmall,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(16.dp)
            )
            if (pregunta != null) {
                Image(
                    painter = painterResource(id = pregunta.imagenId),
                    contentDescription = "Imagen de la pregunta",
                    modifier = Modifier
                        .height(180.dp)
                        .fillMaxWidth()
                        .padding(16.dp)
                        .clip(RoundedCornerShape(12.dp))
                )
                Spacer(modifier = Modifier.height(16.dp))
                pregunta.opciones.forEachIndexed { index, textoOpcion ->
                    Button(
                        onClick = {
                            if (respuestaSeleccionada == null) {
                                respuestaSeleccionada = index
                                if (index == pregunta.indiceCorrecto) {
                                    aciertos++
                                    colorFondo = Color(0xFFB9FBC0) // verde
                                } else {
                                    colorFondo = Color(0xFFFFC2C2) // rojo
                                }
                            }
                        },
                        enabled = respuestaSeleccionada == null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                    ) {
                        Text(textoOpcion)
                    }
                }
            }
        }
    }
}

fun obtenerPreguntasCategoria2(): List<Pregunta> = listOf(
    Pregunta(
        texto = "¿Qué significa REST en API REST?",
        imagenId = R.drawable.c2p1,
        opciones = listOf("Representational State Transfer", "Remote Execution Service", "Random Event Trigger", "Reliable System Transfer"),
        indiceCorrecto = 0
    ),
    Pregunta(
        texto = "¿Cuál método HTTP se utiliza para obtener datos?",
        imagenId = R.drawable.c2p2,
        opciones = listOf("GET", "POST", "PUT", "DELETE"),
        indiceCorrecto = 0
    ),
    Pregunta(
        texto = "¿Qué método HTTP se usa para crear un nuevo recurso?",
        imagenId = R.drawable.c2p3,
        opciones = listOf("POST", "GET", "DELETE", "PATCH"),
        indiceCorrecto = 0
    ),
    Pregunta(
        texto = "¿Cuál método HTTP se utiliza para eliminar un recurso?",
        imagenId = R.drawable.c2p4,
        opciones = listOf("DELETE", "GET", "PUT", "POST"),
        indiceCorrecto = 0
    ),
    Pregunta(
        texto = "¿Qué significa que una API sea stateless?",
        imagenId = R.drawable.c2p5,
        opciones = listOf(
            "No mantiene estado entre peticiones",
            "Mantiene sesión activa",
            "Usa cookies para estado",
            "Requiere autenticación persistente"
        ),
        indiceCorrecto = 0
    ),
    Pregunta(
        texto = "¿Cuál es el formato comúnmente usado para intercambio de datos en API REST?",
        imagenId = R.drawable.c2p6,
        opciones = listOf("JSON", "XML", "CSV", "YAML"),
        indiceCorrecto = 0
    ),
    Pregunta(
        texto = "¿Qué código HTTP indica que una solicitud fue exitosa?",
        imagenId = R.drawable.c2p7,
        opciones = listOf("200", "404", "500", "301"),
        indiceCorrecto = 0
    ),
    Pregunta(
        texto = "¿Qué verbo HTTP es usado para actualizar parcialmente un recurso?",
        imagenId = R.drawable.c2p8,
        opciones = listOf("PATCH", "PUT", "POST", "GET"),
        indiceCorrecto = 0
    ),
    Pregunta(
        texto = "¿Qué representa un endpoint en API REST?",
        imagenId = R.drawable.c2p9,
        opciones = listOf("Una URL para acceder a un recurso", "Un método HTTP", "Un formato de datos", "Un servidor"),
        indiceCorrecto = 0
    ),
    Pregunta(
        texto = "¿Cuál es la función principal de un API Gateway?",
        imagenId = R.drawable.c2p10,
        opciones = listOf(
            "Gestionar y enrutar peticiones a múltiples servicios",
            "Almacenar datos de la API",
            "Generar claves API",
            "Crear bases de datos"
        ),
        indiceCorrecto = 0
    )
)
