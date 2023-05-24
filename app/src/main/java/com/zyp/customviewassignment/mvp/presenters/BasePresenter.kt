package com.zyp.customviewassignment.mvp.presenters

import androidx.lifecycle.LifecycleOwner

interface BasePresenter {
    fun onUIReady(owner: LifecycleOwner)

}