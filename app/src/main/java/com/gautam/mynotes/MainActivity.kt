package com.gautam.mynotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavAction
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.gautam.mynotes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding  = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)

//        setupActionBarWithNavController(findNavController(R.id.fragment))
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragment)
        return navController.navigateUp() ||super.onSupportNavigateUp()
    }
}