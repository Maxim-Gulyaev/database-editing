package android.maxim.roomsimpleapp.database

import androidx.room.*
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface UsersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(users: Users): Completable

    @Query("SELECT * FROM users")
    fun getUsers(): Single<Users>

    @Update
    fun updateUser(users: Users): Completable

    @Delete
    fun deleteUsers(users: Users)

    @Query("DELETE FROM users")
    fun clearTable(): Completable

    @Query("SELECT COUNT(*) FROM users")
    fun ifTableEmpty(): Single<Int>

}