package com.zyp.customviewassignment.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zyp.customviewassignment.R
import com.zyp.customviewassignment.delegates.ProfileItemDelegate
import com.zyp.customviewassignment.views.viewholders.ProfileViewHolder

class ProfileAdapter(private val mDelegate: ProfileItemDelegate) :
    RecyclerView.Adapter<ProfileViewHolder>() {

    val VIEW_TYPE_IMAGE = 1
    val VIEW_TYPE_ADD_TASK = 2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        when (viewType) {
            VIEW_TYPE_ADD_TASK -> {
                val view =
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.view_item_add_task, parent, false)
                return ProfileViewHolder(view)
            }
            else -> {
                val view =
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.view_item_profile, parent, false)
                return ProfileViewHolder(view)
            }
        }

    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            if (position == itemCount - 1) {
                mDelegate.onTapNewTask()
            } else {
                mDelegate.onTapProfile()
            }
        }
    }

    override fun getItemCount(): Int {
        return 4
    }

    override fun getItemViewType(position: Int): Int {
        return when {
            position == itemCount - 1 -> {
                VIEW_TYPE_ADD_TASK
            }
            else -> {
                VIEW_TYPE_IMAGE
            }
        }
    }
}