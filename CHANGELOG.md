# PhotoEditor SDK - Changelog

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
* Internal class TransformedMotionEvent creates wrong./G states in some cases.
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

