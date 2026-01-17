package com.mushafimad.sampleapp

import android.app.Application
import com.mushafimad.core.MushafLibrary
import com.mushafimad.ui.di.uiModule
import org.koin.core.context.loadKoinModules

class SampleApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // Library auto-initializes via ContentProvider!
        // Koin is started with coreModule automatically

        // Load UI module for ViewModels
        loadKoinModules(uiModule)

        // Optional: Initialize manually with custom logger/analytics
        // MushafLibrary.initialize(
        //     context = this,
        //     logger = FirebaseMushafLogger(),
        //     analytics = FirebaseMushafAnalytics()
        // )
    }
}
