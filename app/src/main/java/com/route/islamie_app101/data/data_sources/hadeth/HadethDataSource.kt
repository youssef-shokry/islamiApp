package com.route.islamie_app101.data.data_sources.hadeth

import android.content.Context
import com.route.islamie_app101.data.utils.Constants.Companion.AHADETH_PATH
import com.route.islamie_app101.domain.data_models.HadethDataModel

object HadethDataSource {
    var isLoaded = false
    private val ahadeth: MutableList<HadethDataModel> = emptyList<HadethDataModel>().toMutableList()

    private fun readHadethFile(context: Context) {
        if (isLoaded) return

        val inputStream = context.applicationContext.assets.open(AHADETH_PATH)
        val reader = inputStream.bufferedReader()

        var line = reader.readLine()

        val hadethLines: MutableList<String> = mutableListOf()

        while (line != null) {

            when (line.trim()) {
                "#" -> {
                    val hadethTitle = hadethLines[0]
                    hadethLines.removeAt(0)

                    val hadethContent = hadethLines.joinToString(separator = " ")
                    ahadeth.add(HadethDataModel(hadethTitle, hadethContent))

                    hadethLines.clear()
                }

                else -> {
                    hadethLines.add(line.trim())
                }
            }
            line = reader.readLine()
        }
        isLoaded = true
    }

    fun getAhadethList(context: Context): List<HadethDataModel> {
        readHadethFile(context)
        return ahadeth
    }
}