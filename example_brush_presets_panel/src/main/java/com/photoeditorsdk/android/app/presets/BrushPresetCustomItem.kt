package com.photoeditorsdk.android.app.presets

import android.os.Parcel
import ly.img.android.pesdk.kotlin_extension.parcelableCreator

open class BrushPresetCustomItem : BrushPresetItem {

    constructor(name:String) : super(name,0f,0f,0)

    constructor(parcel: Parcel) : super(parcel)

    companion object {
        @JvmField var CREATOR = parcelableCreator(::BrushPresetCustomItem)
    }
}