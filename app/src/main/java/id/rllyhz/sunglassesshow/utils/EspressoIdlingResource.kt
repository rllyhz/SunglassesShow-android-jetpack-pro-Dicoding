package id.rllyhz.sunglassesshow.utils

import androidx.test.espresso.idling.CountingIdlingResource

object EspressoIdlingResource {
    private const val RESOURCE = "GLOBAL"
    val espressoIdlingResource = CountingIdlingResource(RESOURCE)

    fun increment() {
        espressoIdlingResource.increment()
    }

    fun decrement() {
        espressoIdlingResource.decrement()
    }
}