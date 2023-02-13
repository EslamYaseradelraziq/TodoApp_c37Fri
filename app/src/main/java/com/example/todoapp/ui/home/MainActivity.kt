package com.example.todoapp.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.todoapp.R
import com.example.todoapp.databinding.ActivityMainBinding
import com.example.todoapp.ui.home.AddTask.AddTaskBottomSheet
import com.example.todoapp.ui.home.AddTask.OnDismissListener
import com.example.todoapp.ui.home.list.TasksListFragment
import com.example.todoapp.ui.home.settings.SettingsFargment

class MainActivity : AppCompatActivity() {

    lateinit var viewbinding: ActivityMainBinding
    val tasksListFragment = TasksListFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewbinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewbinding.root)
        viewbinding.bottomNav
            .setOnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.nav_tasks_list -> {
                        viewbinding.screenTitle.setText(R.string.title_tasks_List)
                        showFragment(tasksListFragment)
                    }
                    R.id.nav_settings -> {
                        viewbinding.screenTitle.setText(R.string.setting_tasks_list)
                        showFragment(SettingsFargment())

                    }
                }

                return@setOnItemSelectedListener true
            }
        viewbinding.bottomNav.selectedItemId = R.id.nav_tasks_list
        viewbinding.floatingAddTask.setOnClickListener {
            showAddTaskBottomSheet()
        }
    }

    fun showAddTaskBottomSheet() {
        val addTaskBottomSheet = AddTaskBottomSheet()
        addTaskBottomSheet.OnDismissListener = OnDismissListener {
            tasksListFragment.loadTasks()
        }
        addTaskBottomSheet.show(supportFragmentManager, null)


    }

    fun showFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

}