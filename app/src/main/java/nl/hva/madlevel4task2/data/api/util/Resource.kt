package nl.hva.madlevel4task2.data.api.util


/*
This sealed class holds different classes in it.
You can compare sealed classes to an enum with the addition of the ability to hold state.
In our case, if the request was successful we can hold the Numbers object,
or if there was a failure we can hold an error message and/or stack trace.
Generic type T allows us to reuse this for different types of classes.
 Comparable with a List.
 */


sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String?, data: T? = null) : Resource<T>(data, message)
    class Loading<T> : Resource<T>()
    class Empty<T> : Resource<T>()
}