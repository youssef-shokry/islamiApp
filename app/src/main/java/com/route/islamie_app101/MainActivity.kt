package com.route.islamie_app101

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.route.islamie_app101.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var navHostFragment: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpNavigationComponent()
        fullScreenFragments()

    }

    private fun fullScreenFragments() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when(destination.id){
                R.id.suraFragment -> binding.bottomNav.visibility = View.GONE
                R.id.hadethFragment -> binding.bottomNav.visibility = View.GONE
                else -> {
                    binding.bottomNav.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun setUpNavigationComponent() {
        navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController
        binding.bottomNav.setupWithNavController(navController)
    }


}