package com.photoeditorsdk.android.app;

import android.app.Activity;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import java.io.File;

import ly.img.android.ui.activities.CameraPreviewActivity;
import ly.img.android.ui.activities.CameraPreviewIntent;
import ly.img.android.ui.activities.PhotoEditorIntent;

public class MainActivity extends Activity {

    private static final String FOLDER = "ImgLy";
    public static int CAMERA_PREVIEW_RESULT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new CameraPreviewIntent(this)
                .setExportDir(CameraPreviewIntent.Directory.DCIM, FOLDER)
                .setExportPrefix("img_")
                .setEditorIntent(
                        new PhotoEditorIntent(this)
                        .setExportDir(PhotoEditorIntent.Directory.DCIM, FOLDER)
                        .setExportPrefix("result_")
                        .destroySourceAfterSave(true)
                )
                .startActivityForResult(CAMERA_PREVIEW_RESULT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, android.content.Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == CAMERA_PREVIEW_RESULT) {
            String path = data.getStringExtra(CameraPreviewActivity.RESULT_IMAGE_PATH);

            Toast.makeText(this, "Image Save on: " + path, Toast.LENGTH_LONG).show();

            File mMediaFolder = new File(path); //new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), FOLDER);

            //sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(mMediaFolder)));

            MediaScannerConnection.scanFile(this, new String[] {mMediaFolder.getAbsolutePath()}, null, new MediaScannerConnection.OnScanCompletedListener() {
                    public void onScanCompleted(String path, Uri uri) {
                        finish();
                    }
                }
            );
        }
        finish();
    }
}
