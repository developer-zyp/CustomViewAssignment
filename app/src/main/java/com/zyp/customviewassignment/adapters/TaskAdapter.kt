package com.zyp.customviewassignment.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zyp.customviewassignment.R
import com.zyp.customviewassignment.delegates.TaskItemDelegate
import com.zyp.customviewassignment.views.viewholders.TaskViewHolder

class TaskAdapter(private val mDelegate: TaskItemDelegate) :
    RecyclerView.Adapter<TaskViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.view_item_task, parent, false)
        return TaskViewHolder(view, mDelegate)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bindData(position)
    }

    override fun getItemCount(): Int {
        return 5
    }
}