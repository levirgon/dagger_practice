package com.levirgon.daggerandroidpractice.network

import com.levirgon.daggerandroidpractice.model.User
import kotlinx.coroutines.Deferred
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface AuthApi {

    @GET("users/{id}")
    fun getUser(
        @Path("id") id: String
    ): Deferred<Response<User>>

}