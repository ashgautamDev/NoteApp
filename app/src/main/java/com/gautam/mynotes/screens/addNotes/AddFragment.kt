package com.gautam.mynotes.screens.addNotes

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.gautam.mynotes.R
import com.gautam.mynotes.databinding.FragmentAddBinding
import com.gautam.mynotes.room.Notes
import com.gautam.mynotes.room.NotesDatabase
import com.gautam.mynotes.screens.notes.NotesRepository
import com.gautam.mynotes.screens.notes.NotesViewModel
import com.gautam.mynotes.screens.notes.NotesViewModelFactory


class AddFragment : Fragment() {

    private lateinit var notesViewModel : NotesViewModel
    private  var _binding : FragmentAddBinding? = null

    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
    _binding  = FragmentAddBinding.inflate(inflater,container,false)
        val application = requireNotNull(this.activity).application


        val viewModelFactory = NotesViewModelFactory(application)
        notesViewModel = ViewModelProvider(this , viewModelFactory).get(NotesViewModel::class.java)

        binding.saveButton.setOnClickListener {
           saveNotesToDatabase()
        }
        return binding.root
    }

    private fun saveNotesToDatabase() {
        val title = binding.etTitle.text.toString()
        val description = binding.etDescription.text.toString()
        if (inputCheck(title,description)){
            val notes = Notes(0,title,description)
            notesViewModel.insetNotes(notes)

            Toast.makeText(requireContext(), "Successfully Added", Toast.LENGTH_SHORT).show()
            // Navigate Back to the Main Fragment
            findNavController().navigate(R.id.action_addFragment_to_notesFragment)
        }else{
            Toast.makeText(requireContext(), "Add Again", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(title : String, description : String) : Boolean{
        return !(TextUtils.isEmpty(title) && TextUtils.isEmpty(description))
    }


}