package com.farsan.desknova.ui

import android.content.Context
import android.text.format.DateFormat
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

@Composable
fun Clock(
    context: Context,
    timeText: String,
    dateText: String,
    onClockTap: () -> Unit,
    modifier: Modifier = Modifier
) {
    val time = remember { mutableStateOf(timeText) }
    val date = remember { mutableStateOf(dateText) }

    LaunchedEffect(Unit) {
        while (true) {
            time.value = currentTime(context)
            date.value = currentDate(context)
            delay(1.seconds)
        }
    }

    Column(
        modifier = modifier
            .padding(4.dp)
            .clickable { onClockTap() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = time.value,
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(bottom = 2.dp)
        )
        Text(
            text = date.value,
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

fun currentTime(context: Context): String {
    val is24Hour = DateFormat.is24HourFormat(context)
    val pattern = if (is24Hour) "HH:mm" else "hh:mm"
    return java.text.SimpleDateFormat(pattern, java.util.Locale.getDefault())
        .format(java.util.Date())
}

fun currentDate(context: Context): String {
    return java.text.SimpleDateFormat("EEE, MMM d", java.util.Locale.getDefault())
        .format(java.util.Date())
}
