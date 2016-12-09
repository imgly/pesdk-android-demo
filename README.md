

<p align="center">
  <img src="https://camo.githubusercontent.com/4c4c8d90e242619972a11baa3c33acaaeb9bad00/687474703a2f2f692e696d6775722e636f6d2f666748314852742e706e67" />
</p>
<p align="center">
  <a href="http://developer.android.com/guide/topics/manifest/uses-sdk-element.html#ApiLevels">
    <img src="https://img.shields.io/badge/MIN_SDK-15-B8D529.svg?style=flat">
    <img src="https://img.shields.io/badge/BUILD_SDK-24-92D230.svg?style=flat">
  </a>
  <a href="https://www.photoeditorsdk.com/documentation/android/getting-started">
    <img src="https://img.shields.io/badge/platform-android-2DC25C.svg?style=flat">
  </a>
  <a href="https://artifactory.9elements.com/artifactory/imgly/ly/img/android/photo-editor-sdk/">
    <img src="https://www.photoeditorsdk.com/documentation/badge.svg?type=android_latest" alt="Maven">
  </a>
  <a href="http://twitter.com/PhotoEditorSDK">
    <img src="https://img.shields.io/badge/twitter-@PhotoEditorSDK-8646E2.svg?style=flat" alt="Twitter">
  </a>
</p>
## img.ly SDK for Android


img.ly SDK for Android is for creating stunning images with a nice selection of premium filters.

<a href="https://play.google.com/store/apps/details?id=com.photoeditorsdk.android.app&utm_source=global_co&utm_medium=prtnr&utm_content=Mar2515&utm_campaign=PartBadge&pcampaignid=MKT-Other-global-all-co-prtnr-py-PartBadge-Mar2515-1"><img height="60px" alt="Get it on Google Play" src="https://play.google.com/intl/en_us/badges/images/generic/en-play-badge-border.png" /></a>

### Overview

The img.ly Photo Editor SDK provides a variety of tools and functions for creating photo applications for Android. It is licensed under a proprietary license and intended to be used as better alternative for free software applications such as the [GPUImage](https://github.com/CyberAgent/android-gpuimage) or similiar libraries. If you are interested in our SDK, please [contact us](#author--contact).

#### SDK Core

__ACS Component__ <br/>
A generic Android Camera Stack library which is based on the [android.hardware.Camera](http://developer.android.com/reference/android/hardware/Camera.html) API. Supports front and rear cam, HDR, flash modes and much more.

__SDK Component__ <br/>
The img.ly core library for Android. Containing the OpenGL and toolset implementation.

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
* __57 Stunning filters__. which are builtin and work out of the box.
* __No native code__. Our backend is Renderscript based with highlevel OpenGL support, therefore we dodge all the nasty native library problems other frameworks face.
* __Tablet support__. Works great on tablets.
* __Photoshop LUT__. Design color filters in Photoshop!
With this feature it is possible to generate LUT (Look Up Table) color filters easily from different photo
editing tools. Export and integrate them in minutes!
* __Live Preview__. Filters can be previewed in high quality at realtime.
* __Low memory footprint__. even with high resolution images.
* __Extensible and customizable toolset interface__. Add your own customized filters with [Renderscript](https://developer.android.com/guide/topics/renderscript/index.html) and modify tool properties yourself.

### License

img.ly SDK for Android is a licensed library which can be used for different purposes. <br/>
Please see:

 [LICENSE.PROPIETARY](https://github.com/imgly/imgly-sdk-android/blob/master/LICENSE.PROPIETARY) for PROPIETARY usage.

### Author & Contact

&copy; 9elements GmbH <br/>
[Email](mailto:eray.basar@9elements.com) <br/>
[Homepage](http://www.9elements.com) <br/>
[Follow us on Twitter](https://twitter.com/9elements)

## Installation

> This SDK requires a minimum deployment target of Android API 15 (4.0.4) and Device with HardwareLayer (for LivePreview) and LargeHeap Support (to operate and export large images)


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


##### 2. Configure your Module build.gradle to import the img.ly SDK into your project with jCenter.

There are few things we'll need to add here.
See comments in the example code below.

__DO NOT FORGET TO ADD RENDERSCRIPT SUPPORT!__

```groovy
apply plugin: 'com.android.application'

android {
    /* Set the Compile SDK and the Build SDK min. at SDK 23 or grater. */
    compileSdkVersion 24
    buildToolsVersion '24.0.3'

    /* If you update from SDK 22 and below, the ApacheHttp-Library are removed by Google,
     * if you need the ApacheHttp-Library comment out the next line */
    // useLibrary 'org.apache.http.legacy'

    defaultConfig {
        /* Replace with your App-ID @see http://tools.android.com/tech-docs/new-build-system/applicationid-vs-packagename */
        applicationId "my.domain.application"

        /* Set the minimum supported SDK Version to 15 (Android 4.0.3) or higher */
        minSdkVersion 15

        /* Set the target SDK Version at minimum to 24 or higher */
        targetSdkVersion 24

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

    /* Enables support for the Android Data Binding Library. Without it
     * the app will crash! */
    dataBinding {
        enabled = true
    }

    ...
}

dependencies {
    /* Make sure you are import the latest SDK version */
    compile 'ly.img.android:photo-editor-sdk:2.0.26'
}

...

```

Be also sure to sync your project with the Gradle files after making any edits.

For more information about gradle look at http://developer.android.com/tools/building/configuring-gradle.html

##### 3. Initialize SDK in an Application class.

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.example.mymodule.app" >

    <application
        android:name=".Application"
        ... >

        ...
    </application>

</manifest>

```

```java
public class Application extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();

        ImgLySdk.init(this);
    }
}
```

##### 4. Start img.ly SDK default UI.

This is what your Activity should look like. Follow the steps below to understand the individual workflow:

```java
public class MainActivity extends Activity implements PermissionRequest.Response{

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
                         EditorSaveSettings.SavePolicy.RETURN_ALWAYS_ONLY_OUTPUT
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

##### 4.1. Start Camera Preview Activity with editor backend.

```java
// Camera activity with customized editor access.
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
     
    new CameraPreviewBuilder(this)
            .setSettingsList(settingsList)
            .startActivityForResult(this, CAMERA_PREVIEW_RESULT);
```

##### 4.2. Start Editor Activity standalone.

```java
String myPicture = "PATH_TO_THE_IMAGE"
settingsList
	.getSettingsModel(EditorLoadSettings.class)
	.setImageSourcePath(myPicture, true) // Load with delete protection true!
	
	.getSettingsModel(EditorSaveSettings.class)
	.setExportDir(Directory.DCIM, FOLDER)
	.setExportPrefix("result_")
    .setSavePolicy(
	    EditorSaveSettings.SavePolicy.KEEP_SOURCE_AND_CREATE_ALWAYS_OUTPUT
	);

	new PhotoEditorBuilder(this)
	    .setSettingsList(settingsList)
	    .startActivityForResult(this, CAMERA_PREVIEW_RESULT);
```

##### 5. Customize SDK config for your own Android App.

```java
// Custom toolset modifications can be done by modify the SettingsList.

SettingsList settingsList = new SettingsList();
settingsList.setEventTracker(new CustomEventTracker(Application.ANALYTICS_TRACK_ID))
settingsList.getConfig().setTools(
    new CropEditorTool(R.string.tool_name_crop, R.drawable.icon_tool_crop),
    new OrientationEditorTool(R.string.tool_name_rotate R.drawable.icon_tool_rotate),
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
).setSticker(
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
);
```

##### 6. Set own event tracker. (e. g. Google Analytics...)

```java
// Custom toolset modifications can be done by modify the SettingsList.

SettingsList settingsList = new SettingsList();
settingsList.setEventTracker(new CustomEventTracker(Application.ANALYTICS_TRACK_ID))

//... set this SettingsList to a builder.

```
See [CustomEventTracker.java](./app/src/main/java/com/photoeditorsdk/android/app/CustomEventTracker.java) for an example implementation.


## Troubleshooting

Sometimes Android Studio decides to import `PermissionRequest.Response` from `android.webkit.PermissionRequest`. Make sure you import `ly.img.android.ui.utilities.PermissionRequest` instead.
