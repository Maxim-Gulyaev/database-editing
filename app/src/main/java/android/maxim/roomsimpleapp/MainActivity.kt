package android.maxim.roomsimpleapp

import android.maxim.roomsimpleapp.database.AppDatabase
import android.maxim.roomsimpleapp.database.Users
import android.maxim.roomsimpleapp.database.UsersDao
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.maxim.roomsimpleapp.databinding.ActivityMainBinding
import androidx.activity.viewModels
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var db: UsersDao
    private lateinit var users: Users
    val model by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = AppDatabase.getInstance(applicationContext)?.usersDao()!!

        binding.btnInput.setOnClickListener { editData() }
        binding.btnOutput.setOnClickListener { showUsers() }
        binding.btnClear.setOnClickListener { clearTable() }

        users = Users(1, "initial name")
        db.insertUser(users)
    }

    private fun editData() {
        val editText = binding.etInput.text.toString()
        model.editData(editText)
    }

    private fun clearTable() {
        model.clearTable()
    }

    private fun showUsers() {
        binding.tvOutput.text = model.showUsers().second
        binding.tvId.text = model.showUsers().first.toString()
    }
}