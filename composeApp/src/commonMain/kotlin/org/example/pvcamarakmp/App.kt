package org.example.pvcamarakmp

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import pvcamarakmp.composeapp.generated.resources.Res
import pvcamarakmp.composeapp.generated.resources.compose_multiplatform
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
@Composable
fun App(
    onOpenCamera: () -> Unit = {},
    photo: ImageBitmap? = null          // imagen que viene de Android
) {
    MaterialTheme {
        Surface {
            CameraScreen(
                onOpenCamera = onOpenCamera,
                photo = photo
            )
        }
    }
}

@Composable
fun CameraScreen(
    onOpenCamera: () -> Unit,
    photo: ImageBitmap?
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Button(onClick = onOpenCamera) {
            Text("Activar cámara")
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Si hay foto, la mostramos
        if (photo != null) {
            Image(
                bitmap = photo,
                contentDescription = "Foto tomada",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                contentScale = ContentScale.Crop
            )
        }
    }
}