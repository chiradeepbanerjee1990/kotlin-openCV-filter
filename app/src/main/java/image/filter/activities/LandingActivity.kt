package image.filter.activities

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.ImageView
import com.deep.`kotlin-opencv-filters`.interfaces.openCVcallback
import com.deep.`kotlin-opencv-filters`.processimage.ProcessImage
import image.filter.R
import image.filter.adapter.FiltersAdapter
import image.filter.datamodel.Filters
import image.filter.interfaces.ImageProcessCallback


class LandingActivity : AppCompatActivity(),ImageProcessCallback,openCVcallback {
    override fun callBack(destBitmap: Bitmap) {
        if(iv!=null){
            iv.setImageBitmap(destBitmap)
        }
    }

    override fun openCVcallbackMethod() {
        openCvInitalized(processImage)
    }

    lateinit var iv: ImageView
    lateinit var processImage: ProcessImage
    lateinit var filterView:RecyclerView
    lateinit var filterModel:Filters

    val filterList:ArrayList<Filters> = ArrayList<Filters>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       processImage = ProcessImage.getInstance(this)

        iv = findViewById(R.id.sample_image)
        filterView = findViewById(R.id.filtersView)

    }

    fun openCvInitalized(processImage: ProcessImage){
        iv.setImageDrawable(resources.getDrawable(R.drawable.sample))
        val myBitmapDrawable = iv.drawable as BitmapDrawable
        val toProcessBitmap = myBitmapDrawable.bitmap
        initializeList(toProcessBitmap)
        filterView.adapter = FiltersAdapter(filterList as List<Filters>,this, this)
        filterView.layoutManager = LinearLayoutManager(this,RecyclerView.HORIZONTAL,false)
    }


    fun initializeList(toProcessBitmap: Bitmap){
        val filterNames = arrayOf("Normal","BlackandWhite","Blur","Gaussian Blur","Scaled Image")
        for (i in 0..4){
           when(i){
                1 -> filterList.add(Filters(filterNames[i],ProcessImage.getInstance(this).makeImageBlackAndWhite(toProcessBitmap)))
                2-> filterList.add(Filters(filterNames[i],ProcessImage.getInstance(this).makeImageBlurry(toProcessBitmap)))
                3-> filterList.add(Filters(filterNames[i],ProcessImage.getInstance(this).makeImageSharpGaussian(toProcessBitmap,30.00)))
                4-> filterList.add(Filters(filterNames[i],toProcessBitmap))
               else ->{
                   filterList.add(Filters(filterNames[i],toProcessBitmap))
               }
            }

        }
    }

}
