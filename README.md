


<p align="center">
    <a target="_blank" href="https://www.photoeditorsdk.com/?utm_campaign=Projects&utm_source=Github&utm_medium=PESDK&utm_content=Android-Demo&utm_term=Android"><img src="http://static.photoeditorsdk.com/logo.png" alt="PhotoEditor SDK Logo"/></a>
</p>
<p align="center">
  <a href="http://developer.android.com/guide/topics/manifest/uses-sdk-element.html#ApiLevels">
    <img src="https://img.shields.io/badge/MIN_SDK-15-B8D529.svg?style=flat">
    <img src="https://img.shields.io/badge/BUILD_SDK-25-92D230.svg?style=flat">
  </a>
  <a href="https://www.photoeditorsdk.com/documentation/android/getting-started">
    <img src="https://img.shields.io/badge/platform-android-2DC25C.svg?style=flat">
  </a>
  <a href="https://artifactory.9elements.com/artifactory/imgly/ly/img/android/photo-editor-sdk/">
    <img src="https://api.photoeditorsdk.com/badges/android.svg?" alt="Maven">
  </a>
  <a href="http://twitter.com/PhotoEditorSDK">
    <img src="https://img.shields.io/badge/twitter-@PhotoEditorSDK-8646E2.svg?style=flat" alt="Twitter">
  </a>
  <a href="https://pesdk-slack.herokuapp.com/">
    <img src="https://pesdk-slack.herokuapp.com/badge.svg" alt="Slack Status">
  </a>
</p>



# About PhotoEditor SDK for Android

## Overview

The [PhotoEditor SDK](https://www.photoeditorsdk.com/?utm_campaign=Projects&utm_source=Github&utm_medium=PESDK&utm_content=Android-Demo&utm_term=Android) is a powerful and multifaceted tool which enables you to equip your Android application with high-performant photo editing capabilities. The PhotoEditor SDK is written in Java and can easily be customized to entirely blend with your CI and provide your users with the exact feature set your use-case requires. 

The SDK ships with a large variety of filters, covering all state of the art style- and mood settings that can be previewed in real-time. Unlike other apps that allow a live preview of filters, the [PhotoEditor SDK](https://www.photoeditorsdk.com/?utm_campaign=Projects&utm_source=Github&utm_medium=PESDK&utm_content=Android-Demo&utm_term=Android) even provides a live preview when using high-resolution images.

All operations are non-destructive which allows for fast and uncomplicated revision of the creatives at any given time and creates an intuitive and creative workflow for your users. Please see Features for a detailed list of the photo editing tools included in the [PhotoEditor SDK](https://www.photoeditorsdk.com/?utm_campaign=Projects&utm_source=Github&utm_medium=PESDK&utm_content=Android-Demo&utm_term=Android).



<a href="https://play.google.com/store/apps/details?id=com.photoeditorsdk.android.app&utm_source=global_co&utm_medium=prtnr&utm_content=Mar2515&utm_campaign=PartBadge&pcampaignid=MKT-Other-global-all-co-prtnr-py-PartBadge-Mar2515-1">
    <img height="60" alt="Get it on Google Play" src="https://play.google.com/intl/en_us/badges/images/generic/en-play-badge-border.png" >
</a>

## License 
The PhotoEditorSDK is a product of 9elements GmbH. Please make sure that you have a commercial [license](https://www.photoeditorsdk.com/pricing/?utm_campaign=Projects&utm_source=Github&utm_medium=PESDK&utm_content=Android-Demo&utm_term=Android) before releasing your app. A commercial license is required if you would like to integrate the SDK into any app, regardless of whether you monetize directly (paid app, subscription, service fee), indirectly (advertising, etc.) or are developing a free app. Every license for the PhotoEditor SDK is valid for one product only unless the products are closely related.

If you’d like to use the PhotoEditor SDK for a charitable project, you can do so free of charge. However, please contact us anyway, so we can evaluate whether you qualify for a non-commercial license or not and handle your request accordingly. 

Please [get in touch](https://www.photoeditorsdk.com/pricing/?utm_campaign=Projects&utm_source=Github&utm_medium=PESDK&utm_content=Android-Demo&utm_term=Android) if you’d like to purchase a commercial license or require further information on our pricing and services. Please see the included [LICENSE.md](./LICENSE.md) for licensing details.


## Features

* Over 60 handcrafted **Filters** covering all state of the art style- and mood settings to choose from. 
* Design custom filters in Photoshop and other apps: The API of the PhotoEditor SDK enables you to expand the filter library with your own set of custom filters to define a unique visual language. Custom filters can easily be created by anyone using LUTs (Lookup Tables) from popular apps like Photoshop, GIMP or Lightroom. Design your filter and apply it onto the provided identity image. That will 'record' the filter response, now simply save it and add it as a new filter. Done. 
* An **Overlay** Tool that can be used to create neat lighting effects like lens flare or bokeh but also to furnish pictures with textures like crumpled paper or plaster. You can easily expand the library by importing your own set of overlay assets.  
* An **Adjustment section** that holds both essential and advanced photo editing features like brightness, contrast, saturation, clarity etc. that help tweak and fine tune images to create stunning creatives. 
* A **Transform section** that unifies cropping, flipping and rotation in one feature.  
* The robust **Text Feature** provides all necessary functions for quickly adding text to any picture or creative. The corresponding font library can easily be exchanged, reduced, or expanded.
* A categorized **Sticker library** whose UI is optimized for exploration and discovery. You can easily complement the library with your own custom sticker packages.
* A **Frame Tool** that works with any given photo size or ratio.   
* A high performant **Brush Engine** optimized for touch screen that supports different brush strokes.  
* A **Photo Roll** equipped with a wide range of stock photography and templates with presorted categories. The API allows for easy expansion, reduction and rearrangement of the assets. 
* A clean and intuitive **UI** that ensures an unhindered flow of creativity and a seamless experience while composing creatives. The UI is designed to be customized to completely match your CI and blend with your app. 
* You can strip out every feature you deem unnecessary to provide your users with the exact feature set your use case requires.

* __Android API Level 16+__ Covers nearly 99% of all Android devices with touchscreen.
* __Fast image export up to 4294 MegaPixel__
* __Generic camera support__ for most Android phones.
* __Tablet support__: The PhotoEditor SDK uses auto layout for its views and adapts to each screen size.
* **Non/destructive features and effects:** Quickly revise, redo or even discard your work. 


## SDK Core

__ACS Component__ <br/>
A generic Android Camera Stack library which is based on the [android.hardware.Camera](http://developer.android.com/reference/android/hardware/Camera.html) API. Supports front and rear cam, HDR, flash modes and much more.

__SDK Component__ <br/>
The PhotoEditor core library for Android. Containing the OpenGL and toolkit implementation.

__UI Component__ <br/>
The default UI components consisting of LivePreview and Editor Activity.

### Dependencies

Two Google support libraries needed or used by the SDK.

* com.android.support:recyclerview-v7
* com.android.support:support-annotations


## Documentation

For a detailed documentation, please take a look [here](http://docs.photoeditorsdk.com/guides/android/?utm_campaign=Projects&utm_source=Github&utm_medium=PESDK&utm_content=Android-Demo&utm_term=Android).


## Author

9elements GmbH, [@PhotoEditorSDK](https://twitter.com/PhotoEditorSDK), [www.photoeditorsdk.com](https://www.photoeditorsdk.com/?utm_campaign=Projects&utm_source=Github&utm_medium=PESDK&utm_content=Android-Demo&utm_term=Android)


## Installation

> This SDK requires a minimum deployment target of Android API 15 (4.0.4) and Device with HardwareLayer (for LivePreview), but our official support is starting with API 16 (4.1)


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

/* Optional if you do not use the build-processor see below */
apply plugin: 'com.neenbedankt.android-apt' 

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

        /* Set the target SDK Version at minimum to 25 or higher */
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
    compile 'ly.img.android:photo-editor-sdk:5.0.16'
    
    /* This is optional if you do not want use an `EventTracker` and do not extend our SDK, otherwise it is required. 
     * don't forget to apply the APT plugin see above
     */
    apt 'ly.img.android:build-processor:5.0.16' 
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

Before using any components of the Photo Editor SDK, you have to add your license key file to your application assets folder.
The default name of the license file is "LICENSE" change this by calling `PESDK.init(this, "FILENAME");` instead of `PESDK.init(this);`  

The license is digitally signed so it can not be altered without becoming invalid. Our sample app comes with its own license, so you can try that right away. To try our SDK in your own app, you need to request a trial license because a license is bound to a bundle identifier. You can get a free trial license [here](https://www.photoeditorsdk.com/users/new/?utm_campaign=Projects&utm_source=Github&utm_medium=Side_Projects&utm_content=Android-Demo). In case you want to purchase a license, please get in contact with our Sales Team [here](https://www.photoeditorsdk.com/pricing/?utm_campaign=Projects&utm_source=Github&utm_medium=Side_Projects&utm_content=Android-Demo).

Once you have the license file it can be used to unlock the view controller. The following example demonstrates unlocking the SDK.

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
        settingsList.setEventProcessor(your.packagename.PESDKEvents.clss);
        settingsList.setEventTracker(new CustomEventTracker(Application.ANALYTICS_TRACK_ID));
    }
    // ...
}

```

See [CustomEventTracker.java](./example_files/CustomEventTracker.java) for an example implementation.

## Troubleshooting

Sometimes Android Studio decides to import `PermissionRequest.Response` from `android.webkit.PermissionRequest`. Make sure you import `ly.img.android.ui.utilities.PermissionRequest` instead.
