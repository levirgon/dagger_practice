package com.levirgon.daggerandroidpractice.di.viewmodel_di

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class ViewModelFactoryModule {

    /**
     * if the dependency doesn't need any extra configuration
     * we should use @Binds instead of @Provides
     */
    @Singleton
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelProviderFactory): ViewModelProvider.Factory

}