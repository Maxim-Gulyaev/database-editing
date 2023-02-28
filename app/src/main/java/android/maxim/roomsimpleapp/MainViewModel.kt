package android.maxim.roomsimpleapp

import android.app.Application
import android.maxim.roomsimpleapp.database.AppDatabase
import android.maxim.roomsimpleapp.database.Users
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlin.concurrent.thread

class MainViewModel(application: Application) : AndroidViewModel(application){

    private val db = AppDatabase.getInstance(application)?.usersDao()!!
    private lateinit var users: Users

    val name: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    fun editData(data: String) {
        thread {
            users = Users(1, data)
            db.updateUser(users)
        }
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
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
    }
}