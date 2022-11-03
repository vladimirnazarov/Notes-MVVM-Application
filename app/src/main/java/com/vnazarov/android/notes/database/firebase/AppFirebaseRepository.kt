package com.vnazarov.android.notes.database.firebase

import android.util.Log
import androidx.lifecycle.LiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.vnazarov.android.notes.database.DatabaseRepository
import com.vnazarov.android.notes.model.Note
import com.vnazarov.android.notes.utils.Constants
import com.vnazarov.android.notes.utils.FIREBASE_ID
import com.vnazarov.android.notes.utils.LOGIN
import com.vnazarov.android.notes.utils.PASSWORD

class AppFirebaseRepository : DatabaseRepository {

    private val mAuth = FirebaseAuth.getInstance()

    override val readAll: LiveData<List<Note>> = AllNotesLiveData()

    private val database = Firebase.database.reference.child(mAuth.currentUser?.uid.toString())

    override suspend fun create(note: Note, onSuccess: () -> Unit) {
        val noteID = database.push().key.toString()
        val mapNotes = hashMapOf<String, Any>()

        mapNotes[FIREBASE_ID] = noteID
        mapNotes[Constants.Keys.TITLE] = note.title
        mapNotes[Constants.Keys.SUBTITLE] = note.subtitle

        database.child(noteID).updateChildren(mapNotes).addOnSuccessListener {
            onSuccess()
        }.addOnFailureListener {
            Log.d("Check Add Notes", "Failure")
        }

    }

    override suspend fun update(note: Note, onSuccess: () -> Unit) {
        TODO("Not yet implemented")
    }

    override suspend fun delete(note: Note, onSuccess: () -> Unit) {
        TODO("Not yet implemented")
    }

    override fun signOut() {
        mAuth.signOut()
    }

    override fun connectToDatabase(onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        mAuth.signInWithEmailAndPassword(LOGIN, PASSWORD)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener {
                mAuth.createUserWithEmailAndPassword(LOGIN, PASSWORD)
                    .addOnSuccessListener { onSuccess() }
                    .addOnFailureListener { onFailure(it.message.toString()) }
            }
    }
}