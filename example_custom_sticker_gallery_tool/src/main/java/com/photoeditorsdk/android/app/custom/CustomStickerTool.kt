package com.photoeditorsdk.android.app.custom

import android.animation.Animator
import android.animation.AnimatorSet
import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.annotation.MainThread
import com.photoeditorsdk.android.app.R
import ly.img.android.PESDK
import ly.img.android.SourceType
import ly.img.android.pesdk.backend.model.config.ImageStickerAsset
import ly.img.android.pesdk.backend.model.state.AssetConfig
import ly.img.android.pesdk.backend.model.state.LayerListSettings
import ly.img.android.pesdk.backend.model.state.layer.ImageStickerLayerSettings
import ly.img.android.pesdk.backend.model.state.manager.StateHandler
import ly.img.android.pesdk.ui.model.state.UiConfigSticker
import ly.img.android.pesdk.ui.model.state.UiStateMenu
import ly.img.android.pesdk.ui.panels.AbstractToolPanel
import ly.img.android.pesdk.ui.panels.item.ImageStickerItem
import ly.img.android.pesdk.utils.ThreadUtils

class CustomStickerTool(stateHandler: StateHandler) : AbstractToolPanel(stateHandler) {

    private val layerListSettings = stateHandler[LayerListSettings::class]
    private val menuState = stateHandler[UiStateMenu::class]

    private var PICKER_INTENT = Intent(Intent.ACTION_PICK).also {
        it.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            it.putExtra(Intent.EXTRA_MIME_TYPES, arrayOf("image/*"))
        }
    }

    private fun openGalleryAction() {
        val activity = activity
        val intent = Intent(PICKER_INTENT)
        try {
            activity.getActivityResult(intent) { resultCode: Int, intent: Intent? ->
                val data = intent?.data
                if (resultCode == Activity.RESULT_OK && data != null) {
                    val sourceType = SourceType.detectTypeSafe(data)
                    if (sourceType == SourceType.IMAGE) {
                        val galleryAsset = ImageStickerAsset.createTemporaryStickerAsset(data)

                        stateHandler[AssetConfig::class].addAsset(galleryAsset)
                        stateHandler[UiConfigSticker::class].addToPersonalStickerList(ImageStickerItem.createFromAsset(galleryAsset))
                        ThreadUtils.postToMainThread {
                            attachSticker(galleryAsset)
                        }
                    } else {
                        Toast.makeText(PESDK.getAppContext(), R.string.imgly_unknown_source_from_gallery, Toast.LENGTH_LONG).show()
                        menuState.notifyCancelClicked()
                    }
                } else {
                    menuState.notifyCancelClicked()
                }
            }
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(PESDK.getAppContext(), R.string.imgly_no_gallery_found, Toast.LENGTH_LONG).show()
            menuState.notifyCancelClicked()
        }
    }

    @MainThread
    private fun attachSticker(config: ImageStickerAsset?) {
        val spriteLayerSettings = stateHandler.createLayerSettingsModel(CustomImageStickerLayerSettings::class.java, config)
        layerListSettings.addLayer(spriteLayerSettings)
        layerListSettings.selected = spriteLayerSettings
    }


    override fun onAttached(context: Context?, panelView: View) {
        super.onAttached(context, panelView)
        openGalleryAction()
    }

    override fun getLayoutResource(): Int = R.layout.dummy_layout

    override fun createShowAnimator(p0: View): Animator {
        return AnimatorSet().also {
            it.duration = 0
        }
    }

    override fun createExitAnimator(p0: View): Animator {
        return AnimatorSet().also {
            it.duration = 0
        }
    }

    override fun onDetached() {}

    companion object {
        const val TOOL_ID: String = "CustomStickerTool"
    }

}
