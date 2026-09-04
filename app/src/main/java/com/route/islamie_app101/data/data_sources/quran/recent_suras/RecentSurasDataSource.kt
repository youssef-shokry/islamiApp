package com.route.islamie_app101.data.data_sources.quran.recent_suras

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.route.islamie_app101.data.utils.Constants.Companion.MOST_RECENT_PREF
import com.route.islamie_app101.data.utils.Constants.Companion.RECENT_SURAS_LIST
import com.route.islamie_app101.domain.data_models.sura.SuraDataModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton
import androidx.core.content.edit

@Singleton
class RecentSurasDataSource @Inject constructor(@param:ApplicationContext private val context: Context) {

    private val pref = context.getSharedPreferences(MOST_RECENT_PREF, Context.MODE_PRIVATE)
    private val gson = Gson()

    fun getRecentSuras(): List<SuraDataModel> {

        val json = pref.getString(RECENT_SURAS_LIST, null) ?: return emptyList()
        val type = object : TypeToken<List<SuraDataModel>>() {}.type

        return try {
            gson.fromJson(json, type) ?: emptyList()
        } catch (_: Exception) {
            emptyList()
        }

    }

    fun saveRecentSuras(recentSurasList: List<SuraDataModel>) {
        val json = gson.toJson(recentSurasList)
        pref.edit { putString(RECENT_SURAS_LIST, json).apply() }
    }
}