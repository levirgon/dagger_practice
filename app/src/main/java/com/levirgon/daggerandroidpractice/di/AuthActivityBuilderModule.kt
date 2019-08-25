package com.levirgon.daggerandroidpractice.di

import com.levirgon.daggerandroidpractice.di.auth.AuthModule
import com.levirgon.daggerandroidpractice.di.auth.AuthViewModelModule
import com.levirgon.daggerandroidpractice.ui.auth.AuthActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [AuthViewModelModule::class, AuthModule::class])
abstract class AuthActivityBuilderModule {

    /**
     * because of @ContributesAndroidInjector
     * this class needs to be abstract
     *
     */
    @ContributesAndroidInjector
    abstract fun contributesAuthActivity(): AuthActivity

}