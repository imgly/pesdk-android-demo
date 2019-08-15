package com.photoeditorsdk.android.app

import android.app.Activity
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
import ly.img.android.pesdk.backend.model.constant.Directory
import ly.img.android.pesdk.backend.model.state.*
import ly.img.android.pesdk.backend.model.state.manager.SettingsList
import ly.img.android.pesdk.ui.activity.ImgLyIntent
import ly.img.android.pesdk.ui.activity.PhotoEditorBuilder
import ly.img.android.pesdk.ui.model.state.*
import ly.img.android.pesdk.ui.utils.PermissionRequest
import ly.img.android.serializer._3._0._0.PESDKFileWriter
import java.io.File
import java.io.IOException

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

    // Create a empty new SettingsList and apply the changes on this referance.
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
              StickerPackEmoticons.getStickerCategory(),
              StickerPackShapes.getStickerCategory()
            )
        }
        .configure<SaveSettings> {
            // Set custom editor image export settings
            it.setExportDir(Directory.DCIM, "SomeFolderName")
            it.setExportPrefix("result_")
            it.savePolicy = SaveSettings.SavePolicy.RETURN_ALWAYS_ONLY_OUTPUT
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
        if (intent.resolveActivity(packageManager) != null) {
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

        settingsList[LoadSettings::class].source = inputImage

        PhotoEditorBuilder(this)
          .setSettingsList(settingsList)
          .startActivityForResult(this, PESDK_RESULT)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK && requestCode == GALLERY_RESULT) {
            // Open Editor with some uri in this case with an image selected from the system gallery.
            openEditor(data?.data)

        } else if (resultCode == RESULT_OK && requestCode == PESDK_RESULT) { // Editor has saved an Image.

            val resultURI = data?.getParcelableExtra<Uri?>(ImgLyIntent.RESULT_IMAGE_URI)?.also {
                // Scan result uri to show it up in the Gallery
                sendBroadcast(Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE).setData(it))
            }

            val sourceURI = data?.getParcelableExtra<Uri?>(ImgLyIntent.SOURCE_IMAGE_URI)?.also {
                // Scan source uri to show it up in the Gallery
                sendBroadcast(Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE).setData(it))
            }

            Log.i("PESDK", "Source image is located here $sourceURI")
            Log.i("PESDK", "Result image is located here $resultURI")

            // TODO: Do something with the result image

            // OPTIONAL: read the latest state to save it as a serialisation
            val lastState = data?.getParcelableExtra<SettingsList>(ImgLyIntent.SETTINGS_LIST)
            try {
                PESDKFileWriter(lastState).writeJson(File(
                  Environment.getExternalStorageDirectory(),
                  "serialisationReadyToReadWithPESDKFileReader.json"
                ))
            } catch (e: IOException) {
                e.printStackTrace()
            }

        } else if (resultCode == RESULT_CANCELED && requestCode == PESDK_RESULT) {
            // Editor was canceled
            val sourceURI = data?.getParcelableExtra<Uri?>(ImgLyIntent.SOURCE_IMAGE_URI)
            // TODO: Do something with the source...
        }
    }

}
