package android.maxim.roomsimpleapp

import android.app.Application
import android.maxim.roomsimpleapp.database.AppDatabase
import android.maxim.roomsimpleapp.database.Users
import androidx.lifecycle.AndroidViewModel

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val db = AppDatabase.getInstance(application)?.usersDao()!!
    private lateinit var users: Users

    fun editData(data: String) {
        users = Users(1, data)
        db.updateUser(users)
    }

    fun clearTable() {
        db.clearTable()
    }

    fun showUsers(): Pair<Int, String> {
        val userName = db.getUsername()
        val userId = db.getUserId()
        return userId to userName
    }
}