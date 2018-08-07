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
                callback.callBack(filterList[position].mfiltersBitmap)
            }
            else if(position==1) {

                holder.imagebtn.setImageBitmap(filterList[position].mfiltersBitmap)
                callback.callBack(filterList[position].mfiltersBitmap)

            }else if(position==2) {

                holder.imagebtn.setImageBitmap(filterList[position].mfiltersBitmap)
                callback.callBack(filterList[position].mfiltersBitmap)

            }else if(position==3) {
                holder.imagebtn.setImageBitmap(filterList[position].mfiltersBitmap)
                callback.callBack(filterList[position].mfiltersBitmap)

            }else if(position==4) {
               var scaledBitmap:Bitmap = ProcessImage.getInstance(context).makeScaleImage(filterList[position].mfiltersBitmap)
                holder.imagebtn.setImageBitmap(scaledBitmap)
                callback.callBack(scaledBitmap)

            }

        })

    }

    class FilterViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        val imagebtn:ImageButton = view.findViewById(R.id.imageButton)
        val textDescription:TextView = view.findViewById(R.id.bitmapDescription)


    }
}