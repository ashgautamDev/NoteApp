package com.gautam.mynotes.screens.notes

import androidx.lifecycle.LiveData
import com.gautam.mynotes.room.Notes
import com.gautam.mynotes.room.NotesDao
import com.gautam.mynotes.room.NotesDatabase


class NotesRepository(private val notesDatabase: NotesDatabase) {

    private val notesDao = notesDatabase.notesDao()

    val getAllNotes : LiveData<List<Notes>> = notesDao.getAllNotes()

    suspend fun insertNotes(notes: Notes) {
        notesDao.insertNote(notes)
    }

    suspend fun updateNotes(notes: Notes) {
        notesDao.updateNote(notes)
    }

    suspend fun deleteNotes(notes: Notes) {
       notesDao.deleteNote(notes)
    }


}