package com.zyp.customviewassignment.mvp.presenters

import com.zyp.customviewassignment.delegates.ProfileItemDelegate
import com.zyp.customviewassignment.delegates.TaskItemDelegate
import com.zyp.customviewassignment.mvp.views.MainView

interface MainPresenter : BasePresenter, ProfileItemDelegate, TaskItemDelegate {
    fun initView(view: MainView)
}