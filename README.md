

<p align="center">
  <img src="http://static.photoeditorsdk.com/logo.png" />
</p>
<p align="center">
  <a href="http://developer.android.com/guide/topics/manifest/uses-sdk-element.html#ApiLevels">
    <img src="https://img.shields.io/badge/MIN_SDK-15-B8D529.svg?style=flat">
    <img src="https://img.shields.io/badge/BUILD_SDK-25-92D230.svg?style=flat">
  </a>
  <a href="https://www.photoeditorsdk.com/documentation/android/getting-started/?utm_campaign=Projects&utm_source=Github&utm_medium=PESDK&utm_term=Android">
    <img src="https://img.shields.io/badge/platform-android-2DC25C.svg?style=flat">
  </a>
  <a href="https://artifactory.9elements.com/artifactory/imgly/ly/img/android/photo-editor-sdk/">
    <img src="https://api.photoeditorsdk.com/badges/android.svg" alt="Maven">
  </a>
  <a href="http://twitter.com/PhotoEditorSDK">
    <img src="https://img.shields.io/badge/twitter-@PhotoEditorSDK-8646E2.svg?style=flat" alt="Twitter">
  </a>
  <a href="https://pesdk-slack.herokuapp.com/">
    <img src="https://pesdk-slack.herokuapp.com/badge.svg" alt="Slack Status">
  </a>
</p>



## PhotoEditor SDK for Android by img.ly

The [PhotoEditor SDK](https://www.photoeditorsdk.com/?utm_campaign=Projects&utm_source=Github&utm_medium=PESDK&utm_term=Android) for Android is for creating stunning images with a nice selection of premium filters.

<a href="https://play.google.com/store/apps/details?id=com.photoeditorsdk.android.app&utm_source=global_co&utm_medium=prtnr&utm_content=Mar2515&utm_campaign=PartBadge&pcampaignid=MKT-Other-global-all-co-prtnr-py-PartBadge-Mar2515-1">
    <img height="60" alt="Get it on Google Play" src="https://play.google.com/intl/en_us/badges/images/generic/en-play-badge-border.png" >
</a>

### Overview

The img.ly [PhotoEditor SDK](https://www.photoeditorsdk.com/?utm_campaign=Projects&utm_source=Github&utm_medium=PESDK&utm_term=Android) provides a variety of tools and functions for creating photo applications for Android. It is licensed under a proprietary license and intended to be used as better alternative for free software applications such as the [GPUImage](https://github.com/CyberAgent/android-gpuimage) or similiar libraries. If you are interested in our SDK, please [contact us](#author--contact).

#### SDK Core

__ACS Component__ <br/>
A generic Android Camera Stack library which is based on the [android.hardware.Camera](http://developer.android.com/reference/android/hardware/Camera.html) API. Supports front and rear cam, HDR, flash modes and much more.

__SDK Component__ <br/>
The img.ly core library for Android. Containing the OpenGL and toolkit implementation.

__UI Component__ <br/>
The default UI components consisting of LivePreview and Editor Activity.

#### Dependencies

Two Google support libraries needed or used by the SDK.

* com.android.support:recyclerview-v7
* com.android.support:support-annotations

### Features

* __Android API Level 15+__. Covers nearly 95% of all Android devices.
* __Default UI__. for camera preview and editing. Based on Intents and Activities.
* __Fast image export up to 4294 MegaPixel__. Even with large images and slow devices with low memory the export is done in adequate time with a intelligent unrivaled background processing technology.
* __Generic camera support__. Integrated and featureful on the most Android phones.
* __Crop__, __Rotate__, __Stickers__, __Text Placement__, and __Colorize__. All essential photo editing functions wrapped into a simple, beautiful and customizable UI.
* __57 Stunning filters__ which are builtin and work out of the box.
* __No native code__. Our backend is Renderscript based with highlevel OpenGL support, therefore we dodge all the nasty native library problems other frameworks face.
* __Tablet support__. Works great on tablets.
* __Photoshop LUT__. Design color filters in Photoshop!
With this feature it is possible to generate LUT (Look Up Table) color filters easily from different photo
editing tools. Export and integrate them in minutes!
* __Live Preview__. Filters can be previewed in high quality at realtime.
* __Low memory footprint__. even with high resolution images.
* __Extensible and customizable toolkit interface__. Add your own customized filters with [Renderscript](https://developer.android.com/guide/topics/renderscript/index.html) and modify tool properties yourself.

### License

The PhotoEditor SDK for Android is a licensed library which can be used for different purposes. <br/>
Please see:

 [LICENSE.PROPIETARY](https://github.com/imgly/imgly-sdk-android/blob/master/LICENSE.PROPIETARY) for PROPIETARY usage.

### Author & Contact

&copy; 9elements GmbH <br/>
[Email](mailto:eray.basar@9elements.com) <br/>
[Homepage](https://www.photoeditorsdk.com/?utm_campaign=Projects&utm_source=Github&utm_medium=PESDK&utm_term=Android) <br/>
[Follow us on Twitter](https://twitter.com/9elements)

## Installation

> The SDK requires a minimum deployment target of Android API 15 (4.0.4) and Device with HardwareLayer (for LivePreview) and LargeHeap Support (to operate and export large images)


##### 1. Add the imgly maven repo to the project build.gradle.

```
 allprojects {
     repositories {
         jcenter()
         maven {
             url "https://artifactory.9elements.com/artifactory/imgly"
         }
     }
 }

```


##### 2. Configure your Module build.gradle to import the PhotoEditor SDK into your project with jCenter.

There are few things we'll need to add here.
See comments in the example code below.

__DO NOT FORGET TO ADD RENDERSCRIPT SUPPORT!__

```groovy
apply plugin: 'com.android.application'

android {
    /* Set the Compile SDK and the Build SDK 25. */
    compileSdkVersion 25
    buildToolsVersion '25.0.2'

    /* If you update from SDK 22 and below, the ApacheHttp-Library are removed by Google,
     * if you need the ApacheHttp-Library comment out the next line */
    // useLibrary 'org.apache.http.legacy'

    defaultConfig {
        /* Replace with your App-ID @see http://tools.android.com/tech-docs/new-build-system/applicationid-vs-packagename */
        applicationId "my.domain.application"

        /* Set the minimum supported SDK Version to 15 (Android 4.0.3) or higher */
        minSdkVersion 15

        /* Set the target SDK Version at minimum to 24 or higher */
        targetSdkVersion 25

        /* Set your own Version Code and Version name */
        versionCode 1
        versionName "1.0"

        /* Don't forget this two lines, otherwise the app will crash! */
        renderscriptTargetApi 23
        renderscriptSupportModeEnabled true
    }

    /* Set Java Language level minimal to Java 1.7 or greater */
    compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_7
        targetCompatibility JavaVersion.VERSION_1_7
    }

    //...
}

dependencies {
    /* Make sure you are import the latest SDK version */
    compile 'ly.img.android:photo-editor-sdk:3.1.0'
}

//...

```

Be also sure to sync your project with the Gradle files after making any edits.

For more information about gradle look at http://developer.android.com/tools/building/configuring-gradle.html

##### 3. Initialize SDK in an Application class.

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="..." >

    <application
        android:name=".Application" 
        android:icon="..."
        android:label="..."
        andSoOn="..." >
        <!--...-->
    </application>

</manifest>

```

```java
public class Application extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();

        PESDK.init(this);
    }
}
```

##### 4. Add the License file

Before using any components of the PhotoEditor SDK, you have to add your license key file to your application assets folder.
The default name of the license file is "LICENSE" change this by calling `PESDK.init(this, "FILENAME");` instead of `PESDK.init(this);`  

The license is digitally signed so it can not be altered without becoming invalid. Our sample app comes with its own license, so you can try that right away. To try our SDK in your own app, you need to request a trial license because a license is bound to a bundle identifier. You can start a free two week trial with a demo license [here](https://www.photoeditorsdk.com/users/new/?utm_campaign=Projects&utm_source=Github&utm_medium=PESDK&utm_term=Android).

Once you have the license file it can be used to unlock the view controller. The following example demonstrates the unlock the SDK.

##### 5. Start PhotoEditor SDK default UI.

This is what your Activity should look like. Follow the steps below to understand the individual workflow:

```java
public class MyActivity extends Activity implements PermissionRequest.Response {

    public static int CAMERA_PREVIEW_RESULT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

		SettingsList settingsList = new SettingsList();
		settingsList
					// Set custom camera export settings
                    .getSettingsModel(CameraSettings.class)
                    .setExportDir(Directory.DCIM, FOLDER)
                    .setExportPrefix("camera_")
					// Set custom editor export settings
                    .getSettingsModel(EditorSaveSettings.class)
                    .setExportDir(Directory.DCIM, FOLDER)
                    .setExportPrefix("result_")
                    .setSavePolicy(
                         EditorSaveSettings.SavePolicy.KEEP_SOURCE_AND_CREATE_ALWAYS_OUTPUT
                    );
             
            new CameraPreviewBuilder(this)
                    .setSettingsList(settingsList)
                    .startActivityForResult(this, CAMERA_PREVIEW_RESULT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, android.content.Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == CAMERA_PREVIEW_RESULT) {
            String resultPath = 
                   data.getStringExtra(ImgLyIntent.RESULT_IMAGE_PATH);
            String sourcePath =
                   data.getStringExtra(ImgLyIntent.SOURCE_IMAGE_PATH);

            if (resultPath != null) {
	            // Scan result file
                File file =  new File(resultPath);
                Intent scanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                Uri contentUri = Uri.fromFile(file);
                scanIntent.setData(contentUri);
                sendBroadcast(scanIntent);
            }

            if (sourcePath != null) {
                // Scan camera file
                File file =  new File(sourcePath);
                Intent scanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                Uri contentUri = Uri.fromFile(file);
                scanIntent.setData(contentUri);
                sendBroadcast(scanIntent);
            }

            Toast.makeText(this, "Image Save on: " + resultPath, Toast.LENGTH_LONG).show();
        }
    }

    //Important for Android 6.0 and above permisstion request, don't forget this!
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
        // The Permission was rejected by the user, so the Editor was not opened because it can not save the result image.
        // TODO for you: Show a Hint to the User
    }
}
```

__Do not forget to delegate the onRequestPermissionsResult to PermissionRequest.onRequestPermissionsResult. Otherwise it will not work on Android 6.0 and above.__

##### 5.1. Start Camera Preview Activity with editor backend.

```java

public class MyActivity extends Activity implements PermissionRequest.Response {
    
    // ...
    
    // Start camera with editor access.
    public void startCamera() {
        SettingsList settingsList = new SettingsList();
        settingsList
            // Set custom camera export settings
            .getSettingsModel(CameraSettings.class)
            .setExportDir(Directory.DCIM, FOLDER)
            .setExportPrefix("camera_")
            
            // Set custom editor export settings
            .getSettingsModel(EditorSaveSettings.class)
            .setExportDir(Directory.DCIM, FOLDER)
            .setExportPrefix("result_")
            .setSavePolicy(
                 EditorSaveSettings.SavePolicy.RETURN_ALWAYS_ONLY_OUTPUT
            );
            
        // customizeMyConfig(settingsList);
             
        new CameraPreviewBuilder(this)
                .setSettingsList(settingsList)
                .startActivityForResult(this, CAMERA_PREVIEW_RESULT);
    }
    
    // ...
}
```

##### 5.2. Start Editor Activity standalone.

```java
public class MyActivity extends Activity implements PermissionRequest.Response {
    
    // ...
    
    // Start editor only
    public void startEditor() {
        String myPicture = "PATH_TO_THE_IMAGE";
        
        settingsList
            .getSettingsModel(EditorLoadSettings.class)
            .setImageSourcePath(myPicture, true) // Load with delete protection true!
            
            .getSettingsModel(EditorSaveSettings.class)
            .setExportDir(Directory.DCIM, FOLDER)
            .setExportPrefix("result_")
            .setSavePolicy(
                EditorSaveSettings.SavePolicy.KEEP_SOURCE_AND_CREATE_ALWAYS_OUTPUT
            );
            
        // customizeMyConfig(settingsList);  
        
        new PhotoEditorBuilder(this)
            .setSettingsList(settingsList)
            .startActivityForResult(this, CAMERA_PREVIEW_RESULT);
    }
    
    // ...
}   
```

##### 6. Customize SDK config for your own Android App.

```java
// Custom toolkit modifications can be done by modify the SettingsList.
public class MyActivity extends Activity implements PermissionRequest.Response {
    // ...
    public void customizeMyConfig(SettingsList settingsList) {
       
        settingsList.getConfig().setTools(
            new CropEditorTool(R.string.tool_name_crop, R.drawable.icon_tool_crop),
            new OrientationEditorTool(R.string.tool_name_rotate, R.drawable.icon_tool_rotate),
            new Divider(),
            new FilterEditorTool(R.string.tool_name_filter, R.drawable.icon_tool_filters),
            new ColorAdjustmentTool(R.string.tool_name_adjust, R.drawable.icon_tool_adjust),
            new Divider(),
            new TextEditorTool(R.string.tool_name_text, R.drawable.icon_tool_text),
            new StickerEditorTool(R.string.tool_name_sticker, R.drawable.icon_tool_sticker),
            new Divider(),
            new FocusEditorTool(R.string.tool_name_focus, R.drawable.icon_tool_focus),
            new Divider(),
            new BrushEditorTool(R.string.tool_name_brush, R.drawable.icon_tool_brush)
        ).setStickerLists (
            new StickerCategoryConfig(
                "Internal PNG Stickers",
                ImageSource.create(Uri.parse("https://content.mydomain/stickers/external-stickers-category-icon.png")),
                new ImageStickerConfig(
                    R.string.sticker_name_glasses_normal, 
                    R.drawable.sticker_preview_glasses_normal, 
                    R.drawable.sticker_glasses_normal
                ),
                new ImageStickerConfig(
                    R.string.sticker_name_glasses_nerd, 
                    R.drawable.sticker_preview_glasses_nerd, 
                    R.drawable.sticker_glasses_nerd
                ),
                new ImageStickerConfig(
                    R.string.sticker_name_glasses_shutter_green, 
                    R.drawable.sticker_preview_glasses_shutter_green, 
                    R.drawable.sticker_glasses_shutter_green
                ),
                new ImageStickerConfig(
                    R.string.sticker_name_glasses_shutter_yellow, 
                    R.drawable.sticker_preview_glasses_shutter_yellow, 
                    R.drawable.sticker_glasses_shutter_yellow
                )
            ),
            new StickerCategoryConfig(
                "Internal VectorDrawable Stickers",
                ImageSource.create(Uri.parse("https://content.mydomain/stickers/external-stickers-category-icon.png")),
                new ImageStickerConfig(
                        R.string.imgly_sticker_name_toy_drum, 
                        R.drawable.imgly_sticker_toy_drum, 
                        R.drawable.imgly_sticker_toy_drum, 
                        ImageStickerConfig.OPTION_MODE.INK_STICKER
                ),
                new ImageStickerConfig(
                        R.string.imgly_sticker_name_toy_crayons, 
                        R.drawable.imgly_sticker_toy_crayons, 
                        R.drawable.imgly_sticker_toy_crayons, 
                        ImageStickerConfig.OPTION_MODE.INK_STICKER
                )
            ),
            new StickerCategoryConfig(
                "External Stickers",
                ImageSource.create(Uri.parse("https://content.mydomain/stickers/external-stickers-category-icon.png")),
                new ImageStickerConfig(
                        "My External PNG", 
                        ImageSource.create(Uri.parse("https://content.mydomain/stickers/glasses-preview-128x128.png")), 
                        ImageSource.create(Uri.parse("https://content.mydomain/stickers/glasses.png"))
                ),
                new ImageStickerConfig(
                        "External VectorDrawable", 
                        ImageSource.create(Uri.parse("https://content.mydomain/stickers/glasses-vector.xml")), 
                        ImageSource.create(Uri.parse("https://content.mydomain/stickers/glasses-vector.xml"))
                ),
                new ImageStickerConfig(
                        "My File", 
                        ImageSource.create(Uri.fromFile(myPreviewFile)), 
                        ImageSource.create(Uri.fromFile(myFile))
                )
            )
        );
        
        // setMyEventTracker(settingsList);
    }
    
    // ...
}
```

##### 7. Set own event tracker. (e. g. Google Analytics...)

```java
// Custom toolkit modifications can be done by modify the SettingsList.
public class MyActivity extends Activity implements PermissionRequest.Response {
    // ...
    public void setMyEventTracker(SettingsList settingsList) {
        settingsList.setEventTracker(new CustomEventTracker(Application.ANALYTICS_TRACK_ID));
    }
    // ...
}

```

See [CustomEventTracker.java](./example_files/CustomEventTracker.java) for an example implementation.

## Troubleshooting

Sometimes Android Studio decides to import `PermissionRequest.Response` from `android.webkit.PermissionRequest`. Make sure you import `ly.img.android.ui.utilities.PermissionRequest` instead.
