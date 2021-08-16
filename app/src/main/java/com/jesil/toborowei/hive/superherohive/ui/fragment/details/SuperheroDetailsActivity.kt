package com.jesil.toborowei.hive.superherohive.ui.fragment.details

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.jesil.toborowei.hive.superherohive.R
import com.jesil.toborowei.hive.superherohive.databinding.ActivitySuperheroDetailsBinding
import com.jesil.toborowei.hive.superherohive.model.HeroModel
import com.jesil.toborowei.hive.superherohive.utils.AppConstants
import com.jesil.toborowei.hive.superherohive.utils.AppConstants.DC
import com.jesil.toborowei.hive.superherohive.utils.AppConstants.INTENT_KEY
import com.jesil.toborowei.hive.superherohive.utils.AppConstants.MARVEL
import com.jesil.toborowei.hive.superherohive.utils.providerUtil
import dagger.hilt.android.AndroidEntryPoint
import com.ramijemli.percentagechartview.PercentageChartView
import com.ramijemli.percentagechartview.callback.OnProgressChangeListener


@AndroidEntryPoint
class SuperheroDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySuperheroDetailsBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySuperheroDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val heroData: HeroModel? = intent.extras?.getParcelable(INTENT_KEY)
        val requestOptions = RequestOptions()
            .error(R.drawable.ic_broken_image)

        binding.apply {
            when (heroData?.biography?.publisher) {
                MARVEL -> {
                    Glide.with(this@SuperheroDetailsActivity)
                        .setDefaultRequestOptions(requestOptions)
                        .load(R.drawable.ic_avengers)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .into(detailImageRace)
                }
                DC -> {
                    Glide.with(this@SuperheroDetailsActivity)
                        .setDefaultRequestOptions(requestOptions)
                        .load(R.drawable.ic_justice_leauge)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .into(detailImageRace)
                }
                else -> {
                    detailImageRace.setImageResource(R.drawable.ic_unknown_publisher)
                }
            }
            Glide.with(this@SuperheroDetailsActivity)
                .setDefaultRequestOptions(requestOptions)
                .load(heroData?.images?.md)
                .into(detailImageSmall)
            detailName.text = heroData?.name
            detailRealName.text = heroData?.biography?.fullName
            detailPlaceOfBirth.text = "Place of Birth: ${heroData?.biography?.placeOfBirth}"
            detailFirstAppearance.text = "First Appearance: ${heroData?.biography?.firstAppearance}"
            detailAppearanceGender.text = heroData?.appearance?.gender
            detailAppearanceRace.text = heroData?.appearance?.race
            detailAppearanceEyeColor.text = "Eye Color: ${heroData?.appearance?.eyeColor}"
            detailAppearanceHairColor.text = "Hair Color: ${heroData?.appearance?.hairColor}"
            detailAppearanceHeight.text = "Height: ${heroData?.appearance?.height.toString()}"
            detailAppearanceWeight.text = "Weight: ${heroData?.appearance?.weight.toString()}"
            detailConnectionGroupAffiliation.text = heroData?.connections?.groupAffiliation
            detailConnectionRelatives.text = "Relative: ${heroData?.connections?.relatives}"
            detailWorkOccupation.text = "Work: ${heroData?.work?.occupation}"
            when (heroData?.biography?.publisher) {
                MARVEL -> {
                    Glide.with(this@SuperheroDetailsActivity)
                        .setDefaultRequestOptions(requestOptions)
                        .load(R.drawable.ic_marvel)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .into(detailImagePublisher)
                }
                DC -> {
                    Glide.with(this@SuperheroDetailsActivity)
                        .setDefaultRequestOptions(requestOptions)
                        .load(R.drawable.ic_dc)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .into(detailImagePublisher)
                }
                else -> {
                    detailImagePublisher.setImageResource(R.drawable.ic_unknown_publisher)
                }
            }

        }
    }
}