@file:Suppress("unused")

package com.photoeditorsdk.android.app.custom

import android.os.Parcel
import android.os.Parcelable
import ly.img.android.pesdk.annotations.ImglyEvents
import ly.img.android.pesdk.annotations.OnEvent
import ly.img.android.pesdk.backend.model.state.LayerListSettings
import ly.img.android.pesdk.backend.model.state.ProviderState
import ly.img.android.pesdk.backend.model.state.layer.ImageStickerLayerSettings
import ly.img.android.pesdk.backend.model.state.manager.ImglySettings
import ly.img.android.pesdk.backend.smart.WeatherProvider
import ly.img.android.pesdk.backend.sticker_smart.CustomSmartSticker
import ly.img.android.pesdk.kotlin_extension.layerReadLock
import ly.img.android.pesdk.kotlin_extension.parcelableCreator
import ly.img.android.pesdk.utils.SingletonReference
import ly.img.android.pesdk.utils.ThreadUtils

open class CustomSmartStickerConfig @JvmOverloads constructor(parcel: Parcel? = null) : ImglySettings(parcel) {

    private val customProviderSingletonReference = SingletonReference {
        MyCustomDataProvider().also {
            it.stateHandler = settingsHandler
        }
    }
    val customProvider by customProviderSingletonReference

    fun propagateStateUpdateToLayer() {
        getStateModel(LayerListSettings::class.java).layerReadLock { layerSettingsList ->
            for (layerSettings in layerSettingsList) {
                layerSettings as? ImageStickerLayerSettings ?: continue
                if (layerSettings.stickerConfig.stickerSource.hasProvider(CustomSmartSticker.PROVIDER_NAME)) {
                    ThreadUtils.syncWithMainThread {
                        layerSettings.refreshContent()
                    }
                }
            }
        }
    }

    companion object {
        @JvmField val CREATOR: Parcelable.Creator<CustomSmartStickerConfig> = parcelableCreator(::CustomSmartStickerConfig)
    }
}