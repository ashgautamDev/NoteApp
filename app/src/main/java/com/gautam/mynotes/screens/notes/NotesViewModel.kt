package com.gautam.mynotes.screens.notes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.gautam.mynotes.room.Notes
import kotlinx.coroutines.launch

class NotesViewModel(application: Application, private val repository: NotesRepository) :
    AndroidViewModel(application) {

    val getAllNotes : LiveData<List<Notes>> = repository.getAllNotes


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