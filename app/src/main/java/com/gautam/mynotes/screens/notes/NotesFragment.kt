package com.gautam.mynotes.screens.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.gautam.mynotes.MainActivity
import com.gautam.mynotes.R
import com.gautam.mynotes.adapter.NotesAdapter
import com.gautam.mynotes.databinding.FragmentNotesBinding


class NotesFragment : Fragment() {

    private lateinit var binding: FragmentNotesBinding
 private lateinit var notesViewModel: NotesViewModel
    private lateinit var notesAdapter : NotesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentNotesBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        notesViewModel = (activity as MainActivity).notesViewModel
        recyclerviewSetup()

        binding.floatingActionButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_notesFragment_to_addFragment)
        }
    }


    private fun recyclerviewSetup() {
         notesAdapter = NotesAdapter()
        val recyclerView = binding.rvNotes
        recyclerView.adapter = notesAdapter
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        activity?.let {
            notesViewModel.getAllNotes.observe(viewLifecycleOwner , Observer { noteList->
                notesAdapter.differ.submitList(noteList)
            })
        }
    }

}