/*
 * Message.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.topmovies.mobile.utils.ui

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText

fun Context.toast(message: String?) =
    message?.takeIf { it.isNotBlank() }?.let {
        Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
    }

fun TextInputEditText.onTextChanged(
    change: () -> Unit,
) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            change()
        }
        override fun afterTextChanged(s: Editable?) {}
    })
}

