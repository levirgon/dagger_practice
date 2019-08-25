package com.levirgon.daggerandroidpractice.di.auth

import com.levirgon.daggerandroidpractice.network.AuthApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
object AuthModule {

    @Provides
    @JvmStatic
    fun provideAuthApi(retrofit: Retrofit): AuthApi {
       return retrofit.create(AuthApi::class.java)
    }

}