# PhotoEditor SDK - Changelog

## v7.0.6

### Fixed
* Some focus operations are not correctly serialized.
* Frame disappears after accepting in frame tool panel.
* Color pipette picks are not reverted after cancel.
* NPE if brush is not part of the license feature list.
* Tool list can't be changed.
* migration task crash with `No signature of method: java.lang.String.findIndexOf()` 

## v7.0.5

### Improved
* Video decoding speed.
* JPEG encoding speed.

### Fixed
* Layer randomly not serialized (sometimes cause crashes).
* Sticker position is wrong after loading a serialisation.
* NPE with some limited licenses combinations. 
* StickerCategoryItem equals method ignores id. 
* The default focus highlight rect appears sometimes.
* Text disappears unexpectedly after cancel font or color changes.
* TextDesign sometimes shifted after loading form serialization.
* Sticker not movable after click event.
* After leaving the transform tool the image is not fit to screen.
* Random NPE in Focus Tool.

## v6.6.3

### Fixed
* StickerCategoryItem equals method ignores id.
* The default focus highlight rect appears sometimes.
* Text disappears unexpectedly after cancel font or color changes.
* TextDesign sometimes shifted after loading form serialization.
* Sticker not movable after click event.
* Random crash after fast tool change. 
* After leaving the transform tool the image is not fit to screen.
* Random NPE in Focus Tool.

## v7.0.4

### Fixed
* SaveSettings are ignored.
* Sticker color can not applied under specific conditions.
* Image zoom out after option changes.
* Unwanted painting on the image after zoom with activated brush tool.
* Native Android crash on some devices after change the brush size.
* TextDesign layout do not change in the UI after switching to another the TextDesign sticker.

### Changed
* Updated kotlin version to `1.3.50`.
* Internal use of build tools `29.0.2`

### Added
* Missing feature flag from v5 `TextGlLayer.BOUNDING_BOX_WIDTH_AUTO_FIT` if false the bounding box is not auto fitting after font changes.

## v6.6.2

### Fixed
* The opacity adjustment of stickers cannot be undone.
* Sticker color can not applied under specific conditions.
* Image zoom out after option changes.
* Unwanted painting on the image after zoom with activated brush tool.
* Brush History is broken.
* Native Android crash on some devices after change the brush size.
* TextDesign layout do not change in the UI after switching to another the TextDesign sticker.

### Added
* Missing feature flag from v5 `TextGlLayer.BOUNDING_BOX_WIDTH_AUTO_FIT` if false the bounding box is not auto fitting after font changes.


## v7.0.3

### Fixed
* The opacity adjustment of stickers cannot be undone.
* Error: `package android.support.annotation does not exist` when using AndroidX.

## v7.0.2

### Fixed
* Blur tile glitch.
* Export canceled in background.
* Filter preview sometimes broken.
* Missing thumbnail item in overlay.
* Battery drain on some devices.
* MathUtils.wrapTo360() is outside of range if value is negative.

## v7.0.1

### Fixed
* OOM if device report a too high maxTextureSize.
* crash on some older devices.
* Some gradle build issues.
* Brush is not drawn with the selected color.
* Wrong preview if image or video is rotated. 
* Kotlin extension `(Video|Photo)EditorSettingsList.configure<>{}` has return `SettingsList` instead of `(Video|Photo)EditorSettingsList`. 

## v7.0.0

###Added
* First release of VideoEditorSDK [videoeditorsdk.com](https://videoeditorsdk.com).
* You can use SourceType.detectTypeSafe() on WorkerThread to detect supported images and videos now.
* You can use SourceType.detectTypeFast() on AnyThread to detect images and videos by name.
* The gradle `pesdkConfig` is deprecated, but still compatible for PESDK, please use `imglyConfig` in the future.
* `VideoEditorBuilder` to create VESDK instances.

### 🚨 Changed
* Removed RenderScript support lib.
* Renderer now use OpenGl and a C++ JPEG compressor instead of RenderScript.
* `PESDKEvents.*` are deprecated now, and Events using Strings instead of enums now, *we will provide an gradle auto migration task soon.*
* `EditorSaveSettings` are now PhotoEditorSaveSettings and VideoEditorSaveSettings.
* `SettingsList` are deprecated now use `PhotoEditorSettingsList` and `PhotoEditorSaveSettings` instead.
* `EditorLoadSettings` are deprecated use `LoadSettings` instead.
* Removed `RelativeRectAccurate` class.

### Know limitations
* The Camera do not support video's use the device camera app instead to take photo's and video's

### Fixed
* AndroidX projects crashes.

## v6.6.1

### Fixed
* Deleting the last item of a `DataSourceArrayList` results in an IndexOutOfBoundsException.

## v6.6.0

### Fixed
* Doubled file extension suffix in the export when using names with only one letter.
* Text input field has wrong position, if activity is not in fullscreen mode.
* Broken style of text design color list.

### Added
* New themes `Imgly.Theme`, `Imgly.Theme.NoFullscreen`, `Imgly.Theme.TopActionBar` and `Imgly.Theme.TopActionBar.NoFullscreen` which you can set the fullscreen mode on/off and also the position of the actionbar to the top/bottom.

### 🚨 Changed Layouts (We need that due to an bugfix, please make sure you are using the new version from the default_res_files.)
** Remove wrong `rotation` attribute from `Imgly.PESDK.Editor.Panel.TextDesign.ColorList` style.
*  Changed View class of the text design color list view (`@+id/rv_text_colors`) from `RecyclerView` to `HorizontalListView` in the `imgly_panel_tool_text_design.xml` layout file.

## v6.5.2

### Added
* Drawable alias `imgly_transparent_identity_color_item` and `imgly_transparent_identity_image_bg` which refer to `imgly_transparent_identity` to make them separately customizable.
* 🚨 Drawable `imgly_transparent_identity_alpha_slider`. (Please download the default_res_files of the newest version to accept the new behavior.)
* New color resource `imgly_transform_background_color` for better customization.

### Changed
* Size of some elements of the color picker to achieve equality of the platforms.
* Size of the checker board texture `imgly_transparent_identity` to achieve equality of the platforms.
* Transform crop grid lines can set to 0 to be invisible.

### Fixed
* Typos in the adjustments `Shadows` and `Highlights`.
* Some color identifiers are overwritten incorrectly.

## v6.5.1

### Added
* `Sharpness` as a new adjustment value.

### Changed
* Kotlin version to `1.3.31`.

### Fixed
* TextDesign `Summer Feeling` has no lines (old Serialisation HTML5 and Android < 3.5.0 and iOS < 3.1.1 restore the old design).
* Bounding box of TextDesign `Deko` is too big (old Serialisation HTML5 and Android < 3.5.0 and iOS < 3.1.1 restore the old design).
* `Blacks` and `Whites` adjustment applied to alpha values.
* The serialization will not be serialized / deserialized properly if some modules are not included.
* Resource deprecation warning, keeps alive until restart gradle daemon.

## v5.1.12

### Fixed
* Wrong save policy when using camera.
* Wrong label of sticker color selection tool.

## v6.5.0

### Added
* Example light theme color schema to `imgly_colors.xml` and documentation. Due to a limitation of Android 4, it is currently not possible to change the colors theme at runtime, you need to add these colors at compile time.
* Color identifier to be able to customize in more detail.

### Fixed
* Background thread workload not balanced.
* Thumbnails not shown under certain conditions.
* Wrong tool stack handling.
* Wrong tool event calls on certain conditions. `LEAVE_TOOL` and `ENTER_TOOL` sometimes were in wrong order.
* Wrong PESDK plugin classpath in the `README.md`.

### Changed
* Rename internal RenderScript files to trigger recompiling
* Layout order of `imgly_list_item_filter.xml`, `imgly_list_item_filter_folder_subitem.xml` and `imgly_list_item_overlay.xml`.
* Names of some old color identifier.
* We've removed some legacy styles, make sure you stop using them or that you have a copy from an old version.
** `Imgly.PESDK.Editor.Popup.Brush.HorizontalMargin`
** `Imgly.PESDK.Camera.UI.Container`
** `Imgly.PESDK.Editor.Panel.default.Item.SelectBackground`
** `Imgly.PESDK.Editor.Panel.Overlay.OverlayItem.Value`
** `Imgly.PESDK.Editor.Panel.Transform.Crop`
** `Imgly.PESDK.Widget.GalleryButton`
** `Imgly.PESDK.Widget.GalleryButton.ClickOverlay`
** `Imgly.PESDK.Widget.GalleryButton.PreviewImage`

## v6.4.2

### Fixed
* Typo in the filter name 'High Carp'.

### Changed
* Name of filter 'Gysmo' to 'Colorful'.
* Name of filter 'Cotton Candy' to 'Candy'.
* Name of filter 'High Contrast' to 'Hicon'.
* Order of some filters to be equal to other platforms.

## v6.4.1

### Added
* Support for `com.android.tools.build:gradle` v3.4

## v6.4.0

### Dependency changes
* RenderscriptTargetApi version migrated from 19 to 28.

### Added
* 64bit compatibility.

### Fixed
* Some renderscript crashes.

## v6.3.2

### Fixed
* Incorrect proguard rules prevent proguard from optimizing optimally.

## ~~v6.3.1~~
* Because of an mistake, this release is equal to v6.3.0 and released without any fixes.

### ~~Fixed~~
~~* Wrong proguard rules.~~  

## v6.3.0

### Added
* Folders to the `FilterToolPanel` to group related filters together and to reduce clutter in the menu. To disable folders and get back the old behavior, simply use `getFilterPackWithoutFolders()` from `FilterPackBasic`.
* Duotone filters to default filter pack.

### Fixed
* Wrong save policy when using camera.

### Changed
* Displayed name of a couple of filters.

## v6.2.8

### Added
* `ColorPipetteItem` to color lists.
 
### Fixed
* Issue with multiple layouts.
* Events not fired after replacing a state or a settings class.

## v6.2.7

### Added
* Missing style attribute of `SeekSlider`.

### Fixed
* Wrong parceling of adjust options.

## v6.2.6

### Fixed
* Wrong parceling of tool list.

## v6.2.5

### Added
* Constructor to `PESDKFileReader` to pass `StateHandler` parameter.

## v6.2.4

### Changed
* Parceling from strict type to dynamic type.

### Added
* Setter method for brush color list.

### Fixed
* NPE, when loading broken vector drawables.
* Broken vector drawables.

## v6.2.3

### Fixed
* NPE if not all backend modules are included.
* NPE on load serialization from iOS with enabled autoEnhancement.

## v6.2.2

### Added
* Possibility to use a color pipette.

### Fixed
* Wrong parceling of brush color list.

## v6.2.1

### Fixed
* Wrong renderscript proguard rule for androidx.
* Some vector drawables are broken with android api level 21. 

## v6.2.0

### Fixed
* Sticker Tint mode is converted into ink mode after read a serialization.
* Fatal Exception: java.lang.NumberFormatException: Invalid int: "drawable/%"

### Added
* New `FilterAsset` called `DuotoneFilterAsset`.
* Missing renderscript proguard rule for androidx.

## v6.1.5

### Fixed
* Blacks and Whites Adjustment destroy alpha channel.

### Improved
* Camera preview position calculation for some devices.
* Invalidate renderscript caches.

### Added
* `AcceptTextButton` and `CancelTextButton` to allow text instead of icons in the `ActionBar`.

## v6.1.4

### Fixed
* Exporting image crashes app, when certain text conditions are met.

## v6.1.3

### Fixed
* Exif data is not copied.
* Text alignment configuration is not preserved.
* Style of the transform icon is not identical to other platforms.

## v6.1.2

### Fixed
* The input image is cached, which resulting in the image containing the correct edits, but on a different image than the input image after export.

## v6.1.1

### Fixed
* NullPointerException while entering the `Frame` tool.
* NumberFormatException while inflating layout.
* The editor can not load WebP images.
* Label of the `Original` item in the `Overlay` tool.

### Improved
* Speed to take a picture.

## v5.1.9

### Fixed
* The editor can not load WebP images.
* InflateException in combination with Android Bundles.  

## v6.1.0

### Fixed
* Sticker icons reload by clicking the same sticker category twice.
* The size of the image does not zoom out when you enter the frame tool.

### Added
* Possibility to add more than one `TextDesign` to canvas.
* Possibility to change the background size of an inverted `TextDesign`.
* Keeping the attributes of the most recently created `TextDesign`.
* Keeping the attributes of the mist recently created `Text`
* `FrameOptionToolPanel` which provides frame configuration.
* Possibility to adjust the opacity and width of a `Frame`.
* Possibility to adjust the opacity of a `Sticker`.
* Replace button to `StickerOptionToolPanel`.
* `ToggleAspectItem` which toggle between multiple `CropAspectItem`.
* Option to the `Transform` tool to reset the configurations.
* Option to the `Brush` tool to delete the layer.
* Option to the `Adjustment` tool to reset the configurations.
* Indicator line for the linear blur horizon.
* Visual gab at the snap point of the seek slider.

### Changed
* Default tool order so that the Focus tool is next to the Adjust tool.
* Default order and availability of options in `StickerOptionPanel`, `TextOptionToolPanel` and `TextDesignOptionToolPanel`.
* Styling of `CropAspectItem`, `FilterItem`, `AdjustOption`, `StickerCategoryItem`, `TextDesignItem`, `OverlayItem`, `FrameItem` and `FocusOption`.
* Overlay item toggle through the blend modes on click.
* Multiple icons were replaced across the SDK.
* None overlay item shows image preview instead of icon.

### Breaking-Changes
* The layout has been changed in some details. We offer a compatibility package to support the old layout files we provided in the demo repository.

* Drawable with name `imgly_icon_focus_center_thump` is renamed to `imgly_icon_focus_center_thumb`.

* We have corrected small cross platform deviations of the color
** String with name "pesdk_color_item_00000000" is renamed to "pesdk_common_title_transparentColor"
** String with name "pesdk_color_item_FFFFFFFF" is renamed to "pesdk_common_title_whiteColor"
** String with name "pesdk_color_item_FF7E7E7E" is renamed to "pesdk_common_title_grayColor"
** String with name "pesdk_color_item_FF000000" is renamed to "pesdk_common_title_blackColor"
** String with name "pesdk_color_item_FF30E5E7" is renamed to "pesdk_common_title_lightBlueColor"
** String with name "pesdk_color_item_FF6784FF" is renamed to "pesdk_common_title_blueColor"
** String with name "pesdk_color_item_FF8B60FF" is renamed to "pesdk_common_title_purpleColor"
** String with name "pesdk_color_item_FFE161FF" is renamed to "pesdk_common_title_orchidColor"
** String with name "pesdk_color_item_FFFF64CE" is renamed to "pesdk_common_title_pinkColor"
** String with name "pesdk_color_item_FFFF6688" is renamed to "pesdk_common_title_redColor"
** String with name "pesdk_color_item_FFE74E49" is renamed to "pesdk_common_title_orangeColor"
** String with name "pesdk_color_item_FFF4744D" is renamed to "pesdk_common_title_goldColor"
** String with name "pesdk_color_item_FFFFCD62" is renamed to "pesdk_common_title_yellowColor"
** String with name "pesdk_color_item_FFC8FF5F" is renamed to "pesdk_common_title_oliveColor"
** String with name "pesdk_color_item_FF7EFF60" is renamed to "pesdk_common_title_greenColor"
** String with name "pesdk_color_item_FF42FFDC" is renamed to "pesdk_common_title_aquamarinColor"
** String with name "pesdk_color_item_FF3FFF84" is now unused and removed. 

## v6.0.9

### Fixed
* NullPointerException while changing text configuration.

## v6.0.8

### Fixed
* ArithmeticException in some cases while using dynamic frames.

## v6.0.7

### Fixed
* The overlay intensity was ignored by some blend modes.

## v6.0.6

### Fixed
* Unknown JPEG type cause Crash

## v6.0.5

### Added
* Adjustments: temperature, whites and blacks

### Fixed
* Crash after export if watermark is enabled.
* Some classes are not protected against proguard, which cause in runtime crashes.
* The title bar and therefore the confirm button of the Text and Text Design Tool is not reachable on some phones with a "notch".
* Export of Images with transparency is sometimes not correct. 

### Known issues
* We are currently working on AndroidX support. Due to a bug in the Android RenderScript Support libraries we are actively looking into a workaround. 

## v6.0.4

### Added
* The SDK can now be used for testing purposes without unlocking it with a license. A watermark image will be displayed above the edited photo in such cases. (Simply remove `licencePath` from `pesdkConfig`)

## v6.0.3

### Fixed
* Stickers are sometimes blurry or do not load.

## v5.1.8

### Fixed
* Fix compiler error in newer gradle versions.
* Stickers are sometimes blurry or do not load.

## v6.0.2

### Fixed
* Crash with some png color formats.
* Plugin does not support `kotlin-kapt`.
* Preview quality is bad on some pictures.
* Latest text state (Colors and Font) is not restored.

## v5.1.7

### Fixed
* Crash with some png color formats.

## v6.0.1

### Changed
* You can remove `pesdkVersion` from the `pesdkConfig` in you gradle config, the default value is now the plugin version.

### Fixed
* Missing ForceCrop option.
* Missing ForceCrop Guides on the Page.
* Missing `clear()` options for the assets.
* Missing Kotlin dependency.
* Crash without troubleshoot info, while creating the SettingList()

## v6.0.0

__PLEASE LOOK AT THE NEW DOCUMENTATION MANY THINGS HAS CHANGED__

### Added
* Licenced new feature "TextDesign", get a trail licence to test it.
* Background exporting without UI.
* Gradle integration plugin.
* You can create and start the Editor with different Themes.
* Two new Focus Modes.
* Gamma adjustment.

### Changed
* Java version to 1.8.
* Android SDK to 27.
* Use of Gradle 3.1.3 build plugin for faster builds.
* Split features into separate modules and asset packs, this makes it easier to control the sdk size.
* Refactored Layouts and styles.
* Rework of the focus tool with better single touch control.
* UI is now optional and can completely replaced by your own UI.
* The awkward tool class concept is removed, all functionality is now in the backend.
* You can now add your own operations (you need to understand OpenGl and Renderscript).
* Settings and state classes can be extended with your own code.

### Fixed
* Color mismatch at export.
* Some blurry in the focus area.
* Focus preview broken on some low end devices.
* Cross platform issues with the serialisation.
* `RecyclerView` sometimes scrolls in the wrong position.

## v5.1.5

### Fixed
* SDK crashes if there is a missing font in the serialisation.
* Radial focus creates an oval blur on non-rectangular images.
* ArithmeticException if device's display is too small like on smartwatches.

## v5.1.4

### Fixed
* Alpha blending produces dark glow.
* Clarity adjustment produces dark glow.

## v5.1.3

### Added
* Option `TextGlLayer.BOUNDING_BOX_WIDTH_AUTO_FIT = false` to prevent the Text Sticker bounding box from resizing when the font is changed.

## v5.1.2

### Fixed
* `CameraSettings.setOpenEditorAfterCapture` flag is not working.
* `SOURCE_IMAGE_URI` is `null` if editor is canceled.
* NullPointerException when input image is `null`.

## v5.1.1

### Fixed
* Radial and linear blur do not work on some devices.

## v5.1.0

### Fixed
* SDK crashes if `ImageSource` source file is deleted while decoding (ArithmeticException).
* Editor can be opened twice.
* Serialized stickers are sometimes broken or missing after loading.
* Add `onAttachedToList()` and `onDetachedFromList()`to the `DataSourceViewHolder`
* Add URI Support `EditorLoadSettings.setImageSource()`

### Breaking changes
* `EditorLoadSettings.setImageSourcePath()`  is deprecated use `EditorLoadSettings.setImageSource()` instead.
* `Intent.getStringExtra(ImgLyIntent.SOURCE_IMAGE_PATH)` is deprecated use `Uri source = Intent.getParcelableExtra(ImgLyIntent.SOURCE_IMAGE_URI);` instead.
* `Intent.getStringExtra(ImgLyIntent.RESULT_IMAGE_PATH)` is deprecated use `Uri result = Intent.getParcelableExtra(ImgLyIntent.RESULT_IMAGE_URI);` instead.

## v5.0.22

### Fixed
* Frame list does not live update when config has changed.

### Added
* Optional text buttons for the action bar instead of icon buttons.

## v5.0.21

### Fixed
* Undoing brush step does not work after changing color.

## v5.0.20

### Hot-Fixed
* Possible crash while revert text history

## v5.0.19

### Fixed
* Three of the default asset sticker shapes are missing after gradle 3.0 compiling.

## v5.0.18

### Fixed
* Static frames are not loaded by the deserialization.

## v5.0.17

### Fixed
* Internal class TransformedMotionEvent creates wrong states in some cases.
* Sticker matrix updated wrong which causes flickering.
* Exporting many stickers crashes in some cases.
* Image does not export if only the crop aspect has changed.
* Sticker flickering while resizing on some devices.
* Texture Memory is not free up while using with results in slightly slow down.

## v5.0.16

### Hotfix
* Multitouch gesture is unstable.

### Fixed
* Slider does not close when clicking "To Front" in the Brush tool.

## v5.0.15 __DEPRECATED!__

### Fixed
* A 90 degree turn is not possible if no suitable aspect is found.
* The "Gallery" button is sometimes not visible.
* Layout problems with "right-to-left" languages.

## v5.0.14

### Fixed
* Images not saved if only text is added.

## v5.0.13

### Fixed
* Undoing brush steps reverts brush hardness, color and size.
* Increasing the text size over the limit, increases the bounding box width.
* Brush ignores transformation.
* Typo in filter name.
* OpenGl vertex index out of range error.
* Renderscript crash while export on API 26.
* Exception "Only one Looper may be created per thread".
   * 🚨 This is a breaking change if you use the ThreadUtils.
* Text is added upside down, if the image is flipped.

## v5.0.12 __DEPRECATED!__

### Fixed
* Frame image tiles are repeating incorrectly.
* Frame tiles resolution is too low when frame is stretched.
* Brush is in the wrong position after exporting the image.
* Undoing brush steps creates artifacts when the image is too big.
* Undoing brush steps creates artifacts when the image preview width is not divisible by eight.
* Taking picture with front camera adds wrong exif rotation.
* Wrong exif information handling, when exporting image, which adds a black stripe in some cases.
* Wrong threading which freezes background gl threads.
* Text cursor is not at the end of the text when text is re-edited.
* License issues.

### Changed
* Performance improved.

## v5.0.11

### Fixed
* Artifacts on some devices with non-standard compliant OpenGL ES 2.0 implementation.
* NullPointerException, if image loading interrupts because of a broken file stream.
* Brush history produces color mismatch.
* Sticker is being rotated incorrectly when the image is mirrored

## v5.0.9

### Fixed
* Overlay repeats after exporting but it shouldn't.
* Crash while writing serialization, if the frame feature is disabled by license.

## v5.0.8

### Fixed
* Image do not reload after change it.

## v5.0.7

### Fixed
* Crashes when opening the brush tool while touching the canvas.
* Doesn't loading the picture when open the frame and transform tool while touching the canvas.

### Changed
* Support for images smaller than 32px per side.

## v5.0.6

### Fixed
* Image export is truncated in special cases with exif rotation 8 (Viewport 270 CW)

## v4.1.6

### Fixed
* Image export is truncated in special cases with exif rotation 8 (Viewport 270 CW)

## v5.0.5

### Changed
* Changed crop ratio name to 'Free'.
* Set rotation control dots invisible behind the value.
* Set item label to blue if the item is selected.

### Fixed
* Flipped image rotates in the wrong direction.
* Sticker not loading.

## v5.0.4

### Changed
* Add `ADJUSTMENT_OPTIONS` to Sticker Config, allow the user the change brightness, contrast and saturation of a sticker.  

## v5.0.3

### Fixed
* Fix issues with loading gif and bmp images.
* Licensing issues.
* Filter always uses the placeholder photo. 
* Image not centered by using a custom layout.

## v5.0.2

### Fixed
* The save policy are handled correct now.
* OpenGL Error after deserialize.

### Not fixable at the moment
* Deserialization / Rendering of text is not platform independent.

## v5.0.1-RC1

**This is pre release version and can have some bugs**

### Fixed
* Problems with Android 8.0

### Changed
* Performance improvements. Preview of Brush, Sticker and Text are now extremely fast on most of the devices.
* Layer are now rendered in preview with OpenGL

### Not fixable at the moment
* Deserialization / Rendering of text is not platform independent.

## v5.0.0-beta

### Beta-Release-Notes

**This is a beta version and has some known bugs**
* Android 8.0.0 seams to have an issue with TextureView, this view is used by brush and cause display issues in preview.
  This Bug is also happen in older SDK Versions, we will hat to fix it later by changing our general implementation.
* Deserialization of text is not platform independent.
* The save policy are currently not correct.
* PNG and GIF can crash if the image is to big.

### Added
* GIF loading support for the first frame and exported as PNG or JPEG.
* Global History for all Tools, Local History for some tools.
* Support for serialization and deserialization with json schema v3.0.0 (platform independent, but with some issues).
* The background color of the editor stage is now adjustable in the LayerListSettings.
* The background color of the camera stage is now adjustable in the CameraSettings.

### Changed
* Version 2.1 of the Licence is now required. 
* Assets now must have an unique id for the serialisation.
* Method AbstractEditorTool.isRevertible() is now deprecated, please use isCancelable() and isAcceptable() instead.
* Rename imgly_icon_download to imgly_icon_save.
* Transform Tool now operates like iOS.
* Event dispatcher is now Synchronized.
* Object recycling for better performance and better thread stability.
* New thread handler for better performance and better thread stability.

### Fixed
* Errors with the Event dispatcher.
* Randomly icon change when using VectorDrawables.
* VectorDrawable display issues.
* Some short bugs.
* Add two missing default ColorFilters.

## v4.1.5

### Fixed
* Overlay with transparency looks wrong after export.
* Transform tool does not show the image, if an overlay is applied.
* Crash with NullPointerException, when call StateHandler.hasChanges().
* Crash with NullPointerException("BitmapRegionDecoder.getWidth() on a null object reference") when image format not support by tile decoder.
* Crash with IllegalStateException("child already has a parent").

### Known Bugs, but not fixable without API changes (please wait for upcoming v5.0).
* Android 8.0 has display issues while Brush (Flickering for 1 Frame).
* Some, issues with async events.
* Sometimes, the dismiss dialog is displayed without changes.

## v4.1.4

### Changed
* Remove unused ImgLyTitleBar reference. 

### Fixed
* Brush default color is transparent.

## v4.1.3

### Fixed
* Dismiss edits dialog appears without any changes.

## v4.1.2

### Changed
* Add Ability to configure existing panel options by extend panels. Simply extend a panel like `AdjustmentToolPanel` and override the `createOptionList` (returns the icons in the list) or `createQuickOptionList` (returns the icon inside the image area) method.

## v4.1.1

### Changed
* Configuration changes in PESDKConfig during the editor is open, are now updated instantly in the UI.

### Fixed
* Icon color of some icons is wrong if you change `@color/imgly_icon_color`.

## v4.1.0

### Changed
* Multiline Text feature like iOS.
* Sticker category keep the last selection.

### Fixed
* Preview of transparent images is wrong.

## v4.0.2

### Fixed
* Workaround: RenderScript crashes on Nexus devices with Android 7.1
* NullPointer exception, by some camera modules
* NullPointer exception, by a not reproduceable behavior.
* Linear Focus handles, rotate in the wrong direction if the image is flipped.
 
## v4.0.1

### Fixed
* Overlays sometimes not changed.
* Native crash on Android <= 5.0 devices, because of a memory leak inside Googles Vector Drawable support library (This is not our fault but we have found a Workaround).
* The dismiss dialog appears, without any changes.

## v4.0.0

### Changed
* OpenGl Preview for blazing fast filter preview.
* Other Performance Improvements.
* Memory Improvements.
* An improved Focus Filter.
* Smaller SDK Size
* The preview image is now automatically resized when a slider overlays the preview at the bottom, so that is always completely visible.
* Small improvements

### Added
* Tool "Overlay".
* GPS Tag Support for the Camera.
* Pinch and Zoom in Main View and Brush Tool.
* PNG export Support.

### Fixed
* A lot of bug fixes.

## v3.1.1

### Changed
* Remove old Fonts an add improved fonts.

## v3.0.15

### Fixed
* Camera crash on Androids with API lower or equal 20.
* Cutting Frames on some image size, aspect ratio combination.

## v3.0.12

### Fixed
* Small bugfixes.

## v3.0.11

### Fixed
* When dismiss dialog appears, you can still make changes to the image.
* For each time the cancel button is pressed, one new dismiss dialog is rendered on screen.
* You can also still make changes to the image during the image export.
* Icons in some special cases in wrong order.
* Sticker straighten action if the image itself is rotated.

### Changed
* Layout imgly_popup_confirm.xml has changed and is renamed to `imgly_popup_confirm_dialog.xml`.
* Layout imgly_activity_photo_editor.xml has changed!

## v3.0.10

### Fixed
* Sticker list do not scroll to top if you change the category.

## v3.0.9

### Fixed
* Dismiss edits dialog appears without any changes.
* ImageDecoder crash in some constellations.
* SettingsList configurations randomly ignored.
* Force-Crop not working.
* AutoRotation is sometimes wrong.
* License validation sometimes take to long.

## v3.0.7

### Fixed
* Missing Linter Warning if you use Documents until API 19 and fix crash if you ignore this warning and use it until API 19.
* Toggled list images if you use vector and rastered drawables together.

### Changed
* Show a warning and fix wrong scaled frames, if you put the frame assets in the wrong drawable directory.
