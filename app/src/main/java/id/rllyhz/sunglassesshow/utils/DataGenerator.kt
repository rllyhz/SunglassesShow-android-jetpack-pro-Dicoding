package id.rllyhz.sunglassesshow.utils

import android.app.Activity
import android.content.Context
import java.io.IOException

object DataGenerator {
    private val MOVIES_FILENAME = "movies.json"
    private val TV_SHOW_FILENAME = "tv_show.json"

    private fun loadAssetFile(context: Context, filename: String): String? {
        val jsonString: String

        try {
            jsonString = context.assets.open(filename).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }

        return jsonString
    }

    fun getAllMovies(context: Activity) {
        val jsonData = loadAssetFile(context, MOVIES_FILENAME)
    }
}