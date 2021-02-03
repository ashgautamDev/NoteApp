package com.gautam.mynotes.screens.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.gautam.mynotes.R
import com.gautam.mynotes.adapter.NotesAdapter
import com.gautam.mynotes.databinding.FragmentNotesBinding
import com.gautam.mynotes.room.NotesDatabase


class NotesFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentNotesBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_notes, container, false)

        val notesAdapter = NotesAdapter()
        val recyclerView = binding.rvNotes
        recyclerView.adapter = notesAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val application = requireNotNull(this.activity).application
        val viewModelFactory = NotesViewModelFactory(application)

        val viewModel = ViewModelProvider(this, viewModelFactory).get(NotesViewModel::class.java)


        viewModel.getAllNotes.observe(viewLifecycleOwner , {
            notesAdapter.setData(it)
        })
        binding.notesViewModel = viewModel
        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_notesFragment_to_addFragment)
        }
        return binding.root
    }



}