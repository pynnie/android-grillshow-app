package de.shecken.grillshow.database.recipe

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import de.shecken.grillshow.database.DatabaseConstants.COLUMN_ID
import de.shecken.grillshow.database.DatabaseConstants.COLUMN_UPLOADED_AT
import de.shecken.grillshow.database.DatabaseConstants.TABLE_RECIPE_ENTITY
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {

    @Query("SELECT * FROM $TABLE_RECIPE_ENTITY")
    fun getAll(): Flow<List<RecipeEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(userEntity: RecipeEntity)

    @Query("DELETE FROM $TABLE_RECIPE_ENTITY")
    suspend fun deleteAll()

    @Query("SELECT $COLUMN_UPLOADED_AT FROM $TABLE_RECIPE_ENTITY ORDER BY $COLUMN_UPLOADED_AT DESC LIMIT 1")
    suspend fun getLatestUploadDate(): String?

    @Query("SELECT * FROM $TABLE_RECIPE_ENTITY WHERE $COLUMN_ID = :recipeId LIMIT 1")
    suspend fun getRecipeById(recipeId: String): RecipeEntity?
}