package com.vnazarov.android.notes.utils

import com.vnazarov.android.notes.database.DatabaseRepository

const val TYPE_DATABASE = "type_database"
const val TYPE_ROOM = "type_room"
const val TYPE_FIREBASE = "type_firebase"

lateinit var REPOSITORY: DatabaseRepository

object Constants {

    object Keys{
        const val NOTE_DATABASE = "note_database"
        const val NOTES_TABLE = "notes_table"
        const val ADD_NEW_NOTE = "Add new note"
        const val NOTE_TITLE = "Note title"
        const val NOTE_SUBTITLE = "Note subtitle"
        const val ADD_NOTE = "Add note"
        const val TITLE = "Title"
        const val SUBTITLE = "Subtitle"
        const val WHAT_WILL_WE_USE = "What will we use?"
        const val ROOM_DATABASE = "Room database"
        const val FIREBASE_DATABASE = "Firebase database"
        const val ID = "id"
        const val NONE = "none"
        const val UPDATE = "UPDATE"
        const val DELETE = "DELETE"
        const val NAV_BACK = "NAVIGATE BACK"
        const val EDIT_NOTE = "Edit note"
        const val EMPTY = "Empty"
        const val UPDATE_NOTE = "Update note"
    }

    object Screens{
        const val START_SCREEN = "start_screen"
        const val MAIN_SCREEN = "main_screen"
        const val ADD_SCREEN = "add_screen"
        const val NOTE_SCREEN = "note_screen"
    }
}