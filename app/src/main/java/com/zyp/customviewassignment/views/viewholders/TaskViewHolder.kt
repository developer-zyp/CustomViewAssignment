package com.zyp.customviewassignment.views.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.zyp.customviewassignment.databinding.ViewItemAddTaskBinding
import com.zyp.customviewassignment.databinding.ViewItemTaskBinding
import com.zyp.customviewassignment.delegates.TaskItemDelegate

class TaskViewHolder(itemView: View, mDelegate: TaskItemDelegate) :
    RecyclerView.ViewHolder(itemView) {

    private lateinit var binding: ViewItemTaskBinding

    init {
        binding = ViewItemTaskBinding.bind(itemView)
        binding.imgTaskProfile.setOnClickListener {
            mDelegate.onTapTaskProfile()
        }
        itemView.setOnClickListener {
            mDelegate.onTapTaskItem()
        }

    }

    fun bindData(position: Int) {

    }

}