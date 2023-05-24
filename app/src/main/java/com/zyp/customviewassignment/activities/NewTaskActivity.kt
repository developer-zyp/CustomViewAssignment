package com.zyp.customviewassignment.activities

import android.content.Context
import android.content.Intent
import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.zyp.customviewassignment.R
import com.zyp.customviewassignment.adapters.ProfileAdapter
import com.zyp.customviewassignment.databinding.ActivityNewTaskBinding
import com.zyp.customviewassignment.mvp.presenters.MainPresenterImpl
import com.zyp.customviewassignment.mvp.presenters.NewTaskPresenterImpl
import com.zyp.customviewassignment.mvp.views.NewTaskView

class NewTaskActivity : AppCompatActivity(), NewTaskView {

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, NewTaskActivity::class.java)
        }
    }

    //Presenter
    private lateinit var mPresenter: NewTaskPresenterImpl

    private lateinit var profileAdapter: ProfileAdapter

    private lateinit var binding: ActivityNewTaskBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewModel()
        setupRecyclerView()
        setupListener()

    }

    private fun setupViewModel() {
        mPresenter = ViewModelProvider(this)[NewTaskPresenterImpl::class.java]
        mPresenter.initView(this)
    }

    private fun setupListener() {
        binding.btnBack.setOnClickListener {
            super.onBackPressed()
        }
        binding.btnCreateTask.setOnClickListener {
            mPresenter.createNewTask()
        }
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
    }

    override fun navigateToProfile(userId: Int) {
        startActivity(ProfileActivity.newIntent(this))
    }

    override fun navigateToNewTask() {
        startActivity(newIntent(this))
    }

    override fun createNewTask() {
        showSnackBar("Create New Task")
    }

    override fun showMessage(message: String) {
        showSnackBar(message)
    }

    private fun showSnackBar(message: String) {
        Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG).show()
    }
}