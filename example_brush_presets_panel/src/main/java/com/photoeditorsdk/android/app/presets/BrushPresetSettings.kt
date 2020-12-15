package com.photoeditorsdk.android.app.presets

import android.os.Parcel

import ly.img.android.pesdk.backend.decoder.ImageSource
import ly.img.android.pesdk.backend.model.constant.RevertStrategy
import ly.img.android.pesdk.backend.model.state.manager.ImglySettings

import ly.img.android.pesdk.kotlin_extension.parcelableCreator
import ly.img.android.pesdk.ui.brush.R
import ly.img.android.pesdk.ui.panels.BrushToolPanel
import ly.img.android.pesdk.ui.panels.item.HistoryOption
import ly.img.android.pesdk.ui.panels.item.OptionItem
import ly.img.android.pesdk.ui.panels.item.QuickOptionItem
import ly.img.android.pesdk.ui.panels.item.SpaceFillItem
import ly.img.android.pesdk.ui.panels.item.SpaceItem
import ly.img.android.pesdk.ui.panels.item.ToggleOption

import ly.img.android.pesdk.utils.DataSourceArrayList

class BrushPresetSettings @JvmOverloads constructor(parcel: Parcel? = null) : ImglySettings(parcel) {

    var selectedPresetItem: BrushPresetItem? by value(null)

    val presetList by value(DataSourceArrayList<BrushPresetItem>(), revertStrategy = RevertStrategy.NONE)

    val quickOptionList by value(DataSourceArrayList<OptionItem>().also {
        it.add(QuickOptionItem(BrushToolPanel.OPTION_DELETE, R.string.pesdk_brush_button_delete, ImageSource.create(R.drawable.imgly_icon_delete)))
        it.add(SpaceItem(48))
        it.add(SpaceFillItem(1))
        it.add(ToggleOption(BrushToolPanel.OPTION_BRING_TO_FRONT, R.string.pesdk_brush_button_bringToFront, ImageSource.create(R.drawable.imgly_icon_to_front)))
        it.add(SpaceFillItem(1))
        it.add(HistoryOption(BrushToolPanel.OPTION_UNDO, R.drawable.imgly_icon_undo, false))
        it.add(HistoryOption(BrushToolPanel.OPTION_REDO, R.drawable.imgly_icon_redo, false))
    })

    companion object {
        @JvmField val CREATOR = parcelableCreator(::BrushPresetSettings)
    }
}