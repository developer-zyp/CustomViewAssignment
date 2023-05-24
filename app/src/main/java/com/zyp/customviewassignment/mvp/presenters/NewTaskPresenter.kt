package com.zyp.customviewassignment.mvp.presenters

import com.zyp.customviewassignment.delegates.ProfileItemDelegate
import com.zyp.customviewassignment.delegates.TaskItemDelegate
import com.zyp.customviewassignment.mvp.views.MainView
import com.zyp.customviewassignment.mvp.views.NewTaskView

interface NewTaskPresenter : BasePresenter, ProfileItemDelegate {
    fun initView(view: NewTaskView)
    fun createNewTask()
}