package com.zyp.customviewassignment.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.zyp.customviewassignment.mvp.views.MainView
import com.zyp.customviewassignment.mvp.views.NewTaskView
import com.zyp.customviewassignment.mvp.views.ProfileView

class NewTaskPresenterImpl : ViewModel(), NewTaskPresenter {

    //Model


    //View
    private var mView: NewTaskView? = null

    //States

    override fun initView(view: NewTaskView) {
        mView = view
    }

    override fun createNewTask() {
        mView?.createNewTask()
    }

    override fun onUIReady(owner: LifecycleOwner) {

    }

    override fun onTapProfile() {
        mView?.navigateToProfile(1)
    }

    override fun onTapNewTask() {
        mView?.navigateToNewTask()
    }

}