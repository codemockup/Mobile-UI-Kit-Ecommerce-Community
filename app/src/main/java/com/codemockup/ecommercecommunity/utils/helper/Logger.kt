package com.codemockup.ecommercecommunity.utils.helper

import android.util.Log

object Logger {
    private const val TAG = "MainAppLog"

    // Log an info message
    fun i(message: String, tag: String? = null) {
        Log.i(tag ?: TAG, message)
    }

    // Log a debug message
    fun d(message: String, tag: String? = null) {
        Log.d(tag ?: TAG, message)
    }

    // Log an error message
    fun e(message: String, tag: String? = null, throwable: Throwable? = null) {
        Log.e(tag ?: TAG, message, throwable)
    }

    // Log a warning message
    fun w(message: String, tag: String? = null) {
        Log.w(tag ?: TAG, message)
    }

    // Log verbose message
    fun v(message: String, tag: String? = null) {
        Log.v(tag ?: TAG, message)
    }

    // Log a custom message with level
    fun log(message: String, level: Int) {
        when (level) {
            Log.INFO -> i(message = message)
            Log.DEBUG -> d(message = message)
            Log.ERROR -> e(message = message)
            Log.WARN -> w(message = message)
            Log.VERBOSE -> v(message = message)
            else -> Log.d(TAG, message) // Default to debug if unknown level
        }
    }
}