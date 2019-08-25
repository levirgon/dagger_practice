package com.levirgon.daggerandroidpractice.di

import android.app.Application
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.levirgon.daggerandroidpractice.R
import com.levirgon.daggerandroidpractice.utils.Constants
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * application level instances
 */
@Module
object AppModule {

    @Singleton
    @Provides
    @JvmStatic
    fun providesRequestOptions(): RequestOptions {
        return RequestOptions.placeholderOf(R.drawable.white_background)
            .error(R.drawable.white_background)
    }

    @Singleton
    @Provides
    @JvmStatic
    fun providesGlideManager(application: Application, requestOptions: RequestOptions): RequestManager {
        return Glide.with(application).setDefaultRequestOptions(requestOptions)
    }

    @Singleton
    @Provides
    @JvmStatic
    fun provideAppDrawable(application: Application): Drawable {
        return ContextCompat.getDrawable(application, R.drawable.logo)!!
    }

    @Singleton
    @Provides
    @JvmStatic
    fun provideRetrofitInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL).addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create()).build()
    }


}