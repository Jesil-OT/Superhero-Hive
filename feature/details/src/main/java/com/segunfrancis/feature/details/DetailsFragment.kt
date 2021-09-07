package com.segunfrancis.feature.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.segunfrancis.common.model.HeroModel
import com.segunfrancis.common.util.CommonConstants.DC
import com.segunfrancis.common.util.CommonConstants.IDW
import com.segunfrancis.common.util.CommonConstants.MARVEL
import com.segunfrancis.common.util.fromStringToObject
import com.segunfrancis.common.util.providerUtil
import com.segunfrancis.feature.details.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment(R.layout.fragment_details) {

    private lateinit var binding: FragmentDetailsBinding

    private val args by navArgs<DetailsFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailsBinding.bind(view)

        var weight = " "
        var height = " "
        val hero = args.hero
        val heroData: HeroModel? = hero?.fromStringToObject()
        val requestOptions = RequestOptions()
            .error(R.drawable.ic_broken_image)

        binding.apply {
            heroData?.let { result ->
                detailName.text = result.name
                detailRealName.text = result.biography.fullName ?: result.name
                detailPlaceOfBirth.text = "Place of Birth: ${result.biography.placeOfBirth}"
                detailFirstAppearance.text = "First Appearance: ${result.biography.firstAppearance}"
                detailAppearanceGender.text = result.appearance.gender
                detailAppearanceRace.text = result.appearance.race ?: "Unspecified"
                detailAppearanceEyeColor.text = "Eye Color: ${result.appearance.eyeColor}"
                detailAppearanceHairColor.text = "Hair Color: ${result.appearance.hairColor}"
                detailConnectionGroupAffiliation.text = result.connections.groupAffiliation
                detailConnectionRelatives.text = "Relative: ${result.connections.relatives}"
                detailWorkOccupation.text = "Work: ${result.work.occupation}"

                when {
                    result.biography.publisher.equals(MARVEL, true) -> {
                        Glide.with(this@DetailsFragment)
                            .setDefaultRequestOptions(requestOptions)
                            .load(R.drawable.ic_avengers)
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .into(detailImageRace)
                    }
                    result.biography.publisher.equals(DC, true) -> {
                        Glide.with(this@DetailsFragment)
                            .setDefaultRequestOptions(requestOptions)
                            .load(R.drawable.ic_justice_leauge)
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .into(detailImageRace)
                    }
                    result.biography.publisher.equals(IDW, true) -> {
                        Glide.with(this@DetailsFragment)
                            .load(R.drawable.ic_idw)
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .into(detailImageRace)
                    }
                    else -> {
                        Glide.with(this@DetailsFragment)
                            .setDefaultRequestOptions(requestOptions)
                            .load(result.images.largeImage)
                            .into(detailImageRace)
                    }
                }

                Glide.with(this@DetailsFragment)
                    .setDefaultRequestOptions(requestOptions)
                    .load(result.images.mediumImage)
                    .into(detailImageSmall)

                when {
                    result.biography.publisher.equals(MARVEL, true) -> {
                        Glide.with(this@DetailsFragment)
                            .setDefaultRequestOptions(requestOptions)
                            .load(R.drawable.ic_marvel)
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .into(detailImagePublisher)
                    }
                    result.biography.publisher.equals(DC, true) -> {
                        Glide.with(this@DetailsFragment)
                            .setDefaultRequestOptions(requestOptions)
                            .load(R.drawable.ic_dc)
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .into(detailImagePublisher)
                    }
                    result.biography.publisher.equals(IDW, true) -> {
                        Glide.with(this@DetailsFragment)
                            .load(R.drawable.ic_idw)
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .into(detailImagePublisher)
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