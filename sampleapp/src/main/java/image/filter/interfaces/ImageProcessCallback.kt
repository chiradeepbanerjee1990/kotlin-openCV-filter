package image.filter.interfaces

import android.graphics.Bitmap

interface ImageProcessCallback {

    fun callBack(destBitmap: Bitmap)
}