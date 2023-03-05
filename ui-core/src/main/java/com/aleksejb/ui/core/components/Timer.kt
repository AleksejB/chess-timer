package com.aleksejb.ui.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Timer(
    hours: String,
    minutes: String,
    seconds: String,
    isWhite: Boolean,
    isTicking: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .clickable { onClick() }
            .background(
                when (isWhite) {
                    true -> if (isTicking) Color.Green else Color.White
                    false -> if (isTicking) Color.Green else Color.Black
                }
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Surface(
            color = when (isWhite) {
                true -> if (isTicking) Color.Green else Color.White
                false -> if (isTicking) Color.Green else Color.Black
            }
        ) {
            Text(
                text = "$hours:$minutes:$seconds",
                fontSize = 30.sp,
                color = if (isWhite) Color.Black else Color.White
            )
        }
    }
}