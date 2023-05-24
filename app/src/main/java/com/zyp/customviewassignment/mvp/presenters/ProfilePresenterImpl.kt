package com.zyp.customviewassignment.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.zyp.customviewassignment.mvp.views.MainView
import com.zyp.customviewassignment.mvp.views.ProfileView

class ProfilePresenterImpl : ViewModel(), ProfilePresenter {

    //Model


    //View
    private var mView: ProfileView? = null
    override fun initView(view: ProfileView) {
        mView = view
    }

    override fun onUIReady(owner: LifecycleOwner) {

    }

    //States

    override fun onTapTaskItem() {
        mView?.showMessage("onTapTaskItem")
    }

    override fun onTapTaskProfile() {
        mView?.navigateToProfile(1)
    }

    override fun onTapProfile() {
        mView?.showMessage("onTapCircleImageViewProfile")
    }

}