package com.example.todoapp.ui.home.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.dataBase.model.Task
import com.example.todoapp.databinding.ItemTaskBinding

class TasksRecycleAdapter(var items: List<Task>?) :
    RecyclerView.Adapter<TasksRecycleAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding = ItemTaskBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.viewBinding.title.text = items?.get(position)?.title
        holder.viewBinding.Description.text = items?.get(position)?.description


    }

    override fun getItemCount(): Int {
        return items?.size ?: 0
    }

    fun changeData(newListofTasks: List<Task>?) {
        items = newListofTasks
        notifyDataSetChanged()

    }

    class ViewHolder(val viewBinding: ItemTaskBinding) : RecyclerView.ViewHolder(viewBinding.root)
}
