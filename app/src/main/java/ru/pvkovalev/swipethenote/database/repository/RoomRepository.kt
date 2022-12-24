package ru.pvkovalev.swipethenote.database.repository

import androidx.lifecycle.LiveData
import ru.pvkovalev.swipethenote.database.DatabaseRepository
import ru.pvkovalev.swipethenote.database.dao.NoteRoomDao
import ru.pvkovalev.swipethenote.model.Note

class RoomRepository(private val noteRoomDao: NoteRoomDao) : DatabaseRepository {

    override val readAll: LiveData<List<Note>>
    get() = noteRoomDao.getAllNotes()

    override suspend fun create(note: Note, onSuccess: () -> Unit) {
       noteRoomDao.addNote(note = note)
    }

    override suspend fun update(note: Note, onSuccess: () -> Unit) {
        noteRoomDao.updateNote(note = note)
    }

    override suspend fun delete(note: Note, onSuccess: () -> Unit) {
        noteRoomDao.deleteNote(note = note)
    }
}