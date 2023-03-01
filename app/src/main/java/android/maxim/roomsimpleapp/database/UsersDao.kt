package android.maxim.roomsimpleapp.database

import androidx.room.*
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

@Dao
interface UsersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(users: Users): Completable

    @Query("Select * from users")
    fun getUsers(): Single<Users>

    @Update
    fun updateUser(users: Users): Completable

    @Delete
    fun deleteUsers(users: Users)

    @Query("DELETE FROM users")
    fun clearTable(): Completable

}