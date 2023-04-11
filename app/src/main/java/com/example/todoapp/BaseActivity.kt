package com.example.todoapp

import android.app.ProgressDialog
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class BaseActivity : AppCompatActivity() {
    var progressDialoge: ProgressDialog? = null
    fun showLoadingDialogue() {
        progressDialoge = ProgressDialog(this)
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
        var messageDialodBuilder = AlertDialog.Builder(this)
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