package ru.pvkovalev.swipethenote.utils

import ru.pvkovalev.swipethenote.database.DatabaseRepository

lateinit var REPOSITORY: DatabaseRepository

object Constants {
    object Keys {
        const val NOTE_DATABASE = "notes_database"
        const val NOTES_TABLE = "notes_table"
        const val ADD_NEW_NOTE = "Add new note"
        const val NOTE_DESCRIPTION = "Note description"
        const val SAVE_NOTE = "Save"
        const val ID = "Id"
        const val NONE = "none"
        const val UPDATE = "UPDATE"
        const val DELETE = "DELETE"
        const val NAV_BACK = "NAV_BACK"
        const val EDIT_NOTE = "Edit note"
        const val EMPTY = ""
        const val DESCRIPTION = "Description"
        const val UPDATE_NOTE = "Update note"
    }

    object Screens {
        const val MAIN_SCREEN = "main_screen"
        const val ADD_SCREEN = "add_screen"
        const val EDIT_SCREEN = "edit_screen"
    }
}
