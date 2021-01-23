package com.gautam.mynotes.screens.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gautam.mynotes.R
import com.gautam.mynotes.databinding.FragmentNotesBinding
import com.gautam.mynotes.room.NotesDatabase


class NotesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentNotesBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_notes, container, false)

        val application = requireNotNull(this.activity  ).application
        val noteDao = NotesDatabase.getDatabase(application).notesDao()

        val viewModelFactory = NotesViewModelFactory(noteDao ,application)

        val viewModel = ViewModelProvider(this,viewModelFactory).get(NotesViewModel::class.java)
        binding.notesViewModel = viewModel

        return binding.root
    }


}