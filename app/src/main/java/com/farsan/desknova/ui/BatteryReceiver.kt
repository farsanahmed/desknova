package com.farsan.desknova.ui

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

class BatteryReceiver(
    private val context: Context,
    private val onBatteryChanged: (BatteryState) -> Unit
) : DefaultLifecycleObserver, BroadcastReceiver() {

    private var registered = false

    override fun onCreate(owner: LifecycleOwner) {
        val filter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)
        context.registerReceiver(this, filter)
        registered = true
        // Read initial value
        val intent = context.registerReceiver(null, filter)
        if (intent != null) {
            onBatteryChanged(parse(intent))
        }
    }

    override fun onDestroy(owner: LifecycleOwner) {
        if (registered) {
            context.unregisterReceiver(this)
            registered = false
        }
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        intent?.let { onBatteryChanged(parse(it)) }
    }

    private fun parse(intent: Intent): BatteryState {
        val available = intent.getBooleanExtra(BatteryManager.EXTRA_PRESENT, true)
        val levelRaw = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
        val scaleRaw = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
        val level = if (levelRaw >= 0 && scaleRaw > 0) (levelRaw * 100 / scaleRaw) else 0
        val status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, BatteryManager.BATTERY_STATUS_UNKNOWN)
        val isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
            status == BatteryManager.BATTERY_STATUS_FULL
        return BatteryState(level = level, isCharging = isCharging, available = available)
    }
}
