package android.maxim.roomsimpleapp

import android.maxim.roomsimpleapp.database.AppDatabase
import android.maxim.roomsimpleapp.database.Users
import android.maxim.roomsimpleapp.database.UsersDao
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.maxim.roomsimpleapp.databinding.ActivityMainBinding
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var db: UsersDao
    private lateinit var users: Users

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = AppDatabase.getInstance(applicationContext)?.usersDao()!!

        binding.btnInput.setOnClickListener { editData() }
        binding.btnOutput.setOnClickListener { showUsers() }
        binding.btnClear.setOnClickListener { clearTable() }

        users = Users(1, "ddddd")
        db.insertUser(users)
    }

    private fun editData() {
        val editedText = binding.etInput.text.toString()
        users = Users(1, editedText)
        db.updateUser(users)
    }

    private fun clearTable() {
        db.clearTable()
    }

    private fun showUsers() {
        val userName = db.getUsername()
        val userId = db.getUserId().toString()
        binding.tvOutput.text = userName
        binding.tvId.text = userId
    }
}