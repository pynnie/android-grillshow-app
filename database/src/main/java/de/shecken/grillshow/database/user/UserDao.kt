package de.shecken.grillshow.database.user

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("SELECT * FROM UserEntity LIMIT 1")
    fun selectFirst(): Flow<UserEntity>

    @Insert
    suspend fun insert(userEntity: UserEntity)

    @Query("DELETE FROM UserEntity")
    suspend fun deleteAll()
}
