package de.shecken.grillshow.database.category

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CategoryEntity(
    @PrimaryKey
    val id: String,
    val title: String,
    val description: String,
)
