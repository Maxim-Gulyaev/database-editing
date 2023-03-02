package android.maxim.roomsimpleapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.maxim.roomsimpleapp.databinding.ActivityMainBinding
import android.widget.Toast
import androidx.activity.viewModels
import io.reactivex.rxjava3.disposables.CompositeDisposable

class MainActivity : AppCompatActivity(), MainViewModel.OnTaskCompleteListener {

    private lateinit var binding: ActivityMainBinding
    private val model by viewModels<MainViewModel>()
    private val compositeDisposable by lazy { CompositeDisposable() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState != null) {
            binding.tvId.text = savedInstanceState.getString("id")
            binding.tvOutput.text = savedInstanceState.getString("name")
        } else {
            model.addInitialData(this)
        }

        binding.btnInput.setOnClickListener { updateName() }
        binding.btnOutput.setOnClickListener { showUserName() }
        binding.btnClear.setOnClickListener { clearTable() }
    }

    private fun updateName() {
        val name = binding.etInput.text.toString()
        model.updateName(name, this)
    }

    private fun showUserName() {
        compositeDisposable.add(
            model.getUserName()
                .subscribe ({ data ->
                    binding.tvId.text = data.userId.toString()
                    binding.tvOutput.text = data.userName
                }, {
                    Toast.makeText(this, "Table has no data", Toast.LENGTH_SHORT)
                        .show()
                    binding.tvId.text = resources.getString(R.string.no_data)
                    binding.tvOutput.text = resources.getString(R.string.no_data)
                })
        )
    }

    private fun clearTable() {
        model.clearTable(this)
    }

    override fun onTaskComplete(result: String) {
        Toast.makeText(this, result, Toast.LENGTH_SHORT)
            .show()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("id", binding.tvId.text.toString())
        outState.putString("name", binding.tvOutput.text.toString())
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}





























































    /*override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = AppDatabase.getInstance(applicationContext)?.usersDao()!!

        binding.btnInput.setOnClickListener { editData() }
        binding.btnOutput.setOnClickListener { showUsers() }
        binding.btnClear.setOnClickListener { clearTable() }

        model.setInitialValues()
        this.showUsers()
    }

    private fun editData() {
        val editText = binding.etInput.text.toString()
        model.editData(editText)
    }

    private fun clearTable() {
        model.clearTable()
    }

    private fun showUsers() {
        disposable = model.getObservable()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
            binding.tvOutput.text = it.second
            binding.tvId.text = it.first.toString()
        }
    }

    override fun onStop() {
        super.onStop()
        disposable?.dispose()
    }*/
