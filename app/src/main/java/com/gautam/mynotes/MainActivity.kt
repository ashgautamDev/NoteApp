package com.gautam.mynotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavAction
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.gautam.mynotes.databinding.ActivityMainBinding
import com.gautam.mynotes.screens.notes.NotesViewModel
import com.gautam.mynotes.screens.notes.NotesViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
     lateinit var notesViewModel: NotesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding  = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)

        val notesViewModelFactory = NotesViewModelFactory(application)
        notesViewModel = ViewModelProvider(this, notesViewModelFactory)[NotesViewModel::class.java]
//        setupActionBarWithNavController(findNavController(R.id.fragment))
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragment)
        return navController.navigateUp() ||super.onSupportNavigateUp()
    }
}