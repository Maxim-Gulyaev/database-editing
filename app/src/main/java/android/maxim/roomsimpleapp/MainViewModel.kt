package android.maxim.roomsimpleapp

import android.app.Application
import android.maxim.roomsimpleapp.database.AppDatabase
import android.maxim.roomsimpleapp.database.Users
import androidx.lifecycle.AndroidViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainViewModel(application: Application) : AndroidViewModel(application){

    private val db = AppDatabase.getInstance(application)?.usersDao()!!
    private val compositeDisposable by lazy { CompositeDisposable() }

    interface OnTaskCompleteListener {
        fun onTaskComplete(result: String)
    }

    fun addInitialData(listener: OnTaskCompleteListener) {
        val user = Users(1, "initial name")
        compositeDisposable.add(
            db.insertUser(user)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { listener.onTaskComplete("Initial name is inserted") }
        )
    }

    fun getUserName(): Single<Users> {
        return db.getUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun updateName(name: String, listener: OnTaskCompleteListener) {
        val user = Users(1, name)
        compositeDisposable.add(
            db.updateUser(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                        listener.onTaskComplete("Name is updated")
                }
        )
    }

    fun clearTable(listener: OnTaskCompleteListener) {
        compositeDisposable.add(
            db.clearTable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { listener.onTaskComplete("Table is cleared") }
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}







































/*
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
}*/
