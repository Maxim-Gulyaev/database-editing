package android.maxim.roomsimpleapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class Users (
    @PrimaryKey(autoGenerate = true)
    var userId: Int? = null,
    val userName: String
    )
