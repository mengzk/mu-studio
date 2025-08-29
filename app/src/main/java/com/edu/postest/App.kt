package com.edu.postest

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

/**
 * Author: Meng
 * Date: 2025/08/29
 * Modify: 2025/08/29
 * Desc:
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        private lateinit var myContext: Context
        fun getContext(): Context {
            return myContext
        }
    }
}