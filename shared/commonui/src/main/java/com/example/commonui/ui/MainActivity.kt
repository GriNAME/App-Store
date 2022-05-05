package com.example.commonui.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.commonui.R
import com.example.commonui.databinding.ActivityMainBinding
import com.example.navigation.NavigationFlow
import com.example.navigation.Navigator
import com.example.navigation.ToFlowNavigatable
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.MaterialShapeDrawable
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity(), ToFlowNavigatable {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    @Inject lateinit var navigator: Navigator

    private var backPressed: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.main_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        initBottomNavigation()
    }

    private fun initBottomNavigation() {
        val bottomNavigationViewBackground = binding.bottomNavigationView.background as MaterialShapeDrawable
        bottomNavigationViewBackground.shapeAppearanceModel =
            bottomNavigationViewBackground.shapeAppearanceModel.toBuilder()
                .setTopRightCorner(CornerFamily.ROUNDED, 80F)
                .setTopLeftCorner(CornerFamily.ROUNDED, 80F)
                .build()

        navigator.navController = navController

        binding.bottomNavigationView.setupWithNavController(navController)
    }

    override fun onBackPressed() {
        navController.popBackStack()
        (this as ToFlowNavigatable).navigateToFlow(NavigationFlow.HomeStoreFlow)
        if (backPressed + 2000 > System.currentTimeMillis()) super.onBackPressed()
        else Toast.makeText(
            baseContext, "Press once again to exit!",
            Toast.LENGTH_SHORT
        ).show()
        backPressed = System.currentTimeMillis()
    }

    override fun navigateToFlow(flow: NavigationFlow) {
        navigator.navigateToFlow(flow)
    }
}