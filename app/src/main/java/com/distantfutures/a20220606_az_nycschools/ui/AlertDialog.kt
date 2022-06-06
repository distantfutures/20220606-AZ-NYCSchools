package com.distantfutures.a20220606_az_nycschools.ui

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class AlertDialog(title: String, message: String): DialogFragment() {
    var titleText = title
    var messageText = message
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder
                .setTitle(titleText)
                .setMessage(messageText)
                .setNegativeButton("Close") { _, _ ->
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}