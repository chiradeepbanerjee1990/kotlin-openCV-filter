# Kotlin-openCV-filter

Kotlin openCV filter creates operating image manipulation easy and convinient way directly from Kotlin.

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
### Basic Library Methods

 ```kotlin
 
    makeImageBlurry(srcBitmap: Bitmap): Bitmap
    
    makeImageBlurry(srcBitmap: Bitmap, width: Double, height: Double): Bitmap
    
    makeImageBlackAndWhite(srcBitmap: Bitmap):Bitmap
    
    makeImageSharpGaussian(srcBitmap: Bitmap,sigmaX:Double):Bitmap
    
    makeImageSharpGaussian(srcBitmap: Bitmap,sigmaX:Double, sigmaY:Double):Bitmap
    
    makeScaleImage(srcBitmap: Bitmap):Bitmap

 ```
 
 ### Basic Library Method Implementation

 ```kotlin
  
    processedBitmap = ProcessImage.getInstance(this).makeImageBlackAndWhite(toProcessBitmap)
    
 ```
 
### Image Channel Intensity Filters

**Another powerful API for this library lets you increase intensity of your image's RGB or HSV value individually as the way you are used with Adobe Lightroom/Adobe Photoshop.**

SampleApp includes image manipulation using the API. Operations are very fast. Takes approx 11 sec for 2100 X 1800 pixel image.

```kotlin   
   
   ProcessImage.getInstance(context).changeRedIntensity(srcBitmap,percentage):Bitmap
   
   ProcessImage.getInstance(context).changeGreenIntensity(srcBitmap,percentage):Bitmap
   
   ProcessImage.getInstance(context).changeBlueIntensity(srcBitmap,percentage):Bitmap
   
   ProcessImage.getInstance(context).changeHueIntensity(srcBitmap,percentage):Bitmap
   
   ProcessImage.getInstance(context).changeSaturationIntensity(srcBitmap,percentage):Bitmap
   
   ProcessImage.getInstance(context).changeVignettingIntensity(srcBitmap,percentage):Bitmap

```

 





