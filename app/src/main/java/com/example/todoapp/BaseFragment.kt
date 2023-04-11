package com.example.todoapp

import android.app.ProgressDialog
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment

open class BaseFragment : Fragment() {
    var progressDialoge: ProgressDialog? = null
    fun showLoadingDialogue() {
        progressDialoge = ProgressDialog(requireContext())
        progressDialoge?.setMessage("Loading... ")
        progressDialoge?.show()
    }

    fun hideLoading() {
        progressDialoge?.dismiss()
    }

    var allertDialog: AlertDialog? = null

    fun showMessage(
        message: String,
        posActiontitle: String? = null,
        posAction: DialogInterface.OnClickListener? = null,
        negActiontitle: String? = null,
        negAction: DialogInterface.OnClickListener? = null,
        cancelable: Boolean = true,

        ) {
        var messageDialodBuilder = AlertDialog.Builder(requireContext())
        messageDialodBuilder.setMessage(message)
        if (posActiontitle != null) {
            messageDialodBuilder.setPositiveButton(posActiontitle,
                posAction ?: DialogInterface.OnClickListener { dialog, p1 ->
                    dialog?.dismiss()

                })
        }
        if (negActiontitle != null) {
            messageDialodBuilder.setNegativeButton(negActiontitle,
                negAction ?: DialogInterface.OnClickListener { dialog, i ->
                    dialog?.dismiss()

                })
        }
        messageDialodBuilder.setCancelable(cancelable)
        allertDialog = messageDialodBuilder.show()
    }
}