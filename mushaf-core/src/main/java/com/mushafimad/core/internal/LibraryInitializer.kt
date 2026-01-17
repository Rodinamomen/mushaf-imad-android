package com.mushafimad.core.internal

import com.mushafimad.core.data.audio.ReciterService
import com.mushafimad.core.data.repository.RealmService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Internal class responsible for initializing critical library services
 * This ensures services like Realm and ReciterService are initialized early, avoiding race conditions
 */
@Singleton
internal class LibraryInitializer @Inject constructor(
    private val realmService: RealmService,
    private val reciterService: ReciterService
) {
    private val initScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    /**
     * Eagerly initialize all critical services
     * Called from MushafLibrary.initialize()
     */
    fun initialize() {
        // Access services to trigger their creation and initialization
        // - RealmServiceImpl's init block will start background Realm initialization
        // - ReciterService's init block will start loading reciters from JSON
        initScope.launch {
            // Wait for Realm to be initialized
            realmService.initialize()
            // ReciterService starts loading automatically in its init block
            // No explicit call needed as it loads asynchronously
        }
    }
}
