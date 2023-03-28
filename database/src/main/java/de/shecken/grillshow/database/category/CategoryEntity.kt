package de.shecken.grillshow.database.category

import androidx.room.PrimaryKey

data class CategoryEntity(
    @PrimaryKey
    val id: String,
    val title: String,
    val description: String,
)
