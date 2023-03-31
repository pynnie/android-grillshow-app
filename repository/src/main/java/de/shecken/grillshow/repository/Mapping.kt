package de.shecken.grillshow.repository

import de.shecken.grillshow.database.category.CategoryEntity
import de.shecken.grillshow.database.recipe.RecipeEntity
import de.shecken.grillshow.networking.youtube.Playlist
import de.shecken.grillshow.networking.youtube.response.PlaylistItem
import de.shecken.grillshow.repository.recipe.model.Category
import de.shecken.grillshow.repository.recipe.model.Recipe

fun PlaylistItem.toRecipeEntity() =
    RecipeEntity(
        id = contentDetails.videoId,
        title = snippet.title,
        description = snippet.description,
        thumbnailUrl = snippet.thumbnails.high?.url ?: snippet.thumbnails.medium?.url
        ?: snippet.thumbnails.standard?.url ?: snippet.thumbnails.default?.url ?: "",
        isFavorite = false,
        uploadedAt = contentDetails.videoPublishedAt
    )

fun Playlist.toCategoryEntity() =
    CategoryEntity(
        id = id,
        title = snippet.title,
        description = snippet.description
    )

fun CategoryEntity.toCategory(recipes: List<Recipe>) = Category(
    id = id,
    title = title,
    description = description,
    recipes = recipes
)

fun RecipeEntity.toRecipe() = Recipe(
    id = id,
    title = title,
    description = description,
    thumbnailUrl = thumbnailUrl,
    isFavorite = isFavorite
)

fun Recipe.toEntity(uploadedAt: String, categoryId: String?) = RecipeEntity(
    id = id,
    title = title,
    description = description,
    thumbnailUrl = thumbnailUrl,
    uploadedAt = uploadedAt,
    categoryId = categoryId,
    isFavorite = isFavorite
)