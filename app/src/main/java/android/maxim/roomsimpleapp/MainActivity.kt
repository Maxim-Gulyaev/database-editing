package android.maxim.roomsimpleapp

import android.maxim.roomsimpleapp.database.AppDatabase
import android.maxim.roomsimpleapp.database.UsersDao
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.maxim.roomsimpleapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var db: UsersDao = AppDatabase.getInstance(applicationContext)?.usersDao()!!

        binding.btnInput.setOnClickListener { inputUser() }
        binding.btnOutput.setOnClickListener { showUsers() }
        binding.btnClear.setOnClickListener { clearTable() }

    }

    private fun clearTable() {
        TODO("Not yet implemented")
    }

    private fun showUsers() {
        TODO("Not yet implemented")
    }

    private fun inputUser() {}
}