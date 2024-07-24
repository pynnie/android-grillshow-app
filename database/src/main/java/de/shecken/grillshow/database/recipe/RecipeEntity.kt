package de.shecken.grillshow.database.recipe

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entity class for recipes
 *
 * @param id the videoId from the youtube API
 * @param title full title of the recipe video
 * @param description full description of the recipe video
 * @param thumbnailUrl url to retrieve the videos thumbnail image
 * @param isFavorite flag to show if recipe was marked as favorite
 * @param uploadedAt video upload date (UTC)
 * @param categoryId identifier of a category for the recipe (null if not present)
 */
@Entity
data class RecipeEntity(
    @PrimaryKey
    val id: String,
    val title: String,
    val description: String,
    val thumbnailUrl: String,
    val isFavorite: Boolean,
    val uploadedAt: String,
    val categoryId: String? = null
)
