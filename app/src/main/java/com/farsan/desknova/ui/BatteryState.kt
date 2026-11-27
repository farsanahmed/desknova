package com.farsan.desknova.ui

data class BatteryState(
    val level: Int = 0,        // 0-100
    val isCharging: Boolean = false,
    val available: Boolean = true
)
