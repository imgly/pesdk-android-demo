package com.photoeditorsdk.android.app

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.Button
import android.widget.Toast
import ly.img.android.pesdk.assets.font.basic.FontPackBasic
import ly.img.android.pesdk.assets.frame.basic.FramePackBasic
import ly.img.android.pesdk.assets.overlay.basic.OverlayPackBasic
import ly.img.android.pesdk.assets.sticker.emoticons.StickerPackEmoticons
import ly.img.android.pesdk.assets.sticker.shapes.StickerPackShapes
import ly.img.android.pesdk.backend.model.constant.Directory
import ly.img.android.pesdk.backend.model.state.CameraSettings
import ly.img.android.pesdk.backend.model.state.EditorLoadSettings
import ly.img.android.pesdk.backend.model.state.EditorSaveSettings
import ly.img.android.pesdk.backend.model.state.manager.SettingsList
import ly.img.android.pesdk.ui.activity.CameraPreviewBuilder
import ly.img.android.pesdk.ui.activity.ImgLyIntent
import ly.img.android.pesdk.ui.activity.PhotoEditorBuilder
import ly.img.android.pesdk.ui.model.state.UiConfigFrame
import ly.img.android.pesdk.ui.model.state.UiConfigOverlay
import ly.img.android.pesdk.ui.model.state.UiConfigSticker
import ly.img.android.pesdk.ui.model.state.UiConfigText
import ly.img.android.pesdk.ui.utils.PermissionRequest
import ly.img.android.serializer._3._0._0.PESDKFileWriter
import java.io.File
import java.io.IOException

class MainActivity : Activity(), PermissionRequest.Response {

    // Important permission request for Android 6.0 and above, don't forget to add this!
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        PermissionRequest.onRequestPermissionsResult(requestCode, permissions, grantResults)
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun permissionGranted() {}

    override fun permissionDenied() {
        // The Permission was rejected by the user. The Editor was not opened, as it could not save the result image.
        // TODO: Show a hint to the user and try again.
    }

    var PESDK_RESULT = 1
    var GALLERY_RESULT = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var startCamera = findViewById<Button>(R.id.startCamera)
        var openGallery = findViewById<Button>(R.id.openGallery)

        startCamera.setOnClickListener {
            openCamera()
        }

        openGallery.setOnClickListener {
            openSystemGalleryToSelectAnImage()
        }
    }

    private fun createPesdkSettingsList(): SettingsList {

        // Create a empty new SettingsList and apply the changes on this referance.
        val settingsList = SettingsList()

        // If you include our asset Packs and you use our UI you also need to add them to the UI,
        // otherwise they are only available for the backend
        // See the specific feature sections of our guides if you want to know how to add our own Assets.

        settingsList.getSettingsModel(UiConfigText::class.java).setFontList(
          FontPackBasic.getFontPack()
        )

        settingsList.getSettingsModel(UiConfigFrame::class.java).frameList = FramePackBasic.getFramePack()

        settingsList.getSettingsModel(UiConfigOverlay::class.java).overlayList = OverlayPackBasic.getOverlayPack()

        settingsList.getSettingsModel(UiConfigSticker::class.java).setStickerLists(
          StickerPackEmoticons.getStickerCategory(),
          StickerPackShapes.getStickerCategory()
        )

        // Set custom camera image export settings
        settingsList.getSettingsModel(CameraSettings::class.java)
          .setExportDir(Directory.DCIM, "IMGLY")
          .setExportPrefix("camera_")

        // Set custom editor image export settings
        settingsList.getSettingsModel(EditorSaveSettings::class.java)
          .setExportDir(Directory.DCIM, "IMGLY")
          .setExportPrefix("result_").savePolicy = EditorSaveSettings.SavePolicy.RETURN_ALWAYS_ONLY_OUTPUT

        return settingsList
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK && requestCode == GALLERY_RESULT) {
            // Open Editor with some uri in this case with an image selected from the system gallery.
            val selectedImage = data.data
            openEditor(selectedImage)

        } else if (resultCode == Activity.RESULT_OK && requestCode == PESDK_RESULT) {
            // Editor has saved an Image.
            val resultURI = data.getParcelableExtra<Uri>(ImgLyIntent.RESULT_IMAGE_URI)
            val sourceURI = data.getParcelableExtra<Uri>(ImgLyIntent.SOURCE_IMAGE_URI)

            // Scan result uri to show it up in the Gallery
            if (resultURI != null) {
                sendBroadcast(Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE).setData(resultURI))
            }

            // Scan source uri to show it up in the Gallery
            if (sourceURI != null) {
                sendBroadcast(Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE).setData(sourceURI))
            }

            // TODO: Do something with the result image

            // OPTIONAL: read the latest state to save it as a serialisation
            val lastState = data.getParcelableExtra<SettingsList>(ImgLyIntent.SETTINGS_LIST)
            try {
                val pesdkFileWriter = PESDKFileWriter(lastState)
                pesdkFileWriter.writeJson(File(
                  Environment.getExternalStorageDirectory(),
                  "serialisationReadyToReadWithPESDKFileReader.json"
                ))
            } catch (e: IOException) {
                e.printStackTrace()
            }

        } else if (resultCode == Activity.RESULT_CANCELED && requestCode == PESDK_RESULT) {
            // Editor was canceled
            val sourceURI = data.getParcelableExtra<Uri>(ImgLyIntent.SOURCE_IMAGE_URI)
            // TODO: Do something with the source...
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

    fun openEditor(inputImage: Uri) {
        val settingsList = createPesdkSettingsList()

        settingsList.getSettingsModel(EditorLoadSettings::class.java)
          .setImageSource(inputImage)

        PhotoEditorBuilder(this)
          .setSettingsList(settingsList)
          .startActivityForResult(this, PESDK_RESULT)
    }

    private fun openCamera() {
        val settingsList = createPesdkSettingsList()

        CameraPreviewBuilder(this)
          .setSettingsList(settingsList)
          .startActivityForResult(this, PESDK_RESULT)
    }

}
