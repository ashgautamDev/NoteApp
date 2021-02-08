package com.gautam.mynotes.screens.notes

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.gautam.mynotes.MainActivity
import com.gautam.mynotes.R
import com.gautam.mynotes.adapter.NotesAdapter
import com.gautam.mynotes.databinding.FragmentNotesBinding
import com.gautam.mynotes.modle.Notes
import com.gautam.mynotes.viewmodles.NotesViewModel


class NotesFragment : Fragment(R.layout.fragment_notes) {

    private lateinit var _binding: FragmentNotesBinding
    private val binding get() = _binding!!
    private lateinit var notesViewModel: NotesViewModel
    private lateinit var notesAdapter: NotesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentNotesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        notesViewModel = (activity as MainActivity).notesViewModel
        recyclerViewSetup()

        binding.floatingActionButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_notesFragment_to_addFragment)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.home_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.NotesView -> {
                changeLayoutOfRecyclerView()
                item.title = "Grid View"
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun recyclerViewSetup() {
        notesAdapter = NotesAdapter()
        val recyclerView = binding.rvNotes
        recyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            setHasFixedSize(true)
            adapter = notesAdapter
        }

        activity?.let {
            notesViewModel.getAllNotes.observe(viewLifecycleOwner, Observer { noteList ->
                notesAdapter.differ.submitList(noteList)
                updateUi(noteList)
            })
        }


    }



    private fun updateUi(noteList: List<Notes>) {
        if (noteList.isNotEmpty()) {
            binding.emptyStateLayout.visibility = View.GONE
            binding.rvNotes.visibility = View.VISIBLE
        } else {
            binding.emptyStateLayout.visibility = View.VISIBLE
            binding.rvNotes.visibility = View.GONE
        }
    }
    private fun changeLayoutOfRecyclerView() {
        val recyclerView = binding.rvNotes
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

}