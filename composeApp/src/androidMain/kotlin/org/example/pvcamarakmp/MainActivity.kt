package org.example.pvcamarakmp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import android.Manifest
import androidx.activity.compose.setContent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import org.example.pvcamarakmp.App
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)




        setContent {
            val snackbarHostState = remember { SnackbarHostState() }

            var photo by remember { mutableStateOf<ImageBitmap?>(null) }

            // launcher para pedir permiso de cámara
            val permissionLauncher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.RequestPermission()
            ) { granted ->
                // si quieres mostrar un mensaje puedes usar el snackbar
            }

            // launcher para abrir la cámara (toma una foto previa, sin guardar archivo)
            val cameraLauncher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.TakePicturePreview()
            ) { bitmap ->
                if (bitmap != null) {
                    photo = bitmap.asImageBitmap()   // convertimos a ImageBitmap para Compose
                }
            }

            MaterialTheme {
                Scaffold(
                    snackbarHost = { SnackbarHost(snackbarHostState) }
                ) { _ ->
                    App(
                        onOpenCamera = {
                            // primero pedimos permiso de cámara
                            permissionLauncher.launch(Manifest.permission.CAMERA)
                            // luego abrimos la cámara (si el permiso ya está dado funciona directo)
                            cameraLauncher.launch(null)
                        },
                        photo = photo
                    )
                }
            }
        }




    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}