package com.gautam.mynotes.screens.detail

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.core.app.ShareCompat
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.gautam.mynotes.MainActivity
import com.gautam.mynotes.R
import com.gautam.mynotes.databinding.FragmentDisplayBinding
import com.gautam.mynotes.modle.Notes
import com.gautam.mynotes.viewmodles.NotesViewModel


class DisplayFragment : Fragment() {
    private lateinit var currentNote: Notes
    private lateinit var notesViewModel: NotesViewModel
    private val args: DisplayFragmentArgs by navArgs()

    private lateinit var _binding: FragmentDisplayBinding
    private val binding get() = _binding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentDisplayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        currentNote = args.myargs
        binding.etTitle.setText(currentNote.noteTitle)
        binding.etDescription.setText(currentNote.notesDesc)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.note_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.updateNote -> {
                updateNotes()
                return true
            }
            R.id.shareNote -> {
                shareNotes()
                return true
            }
            R.id.deleteNote -> {
                deleteNote()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteNote() {
        notesViewModel = (activity as MainActivity).notesViewModel
        AlertDialog.Builder(activity).apply {
            setTitle("Delete Note")
            setMessage("Are you sure you want to permanently delete this note?")
            setPositiveButton("DELETE") { _, _ ->
                notesViewModel.deleteNotes(currentNote)
                view?.findNavController()?.navigate(R.id.action_displayFragment_to_notesFragment)
            }
            setNegativeButton("CANCEL", null)
        }.create().show()
    }

    @SuppressLint( "QueryPermissionsNeeded", "StringFormatInvalid")
    private fun shareNotes() {
        val shareMsg = getString(
            R.string.share_message,
            args.myargs.noteTitle,
            args.myargs.notesDesc
        )

        val intent = ShareCompat.IntentBuilder.from(requireActivity())
            .setType("text/plain")
            .setText(shareMsg)
            .intent

        if (intent.resolveActivity(requireActivity().packageManager) != null) {
            startActivity(intent)
        }
    }

    private fun updateNotes() {
        notesViewModel = (activity as MainActivity).notesViewModel

        val title = binding.etTitle.text.toString().trim()
        val description = binding.etDescription.text.toString().trim()
        if (inputcheck(title, description)) {
            val note = Notes(currentNote.noteId, title, description)
            notesViewModel.updateNotes(note)
            Toast.makeText(requireContext(), "Successfully Updated", Toast.LENGTH_SHORT).show()
            view?.findNavController()?.navigate(R.id.action_displayFragment_to_notesFragment)
        } else {
            Toast.makeText(requireContext(), "Enter Fields", Toast.LENGTH_SHORT).show()

        }
    }

    private fun inputcheck(title: String, description: String): Boolean {
        return !(TextUtils.isEmpty(title) && TextUtils.isEmpty(description))
    }

}