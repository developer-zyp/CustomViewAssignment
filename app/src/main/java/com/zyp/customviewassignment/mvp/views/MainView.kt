package com.zyp.customviewassignment.mvp.views


interface MainView : BaseView {
    fun navigateToProfile(userId: Int)
    fun navigateToNewTask()
}