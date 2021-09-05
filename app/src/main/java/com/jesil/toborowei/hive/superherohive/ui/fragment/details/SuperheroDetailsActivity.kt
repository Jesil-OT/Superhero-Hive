package com.jesil.toborowei.hive.superherohive.ui.fragment.details

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.jesil.toborowei.hive.superherohive.R
import com.jesil.toborowei.hive.superherohive.databinding.ActivitySuperheroDetailsBinding
import com.jesil.toborowei.hive.superherohive.model.HeroModel
import com.jesil.toborowei.hive.superherohive.utils.AppConstants.DC
import com.jesil.toborowei.hive.superherohive.utils.AppConstants.IDW
import com.jesil.toborowei.hive.superherohive.utils.AppConstants.INTENT_KEY
import com.jesil.toborowei.hive.superherohive.utils.AppConstants.MARVEL
import com.jesil.toborowei.hive.superherohive.utils.glideHeroImage
import com.jesil.toborowei.hive.superherohive.utils.glideHeroPublisher
import com.jesil.toborowei.hive.superherohive.utils.providerUtil
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SuperheroDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySuperheroDetailsBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySuperheroDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var weight = " "
        var height = " "
        val heroData: HeroModel? = intent.extras?.getParcelable(INTENT_KEY)

        binding.apply {
            heroData?.let { result ->
                detailName.text = result.name
                detailRealName.text = if(result.biography.fullName.isEmpty()) result.name else result.biography.fullName
                detailPlaceOfBirth.text = "Place of Birth: ${result.biography.placeOfBirth}"
                detailFirstAppearance.text = "First Appearance: ${result.biography.firstAppearance}"
                detailAppearanceGender.text = result.appearance.gender
                detailAppearanceRace.text = result.appearance.race ?: "Unspecified"
                detailAppearanceEyeColor.text = "Eye Color: ${result.appearance.eyeColor}"
                detailAppearanceHairColor.text = "Hair Color: ${result.appearance.hairColor}"
                detailConnectionGroupAffiliation.text = result.connections.groupAffiliation
                detailConnectionRelatives.text = "Relative: ${result.connections.relatives}"
                detailWorkOccupation.text = "Work: ${result.work.occupation}"

                detailImageRace.glideHeroImage(view = textviewIntelligence, uri = result.images.largeImage)
                detailImageSmall.glideHeroImage(view = textviewIntelligence, uri = result.images.mediumImage)

                when {
                    result.biography.publisher.equals(MARVEL, true) -> {
                        detailImagePublisher.glideHeroPublisher(view = textviewIntelligence, uri = R.drawable.ic_marvel)
                    }
                    result.biography.publisher.equals(DC, true) -> {
                        detailImagePublisher.glideHeroPublisher(view = textviewIntelligence, uri = R.drawable.ic_dc)
                    }
                    result.biography.publisher.equals(IDW, true) -> {
                        detailImagePublisher.glideHeroPublisher(view = textviewIntelligence, uri = R.drawable.ic_idw)
                    }
                    else -> {
                        detailImagePublisher.setImageResource(R.drawable.ic_unknown_publisher)
                    }
                }

                for (heights in result.appearance.height) {
                    val regex = "\r".toRegex()
                    height += "$heights $regex"
                }
                detailAppearanceHeight.text = "Height: $height"

                for (weights in result.appearance.weight) {
                    val regex = "\r".toRegex()
                    weight += "$weights $regex"
                    detailAppearanceWeight.text = "Weight: $weight"
                }


                with(detailIntelligence) {
                    setAdaptiveColorProvider(providerUtil)
                    setProgress(result.powerStats.intelligence.toFloat(), true)
                }

                with(detailStrength) {
                    setAdaptiveColorProvider(providerUtil)
                    detailStrength.setProgress(result.powerStats.strength.toFloat(), true)
                }

                with(detailSpeed) {
                    setAdaptiveColorProvider(providerUtil)
                    setProgress(result.powerStats.speed.toFloat(), true)
                }

                with(detailDurability) {
                    setAdaptiveColorProvider(providerUtil)
                    detailDurability.setProgress(result.powerStats.durability.toFloat(), true)
                }

                with(detailPower) {
                    setAdaptiveColorProvider(providerUtil)
                    setProgress(result.powerStats.power.toFloat(), true)
                }

                if (result.powerStats.combat > 100) {
                    detailCombat.visibility = View.GONE
                    textviewCombat.visibility = View.GONE
                } else {
                    with(detailCombat) {
                        setAdaptiveColorProvider(providerUtil)
                        setProgress(result.powerStats.combat.toFloat(), true)
                    }
                }
            }
        }
    }
}