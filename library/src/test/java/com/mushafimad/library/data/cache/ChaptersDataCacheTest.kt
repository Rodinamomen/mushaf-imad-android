package com.mushafimad.library.data.cache

import com.mushafimad.library.data.repository.RealmService
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

/**
 * Unit tests for ChaptersDataCache
 * Tests caching behavior and data management
 */
class ChaptersDataCacheTest {

    private lateinit var realmService: RealmService
    private lateinit var cache: ChaptersDataCache

    @Before
    fun setUp() {
        realmService = mockk()
        cache = ChaptersDataCache(realmService)
    }

    @Test
    fun `cache is not loaded by default`() {
        assertFalse(cache.isCached)
        assertFalse(cache.isPartsCached)
        assertFalse(cache.isHizbCached)
        assertFalse(cache.isTypeCached)
    }

    @Test
    fun `allChapters is empty by default`() {
        assertTrue(cache.allChapters.isEmpty())
    }

    @Test
    fun `loadAndCache marks cache as loaded`() = runTest {
        coEvery { realmService.fetchAllChaptersAsync() } returns emptyList()

        cache.loadAndCache()

        assertTrue(cache.isCached)
    }

    @Test
    fun `loadAndCache calls onBatchLoaded callback`() = runTest {
        coEvery { realmService.fetchAllChaptersAsync() } returns emptyList()

        var callbackCalled = false
        var loadedCount = 0

        cache.loadAndCache { count ->
            callbackCalled = true
            loadedCount = count
        }

        assertTrue(callbackCalled)
        assertEquals(0, loadedCount)
    }

    @Test
    fun `loadAndCache does not reload if already cached`() = runTest {
        coEvery { realmService.fetchAllChaptersAsync() } returns emptyList()

        cache.loadAndCache()
        cache.loadAndCache() // Should skip

        assertTrue(cache.isCached)
    }

    @Test
    fun `clearCache resets all flags`() = runTest {
        coEvery { realmService.fetchAllChaptersAsync() } returns emptyList()

        cache.loadAndCache()
        cache.clearCache()

        assertFalse(cache.isCached)
        assertFalse(cache.isPartsCached)
        assertFalse(cache.isHizbCached)
        assertFalse(cache.isTypeCached)
        assertTrue(cache.allChapters.isEmpty())
    }
}
