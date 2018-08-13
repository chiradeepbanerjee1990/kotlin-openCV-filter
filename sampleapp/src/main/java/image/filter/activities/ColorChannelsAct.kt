package image.filter.activities

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import android.widget.SeekBar
import com.deep.`kotlin-opencv-filters`.processimage.ProcessImage
import image.filter.R

class ColorChannelsAct : AppCompatActivity(), SeekBar.OnSeekBarChangeListener {

    lateinit var redSeekbar: SeekBar
    lateinit var greenSeekbar: SeekBar
    lateinit var blueSeekbar: SeekBar

    lateinit var imageview: ImageView
    lateinit  var selectedImage: Bitmap

    override fun onProgressChanged(p0: SeekBar, p1: Int, p2: Boolean) {

        when (p0.id) {
            R.id.redseekbar -> {
                if(selectedImage!= null) {
                    var progress:Int = redSeekbar.progress;
                    val mergedImage: Bitmap = ProcessImage.getInstance(this).changeRedIntensity(selectedImage, progress)
                    imageview.setImageBitmap(mergedImage)

                }
            }

            R.id.greenseekbar -> {
                if(selectedImage!= null) {
                    var progress:Int = redSeekbar.progress;
                    val mergedImage: Bitmap = ProcessImage.getInstance(this).changeGreenIntensity(selectedImage, progress)
                    imageview.setImageBitmap(mergedImage)

                }
            }
            R.id.blueseekbar -> {
                if(selectedImage!= null) {
                    var progress:Int = redSeekbar.progress;
                    val mergedImage: Bitmap = ProcessImage.getInstance(this).changeBlueIntensity(selectedImage, progress)
                    imageview.setImageBitmap(mergedImage)

                }
            }
        }
    }

    override fun onStartTrackingTouch(p0: SeekBar) {

    }

    override fun onStopTrackingTouch(p0: SeekBar) {

    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.colorchannels)

        redSeekbar = findViewById(R.id.redseekbar)
        greenSeekbar = findViewById(R.id.greenseekbar)
        blueSeekbar = findViewById(R.id.blueseekbar)

        imageview = findViewById(R.id.imageView)


        imageview.setOnClickListener(View.OnClickListener {

            var intent: Intent = Intent(Intent.ACTION_PICK)
            intent.setType("image/*")
            startActivityForResult(intent, 101)
        })


        redSeekbar.setOnSeekBarChangeListener(this)
        blueSeekbar.setOnSeekBarChangeListener(this)
        greenSeekbar.setOnSeekBarChangeListener(this)

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, returnedData: Intent?) {
        super.onActivityResult(requestCode, resultCode, returnedData)
        when (requestCode) {
            101 -> {
               selectedImage = BitmapFactory.decodeStream(
                        contentResolver.openInputStream(returnedData!!.data))

                selectedImage = resizeBitamp(selectedImage, 500)
                imageview.setImageBitmap(selectedImage)
            }
        }

    }


    fun resizeBitamp(image: Bitmap, maxSize: Int): Bitmap {
        var width: Float = image.width.toFloat()
        var height: Float = image.height.toFloat()

        var bitmapRatio: Float = (width / height)
        if (bitmapRatio > 1) {
            width = maxSize.toFloat()
            height = (width / bitmapRatio)
        } else {
            height = maxSize.toFloat()
            width = (height * bitmapRatio)
        }

        return Bitmap.createScaledBitmap(image, Math.round(width), Math.round(height), true)
    }
}