package com.zyp.customviewassignment.mvp.views


interface NewTaskView : BaseView {
    fun navigateToProfile(userId: Int)
    fun navigateToNewTask()
    fun createNewTask()
}