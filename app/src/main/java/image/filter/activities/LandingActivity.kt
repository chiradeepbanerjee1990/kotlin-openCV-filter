package image.filter.activities

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.ImageView
import com.deep.`kotlin-opencv-filters`.processimage.ProcessImage
import image.filter.R
import image.filter.adapter.FiltersAdapter
import image.filter.datamodel.Filters
import image.filter.interfaces.ImageProcessCallback


class LandingActivity : AppCompatActivity(),ImageProcessCallback {
    override fun callBack(destBitmap: Bitmap) {
        if(iv!=null){
            iv.setImageBitmap(destBitmap)
        }
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
        iv.setImageDrawable(resources.getDrawable(R.drawable.sample))

        val myBitmapDrawable = iv.drawable as BitmapDrawable
        val toProcessBitmap = myBitmapDrawable.bitmap
        initializeList(toProcessBitmap)

        filterView.layoutManager = LinearLayoutManager(this,RecyclerView.HORIZONTAL,false)
        filterView.adapter = FiltersAdapter(filterList,this, this)

    }

    fun initializeList(toProcessBitmap: Bitmap){
        val filterNames = arrayOf("normal","blackandwhite","blurry")
        for (i in 0..2){
            filterList.add(Filters(filterNames[i],toProcessBitmap))
        }
    }

}
