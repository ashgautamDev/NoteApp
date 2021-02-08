package com.gautam.mynotes.screens.addNotes

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import androidx.navigation.fragment.findNavController
import com.gautam.mynotes.MainActivity
import com.gautam.mynotes.R
import com.gautam.mynotes.databinding.FragmentAddBinding
import com.gautam.mynotes.modle.Notes
import com.gautam.mynotes.viewmodles.NotesViewModel


class AddFragment : Fragment() {

    private lateinit var notesViewModel : NotesViewModel
    private  var _binding : FragmentAddBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
    _binding  = FragmentAddBinding.inflate(inflater,container,false)

//        (activity as AppCompatDialog).apply {
//            this.supportActionBar.title = "Add Note"
//            supportActionBar.setDisplayHomeAsUpEnabled(true)}
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        notesViewModel = (activity as MainActivity).notesViewModel
        binding.saveButton.setOnClickListener {
            saveNotesToDatabase()
        }
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