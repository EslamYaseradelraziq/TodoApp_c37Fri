package com.example.todoapp.ui.home.AddTask

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.todoapp.R
import com.example.todoapp.dataBase.model.MyDataBase
import com.example.todoapp.dataBase.model.Task
import com.example.todoapp.databinding.FragmentAddtaskBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.*

class AddTaskBottomSheet : BottomSheetDialogFragment() {

    lateinit var viewBinding: FragmentAddtaskBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentAddtaskBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    var OnDismissListener: OnDismissListener? = null
    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        OnDismissListener?.onDismiss()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setdate()

        viewBinding.taskDate.setOnClickListener {
            showDatePicker()
        }
        viewBinding.Submit.setOnClickListener {
            addTask()
        }

    }


    fun validate(): Boolean {
        var valide = true
        val title = viewBinding.taskTitle.editText?.text.toString()
        val desc = viewBinding.taskDesc.editText?.text.toString()
        if (title.isNullOrBlank()) {
            viewBinding.taskTitle.error = "please enter title"
            valide = false
        } else
            viewBinding.taskTitle.error = null
        if (desc.isNullOrBlank()) {
            viewBinding.taskDesc.error = "please enter description"
            valide = false
        } else
            viewBinding.taskDesc.error = null
        return valide
    }

    fun addTask() {
        if (validate() == false) {
            return
        }
        val title = viewBinding.taskTitle.editText?.text.toString()
        val desc = viewBinding.taskDesc.editText?.text.toString()
        MyDataBase.getDataBase(requireActivity())
            .taskDao()
            .insertTask(
                Task(
                    title = title,
                    description = desc,
                    date = currentDate.timeInMillis
                )
            )
        showTaskInsertedDialog()
    }

    fun showTaskInsertedDialog() {
        val alertDialogBuilder = AlertDialog.Builder(activity)
            .setMessage(getString(R.string.TaskInsertedSuccessfully))
            .setPositiveButton(
                R.string.ok, { dialog, btnId ->
                    dialog.dismiss()
                    dismiss()

                }

            ).setCancelable(false)
        alertDialogBuilder.show()
    }


    fun setdate() {
        viewBinding.taskDateTitle.text = ("" + currentDate.get(Calendar.DAY_OF_MONTH) +
                "/" + currentDate.get(Calendar.MONTH) + 1 + "/"
                + currentDate.get(Calendar.YEAR))
    }

    var currentDate = Calendar.getInstance()
    fun showDatePicker() {

        val datePicker = DatePickerDialog(
            requireActivity(), { datePicker, year, month, day ->
                currentDate.set(Calendar.YEAR, year)
                currentDate.set(Calendar.MONTH, month)
                currentDate.set(Calendar.DAY_OF_MONTH, day)

                setdate()
            },
            currentDate.get(Calendar.YEAR),
            currentDate.get(Calendar.MONTH),
            currentDate.get(Calendar.DAY_OF_MONTH)
        )
        datePicker.show()
    }
}