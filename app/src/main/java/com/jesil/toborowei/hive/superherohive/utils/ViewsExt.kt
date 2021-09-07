package com.jesil.toborowei.hive.superherohive.utils

import android.graphics.Color
import android.view.View
import android.widget.ImageView
import androidx.core.graphics.ColorUtils
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.jesil.toborowei.hive.superherohive.R
import com.ramijemli.percentagechartview.callback.AdaptiveColorProvider

fun View.showUtils() {
    visibility = View.VISIBLE
}

fun View.hideUtils() {
    visibility = View.GONE
}

fun ImageView.glideHeroImage(view: View, uri: String?) =
    Glide.with(view)
        .load(uri)
        .placeholder(R.drawable.ic_on_loading_image)
        .error(R.drawable.ic_broken_image)
        .into(this)

fun ImageView.glideHeroPublisher(view: View, uri: Int) =
    Glide.with(view)
        .load(uri)
        .into(this)

fun SwipeRefreshLayout.colorSchemeAndRefreshListener(onRefresh: () -> Unit){
    setColorSchemeResources(
        android.R.color.holo_blue_bright,
        android.R.color.holo_green_light,
        android.R.color.holo_orange_light,
        android.R.color.holo_red_light
    )
    setOnRefreshListener {
        onRefresh()
    }
}

val providerUtil: AdaptiveColorProvider =
    object : AdaptiveColorProvider {
        override fun provideTextColor(progress: Float): Int {
            return when{
                progress <= 25f -> Color.parseColor("#F32121")
                progress <= 50f -> Color.parseColor("#C6980D")
                progress <= 75f -> Color.parseColor("#0D7711")
                else -> Color.parseColor("#3551EF")
            }
        }

        override fun provideBackgroundBarColor(progress: Float): Int {
            return ColorUtils.blendARGB(provideProgressColor(progress), Color.BLACK, .8f);
        }

        override fun provideProgressColor(progress: Float): Int {
            return when {
                progress <= 25f -> Color.parseColor("#F32121")
                progress <= 50f -> Color.parseColor("#F1DD31")
                progress <= 75f -> Color.parseColor("#0D7711")
                else -> Color.parseColor("#3551EF")
            }
        }

        override fun provideBackgroundColor(progress: Float): Int {
            return ColorUtils.blendARGB(provideProgressColor(progress), Color.BLACK, .5f);
        }

    }

