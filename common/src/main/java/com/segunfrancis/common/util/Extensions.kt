package com.segunfrancis.common.util

import android.view.View
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

fun View.makeVisible() {
    visibility = View.VISIBLE
}

fun View.makeGone() {
    visibility = View.GONE
}

fun SwipeRefreshLayout.colorSchemeAndRefreshListener(onRefresh: () -> Unit) {
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

fun Fragment.navigate(deepLink: String) {
    val request = NavDeepLinkRequest.Builder
        .fromUri(deepLink.toUri())
        .build()
    findNavController().navigate(request)
}
