package de.shecken.grillshow.shared.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import de.shecken.grillshow.shared.GrillshowTheme
import de.shecken.grillshow.shared.R

@Composable
fun FavIconButton(
    modifier: Modifier = Modifier,
    recipeId: String,
    isFavorite: Boolean,
    onClick: (String, Boolean) -> Unit
) {
    IconToggleButton(
        modifier = modifier,
        checked = isFavorite,
        onCheckedChange = { checked -> onClick(recipeId, checked) }) {
        Icon(
            modifier = Modifier.background(
                color = if (isFavorite) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.inverseSurface,
                shape = CircleShape
            ),
            painter = painterResource(id = R.drawable.ic_favorite), contentDescription = "",
            tint = if (isFavorite) MaterialTheme.colorScheme.inverseSurface else MaterialTheme.colorScheme.inverseOnSurface
        )
    }
}

@Composable
@Preview
private fun FavIconPreview() {
    GrillshowTheme {
        Row {
            FavIconButton(recipeId = "123", isFavorite = true, onClick = { _, _ -> })
            FavIconButton(recipeId = "123", isFavorite = false, onClick = { _, _ -> })
        }
    }
}