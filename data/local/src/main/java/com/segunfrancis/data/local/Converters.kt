package com.segunfrancis.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    @TypeConverter
    fun fromListToString(quote: List<String>?): String? {
        return Gson().toJson(quote)
    }

    @TypeConverter
    fun fromStringToList(value: String?): List<String>? {
        val type = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(value, type)
    }
}