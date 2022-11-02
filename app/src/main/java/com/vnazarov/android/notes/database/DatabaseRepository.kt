package com.vnazarov.android.notes.database

import androidx.lifecycle.LiveData
import com.vnazarov.android.notes.model.Note

interface DatabaseRepository {

    val readAll: LiveData<List<Note>>

    suspend fun create (note: Note, onSuccess: () -> Unit)

    suspend fun update (note: Note, onSuccess: () -> Unit)

    suspend fun delete (note: Note, onSuccess: () -> Unit)

    fun signOut() {}

    fun connectToDatabase(onSuccess: () -> Unit, onFailure: (String) -> Unit) {}
}