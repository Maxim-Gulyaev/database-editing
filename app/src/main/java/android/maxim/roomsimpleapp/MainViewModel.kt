package android.maxim.roomsimpleapp

import android.app.Application
import android.maxim.roomsimpleapp.database.AppDatabase
import android.maxim.roomsimpleapp.database.Users
import androidx.lifecycle.AndroidViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlin.concurrent.thread

class MainViewModel(application: Application) : AndroidViewModel(application){

    private val db = AppDatabase.getInstance(application)?.usersDao()!!
    private lateinit var users: Users

    fun editData(data: String) {
        users = Users(1, data)
        db.updateUser(users).subscribeOn(Schedulers.io())
            .subscribe()
    }

    fun clearTable() {
        thread {
            db.clearTable()
        }
    }

    private fun getUsersFromDB(): Pair<Int, String> {
        val userName = db.getUsername()
        val userId = db.getUserId()
        return userId to userName
    }

    fun getObservable(): Observable<Pair<Int, String>> {
        return Observable.just(this.getUsersFromDB())
            .subscribeOn(Schedulers.io())
            //.observeOn(AndroidSchedulers.mainThread())
    }

    fun setInitialValues() {
       users = Users(1, "initial name")
       db.insertUser(users).subscribeOn(Schedulers.io())
            .subscribe()
    }
}