package com.jesil.toborowei.hive.superherohive.utils

import android.graphics.Color
import android.view.View
import androidx.core.graphics.ColorUtils
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.ramijemli.percentagechartview.callback.AdaptiveColorProvider

fun View.makeVisible() {
    visibility = View.VISIBLE
}

fun View.makeGone() {
    visibility = View.GONE
}

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
                progress <= 25f -> Color.RED
                progress <= 50f -> Color.YELLOW
                progress <= 75f -> Color.GREEN
                progress <= 85 -> Color.CYAN
                else -> Color.BLUE
            }
        }

        override fun provideBackgroundBarColor(progress: Float): Int {
            return ColorUtils.blendARGB(provideProgressColor(progress), Color.BLACK, .8f);
        }

        override fun provideProgressColor(progress: Float): Int {
            return when {
                progress <= 25f -> Color.RED
                progress <= 50f -> Color.YELLOW
                progress <= 75f -> Color.GREEN
                progress <= 85f -> Color.CYAN
                else -> Color.BLUE
            }
        }

        override fun provideBackgroundColor(progress: Float): Int {
            return ColorUtils.blendARGB(provideProgressColor(progress), Color.BLACK, .5f);
        }

    }

fun Fragment.navigate(deepLink: String) {
    val request = NavDeepLinkRequest.Builder
        .fromUri(deepLink.toUri())
        .build()
    findNavController().navigate(request)
}
