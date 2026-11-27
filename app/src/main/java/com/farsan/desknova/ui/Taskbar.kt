package com.farsan.desknova.ui

import android.content.Context
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size

import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp

@Composable
fun Taskbar(
    context: Context,
    batteryState: BatteryState,
    networkState: NetworkState,
    clockTime: String,
    clockDate: String,
    onStartClick: () -> Unit,
    onClockTap: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp)
            .background(Color(0xDD222222)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Left: Start button
        IconButton(
            onClick = onStartClick,
            modifier = Modifier.padding(start = 8.dp)
        ) {
            Canvas(modifier = Modifier.size(24.dp).semantics { contentDescription = "Start menu" }) {
                // Four-square Windows-style icon (original artwork)
                val color = Color(0xFFFFFFFF)
                val squareSize = 6f
                val gap = 2f
                val startX = (24f - (2 * squareSize + gap)) / 2f
                val startY = (24f - (2 * squareSize + gap)) / 2f
                drawRect(
                    color = color,
                    topLeft = Offset(startX, startY),
                    size = Size(squareSize, squareSize)
                )
                drawRect(
                    color = color,
                    topLeft = Offset(startX + squareSize + gap, startY),
                    size = Size(squareSize, squareSize)
                )
                drawRect(
                    color = color,
                    topLeft = Offset(startX, startY + squareSize + gap),
                    size = Size(squareSize, squareSize)
                )
                drawRect(
                    color = color,
                    topLeft = Offset(startX + squareSize + gap, startY + squareSize + gap),
                    size = Size(squareSize, squareSize)
                )
            }
        }

        // Center: reserved empty area for future open-window buttons
        Box(
            modifier = Modifier.fillMaxWidth()
        )

        // Right: system tray (network + battery + clock)
        TrayIcons(
            context = context,
            batteryState = batteryState,
            networkState = networkState,
            modifier = Modifier.padding(end = 4.dp)
        )

        Clock(
            context = context,
            timeText = clockTime,
            dateText = clockDate,
            onClockTap = onClockTap,
            modifier = Modifier.padding(end = 8.dp)
        )
    }
}
