package com.photoeditorsdk.android.app.custom

import ly.img.android.pesdk.backend.model.state.manager.StateHandler
import ly.img.android.pesdk.ui.panels.StickerOptionToolPanel
import ly.img.android.pesdk.ui.panels.StickerToolPanel
import ly.img.android.pesdk.ui.panels.item.OptionItem

class CustomStickerOptionTool(stateHandler: StateHandler) : StickerOptionToolPanel(stateHandler) {
    override fun onItemClick(entity: OptionItem) {
        if (entity.id == OPTION_ADD) {
            menuState.openMainTool(CustomStickerTool.TOOL_ID)
        } else super.onItemClick(entity)
    }

    companion object {
        const val TOOL_ID: String = "CustomStickerOptionTool"
    }
}