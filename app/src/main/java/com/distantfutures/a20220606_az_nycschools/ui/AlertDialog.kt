package com.distantfutures.a20220606_az_nycschools.ui

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.distantfutures.a20220606_az_nycschools.R

class AlertDialog(private val message: String): DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder
                .setTitle("School's Average SAT Scores")
                .setMessage(message)
                .setNegativeButton("Close") { _, _ ->
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}