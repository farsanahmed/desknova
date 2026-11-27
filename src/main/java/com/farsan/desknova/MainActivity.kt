package com.farsan.desknova

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.SetContent
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HelloDeskNova(text: String = "Hello DeskNova") {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Text(text = text, modifier = Modifier.align(center = Alignment.Center))
    }
}

@SetContent
fun App() {
    setContent {
        MaterialTheme {
            HelloDeskNova()
        }
    }
}
