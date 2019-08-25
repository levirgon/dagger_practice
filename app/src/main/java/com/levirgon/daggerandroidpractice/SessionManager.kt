package com.levirgon.daggerandroidpractice

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import com.levirgon.daggerandroidpractice.model.User
import com.levirgon.daggerandroidpractice.utils.AuthResource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionManager @Inject constructor() {

    private val cachedUser = MediatorLiveData<AuthResource<User>>()

    fun authenticateWithId(source: LiveData<AuthResource<User>>) {

        cachedUser.value = AuthResource.Loading()
        cachedUser.addSource(source) {
            cachedUser.value = it
            cachedUser.removeSource(source)
        }
    }

    fun logout(){
        cachedUser.value = AuthResource.Logout()
    }

    fun getAuthUser(): LiveData<AuthResource<User>> {
        return cachedUser
    }


}