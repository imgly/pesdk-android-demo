![img.ly](http://i.imgur.com/EC8walN.png)

## img.ly SDK for Android

img.ly SDK for Android is for creating stunning images with a nice selection of premium filters.

### Overview

The img.ly Photo Editor SDK provides a variety of tools and functions for creating photo applications for Android. It is dual-licensed under AGPLv3 for non-proprietary usage and intended to be used as better alternative for free software applications such as the [GPUImage](https://github.com/CyberAgent/android-gpuimage) or similiar libraries. If you are interested in our SDK for proprietary application (copyleft free), please [contact us](#author--contact).

#### SDK Core

__ACS Component__ <br/>
A generic Android Camera Stack library which is based on the [android.hardware.Camera](http://developer.android.com/reference/android/hardware/Camera.html) API. Supports front and rear cam, HDR, flash modes and much more.

__SDK Component__ <br/>
The img.ly core library for Android. Containing the OpenGL and filter implementation.

__UI Component__ <br/>
The default UI components constisting of LivePreview and Editor Activity.

#### Dependecies
External libraries needed by the SDK.

__Colorpicker__ <br/>
An Android [Colorpicker Library](https://github.com/QuadFlask/colorpicker) licensed under Apache v2.0.

__AndroidSVG__ <br/>
An Android [SVG Library](https://github.com/BigBadaboom/androidsvg) licensed under Apache v2.0.

### Features

* __Android API Level 15+__. Covers nearly 95% of all Android devices.
* __Default UI__ for camera preview and editing. Based on Intents and Activities.
* __Fast image export__. Even with large images or slow devices the export is done in adequate time with background processing.
* __Generic camera support__. Integrated and featureful on the most Android phones.
* __Crop__, __Rotate__, __Stickers__, __Text Placement__, and __Colorize__. All essential photo editing functions wrapped into a simple, beautiful and customizable UI.
* __57 Stunning filters__ which are builtin and work out of the box.
* __Open Source__ with AGPLv3. Want to change anything? Go ahead, we provide the full source code.
* __No native code__. Our backend is Renderscript based with highlevel OpenGL support, therefore we dodge all the nasty native library problems other frameworks face.
* __Tablet support__. Works great on tablets.
* __Photoshop LUT__. Design filters in Photoshop!
With this feature it is possible to generate LUT (Look Up Table) filters easily from different photo
editing tools. Export and integrate them in minutes!
* __Live Preview up to 20mpx__. Filters can be previewed in realtime on full camera resolution.
* __Low memory footprint__ even with high resolution images.
* __Extensible and customizable toolset interface__. Add your own customized filters with [Renderscript](https://developer.android.com/guide/topics/renderscript/index.html) and modify tool properties yourself.

### License

img.ly SDK for Android is a dual-licensed library which can be used for different purposes. <br/>
Please see:

 [LICENSE.AGPL](https://github.com/imgly/imgly-sdk-android-demo/blob/master/LICENSE.AGPL) for NON-PROPIETARY usage. <br/>
 [LICENSE.PROPIETARY](https://github.com/imgly/imgly-sdk-android-demo/blob/master/LICENSE.PROPIETARY) for PROPIETARY usage.

### Author & Contact

&copy; 9elements GmbH <br/>
[Email](mailto:eray.basar@9elements.com) <br/>
[Homepage](http://www.9elements.com) <br/>
[Follow us on Twitter](https://twitter.com/9elements)

## Installation

> Require a minimum deployment target of Android API 15 (4.0.4) and Device with HardwareLayer and LargeHeap Support


##### 1. Import the img.ly SDK into your project with jcenter.
```
apply plugin: 'com.android.application'
...

repositories {
    ...
    jcenter()
}

android {
    compileSdkVersion 23
    buildToolsVersion "23.0.1"
    minSdkVersion 15
	...

    defaultConfig {
        ...
        renderscriptTargetApi 23
        renderscriptSupportModeEnabled true
    }
    ...
}


dependencies {

    compile 'ly.img.android:photo-editor-sdk:1.0.0'
    ...
}
```

##### 2. Initialize SDK in an Applicaton class.
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


##### 3.1. Start img.ly SDK default UI.

```java
public class MainActivity extends Activity {

    public static int CAMERA_PREVIEW_RESULT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new CameraPreviewIntent(this)
                .setExportDir(CameraPreviewIntent.Directory.DCIM, "ImgLyExample")
                .setExportPrefix("example_")
                .setEditorIntent(
                        new PhotoEditorIntent(this)
                        .setExportDir(PhotoEditorIntent.Directory.DCIM, "ImgLyExample")
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

        }
    }
}
```

##### 3.2. Start Camera Preview Activity with editor backend.

```java
// Camera activity intent with customized editor access.
        new CameraPreviewIntent(this)
        .setExportPrefix("example_")
        .setEditorIntent(
            new PhotoEditorIntent(this)
            .setExportDir(PhotoEditorIntent.Directory.DCIM, "ImgLyExample")
            .setExportPrefix("result_")
            .destroySourceAfterSave(true)
        )
        .setExportDir(CameraPreviewIntent.Directory.DCIM, "ImgLyExample")
        .startActivityForResult(CAMERA_PREVIEW_RESULT);
```

##### 3.2. Start Editor Activity standalone.

```java
// Editor activity intent.
        new PhotoEditorIntent(this)
        .setSourceImagePath(imagePath)
        .setExportDir(PhotoEditorIntent.Directory.DCIM, "ImgLyExample")
        .setExportPrefix("result_")
        .destroySourceAfterSave(true)
        .startActivityForResult(CAMERA_PREVIEW_RESULT);
```

##### 4. Customize SDK tools for your own Android App.

```java
// Toolset modifications can be done easily inside the Application class.
public class Application extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();

        ImgLySdk.init(this);

        // Step1 get current configuration.
        ArrayList<CropAspectConfig> cropConfig = PhotoEditorSdkConfig.getCropConfig();

        // Step2 clear it.
        cropConfig.clear();

        // Step3 add the needed properties and filters for different tools.
        cropConfig.add(new CropAspectConfig(R.string.imgly_crop_name_custom, R.drawable.icon_crop_custom, -1));
        cropConfig.add(new CropAspectConfig(R.string.imgly_crop_name_4_3, R.drawable.icon_crop_4_3, 4/3));

        // Step4 done!

        // More examples:
        ArrayList<ColorFilter> colorFilterConfig = PhotoEditorSdkConfig.getFilterConfig();

        colorFilterConfig.clear();

        colorFilterConfig.add(new NoneColorFilter());
        colorFilterConfig.add(new ColorFilterBleached());
        colorFilterConfig.add(new ColorFilterChest());

        ArrayList<ImageStickerConfig> stickers = PhotoEditorSdkConfig.getStickerConfig();

        stickers.clear();

    }
}
```
