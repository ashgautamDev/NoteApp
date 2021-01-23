package com.gautam.mynotes.screens.notes

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gautam.mynotes.room.NotesDao

class NotesViewModelFactory(
    private val notesDao: NotesDao,
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NotesViewModelFactory::class.java)) {
                    return NotesViewModelFactory(notesDao, application) as T
                }
                throw IllegalArgumentException("Unknown ViewModel class")
    }
}