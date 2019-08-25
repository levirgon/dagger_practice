package com.levirgon.daggerandroidpractice.ui.auth

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.RequestManager
import com.levirgon.daggerandroidpractice.R
import com.levirgon.daggerandroidpractice.utils.AuthResource
import com.levirgon.daggerandroidpractice.utils.getViewModel
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_auth.*
import org.jetbrains.anko.toast
import javax.inject.Inject

class AuthActivity : DaggerAppCompatActivity() {


    @Inject
    lateinit var logo: Drawable
    @Inject
    lateinit var imageLoader: RequestManager
    @Inject
    lateinit var providerFactory: ViewModelProvider.Factory

    private val viewModel by lazy {
        getViewModel<AuthViewModel>(providerFactory)
    }

    val Tag = "AuthActivity"

    val clas = AuthViewModel::class.java


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        setLogo()
        subscribeObserver()
    }

    private fun subscribeObserver() {
        viewModel.observeAuthState().observe(this, Observer { response ->
            when (response) {
                is AuthResource.Authenticated -> {
                    showProgressbar(false)
                    toast("success")
                }
                is AuthResource.Error -> {
                    showProgressbar(false)
                    toast("error")
                }
                is AuthResource.Loading -> {
                    showProgressbar(true)
                }
            }
        })
    }

    private fun showProgressbar(yes: Boolean) {
        if (yes) {
            progress_bar.isIndeterminate = true
            progress_bar.visibility = View.VISIBLE
        } else {
            progress_bar.visibility = View.GONE
        }

    }

    private fun setLogo() {
        imageLoader.load(logo).into(login_logo)
    }


}
