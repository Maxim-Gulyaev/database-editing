package android.maxim.roomsimpleapp.database

import androidx.room.*
import io.reactivex.rxjava3.core.Completable

@Dao
interface UsersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(users: Users): Completable

    @Query("Select * from users")
    fun gelAllUsers(): Users

    @Query("SELECT userName FROM users")
    fun getUsername(): String

    @Query("SELECT userId FROM users")
    fun getUserId(): Int

    @Update
    fun updateUser(users: Users): Completable

    @Delete
    fun deleteUsers(users: Users)

    @Query("DELETE FROM users")
    fun clearTable()

}