package com.deep.`kotlin-opencv-filters`.processimage

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import org.opencv.android.BaseLoaderCallback
import org.opencv.android.LoaderCallbackInterface
import org.opencv.android.OpenCVLoader
import org.opencv.android.Utils
import org.opencv.core.Mat
import org.opencv.core.Size
import org.opencv.imgproc.Imgproc

internal const val TAG:String = "Kotlin-OpenCV-Filter"

class ProcessImage private constructor() {

    companion object {
        private var instance : ProcessImage? = null
        private var mcontext: Context? = null
        private var mLoaderCallback:BaseLoaderCallback?=null




        fun  getInstance(context: Context): ProcessImage {
            synchronized(this) {
                if (instance == null) {
                    instance = ProcessImage()
                    mcontext = context
                    mLoaderCallback = object : BaseLoaderCallback(mcontext) {
                        override fun onManagerConnected(status: Int) {
                            when (status) {
                                LoaderCallbackInterface.SUCCESS -> {
                                    Log.i(TAG, "OpenCV loaded successfully")

                                }
                                else -> {
                                    super.onManagerConnected(status)
                                }
                            }
                        }
                    }
                    OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION, mcontext, mLoaderCallback)
                }

                return instance!!
            }
        }
    }


    fun makeImageBlurry(srcBitmap: Bitmap): Bitmap {

        val srcMat = Mat()
        Utils.bitmapToMat(srcBitmap, srcMat)
        val destMat = Mat()
        Imgproc.blur(srcMat, destMat, Size(25.0, 25.0))
        val blurryBitmap = Bitmap.createBitmap(destMat.cols(), destMat.rows(), Bitmap.Config.ARGB_8888)
        Utils.matToBitmap(destMat, blurryBitmap)
        return blurryBitmap

    }

    fun makeImageBlackAndWhite(srcBitmap: Bitmap):Bitmap{
        val blackandwhiteBitmap:Bitmap
        val srcMat = Mat()
        Utils.bitmapToMat(srcBitmap,srcMat)
        val destMat = Mat()
        Imgproc.cvtColor(srcMat,destMat,Imgproc.COLOR_RGB2BGR)
        blackandwhiteBitmap = Bitmap.createBitmap(destMat.cols(),destMat.rows(),Bitmap.Config.ARGB_8888)
        Utils.matToBitmap(destMat,blackandwhiteBitmap)



        return blackandwhiteBitmap
    }



}