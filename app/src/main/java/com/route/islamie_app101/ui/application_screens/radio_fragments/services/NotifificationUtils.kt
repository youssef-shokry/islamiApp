package com.route.islamie_app101.ui.application_screens.radio_fragments.services

import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.media3.common.MediaMetadata
import java.io.ByteArrayOutputStream
import androidx.core.graphics.toColorInt
import androidx.core.graphics.createBitmap

fun createThemedMetadata(title: String): MediaMetadata {
    val themeColor = "#E2BE7F".toColorInt()
    val themedBitmap = createBitmap(800, 800)
    val canvas = Canvas(themedBitmap)
    canvas.drawColor(themeColor)

    return MediaMetadata.Builder()
        .setTitle(title)
        .setArtworkData(
            bitmapToByteArray(themedBitmap),
            MediaMetadata.PICTURE_TYPE_FRONT_COVER
        )
        .setDisplayTitle(title)
        .build()
}

fun bitmapToByteArray(bitmap: Bitmap): ByteArray {
    val stream = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
    return stream.toByteArray()
}