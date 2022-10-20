package com.vnazarov.android.notes

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vnazarov.android.notes.model.Note
import com.vnazarov.android.notes.utils.TYPE_FIREBASE
import com.vnazarov.android.notes.utils.TYPE_ROOM

class MainViewModel(application: Application) : AndroidViewModel(application) {

    val readTest: MutableLiveData<List<Note>> by lazy {
        MutableLiveData<List<Note>>()
    }

    val dbType: MutableLiveData<String> by lazy {
        MutableLiveData<String>(TYPE_ROOM)
    }

    init {
        readTest.value =
            when (dbType.value) {
                TYPE_ROOM -> {
                    listOf<Note>(
                        Note(title = "Note 1", subtitle = "Subtitle for note 1"),
                        Note(title = "Note 2", subtitle = "Subtitle for note 2"),
                        Note(title = "Note 3", subtitle = "Subtitle for note 3"),
                        Note(title = "Note 4", subtitle = "Subtitle for note 4"),
                    )
                }
                TYPE_FIREBASE -> listOf<Note>(Note(title = "Note 5", subtitle = "Subtitle for note 5"))
                else -> listOf()
            }
    }

    fun initDatabase(type: String) {
        dbType.value = type
        Log.i("MVVM Log", "Database initialized with type: $type")
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