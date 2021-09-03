package com.jesil.toborowei.hive.superherohive.ui

import android.annotation.SuppressLint
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.get
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.jesil.toborowei.hive.superherohive.R
import com.jesil.toborowei.hive.superherohive.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        with(binding) {
            setContentView(root)
            setSupportActionBar(toolbar.root)
        }

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment
        navController = navHostFragment.findNavController()
        val appBarConfiguration = AppBarConfiguration(
            setOf(R.id.superheroes, R.id.favorites, R.id.settings)
        )
        binding.apply {
            toolbar.root.setupWithNavController(navController, appBarConfiguration)
            bottomNavigationView.setupWithNavController(navController)
            bottomNavigationView.setOnItemReselectedListener { }
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun quitingDialogQuestion() {
        MaterialAlertDialogBuilder(this)
            .setTitle("Exit Superhero Hive")
            .setMessage("Are you sure you want to quit this app")
            .setPositiveButton(
                "yes"
            ) { _, _ -> finish() }
            .setNegativeButton(
                "No"
            ) { _, _ -> // nothing
            }
            .setBackground(resources.getDrawable(R.color.blue_good))
            .show()
    }
}
