package com.gautam.mynotes

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.gautam.mynotes.databinding.ActivityMainBinding
import com.gautam.mynotes.screens.notes.NotesViewModel
import com.gautam.mynotes.screens.notes.NotesViewModelFactory
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var binding: ActivityMainBinding
    lateinit var notesViewModel: NotesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding  = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
        mAuth = FirebaseAuth.getInstance()
        updateUi()

        val notesViewModelFactory = NotesViewModelFactory(application)
        notesViewModel = ViewModelProvider(this, notesViewModelFactory)[NotesViewModel::class.java]

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragment)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    private fun updateUi() {

        val user = mAuth.currentUser

        Handler().postDelayed(
            { if (user == null) {
                    val singInActivity = Intent(this, FirbaseUiActivity::class.java)
                    startActivity(singInActivity)
                }
            }, 2000
        )
    }
}