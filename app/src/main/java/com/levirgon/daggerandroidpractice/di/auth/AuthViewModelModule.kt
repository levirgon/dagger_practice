package com.levirgon.daggerandroidpractice.di.auth

import androidx.lifecycle.ViewModel
import com.levirgon.daggerandroidpractice.di.viewmodel_di.ViewModelKey
import com.levirgon.daggerandroidpractice.ui.auth.AuthViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AuthViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    abstract fun bindAuthViewModel(viewModel: AuthViewModel): ViewModel

}