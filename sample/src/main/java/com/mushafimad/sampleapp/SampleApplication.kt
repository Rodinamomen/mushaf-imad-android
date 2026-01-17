package com.mushafimad.sampleapp

import android.app.Application
import com.mushafimad.core.MushafLibrary

class SampleApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // Library auto-initializes via ContentProvider!
        // Koin starts automatically and auto-discovers uiModule
        // ✨ Zero configuration required! ✨

        // Optional: Initialize manually with custom logger/analytics
        // MushafLibrary.initialize(
        //     context = this,
        //     logger = FirebaseMushafLogger(),
        //     analytics = FirebaseMushafAnalytics()
        // )
    }
}
