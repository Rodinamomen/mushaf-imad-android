package com.mushafimad.ui.internal

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import com.mushafimad.ui.di.uiModule
import org.koin.core.context.loadKoinModules

/**
 * ContentProvider that automatically loads the UI module into Koin.
 *
 * This provider runs after mushaf-core's provider (due to dependency order),
 * ensuring Koin is already initialized before loading the UI module.
 *
 * This enables zero-configuration for consumers - they don't need to manually
 * load the UI module, it happens automatically when the app starts.
 *
 * @internal This class is not part of the public API.
 */
internal class MushafUiInitProvider : ContentProvider() {

    /**
     * Called by the Android system when the ContentProvider is created.
     * This happens after mushaf-core's ContentProvider.
     *
     * @return true if initialization successful, false otherwise
     */
    override fun onCreate(): Boolean {
        // Load UI module into existing Koin instance
        // (Koin was already started by mushaf-core's ContentProvider)
        loadKoinModules(uiModule)
        return true
    }

    // No-op implementations - this ContentProvider doesn't handle data queries
    // It exists solely for automatic module loading

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? = null

    override fun getType(uri: Uri): String? = null

    override fun insert(uri: Uri, values: ContentValues?): Uri? = null

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int = 0

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int = 0
}
