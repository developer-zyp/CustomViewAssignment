package com.zyp.customviewassignment.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.zyp.customviewassignment.adapters.ProfileAdapter
import com.zyp.customviewassignment.adapters.TaskAdapter
import com.zyp.customviewassignment.databinding.ActivityProfileBinding
import com.zyp.customviewassignment.mvp.presenters.MainPresenterImpl
import com.zyp.customviewassignment.mvp.presenters.ProfilePresenterImpl
import com.zyp.customviewassignment.mvp.views.ProfileView
import com.zyp.customviewassignment.views.viewpods.ProfileViewPod

class ProfileActivity : AppCompatActivity(), ProfileView {

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, ProfileActivity::class.java)
        }
    }

    //Presenter
    private lateinit var mPresenter: ProfilePresenterImpl
    private lateinit var viewPodProfile: ProfileViewPod
    private lateinit var taskAdapter: TaskAdapter

    private lateinit var binding: ActivityProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewModel()
        setupViewPod()
        setupTabLayout()
        setupRecyclerView()
        setupListener()

    }

    private fun setupViewModel() {
        mPresenter = ViewModelProvider(this)[ProfilePresenterImpl::class.java]
        mPresenter.initView(this)
    }

    private fun setupListener() {
        binding.btnBack.setOnClickListener {
            super.onBackPressed()
        }
    }

    private fun setupRecyclerView() {
        taskAdapter = TaskAdapter(mPresenter)
        binding.rcvTasks.layoutManager = GridLayoutManager(this, 1)
        binding.rcvTasks.adapter = taskAdapter
    }

    private fun setupTabLayout() {
        binding.tabProfile.newTab().apply {
            text = "Project Tasks"
            binding.tabProfile.addTab(this)
        }
        binding.tabProfile.newTab().apply {
            text = "Contacts"
            binding.tabProfile.addTab(this)
        }
        binding.tabProfile.newTab().apply {
            text = "About you"
            binding.tabProfile.addTab(this)
        }
    }

    private fun setupViewPod() {
        viewPodProfile = binding.vpProfile.root
        viewPodProfile.setDelegate(mPresenter)
    }

    override fun navigateToProfile(userId: Int) {
        startActivity(newIntent(this))
    }

    override fun showMessage(message: String) {
        showSnackBar(message)
    }

    private fun showSnackBar(message: String) {
        Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG).show()
    }
}