package org.example.pvcamarakmp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun App(
    modifierPadding: PaddingValues = PaddingValues(0.dp),
    photo: ImageBitmap? = null,
    geminiText: String = "",
    onOpenCamera: () -> Unit = {},
    onSendToGemini: () -> Unit = {},
) {
    Surface {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(modifierPadding),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Botón para activar la cámara
                Button(onClick = onOpenCamera) {
                    Text("Activar cámara")
                }

                // Mostrar la foto si existe
                if (photo != null) {
                    Image(
                        bitmap = photo,
                        contentDescription = "Foto tomada",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp),
                        contentScale = ContentScale.Crop
                    )

                    // Botón para enviar a Gemini
                    Button(onClick = onSendToGemini) {
                        Text("Enviar imagen a Gemini")
                    }
                }

                // Mostrar respuesta de Gemini
                if (geminiText.isNotBlank()) {
                    Text(
                        text = geminiText,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun AppPreview() {
    MaterialTheme {
        App()
    }
}
