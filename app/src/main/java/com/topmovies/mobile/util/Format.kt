/*
 * Format.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.topmovies.mobile.util

import java.util.Locale

fun Float.toOneDecimalString(): String = String.format(Locale.US, "%.1f", this)
