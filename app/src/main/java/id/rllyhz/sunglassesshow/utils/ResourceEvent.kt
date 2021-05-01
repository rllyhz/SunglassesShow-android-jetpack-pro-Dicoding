package id.rllyhz.sunglassesshow.utils

sealed class ResourceEvent {
    class Success<ResultType>(val resultList: List<ResultType>?, val result: ResultType?) :
        ResourceEvent()

    class Failure(val message: String) : ResourceEvent()
    object Loading : ResourceEvent()
    object Empty : ResourceEvent()
}
