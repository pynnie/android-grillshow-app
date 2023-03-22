package de.shecken.grillshow.database

import androidx.room.Database
import androidx.room.RoomDatabase
import de.shecken.grillshow.database.DatabaseConstants.DATABASE_VERSION
import de.shecken.grillshow.database.user.UserDao
import de.shecken.grillshow.database.user.UserEntity

@Database(
    entities = [
        UserEntity::class
    ],
    version = DATABASE_VERSION
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
}
