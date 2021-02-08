package com.gautam.mynotes.viewmodles

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gautam.mynotes.viewmodles.NotesViewModel

class NotesViewModelFactory(
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass : Class<T>) : T {
        if (modelClass.isAssignableFrom(NotesViewModel::class.java)) {
                    return NotesViewModel(application) as T
                }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}