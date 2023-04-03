package de.shecken.grillshow.database.recipe

import androidx.room.*
import de.shecken.grillshow.database.DatabaseConstants.DEFAULT_LIST_SIZE
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

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun update(recipeEntity: RecipeEntity)

    @Query("DELETE FROM $TABLE_RECIPE_ENTITY")
    suspend fun deleteAll()

    @Query("SELECT $COLUMN_UPLOADED_AT FROM $TABLE_RECIPE_ENTITY ORDER BY $COLUMN_UPLOADED_AT DESC LIMIT 1")
    suspend fun getLatestUploadDate(): String?

    @Query("SELECT * FROM $TABLE_RECIPE_ENTITY WHERE $COLUMN_ID = :recipeId LIMIT 1")
    fun getRecipeById(recipeId: String): RecipeEntity?

    @Query("SELECT * FROM $TABLE_RECIPE_ENTITY WHERE $COLUMN_ID = :recipeId LIMIT 1")
    fun getRecipeFlowById(recipeId: String): Flow<RecipeEntity?>

    @Query("SELECT * FROM $TABLE_RECIPE_ENTITY ORDER BY $COLUMN_UPLOADED_AT DESC LIMIT $DEFAULT_LIST_SIZE")
    fun getLatestRecipes(): Flow<List<RecipeEntity>>
}