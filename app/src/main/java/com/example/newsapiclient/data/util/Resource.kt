package com.example.newsapiclient.data.util

/** considering state is very useful for error handling an more interactive experience
to the user
 */

sealed class Resource<T>(
    val data: T? = null,
    val message : String? = null
){
    class Success<T>(data: T):Resource<T>(data)
    class Loading<T>(data: T? = null):Resource<T>(data)
    class Error<T>(message: String, data: T? = null):Resource<T>(data)

}
