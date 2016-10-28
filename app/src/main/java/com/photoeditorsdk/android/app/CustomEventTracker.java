package com.photoeditorsdk.android.app;

import android.os.Parcel;

import ly.img.android.ImgLySdk;
import ly.img.android.sdk.models.state.BrushLayerSettings;
import ly.img.android.sdk.models.state.BrushLayerSettingsEvent;
import ly.img.android.sdk.models.state.ColorAdjustmentSettings;
import ly.img.android.sdk.models.state.ColorAdjustmentSettingsEvent;
import ly.img.android.sdk.models.state.CropSettings;
import ly.img.android.sdk.models.state.CropSettingsEvent;
import ly.img.android.sdk.models.state.EditorMenuState;
import ly.img.android.sdk.models.state.EditorMenuStateEvent;
import ly.img.android.sdk.models.state.FilterSettings;
import ly.img.android.sdk.models.state.FilterSettingsEvent;
import ly.img.android.sdk.models.state.FocusSettings;
import ly.img.android.sdk.models.state.FocusSettingsEvent;
import ly.img.android.sdk.models.state.LayerListSettings;
import ly.img.android.sdk.models.state.LayerListSettingsEvent;
import ly.img.android.sdk.models.state.OrientationSettings;
import ly.img.android.sdk.models.state.OrientationSettingsEvent;
import ly.img.android.sdk.models.state.StickerLayerSettings;
import ly.img.android.sdk.models.state.StickerLayerSettingsEvent;
import ly.img.android.sdk.models.state.manager.EventTracker;
import ly.img.android.sdk.models.state.manager.OnStateEvent;

@SuppressWarnings("unused")
public class CustomEventTracker extends EventTracker {

    private String trackerId;

    public CustomEventTracker(String trackerId) {
        init(trackerId);
    }

    private void init(String trackerId) {
        this.trackerId = trackerId;
        /* Example
        GoogleAnalytics analytics = GoogleAnalytics.getInstance(ImgLySdk.getAppContext());
        googleAnalyticsTracker = analytics.newTracker(trackerId);
        */
    }

    @OnStateEvent(model = EditorMenuState.class, event = EditorMenuStateEvent.TOOL_STACK_CHANGED, onInvalidation = false, onRevert = false)
    protected void changeToolView(EditorMenuState menuState) {
        /* Example
        googleAnalyticsTracker.setScreenName(menuState.getCurrentTool().getName());
        googleAnalyticsTracker.send(new HitBuilders.ScreenViewBuilder().build());
        */
    }

    @OnStateEvent(model = EditorMenuState.class, event = EditorMenuStateEvent.CANCEL_AND_LEAVE, onInvalidation = false, onRevert = false)
    protected void onLeaveToolWithCancel(EditorMenuState menuState) {
        /* Example
        HitBuilders.EventBuilder builder = new HitBuilders.EventBuilder()
                .setCategory("menu action")
                .setLabel(menuState.getCurrentTool().getName())
                .setAction("cancel");
        googleAnalyticsTracker.send(builder.build());
        */
    }

    @OnStateEvent(model = EditorMenuState.class, event = EditorMenuStateEvent.ACCEPT_AND_LEAVE, onInvalidation = false, onRevert = false)
    protected void onLeaveToolWithAccept(EditorMenuState menuState) {
        /* Example
        HitBuilders.EventBuilder builder = new HitBuilders.EventBuilder()
                .setCategory("menu action")
                .setLabel(menuState.getCurrentTool().getName())
                .setAction("accept");
        googleAnalyticsTracker.send(builder.build());
        */
    }

    @OnStateEvent(model = ColorAdjustmentSettings.class, event = ColorAdjustmentSettingsEvent.CONTRAST, onInvalidation = false, onRevert = false)
    protected void onColorAdjustmentChangeContrast(ColorAdjustmentSettings colorAdjustmentSettings) {
        /* Example
        HitBuilders.EventBuilder builder = new HitBuilders.EventBuilder()
                .setCategory("change color adjustment")
                .setLabel("contrast")
                .setAction("changed")
                .setValue(Math.round(colorAdjustmentSettings.getContrast() * 100));
        googleAnalyticsTracker.send(builder.build());
        */
    }

    @OnStateEvent(model = ColorAdjustmentSettings.class, event = ColorAdjustmentSettingsEvent.BRIGHTNESS, onInvalidation = false, onRevert = false)
    protected void onColorAdjustmentChangeBrightness(ColorAdjustmentSettings colorAdjustmentSettings) {
        /* Example
        HitBuilders.EventBuilder builder = new HitBuilders.EventBuilder()
                .setCategory("change color adjustment")
                .setLabel("brightness")
                .setAction("changed")
                .setValue(Math.round(colorAdjustmentSettings.getBrightness() * 100));
        googleAnalyticsTracker.send(builder.build());
        */
    }

    @OnStateEvent(model = ColorAdjustmentSettings.class, event = ColorAdjustmentSettingsEvent.SATURATION, onInvalidation = false, onRevert = false)
    protected void onColorAdjustmentChangeSaturation(ColorAdjustmentSettings colorAdjustmentSettings) {
        /* Example
        HitBuilders.EventBuilder builder = new HitBuilders.EventBuilder()
                .setCategory("change color adjustment")
                .setLabel("saturation")
                .setAction("changed")
                .setValue(Math.round(colorAdjustmentSettings.getSaturation() * 100));
        googleAnalyticsTracker.send(builder.build());
        */
    }

    @OnStateEvent(model = BrushLayerSettings.class, event = BrushLayerSettingsEvent.COLOR, onInvalidation = false, onRevert = false)
    protected void onBrushColorChange(BrushLayerSettings brushLayerSettings) {
        /* Example
        HitBuilders.EventBuilder builder = new HitBuilders.EventBuilder()
                .setCategory("change brush")
                .setLabel("color")
                .setAction("changed")
                .setValue(brushLayerSettings.getBrushColor());
        googleAnalyticsTracker.send(builder.build());
        */
    }

    @OnStateEvent(model = BrushLayerSettings.class, event = BrushLayerSettingsEvent.HARDNESS, onInvalidation = false, onRevert = false)
    protected void onBrushHardnessChange(BrushLayerSettings brushLayerSettings) {
        /* Example
        HitBuilders.EventBuilder builder = new HitBuilders.EventBuilder()
                .setCategory("change brush")
                .setLabel("hardness")
                .setAction("changed")
                .setValue(Math.round(brushLayerSettings.getBrushHardness() * 100));
        googleAnalyticsTracker.send(builder.build());
        */
    }

    @OnStateEvent(model = BrushLayerSettings.class, event = BrushLayerSettingsEvent.SIZE, onInvalidation = false, onRevert = false)
    protected void onBrushSizeChange(BrushLayerSettings brushLayerSettings) {
        /* Example
        HitBuilders.EventBuilder builder = new HitBuilders.EventBuilder()
                .setCategory("change brush")
                .setLabel("size")
                .setAction("changed")
                .setValue(Math.round(brushLayerSettings.getBrushSize()));
        googleAnalyticsTracker.send(builder.build());
        */
    }

    @OnStateEvent(model = CropSettings.class, event = CropSettingsEvent.ASPECT, onInvalidation = false, onRevert = false)
    protected void onCropAspectChanged(CropSettings cropSettings) {
        /* Example
        HitBuilders.EventBuilder builder = new HitBuilders.EventBuilder()
                .setCategory("crop changed")
                .setLabel("aspect")
                .setAction("changed aspect to: "+cropSettings.getAspect().getName());
        googleAnalyticsTracker.send(builder.build());
        */
    }

    @OnStateEvent(model = FilterSettings.class, event = FilterSettingsEvent.FILTER, onInvalidation = false, onRevert = false)
    protected void onFilterChanged(FilterSettings filterSettings) {
        /* Example
        HitBuilders.EventBuilder builder = new HitBuilders.EventBuilder()
                .setCategory("filter changed")
                .setLabel("filter: " + filterSettings.getFilter().getName())
                .setAction("set fitler");
        googleAnalyticsTracker.send(builder.build());
        */
    }

    @OnStateEvent(model = FilterSettings.class, event = FilterSettingsEvent.INTENSITY, onInvalidation = false, onRevert = false)
    protected void onFilterIntensityChanged(FilterSettings filterSettings) {
        /* Example
        HitBuilders.EventBuilder builder = new HitBuilders.EventBuilder()
                .setCategory("filter changed")
                .setLabel("filter: " + filterSettings.getFilter().getName())
                .setAction("changed intensity to: " + filterSettings.getIntensity());
        googleAnalyticsTracker.send(builder.build());
        */
    }

    @OnStateEvent(model = FocusSettings.class, event = FocusSettingsEvent.MODE, onInvalidation = false, onRevert = false)
    protected void onFocusTypeChanged(FocusSettings focusSettings) {
        /* Example
        HitBuilders.EventBuilder builder = new HitBuilders.EventBuilder()
                .setCategory("focus changed")
                .setLabel("focus: " + focusSettings.getFocusMode())
                .setAction("set type");
        googleAnalyticsTracker.send(builder.build());
        */
    }

    @OnStateEvent(model = FocusSettings.class, event = FocusSettingsEvent.INTENSITY, onInvalidation = false, onRevert = false)
    protected void onFocusIntensityChanged(FocusSettings focusSettings) {
        /* Example
        HitBuilders.EventBuilder builder = new HitBuilders.EventBuilder()
                .setCategory("focus changed")
                .setLabel("focus: " + focusSettings.getFocusMode())
                .setAction("set intensity")
                .setValue(Math.round(focusSettings.getFocusIntensity() * 100));

        googleAnalyticsTracker.send(builder.build());
        */
    }

    @OnStateEvent(model = OrientationSettings.class, event = OrientationSettingsEvent.FLIP_VERTICAL, onInvalidation = false, onRevert = false)
    protected void onVerticalFlipChanged(OrientationSettings orientationSettings) {
        /* Example
        HitBuilders.EventBuilder builder = new HitBuilders.EventBuilder()
                .setCategory("orientation changed")
                .setLabel("Is vertical flipped: " + orientationSettings.isVerticalFlipped())
                .setAction("change vertical flip");

        googleAnalyticsTracker.send(builder.build());
        */
    }

    @OnStateEvent(model = OrientationSettings.class, event = OrientationSettingsEvent.FLIP_HORIZONTAL, onInvalidation = false, onRevert = false)
    protected void onHorizontalFlipChanged(OrientationSettings orientationSettings) {
        /* Example
        HitBuilders.EventBuilder builder = new HitBuilders.EventBuilder()
                .setCategory("orientation changed")
                .setLabel("Is horizontal flipped: " + orientationSettings.isHorizontalFlipped())
                .setAction("change horizontal flip");

        googleAnalyticsTracker.send(builder.build());
        */
    }

    @OnStateEvent(model = OrientationSettings.class, event = OrientationSettingsEvent.ROTATION_CW, onInvalidation = false, onRevert = false)
    protected void onRotationCW(OrientationSettings orientationSettings) {
        int rotation = orientationSettings.getRotation();
        /* Example
        HitBuilders.EventBuilder builder = new HitBuilders.EventBuilder()
                .setCategory("orientation changed")
                .setLabel("Image Rotation: " + rotation)
                .setAction("rotate CW");

        googleAnalyticsTracker.send(builder.build());
        */
    }

    @OnStateEvent(model = OrientationSettings.class, event = OrientationSettingsEvent.ROTATION_CCW, onInvalidation = false, onRevert = false)
    protected void onRotationCCW(OrientationSettings orientationSettings) {
        int rotation = orientationSettings.getRotation();
        /* Example
        HitBuilders.EventBuilder builder = new HitBuilders.EventBuilder()
                .setCategory("orientation changed")
                .setLabel("Image Rotation: " + rotation)
                .setAction("rotate CCW");

        googleAnalyticsTracker.send(builder.build());
        */
    }

    @OnStateEvent(model = LayerListSettings.class, event = LayerListSettingsEvent.ADD_LAYER, onInvalidation = false, onRevert = false)
    protected void onStickerAdd(LayerListSettings settings) {
        /* Example
        List<LayerListSettings.LayerSettings> list = settings.getLayerSettingsList();
        LayerListSettings.LayerSettings lastLayer = list.get(list.size() - 1);
        if (lastLayer instanceof StickerLayerSettings) {
            AbstractConfig.StickerConfigInterface stickerConfig = ((StickerLayerSettings) lastLayer).getStickerConfig();

            HitBuilders.EventBuilder builder = new HitBuilders.EventBuilder()
                    .setCategory(stickerConfig.getType() == AbstractConfig.StickerConfigInterface.STICKER_TYPE.TEXT ? "text": "sticker")
                    .setLabel(stickerConfig.getName())
                    .setAction("add sticker");
            googleAnalyticsTracker.send(builder.build());
        }*/
    }

    @OnStateEvent(model = LayerListSettings.class, event = LayerListSettingsEvent.REMOVE_LAYER, onInvalidation = false, onRevert = false)
    protected void onStickerRemove(LayerListSettings settings) {
        /* Example
        List<LayerListSettings.LayerSettings> list = settings.getLayerSettingsList();
        LayerListSettings.LayerSettings lastLayer = list.get(list.size() - 1);
        if (lastLayer instanceof StickerLayerSettings) {
            AbstractConfig.StickerConfigInterface stickerConfig = ((StickerLayerSettings) lastLayer).getStickerConfig();

            HitBuilders.EventBuilder builder = new HitBuilders.EventBuilder()
                    .setCategory(stickerConfig.getType() == AbstractConfig.StickerConfigInterface.STICKER_TYPE.TEXT ? "text": "sticker")
                    .setLabel(stickerConfig.getName())
                    .setAction("remove sticker");
            googleAnalyticsTracker.send(builder.build());
        }*/
    }

    @SuppressWarnings("unused")
    @OnStateEvent(model = StickerLayerSettings.class, event = StickerLayerSettingsEvent.FLIP_HORIZONTAL, onInvalidation = false, onRevert = false)
    protected void onStickerFlipHorizontal(LayerListSettings settings) {
        /* Example
        LayerListSettings.LayerSettings lastLayer = settings.getSelected();
        if (lastLayer instanceof StickerLayerSettings) {
            AbstractConfig.StickerConfigInterface stickerConfig = ((StickerLayerSettings) lastLayer).getStickerConfig();
            HitBuilders.EventBuilder builder = new HitBuilders.EventBuilder()
                    .setCategory(stickerConfig.getType() == AbstractConfig.StickerConfigInterface.STICKER_TYPE.TEXT ? "text": "sticker")
                    .setLabel(stickerConfig.getName())
                    .setAction("horizontal flip");
            googleAnalyticsTracker.send(builder.build());
        }*/
    }

    @OnStateEvent(model = StickerLayerSettings.class, event = StickerLayerSettingsEvent.FLIP_VERTICAL, onInvalidation = false, onRevert = false)
    protected void onStickerFlipVertical(LayerListSettings settings) {
        /* Example
        LayerListSettings.LayerSettings lastLayer = settings.getSelected();
        if (lastLayer instanceof StickerLayerSettings) {
            AbstractConfig.StickerConfigInterface stickerConfig = ((StickerLayerSettings) lastLayer).getStickerConfig();
            HitBuilders.EventBuilder builder = new HitBuilders.EventBuilder()
                    .setCategory(stickerConfig.getType() == AbstractConfig.StickerConfigInterface.STICKER_TYPE.TEXT ? "text": "sticker")
                    .setLabel(stickerConfig.getName())
                    .setAction("vertical flip");
            googleAnalyticsTracker.send(builder.build());
        }*/
    }


    // Many more events trackable ..........

    @OnStateEvent(model = StickerLayerSettings.class, event = StickerLayerSettingsEvent.PLACEMENT_INVALID, onInvalidation = false, onRevert = false)
    protected void onStickerPositionChange(LayerListSettings settings) {
        /* Example
        LayerListSettings.LayerSettings lastLayer = settings.getSelected();
        if (lastLayer instanceof StickerLayerSettings) {
            AbstractConfig.StickerConfigInterface stickerConfig = ((StickerLayerSettings) lastLayer).getStickerConfig();
            HitBuilders.EventBuilder builder = new HitBuilders.EventBuilder()
                    .setCategory(stickerConfig.getType() == AbstractConfig.StickerConfigInterface.STICKER_TYPE.TEXT ? "text": "sticker")
                    .setLabel(stickerConfig.getName())
                    .setAction("position changed");
            googleAnalyticsTracker.send(builder.build());

        }
        */
    }

    @OnStateEvent(model = LayerListSettings.class, event = LayerListSettingsEvent.BRING_TO_FRONT, onInvalidation = false, onRevert = false)
    protected void onStickerToFront(LayerListSettings settings) {
        /* Example
        LayerListSettings.LayerSettings lastLayer = settings.getSelected();
        if (lastLayer instanceof StickerLayerSettings) {
            AbstractConfig.StickerConfigInterface stickerConfig = ((StickerLayerSettings) lastLayer).getStickerConfig();
            HitBuilders.EventBuilder builder = new HitBuilders.EventBuilder()
                    .setCategory(stickerConfig.getType() == AbstractConfig.StickerConfigInterface.STICKER_TYPE.TEXT ? "text": "sticker")
                    .setLabel(stickerConfig.getName())
                    .setAction("bring to front");
            googleAnalyticsTracker.send(builder.build());
        }
        */
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.trackerId);
    }

    protected CustomEventTracker(Parcel in) {
        super(in);
        init(in.readString());
    }

    public static final Creator<CustomEventTracker> CREATOR = new Creator<CustomEventTracker>() {
        @Override
        public CustomEventTracker createFromParcel(Parcel source) {
            return new CustomEventTracker(source);
        }

        @Override
        public CustomEventTracker[] newArray(int size) {
            return new CustomEventTracker[size];
        }
    };
}
