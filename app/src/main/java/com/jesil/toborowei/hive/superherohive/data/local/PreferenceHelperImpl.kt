package com.jesil.toborowei.hive.superherohive.data.local

import android.content.SharedPreferences
import javax.inject.Inject

class PreferenceHelperImpl @Inject constructor(private val preference: SharedPreferences) : PreferenceHelper {

    override fun addFavorite(id: Int) {
        preference.edit().putBoolean(id.toString(), true).apply()
    }

    override fun removeFavorite(id: Int) {
        preference.edit().remove(id.toString()).apply()
    }

    override fun getFavorite(id: Int): Boolean {
        return preference.getBoolean(id.toString(), false)
    }
}
