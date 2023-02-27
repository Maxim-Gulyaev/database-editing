package android.maxim.roomsimpleapp

import android.maxim.roomsimpleapp.database.AppDatabase
import android.maxim.roomsimpleapp.database.Users
import android.maxim.roomsimpleapp.database.UsersDao
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.maxim.roomsimpleapp.databinding.ActivityMainBinding
import androidx.activity.viewModels
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var db: UsersDao
    private lateinit var users: Users
    val model by viewModels<MainViewModel>()
    lateinit var disposable: Disposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = AppDatabase.getInstance(applicationContext)?.usersDao()!!

        binding.btnInput.setOnClickListener { editData() }
        binding.btnOutput.setOnClickListener { showUsers() }
        binding.btnClear.setOnClickListener { clearTable() }

        thread {
            users = Users(1, "initial name")
            db.insertUser(users)
        }
    }

    private fun editData() {
        val editText = binding.etInput.text.toString()
        model.editData(editText)
    }

    private fun clearTable() {
        model.clearTable()
    }

    private fun showUsers() {
        disposable = model.getObservable().subscribe {
            binding.tvOutput.text = it.second
            binding.tvId.text = it.first.toString()
        }
    }

    /*private fun getObservable(): Observable<Pair<Int, String>> {
        return Observable.just(model.showUsers())
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
    }
*/
    override fun onStop() {
        super.onStop()
        disposable.dispose()
    }
}