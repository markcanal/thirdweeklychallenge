package com.example.movielocal.common.utils

import android.content.Context

object MovieFileUtils {
    fun getJsonFromString(context: Context, fileName: String): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
        return jsonString
    }
}