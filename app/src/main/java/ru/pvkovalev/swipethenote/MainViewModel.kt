package ru.pvkovalev.swipethenote

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.pvkovalev.swipethenote.model.Note

class MainViewModel(application: Application) : AndroidViewModel(application) {

    val readTest: MutableLiveData<List<Note>> by lazy {
        MutableLiveData<List<Note>>()
    }

    init {
        readTest.value = listOf(
            Note(description = "Description1"),
            Note(description = "Description2"),
            Note(description = "Description3"),
            Note(description = "Description4")
        )
    }

    fun initDatabase() {
        Log.d("checkData", "MainViewModel initDatabase")
    }
}

class MainViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(application = application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}