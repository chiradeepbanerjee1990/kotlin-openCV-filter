# Kotlin-openCV-filter

Kotlin-openCV-filter creates operating openCV image manipulation easy and convinient way directly from Kotlin.

The Library is currently in development phase.

### Initialization

```kotlin
   lateinit var processImage: ProcessImage
   processImage = ProcessImage.getInstance(this)
   
   class MainActivity : AppCompatActivity(),openCVcallback {
   ....
   
    override fun openCVcallbackMethod() {
      //kotlin-openCV Initialized
    }
   ....
```
### Library Methods

 ```kotlin
 
    makeImageBlurry(srcBitmap: Bitmap): Bitmap
    makeImageBlurry(srcBitmap: Bitmap, width: Double, height: Double): Bitmap
    makeImageBlackAndWhite(srcBitmap: Bitmap):Bitmap
    makeImageSharpGaussian(srcBitmap: Bitmap,sigmaX:Double):Bitmap
    makeImageSharpGaussian(srcBitmap: Bitmap,sigmaX:Double, sigmaY:Double):Bitmap
    makeScaleImage(srcBitmap: Bitmap):Bitmap

 ```
 
### Sample Method Implementation

 ```kotlin
  
    processedBitmap = ProcessImage.getInstance(this).makeImageBlackAndWhite(toProcessBitmap)
    
 ```




