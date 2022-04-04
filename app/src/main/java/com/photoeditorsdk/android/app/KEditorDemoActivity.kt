package com.photoeditorsdk.android.app

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.Toast
import ly.img.android.pesdk.PhotoEditorSettingsList
import ly.img.android.pesdk.assets.filter.basic.FilterPackBasic
import ly.img.android.pesdk.assets.font.basic.FontPackBasic
import ly.img.android.pesdk.assets.frame.basic.FramePackBasic
import ly.img.android.pesdk.assets.overlay.basic.OverlayPackBasic
import ly.img.android.pesdk.assets.sticker.emoticons.StickerPackEmoticons
import ly.img.android.pesdk.assets.sticker.shapes.StickerPackShapes
import ly.img.android.pesdk.backend.model.EditorSDKResult
import ly.img.android.pesdk.backend.model.constant.OutputMode
import ly.img.android.pesdk.backend.model.state.LoadSettings
import ly.img.android.pesdk.backend.model.state.PhotoEditorSaveSettings
import ly.img.android.pesdk.ui.activity.PhotoEditorBuilder
import ly.img.android.pesdk.ui.model.state.UiConfigFilter
import ly.img.android.pesdk.ui.model.state.UiConfigFrame
import ly.img.android.pesdk.ui.model.state.UiConfigOverlay
import ly.img.android.pesdk.ui.model.state.UiConfigSticker
import ly.img.android.pesdk.ui.model.state.UiConfigText
import ly.img.android.pesdk.ui.panels.item.PersonalStickerAddItem
import ly.img.android.serializer._3.IMGLYFileWriter
import java.io.File
import java.io.IOException

class KEditorDemoActivity : Activity() {

    companion object {
        const val PESDK_RESULT = 1
        const val GALLERY_RESULT = 2
    }

    // Create a empty new SettingsList and apply the changes on this reference.
    // If you include our asset Packs and use our UI you also need to add them to the UI Config,
    // otherwise they are only available for the backend (like Serialisation)
    // See the specific feature sections of our guides if you want to know how to add your own Assets.
    private fun createPesdkSettingsList() =
      PhotoEditorSettingsList(true)
        .configure<UiConfigFilter> {
            it.setFilterList(FilterPackBasic.getFilterPack())
        }
        .configure<UiConfigText> {
            it.setFontList(FontPackBasic.getFontPack())
        }
        .configure<UiConfigFrame> {
            it.setFrameList(FramePackBasic.getFramePack())
        }
        .configure<UiConfigOverlay> {
            it.setOverlayList(OverlayPackBasic.getOverlayPack())
        }
        .configure<UiConfigSticker> {
            it.setStickerLists(
                PersonalStickerAddItem(),
                StickerPackEmoticons.getStickerCategory(),
                StickerPackShapes.getStickerCategory()
            )
        }
        .configure<PhotoEditorSaveSettings> {
            // Set custom editor image export settings
            it.setOutputToGallery(Environment.DIRECTORY_DCIM)
            it.outputMode = OutputMode.EXPORT_IF_NECESSARY
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val openGallery = findViewById<Button>(R.id.openGallery)

        openGallery.setOnClickListener {
            openSystemGalleryToSelectAnImage()
        }
    }

    fun openSystemGalleryToSelectAnImage() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        try {
            startActivityForResult(intent, GALLERY_RESULT)
        } catch (ex: ActivityNotFoundException) {
            Toast.makeText(
                this,
                "No Gallery APP installed",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    fun openEditor(inputImage: Uri?) {
        val settingsList = createPesdkSettingsList()

        settingsList.configure<LoadSettings> {
            it.source = inputImage
        }

        PhotoEditorBuilder(this)
          .setSettingsList(settingsList)
          .startActivityForResult(this, PESDK_RESULT)

        settingsList.release()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)

        intent ?: return

        if (resultCode == RESULT_OK && requestCode == GALLERY_RESULT) {
            // Open Editor with some uri in this case with an image selected from the system gallery.
            val selectedImage = intent.data
            if (selectedImage != null) {
                openEditor(selectedImage)
            }
        } else if (resultCode == RESULT_OK && requestCode == PESDK_RESULT) {
            // Editor has saved an Image.
            val data = EditorSDKResult(intent)

            Log.i("PESDK", "Source image is located here ${data.sourceUri}")
            Log.i("PESDK", "Result image is located here ${data.resultUri}")

            // TODO: Do something with the result image

            // OPTIONAL: read the latest state to save it as a serialisation
            val lastState = data.settingsList
            try {
                IMGLYFileWriter(lastState).writeJson(File(
                    Environment.getExternalStorageDirectory(),
                    "serialisationReadyToReadWithPESDKFileReader.json"
                ))
            } catch (e: IOException) {
                e.printStackTrace()
            }

            lastState.release()

        } else if (resultCode == RESULT_CANCELED && requestCode == PESDK_RESULT) {
            // Editor was canceled
            val data = EditorSDKResult(intent)

            val sourceURI = data.sourceUri
            // TODO: Do something with the source...*/
        }
    }

}
