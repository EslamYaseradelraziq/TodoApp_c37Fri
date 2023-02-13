package com.example.todoapp.dataBase.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todoapp.dataBase.model.dao.TasksDao

@Database(entities = [Task::class], version = 1, exportSchema = false)
abstract class MyDataBase : RoomDatabase() {

    abstract fun taskDao(): TasksDao

    companion object {
        private var myDataBase: MyDataBase? = null
        private val dataBaseName = "RouteTasksDataBase"
        fun getDataBase(context: Context): MyDataBase {
            if (myDataBase == null) {
                myDataBase = Room.databaseBuilder(
                    context,
                    MyDataBase::class.java,
                    dataBaseName
                ).allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()

            }
            return myDataBase!!

        }
    }
}