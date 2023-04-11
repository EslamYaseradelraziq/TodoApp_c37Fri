package com.example.todoapp.ui.home.list

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.todoapp.BaseFragment
import com.example.todoapp.Constant.Companion.TASK
import com.example.todoapp.EditActivity
import com.example.todoapp.dataBase.model.MyDataBase
import com.example.todoapp.dataBase.model.Task
import com.example.todoapp.databinding.FragmentListBinding
import com.prolificinteractive.materialcalendarview.CalendarDay
import java.util.*

open class TasksListFragment : BaseFragment() {

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
        Viewbinding.calendarView.setOnDateChangedListener { widget, date, selected ->
            if (selected) {
                currentDate.set(Calendar.DAY_OF_MONTH, date.day)
                currentDate.set(Calendar.MONTH, date.month - 1)
                currentDate.set(Calendar.YEAR, date.year)
                loadTasks()
            }
            Viewbinding.calendarView.selectedDate = CalendarDay.today()
            tasksAdapter.OnItemClicked = object : OnItemClicked {
                override fun OnItemClick(task: Task) {
                    showMessage("what do you want to do", "Update", { _, i ->
                        UpdateTodo(task)
                    }, "makeDone", { _, i ->
                        makeTodoDone(task)
                    })
                }

                private fun makeTodoDone(task: Task) {
                    TODO("Not yet implemented")
                }

                private fun UpdateTodo(task: Task) {

                    var intent = Intent(requireContext(), EditActivity::class.java)
                    intent.putExtra(TASK, task)
                    startActivity(intent)

                }

            }
        }
        // loadTasks()

    }

    var currentDate = Calendar.getInstance()

    init {
        currentDate.set(Calendar.HOUR, 0)
        currentDate.set(Calendar.MINUTE, 0)
        currentDate.set(Calendar.SECOND, 0)
        currentDate.set(Calendar.MILLISECOND, 0)
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
            .taskDao()
            .getTasksByDate(currentDate.timeInMillis)
        tasksAdapter.changeData(tasks)
    }
}