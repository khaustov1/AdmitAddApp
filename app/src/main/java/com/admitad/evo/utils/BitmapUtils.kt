package com.admitad.evo.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.net.HttpURLConnection
import java.net.URL


object BitmapUtils {

    fun getImageFromURL(imageUrl: String): Bitmap? {
        var image: Bitmap? = null

        try {
            val url = URL(imageUrl)

            var connection = url.openConnection() as HttpURLConnection

            var stream = connection.inputStream

            BitmapFactory.decodeStream(stream, null, null)

            connection = url.openConnection() as HttpURLConnection

            stream = connection.inputStream

            image = BitmapFactory.decodeStream(stream, null, null)

            return image
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return image
    }
}