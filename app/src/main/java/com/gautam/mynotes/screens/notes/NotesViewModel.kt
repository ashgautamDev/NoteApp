package com.gautam.mynotes.screens.notes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.gautam.mynotes.room.Notes
import com.gautam.mynotes.room.NotesDatabase
import kotlinx.coroutines.launch

class NotesViewModel(application: Application) :
    AndroidViewModel(application) {

    private val repository: NotesRepository
    var getAllNotes: LiveData<List<Notes>>

    init {
        val notesDatabase = NotesDatabase.getDatabase(application)
        repository = NotesRepository(notesDatabase)
        getAllNotes = repository.getAllNotes
    }


    fun insetNotes(notes: Notes) = viewModelScope.launch {
        repository.insertNotes(notes)
    }

    fun deleteNotes(notes: Notes) = viewModelScope.launch {
        repository.deleteNotes(notes)
    }

    fun updateNotes(notes: Notes) = viewModelScope.launch {
        repository.updateNotes(notes)
    }


}