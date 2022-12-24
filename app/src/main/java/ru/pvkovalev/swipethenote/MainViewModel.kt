package ru.pvkovalev.swipethenote

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.pvkovalev.swipethenote.database.AppRoomDatabase
import ru.pvkovalev.swipethenote.database.repository.RoomRepository
import ru.pvkovalev.swipethenote.model.Note
import ru.pvkovalev.swipethenote.utils.REPOSITORY

class MainViewModel(application: Application) : AndroidViewModel(application) {

  val context = application

    fun initDatabase(onSuccess: () -> Unit) {
        val dao = AppRoomDatabase.getInstance(context = context).getRoomDao()
        REPOSITORY = RoomRepository(dao)
        onSuccess()
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