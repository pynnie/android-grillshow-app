package de.shecken.grillshow.database

import androidx.room.Database
import androidx.room.RoomDatabase
import de.shecken.grillshow.database.DatabaseConstants.DATABASE_VERSION
import de.shecken.grillshow.database.category.CategoryDao
import de.shecken.grillshow.database.category.CategoryEntity
import de.shecken.grillshow.database.recipe.RecipeDao
import de.shecken.grillshow.database.recipe.RecipeEntity

@Database(
    entities = [
        RecipeEntity::class,
        CategoryEntity::class
    ],
    version = DATABASE_VERSION
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun recipeDao(): RecipeDao
    abstract fun categoryDao(): CategoryDao
}
