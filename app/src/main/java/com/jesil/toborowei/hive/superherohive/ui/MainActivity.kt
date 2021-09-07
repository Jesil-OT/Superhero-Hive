package com.jesil.toborowei.hive.superherohive.ui

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
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
            toolbar.root.title = resources.getString(R.string.app_name)
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.settings, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.settings -> {
                navigateToSettings()
                false
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    private fun navigateToSettings() {

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
