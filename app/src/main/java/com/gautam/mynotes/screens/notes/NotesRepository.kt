package com.gautam.mynotes.screens.notes

import com.gautam.mynotes.room.Notes
import com.gautam.mynotes.room.NotesDao

class NotesRepository(private val notesDao: NotesDao) {

    suspend fun insertNotes(notes: Notes) {
        notesDao.insertNote(notes)
    }

    suspend fun updateNotes(notes: Notes) {
        notesDao.updateNote(notes)
    }

    suspend fun deleteNotes(notes: Notes) {
        notesDao.deleteNote(notes)
    }

    fun getAllNotes() {
        notesDao.getAllNotes()
    }

}