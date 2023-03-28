package de.shecken.grillshow.database.category

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import de.shecken.grillshow.database.DatabaseConstants.TABLE_CATEGORY_ENTITY
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {
    @Query("SELECT * FROM $TABLE_CATEGORY_ENTITY")
    fun getAllCategories(): Flow<List<CategoryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(categoryEntity: CategoryEntity)

    @Query("DELETE FROM $TABLE_CATEGORY_ENTITY")
    suspend fun deleteAll()
}