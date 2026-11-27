package com.farsan.desknova.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TrayIcons(
    context: android.content.Context,
    batteryState: BatteryState,
    networkState: NetworkState,
    modifier: Modifier = Modifier
) {
    val isConnected = if (networkState.available) networkState.connected else false

    Row(
        modifier = modifier.fillMaxWidth().padding(horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = {}) {
            Canvas(modifier = Modifier.size(24.dp).semantics { contentDescription = if (isConnected) "Network connected" else "Network unavailable" }) {
                // Network icon: 3 ascending signal bars
                val color = if (isConnected) Color(0xFF4CAF50) else Color(0xFF9E9E9E)
                drawLine(
                    color = color,
                    strokeWidth = 2f,
                    start = Offset(4f, 20f),
                    end = Offset(4f, 12f)
                )
                drawLine(
                    color = color,
                    strokeWidth = 2f,
                    start = Offset(10f, 20f),
                    end = Offset(10f, 8f)
                )
                drawLine(
                    color = color,
                    strokeWidth = 2f,
                    start = Offset(16f, 20f),
                    end = Offset(16f, 4f)
                )
            }
        }

        androidx.compose.foundation.layout.Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            IconButton(onClick = {}) {
                Canvas(modifier = Modifier.size(24.dp).semantics { contentDescription = "Battery ${batteryState.level}%${if (batteryState.isCharging) " charging" else ""}" }) {
                    // Battery icon
                    val level = batteryState.level
                    val isCharging = batteryState.isCharging
                    val color = if (isCharging) Color(0xFF4CAF50) else Color(0xFF2196F3)
                    val fillPercent = level.coerceIn(0, 100) / 100f
                    // Battery outline (rectangle without rounded corners)
                    drawRect(
                        color = Color(0xFF9E9E9E),
                        topLeft = Offset(0f, 4f),
                        size = Size(20f, 12f)
                    )
                    // Terminal
                    drawRect(
                        color = Color(0xFF9E9E9E),
                        topLeft = Offset(20f, 8f),
                        size = Size(4f, 4f)
                    )
                    // Fill
                    val fillHeight = 8f * fillPercent
                    drawRect(
                        color = color,
                        topLeft = Offset(2f, 14f - fillHeight),
                        size = Size(16f, fillHeight)
                    )
                    // Charging mark
                    if (isCharging) {
                        drawLine(
                            color = Color(0xFFFFFFFF),
                            strokeWidth = 2f,
                            start = Offset(10f, 6f),
                            end = Offset(12f, 10f)
                        )
                        drawLine(
                            color = Color(0xFFFFFFFF),
                            strokeWidth = 2f,
                            start = Offset(12f, 10f),
                            end = Offset(14f, 6f)
                        )
                    }
                }
            }
            Text(
                text = "${batteryState.level}%",
                fontSize = 10.sp,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}
