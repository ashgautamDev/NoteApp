package com.gautam.mynotes.repository

import androidx.lifecycle.LiveData
import androidx.room.Query
import com.gautam.mynotes.modle.Notes
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