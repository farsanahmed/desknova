package com.farsan.desknova.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.background
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DesktopScreen(
    context: android.content.Context,
    batteryState: BatteryState,
    networkState: NetworkState,
    clockTime: String,
    clockDate: String,
    onStartClick: () -> Unit,
    onClockTap: () -> Unit
) {
    var startPanelVisible = remember { mutableStateOf(false) }
    var clockPopupVisible = remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Wallpaper: gradient behind everything
        DesktopWallpaper(
            modifier = Modifier.fillMaxSize()
        )

        // Taskbar at bottom
        Taskbar(
            context = context,
            batteryState = batteryState,
            networkState = networkState,
            clockTime = clockTime,
            clockDate = clockDate,
            onStartClick = {
                startPanelVisible.value = true
            },
            onClockTap = {
                clockPopupVisible.value = true
            },
            modifier = Modifier.align(Alignment.BottomCenter)
                .fillMaxWidth()
        )

        // Start panel (opens from Start button)
        StartPanel(
            visible = startPanelVisible.value,
            onClose = { startPanelVisible.value = false },
            modifier = Modifier
                .fillMaxSize()
                .clickable { startPanelVisible.value = false }
        )

        // Clock popup
        if (clockPopupVisible.value) {
            ClockPopup(
                visible = clockPopupVisible.value,
                context = context,
                onClose = { clockPopupVisible.value = false }
            )
        }
    }
}

@Composable
fun DesktopWallpaper(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF0F172A),
                            Color(0xFF1E293B),
                            Color(0xFF334155)
                        )
                    )
                )
        )
    }
}

@Composable
fun ClockPopup(
    visible: Boolean,
    context: android.content.Context,
    onClose: () -> Unit
) {
    if (!visible) return

    MaterialTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .clickable { onClose() },
            color = Color(0xCC000000)
        ) {
            AlertDialog(
                onDismissRequest = onClose,
                title = {
                    Text(text = "Date & Time", color = MaterialTheme.colorScheme.onSurface)
                },
                text = {
                    Text(
                        text = nowFormatted(context),
                        fontSize = 18.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                },
                confirmButton = {
                    TextButton(onClick = onClose) {
                        Text(text = "OK")
                    }
                }
            )
        }
    }
}

fun nowFormatted(context: android.content.Context): String {
    val is24Hour = android.text.format.DateFormat.is24HourFormat(context)
    val pattern = if (is24Hour) "yyyy-MM-dd HH:mm:ss" else "yyyy-MM-dd hh:mm:ss a"
    return java.text.SimpleDateFormat(pattern, java.util.Locale.getDefault())
        .format(java.util.Date())
}
