package com.deep.`kotlin-opencv-filters`.histogram

import android.graphics.Bitmap
import org.opencv.android.Utils
import org.opencv.core.Core
import org.opencv.core.CvType
import org.opencv.core.Mat
import org.opencv.imgproc.Imgproc
import java.util.*

const val TAG:String = "HistoGramFilters"

class HistogramFilters() {

    class CustomException(override var message:String): Exception(message)

    var matB: Mat? = null
        get() = field
    set(value) {field = value}

    var matG:Mat? = null
        get() = field
        set(value) {field = value}

    var matR:Mat? = null
        get() = field
        set(value) {field = value}

    var matH:Mat? = null
        get() = field
        set(value) {field = value}

    var matS:Mat? = null
        get() = field
        set(value) {field = value}

    var matV:Mat? = null
        get() = field
        set(value) {field = value}

    var matA:Mat? = null
        get() = field
        set(value) {field = value}

    lateinit var srcBitmap: Bitmap

    constructor(srcBitmap: Bitmap) : this() {
        this.srcBitmap = srcBitmap

        val srcMat = Mat()
        Utils.bitmapToMat(srcBitmap, srcMat)
        var BGRAMat = Mat()
        Imgproc.cvtColor(srcMat, BGRAMat, Imgproc.COLOR_RGB2BGRA)
        populateBGRA(BGRAMat)

        var HSVMat = Mat()
        Imgproc.cvtColor(srcMat, HSVMat, Imgproc.COLOR_RGB2HSV)
        populateHSV(HSVMat)
    }

    fun populateBGRA(matBGRA: Mat) {
        var channels: ArrayList<Mat> = ArrayList<Mat>()
        Core.split(matBGRA, channels)

        this.matB = channels.get(0)
        this.matG = channels.get(1)
        this.matR = channels.get(2)
        this.matA = channels.get(3)
    }

    fun populateHSV(matHSV: Mat) {
        var channels: ArrayList<Mat> = ArrayList<Mat>()
        Core.split(matHSV, channels)
        this.matH = channels.get(0)
        this.matS = channels.get(1)
        this.matV = channels.get(2)
    }


    fun increaseBlueIntensity(percentage:Int){
        for(i in 0..matB!!.rows()-1){
            for(r in 0..matB!!.cols()-1){
                var existingConfig:DoubleArray = matB!!.get(i,r)
                if((existingConfig[0]*percentage)/100 + existingConfig[0] <= 255.00)
                {
                    existingConfig[0] = (existingConfig[0]*percentage)/100 + existingConfig[0]
                }else
                {
                    // Pixel value crosses the borderline, assign the border value
                    existingConfig[0] = 255.00
                }
                matB!!.put(i,r,existingConfig[0])
            }
        }
    }

    fun increaseRedIntensity(percentage:Int){
        for(i in 0..matR!!.rows()-1){
            for(r in 0..matR!!.cols()-1){
                var existingConfig:DoubleArray = matR!!.get(i,r)
                if((existingConfig[0]*percentage)/100 + existingConfig[0] <= 255.00)
                {
                    existingConfig[0] = (existingConfig[0]*percentage)/100 + existingConfig[0]
                }else
                {
                    // Pixel value crosses the borderline, assign the border value
                    existingConfig[0] = 255.00
                }
                matR!!.put(i,r,existingConfig[0])
            }
        }
    }

    fun increaseGreenIntensity(percentage:Int){
        for(i in 0..matG!!.rows()-1){
            for(r in 0..matG!!.cols()-1){
                var existingConfig:DoubleArray = matG!!.get(i,r)
                if((existingConfig[0]*percentage)/100 + existingConfig[0] <= 255.00)
                {
                    existingConfig[0] = (existingConfig[0]*percentage)/100 + existingConfig[0]
                }else
                {
                    // Pixel value crosses the borderline, assign the border value
                    existingConfig[0] = 255.00
                }
                matG!!.put(i,r,existingConfig[0])
            }
        }
    }

    fun increaseHueIntensity(percentage:Int){
        for(i in 0..matH!!.rows()-1){
            for(r in 0..matH!!.cols()-1){
                var existingConfig:DoubleArray = matH!!.get(i,r)
                if((existingConfig[0]*percentage)/100 + existingConfig[0] <= 255.00)
                {
                    existingConfig[0] = (existingConfig[0]*percentage)/100 + existingConfig[0]
                }else
                {
                    // Pixel value crosses the borderline, assign the border value
                    existingConfig[0] = 255.00
                }
                matH!!.put(i,r,existingConfig[0])
            }
        }
    }

    fun increaseSaturationIntensity(percentage:Int){
        for(i in 0..matS!!.rows()-1){
            for(r in 0..matS!!.cols()-1){
                var existingConfig:DoubleArray = matS!!.get(i,r)
                if((existingConfig[0]*percentage)/100 + existingConfig[0] <= 255.00)
                {
                    existingConfig[0] = (existingConfig[0]*percentage)/100 + existingConfig[0]
                }else
                {
                    // Pixel value crosses the borderline, assign the border value
                    existingConfig[0] = 255.00
                }
                matS!!.put(i,r,existingConfig[0])
            }
        }
    }

    fun increaseVignettingIntensity(percentage:Int){
        for(i in 0..matV!!.rows()-1){
            for(r in 0..matV!!.cols()-1){
                var existingConfig:DoubleArray = matV!!.get(i,r)
                if((existingConfig[0]*percentage)/100 + existingConfig[0] <= 255.00)
                {
                    existingConfig[0] = (existingConfig[0]*percentage)/100 + existingConfig[0]
                }else
                {
                    // Pixel value crosses the borderline, assign the border value
                    existingConfig[0] = 255.00
                }
                matV!!.put(i,r,existingConfig[0])
            }
        }
    }




    fun mergeResult():Bitmap{
        var destMat:Mat  = Mat()
        var listMat: MutableList<Mat?>? = Arrays.asList(matR, matG, matB)
        Core.merge(listMat,destMat)
        destMat.convertTo(destMat, CvType.CV_8UC3)
        var mergedImage:Bitmap = Bitmap.createBitmap(destMat.cols(), destMat.rows(), Bitmap.Config.ARGB_8888)
        Utils.matToBitmap(destMat, mergedImage)
        return mergedImage
    }


}