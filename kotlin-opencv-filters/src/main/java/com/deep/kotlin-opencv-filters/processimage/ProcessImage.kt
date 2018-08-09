package com.deep.`kotlin-opencv-filters`.processimage

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import com.deep.`kotlin-opencv-filters`.histogram.HistogramFilters
import com.deep.`kotlin-opencv-filters`.interfaces.openCVcallback
import org.opencv.android.BaseLoaderCallback
import org.opencv.android.LoaderCallbackInterface
import org.opencv.android.OpenCVLoader
import org.opencv.android.Utils
import org.opencv.core.Mat
import org.opencv.core.Size
import org.opencv.imgproc.Imgproc

internal const val TAG: String = "Kotlin-OpenCV-Filter"

class ProcessImage private constructor() {

    companion object {
        private var instance: ProcessImage? = null
        private var mcontext: Context? = null
        private var mLoaderCallback: BaseLoaderCallback? = null
        lateinit var opencvcallback: openCVcallback


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

        fun loadOpenCV() {
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

    /***
     * Return the processed Blurry Bitmap for the given [srcBitmap]
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

    /***
     * Return the processed Blurry Bitmap for the given [srcBitmap], [width], [height]
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

     /***
     * Return the processed Black and White Bitmap for the given [srcBitmap]
     */
    fun makeImageBlackAndWhite(srcBitmap: Bitmap): Bitmap {
        val blackandwhiteBitmap: Bitmap
        val srcMat = Mat()
        Utils.bitmapToMat(srcBitmap, srcMat)
        val destMat = Mat()
        Imgproc.cvtColor(srcMat, destMat, Imgproc.COLOR_RGB2BGR)
        blackandwhiteBitmap = Bitmap.createBitmap(destMat.cols(), destMat.rows(), Bitmap.Config.ARGB_8888)
        Utils.matToBitmap(destMat, blackandwhiteBitmap)
        return blackandwhiteBitmap
    }

    /***
     * Return the processed Gaussian Blurry Bitmap for the given [srcBitmap], [sigmaX]
     */
    fun makeImageSharpGaussian(srcBitmap: Bitmap, sigmaX: Double): Bitmap {
        val sharpBitmap: Bitmap
        val srcMat = Mat()
        Utils.bitmapToMat(srcBitmap, srcMat)
        val destMat = Mat()
        Imgproc.GaussianBlur(srcMat, destMat, Size(0.00, 0.00), sigmaX)
        sharpBitmap = Bitmap.createBitmap(destMat.cols(), destMat.rows(), Bitmap.Config.ARGB_8888)
        Utils.matToBitmap(destMat, sharpBitmap)
        return sharpBitmap
    }

    /***
     * Return the processed Gaussian Blurry Bitmap for the given [srcBitmap], [sigmaX], [sigmaY]
     */
    fun makeImageSharpGaussian(srcBitmap: Bitmap, sigmaX: Double, sigmaY: Double): Bitmap {
        val sharpBitmap: Bitmap
        val srcMat = Mat()
        Utils.bitmapToMat(srcBitmap, srcMat)
        val destMat = Mat()
        Imgproc.GaussianBlur(srcMat, destMat, Size(0.00, 0.00), sigmaX, sigmaY)
        sharpBitmap = Bitmap.createBitmap(destMat.cols(), destMat.rows(), Bitmap.Config.ARGB_8888)
        Utils.matToBitmap(destMat, sharpBitmap)
        return sharpBitmap
    }

    /***
     * Return the processed Scaled Bitmap for the given [srcBitmap]
     */
    fun makeScaleImage(srcBitmap: Bitmap): Bitmap {
        val scaledImage: Bitmap
        val srcMat = Mat()
        Utils.bitmapToMat(srcBitmap, srcMat)
        var destMat = Mat()
        Imgproc.resize(srcMat, destMat, Size((srcBitmap.width * 2) * 1.00, (srcBitmap.height * 2) * 1.00))
        scaledImage = Bitmap.createBitmap(destMat.cols(), destMat.rows(), Bitmap.Config.ARGB_8888)
        Utils.matToBitmap(destMat, scaledImage)
        return scaledImage
    }

    /***
     * Return the processed Scaled Bitmap for the given [srcBitmap], [scaleFactor]
     */
    fun makeScaleImageByFactor(srcBitmap: Bitmap, scaleFactor: Int): Bitmap {
        val scaledImage: Bitmap
        val srcMat = Mat()
        Utils.bitmapToMat(srcBitmap, srcMat)
        var destMat = Mat()
        Imgproc.resize(srcMat, destMat, Size((srcBitmap.width * scaleFactor) * 1.00, (srcBitmap.height * scaleFactor) * 1.00))
        scaledImage = Bitmap.createBitmap(destMat.cols(), destMat.rows(), Bitmap.Config.ARGB_8888)
        Utils.matToBitmap(destMat, scaledImage)
        return scaledImage
    }


    fun changeBlueIntensity(srcBitmap: Bitmap, percentage:Int):Bitmap{
        Log.e(TAG,"Start the process::"+System.currentTimeMillis())
        var histManipulate:HistogramFilters = HistogramFilters(srcBitmap)
        histManipulate.increaseBlueIntensity(percentage)
        var mergedImage:Bitmap = histManipulate.mergeResult()
        Log.e(TAG,"Finish the process::"+System.currentTimeMillis())
        return mergedImage
    }

    fun changeRedIntensity(srcBitmap: Bitmap, percentage:Int):Bitmap{
        Log.e(TAG,"Start the process::"+System.currentTimeMillis())
        var histManipulate:HistogramFilters = HistogramFilters(srcBitmap)
        histManipulate.increaseRedIntensity(percentage)
        var mergedImage:Bitmap = histManipulate.mergeResult()
        Log.e(TAG,"Finish the process::"+System.currentTimeMillis())
        return mergedImage
    }

    fun changeGreenIntensity(srcBitmap: Bitmap, percentage:Int):Bitmap{
        Log.e(TAG,"Start the process::"+System.currentTimeMillis())
        var histManipulate:HistogramFilters = HistogramFilters(srcBitmap)
        histManipulate.increaseGreenIntensity(percentage)
        var mergedImage:Bitmap = histManipulate.mergeResult()
        Log.e(TAG,"Finish the process::"+System.currentTimeMillis())
        return mergedImage
    }


    fun changeHueIntensity(srcBitmap: Bitmap, percentage:Int):Bitmap{
        Log.e(TAG,"Start the process::"+System.currentTimeMillis())
        var histManipulate:HistogramFilters = HistogramFilters(srcBitmap)
        histManipulate.increaseHueIntensity(percentage)
        var mergedImage:Bitmap = histManipulate.mergeResult()
        Log.e(TAG,"Finish the process::"+System.currentTimeMillis())
        return mergedImage
    }

    fun changeSaturationIntensity(srcBitmap: Bitmap, percentage:Int):Bitmap{
        Log.e(TAG,"Start the process::"+System.currentTimeMillis())
        var histManipulate:HistogramFilters = HistogramFilters(srcBitmap)
        histManipulate.increaseSaturationIntensity(percentage)
        var mergedImage:Bitmap = histManipulate.mergeResult()
        Log.e(TAG,"Finish the process::"+System.currentTimeMillis())
        return mergedImage
    }

    fun changeVignettingIntensity(srcBitmap: Bitmap, percentage:Int):Bitmap{
        Log.e(TAG,"Start the process::"+System.currentTimeMillis())
        var histManipulate:HistogramFilters = HistogramFilters(srcBitmap)
        histManipulate.increaseVignettingIntensity(percentage)
        var mergedImage:Bitmap = histManipulate.mergeResult()
        Log.e(TAG,"Finish the process::"+System.currentTimeMillis())
        return mergedImage
    }


}