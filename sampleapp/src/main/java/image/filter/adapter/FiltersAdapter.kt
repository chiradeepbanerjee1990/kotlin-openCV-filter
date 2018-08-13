package image.filter.adapter

import android.content.Context
import android.graphics.Bitmap
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import com.deep.`kotlin-opencv-filters`.processimage.ProcessImage
import image.filter.R
import image.filter.datamodel.Filters
import image.filter.interfaces.ImageProcessCallback

class FiltersAdapter(private val filterList:List<Filters>, private val context: Context, private val callback: ImageProcessCallback) :
        RecyclerView.Adapter<FiltersAdapter.FilterViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterViewHolder {
       return FilterViewHolder(LayoutInflater.from(context).inflate(R.layout.filteritem, parent, false))
    }

    override fun getItemCount(): Int {
        return filterList.size
    }

    override fun onBindViewHolder(holder: FilterViewHolder, position: Int) {
        holder.imagebtn.setImageBitmap(filterList[position].mfiltersBitmap)
        holder.textDescription.text = filterList[position].type

        holder.imagebtn.setOnClickListener(View.OnClickListener {
            view: View? ->
            if(position==0){
                callback.callBack(filterList[position].mfiltersBitmap,false)
            }
            else if(position==1) {

                holder.imagebtn.setImageBitmap(filterList[position].mfiltersBitmap)
                callback.callBack(filterList[position].mfiltersBitmap,false)

            }else if(position==2) {

                holder.imagebtn.setImageBitmap(filterList[position].mfiltersBitmap)
                callback.callBack(filterList[position].mfiltersBitmap,false)

            }else if(position==3) {
                holder.imagebtn.setImageBitmap(filterList[position].mfiltersBitmap)
                callback.callBack(filterList[position].mfiltersBitmap,false)

            }else if(position==4) {
               var scaledBitmap:Bitmap = ProcessImage.getInstance(context).makeScaleImage(filterList[position].mfiltersBitmap)
                holder.imagebtn.setImageBitmap(scaledBitmap)
                callback.callBack(scaledBitmap,false)

            }else if(position==5) {
             val mergedImage:Bitmap = ProcessImage.getInstance(context).changeBlueIntensity(filterList[position].mfiltersBitmap,25)
                holder.imagebtn.setImageBitmap(mergedImage)
                callback.callBack(mergedImage,false)
             }else if(position==6) {
                val mergedImage:Bitmap = ProcessImage.getInstance(context).changeRedIntensity(filterList[position].mfiltersBitmap,25)
                holder.imagebtn.setImageBitmap(mergedImage)
                callback.callBack(mergedImage,false)
            }else if(position==7) {
                val mergedImage:Bitmap = ProcessImage.getInstance(context).changeGreenIntensity(filterList[position].mfiltersBitmap,25)
                holder.imagebtn.setImageBitmap(mergedImage)
                callback.callBack(mergedImage,false)
            }else if(position==8) {
                val mergedImage:Bitmap = ProcessImage.getInstance(context).changeHueIntensity(filterList[position].mfiltersBitmap,25)
                holder.imagebtn.setImageBitmap(mergedImage)
                callback.callBack(mergedImage,false)
            }else if(position==9) {
                val mergedImage:Bitmap = ProcessImage.getInstance(context).changeSaturationIntensity(filterList[position].mfiltersBitmap,25)
                holder.imagebtn.setImageBitmap(mergedImage)
                callback.callBack(mergedImage,false)
            }else if(position==10) {
                val mergedImage:Bitmap = ProcessImage.getInstance(context).changeVignettingIntensity(filterList[position].mfiltersBitmap,25)
                holder.imagebtn.setImageBitmap(mergedImage)
                callback.callBack(mergedImage,false)
            }else if(position==11) {
                var confg:Bitmap.Config = Bitmap.Config.ARGB_8888
                var btmap:Bitmap = Bitmap.createBitmap(400,800,confg)
                callback.callBack(btmap,true)
            }

        })

    }

    class FilterViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        val imagebtn:ImageButton = view.findViewById(R.id.imageButton)
        val textDescription:TextView = view.findViewById(R.id.bitmapDescription)


    }
}