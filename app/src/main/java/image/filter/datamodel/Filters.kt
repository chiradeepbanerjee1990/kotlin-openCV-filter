package image.filter.datamodel

import android.graphics.Bitmap

open class Filters{

    var type:String
    var mfiltersBitmap: Bitmap

    constructor(type:String,mfiltersBitmap:Bitmap){
        this.type = type
        this.mfiltersBitmap = mfiltersBitmap

    }
}