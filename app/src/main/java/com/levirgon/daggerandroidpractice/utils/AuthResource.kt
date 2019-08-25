package com.levirgon.daggerandroidpractice.utils

// A generic class that contains data and status about loading this data.
sealed class AuthResource<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Authenticated<T>(data: T) : AuthResource<T>(data)
    class Loading<T>(data: T? = null) : AuthResource<T>(data)
    class Logout<T>(data: T? = null) : AuthResource<T>(data)
    class Error<T>(message: String, data: T? = null) : AuthResource<T>(data, message)
}