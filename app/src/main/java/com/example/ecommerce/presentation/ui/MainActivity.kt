package com.example.ecommerce.presentation.ui

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.ecommerce.R
import com.example.ecommerce.databinding.ActivityMainBinding
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.MaterialShapeDrawable
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
        initBottomNavigation()
    }

    private fun initBottomNavigation() {
        val bottomNavigationViewBackground = binding.bottomNavigationView.background as MaterialShapeDrawable
        bottomNavigationViewBackground.shapeAppearanceModel =
            bottomNavigationViewBackground.shapeAppearanceModel.toBuilder()
                .setTopRightCorner(CornerFamily.ROUNDED, 80F)
                .setTopLeftCorner(CornerFamily.ROUNDED, 80F)
                .build()

        val appBarConfiguration = AppBarConfiguration(
            setOf(

                R.id.homeFragment, // Заглушка для избранного
                R.id.homeFilterBottomSheet // Заглушка для профиля
            )
        )

        binding.bottomNavigationView.setupWithNavController(navController)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onResume() {
        super.onResume()

        val locations =
            arrayOf("Zihuatanejo, Gro", "Moscow, Russia", "New York, USA", "Bambalabma, Hab", "Kurguda, Sato")
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

            mainFilterButton.setOnClickListener {
                navController.navigate(R.id.action_homeFragment_to_homeFilterBottomSheet)
            }
        }
    }
}