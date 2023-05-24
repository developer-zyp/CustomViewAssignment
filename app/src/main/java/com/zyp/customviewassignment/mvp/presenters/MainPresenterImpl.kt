package com.zyp.customviewassignment.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.zyp.customviewassignment.mvp.views.MainView

class MainPresenterImpl : ViewModel(), MainPresenter {

    //Model


    //View
    private var mView: MainView? = null

    //States

    override fun initView(view: MainView) {
        mView = view
    }

    override fun onUIReady(owner: LifecycleOwner) {

    }

    override fun onTapProfile() {
        mView?.navigateToProfile(1)
    }

    override fun onTapNewTask() {
        mView?.navigateToNewTask()
    }

    override fun onTapTaskItem() {
        mView?.showMessage("onTapTaskItem")
    }

    override fun onTapTaskProfile() {
        mView?.navigateToProfile(1)
    }

}