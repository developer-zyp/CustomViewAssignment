package com.zyp.customviewassignment.mvp.presenters

import com.zyp.customviewassignment.delegates.ProfileItemDelegate
import com.zyp.customviewassignment.delegates.TaskItemDelegate
import com.zyp.customviewassignment.mvp.views.MainView
import com.zyp.customviewassignment.mvp.views.ProfileView
import com.zyp.customviewassignment.views.viewpods.ProfileViewPod

interface ProfilePresenter : BasePresenter, TaskItemDelegate, ProfileViewPod.Delegate {
    fun initView(view: ProfileView)
}