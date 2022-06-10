package com.kudelich.mmfapp

import android.app.Application
import com.kudelich.mmfapp.util.Prefs
import dagger.hilt.android.HiltAndroidApp

val prefs: Prefs by lazy {
    BsuApplication.prefs!!
}

@HiltAndroidApp
class BsuApplication : Application() {
    companion object {
        var prefs: Prefs? = null
        lateinit var instance: BsuApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()

        instance = this
        prefs = Prefs(applicationContext)
    }
}