package com.example.encuestadeaceptacin

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.encuestadeaceptacin.ui.theme.EncuestaDeAceptaciónTheme
import kotlin.system.exitProcess

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EncuestaContent()
        }
    }
}


@Composable
fun MainScreen() {
    var showDialog by remember { mutableStateOf(true) }

    if (showDialog) {
        TermsAndConditionsDialog(
            onDismiss = { accepted ->
                if (!accepted) {
                    // Close the app
                    exitProcess(0)
                }
                showDialog = false
            }
        )
    }

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Greeting(modifier = Modifier.padding(innerPadding))
    }
}

@Composable
fun TermsAndConditionsDialog(onDismiss: (Boolean) -> Unit) {
    AlertDialog(
        onDismissRequest = {},
        title = { Text(text = "Términos y Condiciones") },
        text = {
            Column {
                Text(text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.")
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.")
            }
        },
        confirmButton = {
            TextButton(onClick = { onDismiss(true) }) {
                Text("Aceptar")
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismiss(false) }) {
                Text("Rechazar")
            }
        }
    )
}

@Composable
fun Greeting(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 16.dp), // Espaciado desde la parte superior
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Encuesta de Aceptación",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
    }
}

@Composable
fun EncuestaContent(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 16.dp), // Espaciado desde la parte superior
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Encuesta de Aceptación",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Preguntas
        EncuestaQuestion("¿Cree que su Universidad en la mejor de Managua?", listOf("Si", "No"))
        EncuestaQuestion("¿Cree que la calidad de su Universidad es la mejor?", listOf("Si", "No"))
        EncuestaQuestion("¿Cree que las clases que se imparten le ayudarán a su vida como profesional?", listOf("Si", "No"))
        EncuestaQuestion("¿Cree que la clase de Programación Orientada a Objetos le contribuirá a su futuro profesional?", listOf("Si", "No"))
        EncuestaQuestion("¿Considera que hacer la tarea con carácter individual y honesto le ayudará en el futuro profesional?", listOf("Si", "No"))
    }
}

@Composable
fun EncuestaQuestion(question: String, options: List<String>) {
    Column(
        modifier = Modifier.padding(vertical = 8.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = question,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp
        )
        Spacer(modifier = Modifier.height(8.dp))

        RadioGroup(options)
    }
}
@Composable
fun RadioGroup(options: List<String>) {
    var selectedOption by remember { mutableStateOf("") }

    Column {
        options.forEach { option ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 4.dp)
            ) {
                RadioButton(
                    selected = (selectedOption == option),
                    onClick = { selectedOption = option },
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text(text = option)
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EncuestaDeAceptaciónTheme {
        MainScreen()
    }
}