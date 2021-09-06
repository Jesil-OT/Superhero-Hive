package com.segunfrancis.feature.home.util

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

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