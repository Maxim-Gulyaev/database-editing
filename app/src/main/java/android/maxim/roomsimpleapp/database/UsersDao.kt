package android.maxim.roomsimpleapp.database

import androidx.room.*

@Dao
interface UsersDao {

    @Insert
    fun insertUser(users: Users)

    @Query("Select * from users")
    fun gelAllUsers(): Users

    @Update
    fun updateUser(users: Users)

    @Delete
    fun deleteUsers(users: Users)

}