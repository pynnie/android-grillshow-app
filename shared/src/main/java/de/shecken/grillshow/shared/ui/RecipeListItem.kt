package de.shecken.grillshow.shared.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import de.shecken.grillshow.shared.GrillshowTheme
import de.shecken.grillshow.shared.R

@Composable
fun RecipeListItem(
    recipeId: String,
    title: String,
    imageUrl: String,
    onItemClick: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onItemClick(recipeId) }, verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier.weight(0.3f)
        ) {
            AsyncImage(
                model = imageUrl,
                contentDescription = "",
                placeholder = debugPlaceholder(
                    debugPreview = R.drawable.default_thumbnail
                )
            )
        }

        Text(
            modifier = Modifier
                .weight(0.7f)
                .padding(vertical = 4.dp, horizontal = 8.dp),
            text = title,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Preview
@Composable
private fun RecipeListItemPreview() {
    GrillshowTheme {
        RecipeListItem(
            recipeId = "1",
            title = "Recipe Title",
            imageUrl = "https://www.themealdb.com/images/media/meals/58oia61564916529.jpg",
            onItemClick = {}
        )
    }
}