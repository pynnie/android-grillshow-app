package de.shecken.grillshow.database.user

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(
    val firstName: String,
    val lastName: String
) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
