package com.mushafimad.library.ui.mushaf

import android.content.Context
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.mushafimad.library.domain.models.Verse
import com.mushafimad.library.ui.theme.readingTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStream

/**
 * Displays a single Quran line image loaded from assets
 * Matches iOS QuranLineImageView implementation
 *
 * Images are stored in assets/quran-images/{page}/{line}.png
 * Each page has 15 lines (0-14)
 */
@Composable
fun QuranLineImageView(
    page: Int,
    line: Int,
    containerWidth: Float,
    scaledImageHeight: Float,
    verses: List<Verse>,
    selectedVerse: Verse? = null,
    highlightedVerse: Verse? = null,
    onVerseClick: ((Verse) -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val readingTheme = MaterialTheme.readingTheme

    var imageBitmap by remember(page, line) { mutableStateOf<androidx.compose.ui.graphics.ImageBitmap?>(null) }

    // Load image from assets
    LaunchedEffect(page, line) {
        imageBitmap = loadLineImage(context, page, line)
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(scaledImageHeight.dp)
            .clickable(enabled = onVerseClick != null) {
                // Find verse on this line and invoke callback
                verses.firstOrNull()?.let { onVerseClick?.invoke(it) }
            }
    ) {
        imageBitmap?.let { bitmap ->
            Image(
                bitmap = bitmap,
                contentDescription = "Quran page $page line $line",
                contentScale = ContentScale.Fit,
                colorFilter = ColorFilter.tint(readingTheme.textColor),
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

/**
 * Load line image from assets
 * Path: assets/quran-images/{page}/{line}.png
 */
private suspend fun loadLineImage(
    context: Context,
    page: Int,
    line: Int
): androidx.compose.ui.graphics.ImageBitmap? = withContext(Dispatchers.IO) {
    try {
        val assetPath = "quran-images/$page/$line.png"
        val inputStream: InputStream = context.assets.open(assetPath)
        val bitmap = BitmapFactory.decodeStream(inputStream)
        inputStream.close()
        bitmap?.asImageBitmap()
    } catch (e: Exception) {
        println("QuranLineImageView: Failed to load image for page $page line $line: ${e.message}")
        null
    }
}
