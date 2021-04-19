package id.rllyhz.sunglassesshow.data

data class Movie(
    val id: Int,
    val title: String,
    val posterUrl: String,
    val year: Int,
    val releashedAt: String,
    var duration: String,
    val rate: String,
    val genres: String,
    val languages: String,
    val synopsis: String,
    val director: String,
    val userScore: String
)
