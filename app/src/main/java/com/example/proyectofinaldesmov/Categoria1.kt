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

data class Pregunta(
    val texto: String,
    val imagenId: Int,
    val opciones: List<String>,
    val indiceCorrecto: Int
)

@Composable
fun Categoria1(navController: NavController) {
    val preguntas = remember { obtenerPreguntasCategoria1() }
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
            modifier = Modifier.fillMaxSize().padding(32.dp),
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
            modifier = Modifier.fillMaxSize().background(colorFondo).padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Pregunta ${indiceActual + 1} / ${preguntas.size}", style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(8.dp))
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
                        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
                    ) {
                        Text(textoOpcion)
                    }
                }
            }
        }
    }
}
fun obtenerPreguntasCategoria1(): List<Pregunta> = listOf(
    Pregunta(
        texto = "¿Qué comando se usa para iniciar un repositorio Git?",
        imagenId = R.drawable.c1p1, // imagen propia para esta pregunta
        opciones = listOf("git start", "git init", "git begin", "git create"),
        indiceCorrecto = 1
    ),
    Pregunta(
        texto = "¿Cómo se llaman los cambios que aún no has guardado en Git?",
        imagenId = R.drawable.c1p2,
        opciones = listOf("Commits", "Pushes", "Staged", "Unstaged"),
        indiceCorrecto = 3
    ),
    Pregunta(
        texto = "¿Qué comando se usa para guardar los cambios localmente?",
        imagenId = R.drawable.c1p3,
        opciones = listOf("git push", "git commit", "git pull", "git status"),
        indiceCorrecto = 1
    ),
    Pregunta(
        texto = "¿Qué comando sube tus cambios al repositorio remoto?",
        imagenId = R.drawable.c1p4,
        opciones = listOf("git fetch", "git push", "git clone", "git merge"),
        indiceCorrecto = 1
    ),
    Pregunta(
        texto = "¿Cómo se llama la copia local de un repositorio remoto?",
        imagenId = R.drawable.c1p5,
        opciones = listOf("Branch", "Clone", "Fork", "Commit"),
        indiceCorrecto = 1
    ),
    Pregunta(
        texto = "¿Qué comando muestra el estado de tus archivos?",
        imagenId = R.drawable.c1p6,
        opciones = listOf("git status", "git log", "git diff", "git branch"),
        indiceCorrecto = 0
    ),
    Pregunta(
        texto = "¿Cómo se llama una versión paralela del repositorio para trabajar?",
        imagenId = R.drawable.c1p7,
        opciones = listOf("Fork", "Branch", "Clone", "Commit"),
        indiceCorrecto = 1
    ),
    Pregunta(
        texto = "¿Qué comando combina cambios de una rama a otra?",
        imagenId = R.drawable.c1p8,
        opciones = listOf("git merge", "git rebase", "git pull", "git push"),
        indiceCorrecto = 0
    ),
    Pregunta(
        texto = "¿Qué archivo contiene la configuración para ignorar archivos?",
        imagenId = R.drawable.c1p9,
        opciones = listOf(".gitignore", "README.md", "LICENSE", ".gitconfig"),
        indiceCorrecto = 0
    ),
    Pregunta(
        texto = "¿Qué comando se usa para descargar un repositorio remoto por primera vez?",
        imagenId = R.drawable.c1p10,
        opciones = listOf("git pull", "git fetch", "git clone", "git init"),
        indiceCorrecto = 2
    )
)
