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


import java.io.File
import java.io.IOException
import ly.img.android.pesdk.PhotoEditorSettingsList
import ly.img.android.pesdk.assets.font.basic.FontPackBasic
import ly.img.android.pesdk.assets.sticker.emoticons.StickerPackEmoticons
import ly.img.android.pesdk.assets.sticker.shapes.StickerPackShapes
import ly.img.android.pesdk.backend.decoder.ImageSource
import ly.img.android.pesdk.backend.model.EditorSDKResult
import ly.img.android.pesdk.backend.model.config.ImageStickerAsset
import ly.img.android.pesdk.backend.model.config.MultiImageStickerAsset
import ly.img.android.pesdk.backend.model.constant.OutputMode
import ly.img.android.pesdk.backend.model.state.AssetConfig
import ly.img.android.pesdk.backend.model.state.LoadSettings
import ly.img.android.pesdk.backend.model.state.PhotoEditorSaveSettings
import ly.img.android.pesdk.backend.sticker_smart.*
import ly.img.android.pesdk.ui.activity.PhotoEditorBuilder

import ly.img.android.pesdk.ui.model.state.*
import ly.img.android.pesdk.ui.panels.item.ImageStickerItem
import ly.img.android.pesdk.ui.panels.item.PersonalStickerAddItem
import ly.img.android.pesdk.ui.utils.PermissionRequest

class KEditorDemoActivity : Activity(), PermissionRequest.Response {

    companion object {
        const val PESDK_RESULT = 12371283
        const val GALLERY_RESULT = 9038132
    }

    // Create a empty new SettingsList and apply the changes on this reference.
    // If you include our asset Packs and use our UI you also need to add them to the UI,
    // otherwise they are only available for the backend (like Serialisation)
    // See the specific feature sections of our guides if you want to know how to add our own Assets.
    private fun createPesdkSettingsList() =
      PhotoEditorSettingsList(true)
        .configure<AssetConfig> {
            // Add a toggleable sticker assets. (Toggle by click on it.)
            it.addAsset(MultiImageStickerAsset(
                id = "my_custom_smart_sticker", stickerAssets = arrayListOf(
                    ImageStickerAsset("my_custom_smart_sticker_0", ImageSource.create(CustomSmartSticker0::class.java)),
                    ImageStickerAsset("my_custom_smart_sticker_1", ImageSource.create(CustomSmartSticker1::class.java)),
                    ImageStickerAsset("my_custom_smart_sticker_2", ImageSource.create(CustomSmartSticker2::class.java)),
                )
            )
            )
        }
        .configure<UiConfigText> {
            it.setFontList(FontPackBasic.getFontPack())
        }
        .configure<UiConfigSticker> {
            it.setStickerLists(
                PersonalStickerAddItem(),
                StickerPackEmoticons.getStickerCategory().also {
                    it.stickerList.add(0,
                        ImageStickerItem(
                            // Id of your Asset
                            "my_custom_smart_sticker",
                            // It is recommended to use a string resource here.
                            "TestSticker",
                            // SmartStickers can be also used as Icon and Preview.
                            ImageSource.create(CustomSmartSticker0::class.java)
                        )
                    )
                },
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

        } else if (resultCode == RESULT_CANCELED && requestCode == PESDK_RESULT) {
            // Editor was canceled
            val data = EditorSDKResult(intent)

            val sourceURI = data.sourceUri
            // TODO: Do something with the source...*/
        }
    }

    // Important permission request for Android 6.0 and above, don't forget to add this!
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        PermissionRequest.onRequestPermissionsResult(requestCode, permissions, grantResults)
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun permissionGranted() {}
    override fun permissionDenied() {}
}
