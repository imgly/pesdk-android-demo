package com.photoeditorsdk.android.app.presets

import android.graphics.Bitmap
import android.os.Parcel
import com.photoeditorsdk.android.app.R
import ly.img.android.pesdk.backend.decoder.ImageSource
import ly.img.android.pesdk.kotlin_extension.parcelableCreator
import ly.img.android.pesdk.ui.panels.item.AbstractIdItem
import ly.img.android.pesdk.ui.panels.item.AbstractItem
import ly.img.android.pesdk.utils.ColorFillSource

open class BrushPresetItem : AbstractItem {

    val size:Float
    val color:Int
    val hardness:Float

    private val fillSource = ColorFillSource(ImageSource.create(R.drawable.imgly_icon_option_text_color_bg), ImageSource.create(R.drawable.imgly_icon_option_text_color_fill))

    @JvmOverloads constructor(name: String? = null, size: Float, hardness: Float, color: Int) : super(name, null) {
        this.size = size
        this.color = color
        this.hardness = hardness
    }

    override fun getLayout() = R.layout.imgly_list_item_option
    override fun isSelectable() = true
    override fun getThumbnailResId() = 0
    override fun hasStaticThumbnail() = false

    override fun describeContents() = 0
    override fun getThumbnailBitmap() = getThumbnailBitmap(0)
    override fun getThumbnailBitmap(maxWidth: Int) = fillSource.getColoredBitmap(color)

    constructor(parcel: Parcel) : super(parcel) {
        size = parcel.readFloat()
        color = parcel.readInt()
        hardness = parcel.readFloat()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        super.writeToParcel(parcel, flags)
        parcel.writeFloat(size)
        parcel.writeInt(color)
        parcel.writeFloat(hardness)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as BrushPresetItem

        if (size != other.size) return false
        if (color != other.color) return false
        if (hardness != other.hardness) return false

        return true
    }

    override fun hashCode(): Int {
        var result = size.hashCode()
        result = 31 * result + color
        result = 31 * result + hardness.hashCode()
        return result
    }

    companion object {
        @JvmField var CREATOR = parcelableCreator(::BrushPresetItem)
    }
}