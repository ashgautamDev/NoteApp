package com.gautam.mynotes.screens.notes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.gautam.mynotes.room.Notes
import kotlinx.coroutines.launch

class NotesViewModel(
    private val repository: NotesRepository,
    application: Application
) : AndroidViewModel(application) {

    fun getAllNotes() = repository.getAllNotes()

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