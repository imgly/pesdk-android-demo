package com.photoeditorsdk.android.app.custom

import android.os.Parcel
import ly.img.android.pesdk.backend.model.state.layer.ImageStickerLayerSettings
import ly.img.android.pesdk.backend.model.config.ImageStickerAsset

internal open class CustomImageStickerLayerSettings : ImageStickerLayerSettings {
    constructor(stickerConfig: ImageStickerAsset) : super(stickerConfig)
    protected constructor(parcel: Parcel) : super(parcel)

    override fun getLayerToolId(): String = CustomStickerOptionTool.TOOL_ID
}