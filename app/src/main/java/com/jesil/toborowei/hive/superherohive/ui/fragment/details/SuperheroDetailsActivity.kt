package com.jesil.toborowei.hive.superherohive.ui.fragment.details

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.jesil.toborowei.hive.superherohive.R
import com.jesil.toborowei.hive.superherohive.databinding.ActivitySuperheroDetailsBinding
import com.jesil.toborowei.hive.superherohive.model.HeroModel
import com.jesil.toborowei.hive.superherohive.utils.AppConstants
import com.jesil.toborowei.hive.superherohive.utils.AppConstants.DC
import com.jesil.toborowei.hive.superherohive.utils.AppConstants.INTENT_KEY
import com.jesil.toborowei.hive.superherohive.utils.AppConstants.MARVEL
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SuperheroDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySuperheroDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val heroData: HeroModel? = intent.extras?.getParcelable(INTENT_KEY)

    }
}