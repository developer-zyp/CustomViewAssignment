package com.zyp.customviewassignment.activities

import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.zyp.customviewassignment.adapters.ProfileAdapter
import com.zyp.customviewassignment.adapters.TaskAdapter
import com.zyp.customviewassignment.databinding.ActivityMainBinding
import com.zyp.customviewassignment.mvp.presenters.MainPresenterImpl
import com.zyp.customviewassignment.mvp.views.MainView

class MainActivity : AppCompatActivity(), MainView {

    private lateinit var profileAdapter: ProfileAdapter
    private lateinit var taskAdapter: TaskAdapter

    //Presenter
    private lateinit var mPresenter: MainPresenterImpl

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewModel()
        setupRecyclerView()
    }

    private fun setupViewModel() {
        mPresenter = ViewModelProvider(this)[MainPresenterImpl::class.java]
        mPresenter.initView(this)
    }

    private fun setupRecyclerView() {
        profileAdapter = ProfileAdapter(mPresenter)
        binding.rcvProfile.addItemDecoration(object : RecyclerView.ItemDecoration() {
            private val overlapValue = -50
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                super.getItemOffsets(outRect, view, parent, state)
                val position = parent.getChildAdapterPosition(view)
                if (position != 0) outRect.set(overlapValue, 0, 0, 0)

            }
        })
        binding.rcvProfile.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rcvProfile.adapter = profileAdapter


        taskAdapter = TaskAdapter(mPresenter)
        binding.rcvTasks.layoutManager = GridLayoutManager(this, 1)
        binding.rcvTasks.adapter = taskAdapter

    }

    override fun navigateToProfile(userId: Int) {
        startActivity(ProfileActivity.newIntent(this))
    }

    override fun navigateToNewTask() {
        startActivity(NewTaskActivity.newIntent(this))
    }

    override fun showMessage(message: String) {
        showSnackBar(message)
    }

    private fun showSnackBar(message: String) {
        Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG).show()
    }

}