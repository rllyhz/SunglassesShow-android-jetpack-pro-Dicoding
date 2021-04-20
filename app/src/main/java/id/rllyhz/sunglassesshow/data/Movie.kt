package id.rllyhz.sunglassesshow.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val id: Int,
    @SerializedName("poster_name")
    val posterName: String,
    val title: String,
    val year: Int,
    val genres: String,
    val duration: String,
    val rate: String,
    @SerializedName("released_at")
    val releasedAt: String,
    val language: String,
    @SerializedName("user_score")
    val userScore: String,
    val tagline: String,
    val synopsis: String,
    val director: String
) : Parcelable {

    val rating: Float
        get() = userScore.replace("%", "").toInt().let { score ->
            return when {
                score <= 10 -> 1f
                score <= 20 -> 1.5f
                score <= 30 -> 2f
                score <= 40 -> 2.5f
                score <= 50 -> 3f
                score <= 60 -> 3.4f
                score <= 70 -> 3.8f
                score <= 80 -> 4f
                score <= 90 -> 4.5f
                score <= 100 -> 5f
                else -> 0f
            }
        }
}
