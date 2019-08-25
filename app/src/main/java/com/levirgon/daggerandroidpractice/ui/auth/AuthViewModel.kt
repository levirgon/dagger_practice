package com.levirgon.daggerandroidpractice.ui.auth

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.levirgon.daggerandroidpractice.SessionManager
import com.levirgon.daggerandroidpractice.model.User
import com.levirgon.daggerandroidpractice.network.AuthApi
import com.levirgon.daggerandroidpractice.utils.AuthResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.Exception

class AuthViewModel @Inject constructor(private val api: AuthApi, private val sessionManager: SessionManager) :
    ViewModel() {

    private val TAG = "AuthViewModel"


    init {
        Log.e(TAG, "IT IS WORKING with $api")

        authenticateWithId()
    }


    private fun authenticateWithId() {
        queryUserId(1)
    }

    private fun queryUserId(id: Int) {

        val userStatus = MediatorLiveData<AuthResource<User>>()
        fun setValue(value: AuthResource<User>?) {
            GlobalScope.launch(Dispatchers.Main) {
                userStatus.value = value
                sessionManager.authenticateWithId(userStatus)
            }
        }

        GlobalScope.launch {
            val request = api.getUser("$id")
            try {
                val response = request.await()
                if (response.isSuccessful) {
                    Log.e(TAG, "successfully recovered" + response.body()?.email)
                    setValue(response.body()?.let { AuthResource.Authenticated(it) })
                } else {
                    Log.e(TAG, "failed to authenticate")
                    setValue(AuthResource.Error("failed to authenticate"))
                }

            } catch (e: Exception) {
                Log.e(TAG, e.localizedMessage)
                setValue(AuthResource.Error(e.localizedMessage))
            }
        }

    }

    fun observeAuthState(): LiveData<AuthResource<User>> {
        return sessionManager.getAuthUser()
    }

}