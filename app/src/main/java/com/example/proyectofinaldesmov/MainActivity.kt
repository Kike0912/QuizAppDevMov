package com.example.proyectofinaldesmov

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.example.proyectofinaldesmov.ui.theme.ProyectoFinalDesMovTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProyectoFinalDesMovTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    Navegacion()
                }
            }
        }
    }
}
