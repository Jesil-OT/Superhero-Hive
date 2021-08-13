package com.jesil.toborowei.hive.superherohive.ui

import android.graphics.Path
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jesil.toborowei.hive.superherohive.R
import com.jesil.toborowei.hive.superherohive.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.superheroToolbar)
        binding.superheroToolbar.isTitleCentered = true

    }
}