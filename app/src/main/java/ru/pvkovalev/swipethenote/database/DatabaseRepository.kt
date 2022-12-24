package ru.pvkovalev.swipethenote.database

import androidx.lifecycle.LiveData
import ru.pvkovalev.swipethenote.model.Note

interface DatabaseRepository {

    val readAll: LiveData<List<Note>>

    suspend fun create(note: Note, onSuccess: () -> Unit)

    suspend fun update(note: Note, onSuccess: () -> Unit)

    suspend fun delete(note: Note, onSuccess: () -> Unit)
}