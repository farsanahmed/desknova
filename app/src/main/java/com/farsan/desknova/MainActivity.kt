package com.farsan.desknova

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.farsan.desknova.ui.BatteryReceiver
import com.farsan.desknova.ui.BatteryState
import com.farsan.desknova.ui.DesktopScreen
import com.farsan.desknova.ui.NetworkState

class MainActivity : ComponentActivity() {

    private lateinit var batteryReceiver: BatteryReceiver

    private val batteryState: MutableState<BatteryState> = mutableStateOf(BatteryState())
    private val networkState: MutableState<NetworkState> = mutableStateOf(currentNetworkState(this))
    private val clockTime: MutableState<String> = mutableStateOf("--:--")
    private val clockDate: MutableState<String> = mutableStateOf("")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Keep the screen on while the app is open and in front
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        batteryReceiver = BatteryReceiver(
            context = this,
            onBatteryChanged = { state ->
                batteryState.value = state
            }
        )

        setContent {
            DesktopScreen(
                context = this,
                batteryState = batteryState.value,
                networkState = networkState.value,
                clockTime = clockTime.value,
                clockDate = clockDate.value,
                onStartClick = { /* Start button handled by DesktopScreen */ },
                onClockTap = { /* Clock tap handled by DesktopScreen */ }
            )
        }
    }

    override fun onStart() {
        super.onStart()
        networkState.value = currentNetworkState(this)
    }

    override fun onStop() {
        super.onStop()
    }

    fun reportNetworkState(state: NetworkState) {
        networkState.value = state
    }

    private fun currentNetworkState(context: Context): NetworkState {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val info = cm.activeNetworkInfo
        return NetworkState(
            connected = info?.isConnectedOrConnecting == true,
            available = true
        )
    }
}
