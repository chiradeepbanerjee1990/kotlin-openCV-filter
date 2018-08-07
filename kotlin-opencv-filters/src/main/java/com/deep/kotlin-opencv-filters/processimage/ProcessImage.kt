package com.deep.`kotlin-opencv-filters`.processimage

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import com.deep.`kotlin-opencv-filters`.interfaces.openCVcallback
import org.opencv.android.BaseLoaderCallback
import org.opencv.android.LoaderCallbackInterface
import org.opencv.android.OpenCVLoader
import org.opencv.android.Utils
import org.opencv.core.Mat
import org.opencv.core.Size
import org.opencv.imgproc.Imgproc

internal const val TAG:String = "Kotlin-OpenCV-Filter"

class ProcessImage private constructor()    {

    companion object {
        private var instance : ProcessImage? = null
        private var mcontext: Context? = null
        private var mLoaderCallback:BaseLoaderCallback?=null
        lateinit var opencvcallback:openCVcallback


        fun getInstance(context: Context): ProcessImage {
            synchronized(this) {
                if (instance == null) {
                    instance = ProcessImage()
                    mcontext = context
                    opencvcallback = mcontext as openCVcallback
                    loadOpenCV()
                }
                return instance!!
            }
        }

        fun loadOpenCV(){
            mLoaderCallback = object : BaseLoaderCallback(mcontext) {
                override fun onManagerConnected(status: Int) {
                    when (status) {
                        LoaderCallbackInterface.SUCCESS -> {
                            Log.i(TAG, "OpenCV loaded successfully")
                            opencvcallback.openCVcallbackMethod()

                        }
                        else -> {
                            super.onManagerConnected(status)
                        }
                    }
                }
            }
            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION, mcontext, mLoaderCallback)

        }
    }

    /**
     * Call this function when width and height is fixed.
     * Default value width 25.0 and height 25.0
     */
    fun makeImageBlurry(srcBitmap: Bitmap): Bitmap {
        val srcMat = Mat()
        Utils.bitmapToMat(srcBitmap, srcMat)
        val destMat = Mat()
        // Default Size parameter width 25.0 and height 25.0
        Imgproc.blur(srcMat, destMat, Size(25.0, 25.0))
        val blurryBitmap = Bitmap.createBitmap(destMat.cols(), destMat.rows(), Bitmap.Config.ARGB_8888)
        Utils.matToBitmap(destMat, blurryBitmap)
        return blurryBitmap

    }

    /**
     * Call this function when width and height is variable
     */
    fun makeImageBlurry(srcBitmap: Bitmap, width: Double, height: Double): Bitmap {
        val srcMat = Mat()
        Utils.bitmapToMat(srcBitmap, srcMat)
        val destMat = Mat()
        Imgproc.blur(srcMat, destMat, Size(width, height))
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


    fun makeImageSharpGaussian(srcBitmap: Bitmap,sigmaX:Double):Bitmap {
        val sharpBitmap:Bitmap
        val srcMat = Mat()
        Utils.bitmapToMat(srcBitmap,srcMat)
        val destMat = Mat()
        Imgproc.GaussianBlur(srcMat,destMat,Size(0.00,0.00), sigmaX)
        sharpBitmap = Bitmap.createBitmap(destMat.cols(),destMat.rows(),Bitmap.Config.ARGB_8888)
        Utils.matToBitmap(destMat,sharpBitmap)
        return sharpBitmap
    }

    fun makeImageSharpGaussian(srcBitmap: Bitmap,sigmaX:Double, sigmaY:Double):Bitmap {
        val sharpBitmap:Bitmap
        val srcMat = Mat()
        Utils.bitmapToMat(srcBitmap,srcMat)
        val destMat = Mat()
        Imgproc.GaussianBlur(srcMat,destMat,Size(0.00,0.00), sigmaX, sigmaY)
        sharpBitmap = Bitmap.createBitmap(destMat.cols(),destMat.rows(),Bitmap.Config.ARGB_8888)
        Utils.matToBitmap(destMat,sharpBitmap)
        return sharpBitmap
    }

    fun makeScaleImage(srcBitmap: Bitmap):Bitmap{
        val scaledImage:Bitmap
        val srcMat = Mat()
        Utils.bitmapToMat(srcBitmap,srcMat)
        var destMat = Mat()
        Imgproc.resize(srcMat,destMat,Size((srcBitmap.width*2)*1.00 ,(srcBitmap.height*2)*1.00))
        scaledImage = Bitmap.createBitmap(destMat.cols(),destMat.rows(),Bitmap.Config.ARGB_8888)
        Utils.matToBitmap(destMat,scaledImage)
        return scaledImage
    }



}