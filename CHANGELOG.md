# PhotoEditor SDK - Changelog

## v4.0.1

### Fixed
* Overlays sometimes not changed.
* Native crash on Android <= 5.0 devices, because of a memory leak inside Googles Vector Drawable support library. (This is not our fault but we have found a Workaround)
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
* PNG export Support

### Fixed
* A lot of bug fixes.

## v3.1.1

### Changed
* Remove old Fonts an add improved fonts

## v3.0.15

### Fixed
* Camera crash on Androids with API lower or equal 20.
* Cutting Frames on some image size, aspect ratio combination.

## v3.0.12

### Fixed
* Small bugfixes

## v3.0.11

### Fixed
* When dismiss dialog appears, you can still make changes to the image.
* For each time the cancel button is pressed, one new dismiss dialog is rendered on screen.
* You can also still make changes to the image during the image export.
* Icons in some special cases in wrong order.
* Sticker straighten action if the image itself is rotated.

### Changed
* Layout imgly_popup_confirm.xml has changed and is renamed to imgly_popup_confirm_dialog.xml
* Layout imgly_activity_photo_editor.xml has changed!

## v3.0.10

### Fixed
* Sticker list do not scroll to top if you change the category.

## v3.0.9

### Fixed
* Dismiss edits dialog appears without any changes
* ImageDecoder crash in some constellations
* SettingsList configurations randomly ignored.
* Force-Crop not working.
* AutoRotation is sometimes wrong.
* License validation sometimes take to long

## v3.0.7

### Fixed
* Missing Linter Warning if you use Documents until API 19 and fix crash if you ignore this warning and use it until API 19.
* Toggled list images if you use vector and rastered drawables together

### Changed
* Show a warning and fix wrong scaled frames, if you put the frame assets in the wrong drawable directory.

