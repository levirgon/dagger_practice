package com.levirgon.daggerandroidpractice.di

import android.app.Application
import com.levirgon.daggerandroidpractice.BaseApplication
import com.levirgon.daggerandroidpractice.SessionManager
import com.levirgon.daggerandroidpractice.di.viewmodel_di.ViewModelFactoryModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class,
        AuthActivityBuilderModule::class,
        AppModule::class,
        ViewModelFactoryModule::class]
)
interface AppComponent : AndroidInjector<BaseApplication> {

    fun sessionManager():SessionManager

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

}