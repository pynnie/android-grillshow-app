package de.shecken.grillshow.shared.ui

import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp


@Composable
fun ListDivider() {
    Divider(
        color = MaterialTheme.colorScheme.onSurface,
        thickness = 1.dp
    )
}