package com.example.todoapp.dataBase.model.dao

import androidx.room.*
import com.example.todoapp.dataBase.model.Task

@Dao
interface TasksDao {

    @Insert
    fun insertTask(task: Task)

    @Delete
    fun deleteTask(task: Task)

    @Update
    fun updateTask(task: Task)

    @Query("select * from tasks")
    fun getAllTasks(): List<Task>

    @Query("select * from tasks where date=:selectedate")
    fun getTasksByDate(selectedate: Long): List<Task>

}