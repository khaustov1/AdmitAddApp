package com.admitad.evo.data.local

import android.annotation.SuppressLint
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.ContextWrapper
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException


class LocalStorageImp(private val context: Context) : LocalStorage {

    override fun updatePicture(picture: Bitmap) = saveImageToInternalStorage(picture, IMAGE_NAME)

    override fun updateTextHeader(textHeader: String) = saveText(HEADER_KEY, textHeader)

    override fun updateTextCTA(textCTA: String) = saveText(CTA_KEY, textCTA)

    override fun updateOfferLink(offerLink: String) = saveText(OFFER_KEY, offerLink)

    override fun updateTextFooter(textFooter: String) = saveText(FOOTER_KEY, textFooter)

    override fun getPicture(): Bitmap? = loadImageFromStorage(IMAGE_NAME)

    override fun getTextHeader(): String? = loadText(HEADER_KEY)

    override fun getTextCTA(): String? = loadText(CTA_KEY)

    override fun getOfferLink(): String? = loadText(OFFER_KEY)

    override fun getTextFooter(): String? = loadText(FOOTER_KEY)

    override fun getPrintedBlocksCounter(): Int = loadInteger(PRINTED_BLOCKS_COUNTER)

    override fun incrementPrintedBlocksCounter() =
        saveInteger(PRINTED_BLOCKS_COUNTER, loadInteger(PRINTED_BLOCKS_COUNTER) + 1)

    override fun getUpdateBlockCounter(): Int = loadInteger(UPDATE_BLOCK_COUNTER)

    override fun incrementUpdateBlockCounter() =
        saveInteger(UPDATE_BLOCK_COUNTER, loadInteger(UPDATE_BLOCK_COUNTER) + 1)

    override fun getUpdateFailureCounter(): Int = loadInteger(UPDATE_FAILURE_COUNTER)

    override fun incrementUpdateFailureCounter() =
        saveInteger(UPDATE_FAILURE_COUNTER, loadInteger(UPDATE_FAILURE_COUNTER) + 1)

    private fun getImageDirectory(): File? =
        try {
            ContextWrapper(context).getDir(IMAGE_DIRECTORY, Context.MODE_PRIVATE)
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }

    private fun saveImageToInternalStorage(bitmapImage: Bitmap, imageName: String) {
        val directory = getImageDirectory()
        val filePath = File(directory, imageName)

        FileOutputStream(filePath).use {
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, it)
        }
    }

    private fun loadImageFromStorage(imageName: String): Bitmap? =
        try {
            val file = File(getImageDirectory(), imageName)
            BitmapFactory.decodeStream(FileInputStream(file))
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }

    override fun updatePlacement(placement: Int) = saveInteger(BLOCK_PLACEMENT, placement)

    override fun getPlacement(): Int = loadInteger(BLOCK_PLACEMENT)


    @SuppressLint("ApplySharedPref")
    private fun saveInteger(key: String, value: Int) {
        val sPref = context.getSharedPreferences(PREF_NAME, MODE_PRIVATE)
        val ed = sPref.edit()
        ed.putInt(key, value)
        ed.commit()
    }

    private fun loadInteger(key: String): Int {
        val sPref = context.getSharedPreferences(PREF_NAME, MODE_PRIVATE)
        return sPref.getInt(key, -1)
    }

    @SuppressLint("ApplySharedPref")
    private fun saveText(key: String, value: String) {
        val sPref = context.getSharedPreferences(PREF_NAME, MODE_PRIVATE)
        val ed = sPref.edit()
        ed.putString(key, value)
        ed.commit()
    }

    private fun loadText(key: String): String? {
        val sPref = context.getSharedPreferences(PREF_NAME, MODE_PRIVATE)
        return sPref.getString(key, null)
    }

    companion object {
        const val IMAGE_DIRECTORY = "image_directory"
        const val IMAGE_NAME = "picture.png"
        const val PREF_NAME = "admitad"
        const val HEADER_KEY = "header_text"
        const val FOOTER_KEY = "footer_text"
        const val OFFER_KEY = "offer_link"
        const val CTA_KEY = "cta_text"
        const val PRINTED_BLOCKS_COUNTER = "printed_blocks"
        const val UPDATE_BLOCK_COUNTER = "update_block"
        const val UPDATE_FAILURE_COUNTER = "update_failure"
        const val BLOCK_PLACEMENT = "block_placement"
    }

}