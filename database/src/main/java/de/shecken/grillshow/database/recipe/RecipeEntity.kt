package de.shecken.grillshow.database.recipe

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RecipeEntity(
    @PrimaryKey
    val id: String,
    val title: String,
    val description: String,
    val videoId: String,
    val thumbnailUrl: String,
    val isFavorite: Boolean,
    val uploadedAt: Long
)
