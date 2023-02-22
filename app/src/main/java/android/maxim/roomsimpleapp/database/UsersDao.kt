package android.maxim.roomsimpleapp.database

import androidx.room.*

@Dao
interface UsersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(users: Users)

    @Query("Select * from users")
    fun gelAllUsers(): Users

    @Query("SELECT userName FROM users")
    fun getUsername(): String

    @Query("SELECT userId FROM users")
    fun getUserId(): Int

    @Update
    fun updateUser(users: Users)

    @Delete
    fun deleteUsers(users: Users)

    @Query("DELETE FROM users")
    fun clearTable()

}