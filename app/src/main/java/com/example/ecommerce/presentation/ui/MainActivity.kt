package com.example.ecommerce.presentation.ui

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.ecommerce.R
import com.example.ecommerce.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.main_host_fragment) as NavHostFragment
        navController = navHostFragment.navController


        setSupportActionBar(binding.mainToolbar)
    }

    override fun onResume() {
        super.onResume()

        val locations = arrayOf("Zihuatanejo, Gro", "Moscow, Russia", "New York, USA", "Bambalabma, Hab")
        val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_item, locations)

        binding.apply {

            autoCompleteTextView.setAdapter(arrayAdapter)
            autoCompleteTextView.setOnItemClickListener { parent, view, position, id ->
                Toast.makeText(
                    this@MainActivity,
                    "Selected location is ${parent.getItemAtPosition(position)}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}