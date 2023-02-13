package com.example.todoapp.ui.home.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.todoapp.dataBase.model.MyDataBase
import com.example.todoapp.databinding.FragmentListBinding

class TasksListFragment : Fragment() {

    lateinit var Viewbinding: FragmentListBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Viewbinding = FragmentListBinding.inflate(
            inflater,
            container,
            false
        )
        return Viewbinding.root
    }

    lateinit var tasksAdapter: TasksRecycleAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tasksAdapter = TasksRecycleAdapter(null)
        Viewbinding.tasksRecycler.adapter = tasksAdapter
        loadTasks()

    }

    override fun onResume() {
        super.onResume()
        loadTasks()
    }

    fun loadTasks() {
        if (!isResumed) {
            return
        }
        var tasks = MyDataBase.getDataBase(
            requireActivity()
        )
            .taskDao().getAllTasks()
        tasksAdapter.changeData(tasks)
    }
}