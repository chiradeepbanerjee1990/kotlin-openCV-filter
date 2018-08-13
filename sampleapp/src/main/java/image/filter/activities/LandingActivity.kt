package image.filter.activities

import android.content.Intent
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
    override fun callBack(destBitmap: Bitmap,isDemo:Boolean) {
        if(iv!=null && !isDemo){
            iv.setImageBitmap(destBitmap)
        }else{
            var intent: Intent = Intent(this,ColorChannelsAct::class.java)
            startActivity(intent)
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
        val filterNames = arrayOf("Normal","BlackandWhite","Blur","Gaussian Blur","Scaled Image","Increase Blue",
                "Increase Red","Increase Green","Increase Hue","Increase Saturation","Increase Vignetting","Channel Filter Demo")
        for (i in 0..11){
           when(i){
                1 -> filterList.add(Filters(filterNames[i],ProcessImage.getInstance(this).makeImageBlackAndWhite(toProcessBitmap)))
                2-> filterList.add(Filters(filterNames[i],ProcessImage.getInstance(this).makeImageBlurry(toProcessBitmap)))
                3-> filterList.add(Filters(filterNames[i],ProcessImage.getInstance(this).makeImageSharpGaussian(toProcessBitmap,30.00)))
                4-> filterList.add(Filters(filterNames[i],toProcessBitmap))
                5-> {
                    filterList.add(Filters(filterNames[i],((resources.getDrawable(R.drawable.sampletwo) as BitmapDrawable).bitmap)))
                    iv.setImageDrawable(resources.getDrawable(R.drawable.sampletwo))
                }
               6-> {
                   filterList.add(Filters(filterNames[i],((resources.getDrawable(R.drawable.red) as BitmapDrawable).bitmap)))
                   iv.setImageDrawable(resources.getDrawable(R.drawable.red))
               }
               7-> {
                   filterList.add(Filters(filterNames[i],((resources.getDrawable(R.drawable.sampletwo) as BitmapDrawable).bitmap)))
                   iv.setImageDrawable(resources.getDrawable(R.drawable.sampletwo))
               }
               8-> {
                   filterList.add(Filters(filterNames[i],((resources.getDrawable(R.drawable.red) as BitmapDrawable).bitmap)))
                   iv.setImageDrawable(resources.getDrawable(R.drawable.red))
               }
               9-> {
                   filterList.add(Filters(filterNames[i],((resources.getDrawable(R.drawable.red) as BitmapDrawable).bitmap)))
                   iv.setImageDrawable(resources.getDrawable(R.drawable.red))
               }
               10-> {
                   filterList.add(Filters(filterNames[i],((resources.getDrawable(R.drawable.red) as BitmapDrawable).bitmap)))
                   iv.setImageDrawable(resources.getDrawable(R.drawable.red))
               }

               11-> {
                  var confg:Bitmap.Config = Bitmap.Config.ARGB_8888
                   var btmap:Bitmap = Bitmap.createBitmap(400,800,confg)
                   filterList.add(Filters(filterNames[i],btmap))
                   iv.setImageBitmap(btmap)

               }
               else ->{
                   filterList.add(Filters(filterNames[i],toProcessBitmap))
               }
            }

        }
    }

}
