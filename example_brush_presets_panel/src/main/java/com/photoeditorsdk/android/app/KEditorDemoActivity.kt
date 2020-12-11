package com.photoeditorsdk.android.app

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.photoeditorsdk.android.app.presets.BrushPresetCustomItem
import com.photoeditorsdk.android.app.presets.BrushPresetItem
import com.photoeditorsdk.android.app.presets.BrushPresetPanel
import com.photoeditorsdk.android.app.presets.BrushPresetSettings
import java.io.File
import java.io.IOException
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
import ly.img.android.pesdk.kotlin_extension.ColorValue
import ly.img.android.pesdk.ui.activity.PhotoEditorBuilder
import ly.img.android.pesdk.ui.model.state.*
import ly.img.android.pesdk.ui.panels.item.PersonalStickerAddItem
import ly.img.android.pesdk.ui.utils.PermissionRequest
import ly.img.android.serializer._3.IMGLYFileWriter

class KEditorDemoActivity : Activity(), PermissionRequest.Response {

    companion object {
        const val PESDK_RESULT = 1
        const val GALLERY_RESULT = 2
    }

    // Important permission request for Android 6.0 and above, don't forget to add this!
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        PermissionRequest.onRequestPermissionsResult(requestCode, permissions, grantResults)
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun permissionGranted() {}

    override fun permissionDenied() {
        /* TODO: The Permission was rejected by the user. The Editor was not opened,
         * Show a hint to the user and try again. */
    }

    // Create a empty new SettingsList and apply the changes on this reference.
    // If you include our asset Packs and use our UI you also need to add them to the UI,
    // otherwise they are only available for the backend (like Serialisation)
    // See the specific feature sections of our guides if you want to know how to add our own Assets.
    private fun createPesdkSettingsList() =
      PhotoEditorSettingsList()
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
        }.also {
            enableBrushPresets(it)
        }

    private fun enableBrushPresets(settingsList: PhotoEditorSettingsList) =
      settingsList
        .configure<BrushPresetSettings> {
            BrushPresetPanel.Companion // This line is necessary to be sure that the static initialisation is done and the tool is replaced.
            it.presetList.also { presets ->
                presets.add(BrushPresetItem("Red", 0.01f, 0.5f, ColorValue(1.0f, 255, 0, 0)))
                presets.add(BrushPresetItem("Green", 0.01f, 0.5f, ColorValue(1.0f, 0, 255, 0)))
                presets.add(BrushPresetItem("Marker", 0.03f, 1f, ColorValue(0.5f, 255, 255, 0)))
                presets.add(BrushPresetCustomItem("Default"))
            }
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
        val intent = Intent(Intent.ACTION_PICK)
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT) {
            intent.type = "image/*"
        } else {
            intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*")
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R || intent.resolveActivity(packageManager) != null) {
            startActivityForResult(intent, GALLERY_RESULT)
        } else {
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

        } else if (resultCode == RESULT_CANCELED && requestCode == PESDK_RESULT) {
            // Editor was canceled
            val data = EditorSDKResult(intent)

            val sourceURI = data.sourceUri
            // TODO: Do something with the source...*/
        }
    }

}
