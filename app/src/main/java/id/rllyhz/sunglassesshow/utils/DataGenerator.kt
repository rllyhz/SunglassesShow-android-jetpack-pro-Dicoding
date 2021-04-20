package id.rllyhz.sunglassesshow.utils

import android.content.Context
import com.google.gson.Gson
import id.rllyhz.sunglassesshow.data.Movie
import id.rllyhz.sunglassesshow.data.TVShow
import java.io.IOException

object DataGenerator {
    private const val MOVIES_FILENAME = "movies.json"
    private const val TV_SHOW_FILENAME = "tv_show.json"

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

    fun getAllMovies(context: Context): Array<Movie>? {
        loadAssetFile(context, MOVIES_FILENAME)?.run {
            return Gson().fromJson(this, Array<Movie>::class.java)
        }

        return null
    }

    fun getAllTVShows(context: Context): Array<TVShow>? {
        loadAssetFile(context, TV_SHOW_FILENAME)?.run {
            return Gson().fromJson(this, Array<TVShow>::class.java)
        }

        return null
    }
}