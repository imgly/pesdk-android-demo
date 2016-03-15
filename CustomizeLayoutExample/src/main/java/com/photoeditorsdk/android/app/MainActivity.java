package com.photoeditorsdk.android.app;

import android.app.Activity;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.Toast;

import java.io.File;

import ly.img.android.ui.activities.CameraPreviewActivity;
import ly.img.android.ui.activities.CameraPreviewIntent;
import ly.img.android.ui.activities.PhotoEditorIntent;
import ly.img.android.ui.utilities.PermissionRequest;

public class MainActivity extends Activity  implements PermissionRequest.Response {

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

            Toast.makeText(this, "Image saved at: " + path, Toast.LENGTH_LONG).show();

            File mMediaFolder = new File(path);

            MediaScannerConnection.scanFile(this, new String[] {mMediaFolder.getAbsolutePath()}, null, new MediaScannerConnection.OnScanCompletedListener() {
                        public void onScanCompleted(String path, Uri uri) {

                        }
                    }
            );
        }
        finish();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        PermissionRequest.onRequestPermissionsResult(requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void permissionGranted() {

    }

    @Override
    public void permissionDenied() {
        finish();
        System.exit(0);
    }
}
