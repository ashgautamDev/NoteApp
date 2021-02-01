package com.gautam.mynotes.screens.notes

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gautam.mynotes.room.NotesDao

@Suppress("UNCHECKED_CAST")
class NotesViewModelFactory(
    private val repository: NotesRepository,
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass : Class<T>) : T {
        if (modelClass.isAssignableFrom(NotesViewModel::class.java)) {
                    return NotesViewModel(application, repository) as T
                }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}