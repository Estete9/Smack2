package com.example.smack2.Controller

import android.app.Application
import com.example.smack2.Utilities.SharedPrefs

class App : Application() {

    companion object {
        lateinit var prefs: SharedPrefs
    }

    override fun onCreate() {

        prefs = SharedPrefs(applicationContext)
        super.onCreate()
    }
}