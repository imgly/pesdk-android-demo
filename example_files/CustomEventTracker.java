package com.photoeditorsdk.android.app;

import android.os.Parcel;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import java.util.List;

import ly.img.android.PESDK;
import ly.img.android.sdk.models.config.interfaces.StickerConfigInterface;
import ly.img.android.sdk.utils.Trace;
import ly.img.sdk.android.annotations.StateEvents;
import ly.img.android.PESDKEvents;
import ly.img.android.sdk.models.state.ColorAdjustmentSettings;
import ly.img.android.sdk.models.state.EditorMenuState;
import ly.img.android.sdk.models.state.FilterSettings;
import ly.img.android.sdk.models.state.FocusSettings;
import ly.img.android.sdk.models.state.LayerListSettings;
import ly.img.android.sdk.models.state.TransformSettings;
import ly.img.android.sdk.models.state.layer.BrushLayerSettings;
import ly.img.android.sdk.models.state.layer.StickerLayerSettings;
import ly.img.android.sdk.models.state.manager.EventTracker;
import ly.img.sdk.android.annotations.OnEvent;


@StateEvents
public class CustomEventTracker extends EventTracker {
    private Tracker googleAnalyticsTracker;
    private String trackerId;

    public CustomEventTracker(String trackerId) {
        init(trackerId);
    }

    private void init(String trackerId) {
        this.trackerId = trackerId;
        GoogleAnalytics analytics = GoogleAnalytics.getInstance(PESDK.getAppContext());
        googleAnalyticsTracker = analytics.newTracker(trackerId);
    }

    @OnEvent(PESDKEvents.EditorMenuState_TOOL_STACK_CHANGED)
    protected void changeToolView(EditorMenuState menuState) {
        googleAnalyticsTracker.setScreenName(menuState.getCurrentTool().getName());
        googleAnalyticsTracker.send(new HitBuilders.ScreenViewBuilder().build());
    }

    @OnEvent(value = PESDKEvents.EditorMenuState_CANCEL_AND_LEAVE, ignoreReverts = true)
    protected void onLeaveToolWithCancel(EditorMenuState menuState) {
        HitBuilders.EventBuilder builder = new HitBuilders.EventBuilder()
          .setCategory("menu action")
          .setLabel(menuState.getCurrentTool().getName())
          .setAction("cancel");
        googleAnalyticsTracker.send(builder.build());
    }


    @OnEvent(value = PESDKEvents.EditorMenuState_ACCEPT_AND_LEAVE, ignoreReverts = true)
    protected void onLeaveToolWithAccept(EditorMenuState menuState) {
        HitBuilders.EventBuilder builder = new HitBuilders.EventBuilder()
          .setCategory("menu action")
          .setLabel(menuState.getCurrentTool().getName())
          .setAction("accept");
        googleAnalyticsTracker.send(builder.build());
    }


    @OnEvent(value = PESDKEvents.ColorAdjustmentSettings_CONTRAST, ignoreReverts = true, triggerDelay = 1000)
    protected void onColorAdjustmentChangeContrast(ColorAdjustmentSettings colorAdjustmentSettings) {
        HitBuilders.EventBuilder builder = new HitBuilders.EventBuilder()
          .setCategory("change color adjustment")
          .setLabel("contrast")
          .setAction("changed")
          .setValue(Math.round(colorAdjustmentSettings.getContrast() * 100));

        googleAnalyticsTracker.send(builder.build());
    }


    @OnEvent(value = PESDKEvents.ColorAdjustmentSettings_BRIGHTNESS, ignoreReverts = true, triggerDelay = 1000)
    protected void onColorAdjustmentChangeBrightness(ColorAdjustmentSettings colorAdjustmentSettings) {
        HitBuilders.EventBuilder builder = new HitBuilders.EventBuilder()
          .setCategory("change color adjustment")
          .setLabel("brightness")
          .setAction("changed")
          .setValue(Math.round(colorAdjustmentSettings.getBrightness() * 100));
        googleAnalyticsTracker.send(builder.build());
    }


    @OnEvent(value = PESDKEvents.ColorAdjustmentSettings_SATURATION, ignoreReverts = true, triggerDelay = 1000)
    protected void onColorAdjustmentChangeSaturation(ColorAdjustmentSettings colorAdjustmentSettings) {
        HitBuilders.EventBuilder builder = new HitBuilders.EventBuilder()
          .setCategory("change color adjustment")
          .setLabel("saturation")
          .setAction("changed")
          .setValue(Math.round(colorAdjustmentSettings.getSaturation() * 100));
        googleAnalyticsTracker.send(builder.build());
        Trace.out("Test", "Saturation", Math.round(colorAdjustmentSettings.getSaturation() * 100));
    }



    @OnEvent(value = PESDKEvents.BrushLayerSettings_COLOR, ignoreReverts = true, triggerDelay = 1000)
    protected void onBrushColorChange(BrushLayerSettings brushLayerSettings) {
        HitBuilders.EventBuilder builder = new HitBuilders.EventBuilder()
          .setCategory("change brush")
          .setLabel("color")
          .setAction("changed")
          .setValue(brushLayerSettings.getBrushColor());
        googleAnalyticsTracker.send(builder.build());
    }


    @OnEvent(value = PESDKEvents.BrushLayerSettings_HARDNESS, ignoreReverts = true, triggerDelay = 1000)
    protected void onBrushHardnessChange(BrushLayerSettings brushLayerSettings) {
        HitBuilders.EventBuilder builder = new HitBuilders.EventBuilder()
          .setCategory("change brush")
          .setLabel("hardness")
          .setAction("changed")
          .setValue(Math.round(brushLayerSettings.getBrushHardness() * 100));
        googleAnalyticsTracker.send(builder.build());
    }


    @OnEvent(value = PESDKEvents.BrushLayerSettings_SIZE, ignoreReverts = true, triggerDelay = 1000)
    protected void onBrushSizeChange(BrushLayerSettings brushLayerSettings) {
        HitBuilders.EventBuilder builder = new HitBuilders.EventBuilder()
          .setCategory("change brush")
          .setLabel("size")
          .setAction("changed")
          .setValue(Math.round(brushLayerSettings.getBrushSize()));
        googleAnalyticsTracker.send(builder.build());
    }


    @OnEvent(value = PESDKEvents.TransformSettings_ASPECT, ignoreReverts = true, triggerDelay = 1000)
    protected void onCropAspectChanged(TransformSettings cropSettings) {
        HitBuilders.EventBuilder builder = new HitBuilders.EventBuilder()
          .setCategory("crop changed")
          .setLabel("aspect")
          .setAction("changed aspect to: "+cropSettings.getAspectConfig().getName());
        googleAnalyticsTracker.send(builder.build());
    }


    @OnEvent(value = PESDKEvents.FilterSettings_FILTER, ignoreReverts = true, triggerDelay = 1000)
    protected void onFilterChanged(FilterSettings filterSettings) {
        HitBuilders.EventBuilder builder = new HitBuilders.EventBuilder()
          .setCategory("filter changed")
          .setLabel("filter: " + filterSettings.getFilter().getName())
          .setAction("set fitler");
        googleAnalyticsTracker.send(builder.build());
    }


    @OnEvent(value = PESDKEvents.FilterSettings_INTENSITY, ignoreReverts = true, triggerDelay = 1000)
    protected void onFilterIntensityChanged(FilterSettings filterSettings) {
        HitBuilders.EventBuilder builder = new HitBuilders.EventBuilder()
          .setCategory("filter changed")
          .setLabel("filter: " + filterSettings.getFilter().getName())
          .setAction("changed intensity to: " + filterSettings.getIntensity());
        googleAnalyticsTracker.send(builder.build());
    }


    @OnEvent(value = PESDKEvents.FocusSettings_MODE, ignoreReverts = true, triggerDelay = 1000)
    protected void onFocusTypeChanged(FocusSettings focusSettings) {
        HitBuilders.EventBuilder builder = new HitBuilders.EventBuilder()
          .setCategory("focus changed")
          .setLabel("focus: " + focusSettings.getFocusMode())
          .setAction("set type");
        googleAnalyticsTracker.send(builder.build());
    }


    @OnEvent(value = PESDKEvents.FocusSettings_INTENSITY, ignoreReverts = true, triggerDelay = 1000)
    protected void onFocusIntensityChanged(FocusSettings focusSettings) {
        HitBuilders.EventBuilder builder = new HitBuilders.EventBuilder()
          .setCategory("focus changed")
          .setLabel("focus: " + focusSettings.getFocusMode())
          .setAction("set intensity")
          .setValue(Math.round(focusSettings.getFocusIntensity() * 100));

        googleAnalyticsTracker.send(builder.build());
    }

    @OnEvent(value = PESDKEvents.LayerListSettings_ADD_LAYER, ignoreReverts = true)
    protected void onStickerAdd(LayerListSettings settings) {
        List<LayerListSettings.LayerSettings> list = settings.getLayerSettingsList();
        LayerListSettings.LayerSettings lastLayer = list.get(list.size() - 1);
        if (lastLayer instanceof StickerLayerSettings) {
            StickerConfigInterface stickerConfig = ((StickerLayerSettings) lastLayer).getStickerConfig();
            HitBuilders.EventBuilder builder = new HitBuilders.EventBuilder()
              .setCategory(stickerConfig.getType() == StickerConfigInterface.STICKER_TYPE.TEXT ? "text": "sticker")
              .setLabel(stickerConfig.getName())
              .setAction("add sticker");
            googleAnalyticsTracker.send(builder.build());
        }
    }


    @OnEvent(value = PESDKEvents.LayerListSettings_REMOVE_LAYER, ignoreReverts = true)
    protected void onStickerRemove(LayerListSettings settings) {
        List<LayerListSettings.LayerSettings> list = settings.getLayerSettingsList();
        LayerListSettings.LayerSettings lastLayer = list.get(list.size() - 1);
        if (lastLayer instanceof StickerLayerSettings) {
            StickerConfigInterface stickerConfig = ((StickerLayerSettings) lastLayer).getStickerConfig();
            HitBuilders.EventBuilder builder = new HitBuilders.EventBuilder()
              .setCategory(stickerConfig.getType() == StickerConfigInterface.STICKER_TYPE.TEXT ? "text": "sticker")
              .setLabel(stickerConfig.getName())
              .setAction("remove sticker");
            googleAnalyticsTracker.send(builder.build());
        }
    }

    @SuppressWarnings("unused")
    @OnEvent(value = PESDKEvents.StickerLayerSettings_FLIP_HORIZONTAL, ignoreReverts = true)
    protected void onStickerFlipHorizontal(LayerListSettings settings) {
        LayerListSettings.LayerSettings lastLayer = settings.getSelected();
        if (lastLayer instanceof StickerLayerSettings) {
            StickerConfigInterface stickerConfig = ((StickerLayerSettings) lastLayer).getStickerConfig();
            HitBuilders.EventBuilder builder = new HitBuilders.EventBuilder()
              .setCategory(stickerConfig.getType() == StickerConfigInterface.STICKER_TYPE.TEXT ? "text": "sticker")
              .setLabel(stickerConfig.getName())
              .setAction("horizontal flip");
            googleAnalyticsTracker.send(builder.build());
        }
    }


    @OnEvent(value = PESDKEvents.StickerLayerSettings_FLIP_VERTICAL, ignoreReverts = true)
    protected void onStickerFlipVertical(LayerListSettings settings) {
        LayerListSettings.LayerSettings lastLayer = settings.getSelected();
        if (lastLayer instanceof StickerLayerSettings) {
            StickerConfigInterface stickerConfig = ((StickerLayerSettings) lastLayer).getStickerConfig();
            HitBuilders.EventBuilder builder = new HitBuilders.EventBuilder()
              .setCategory(stickerConfig.getType() == StickerConfigInterface.STICKER_TYPE.TEXT ? "text": "sticker")
              .setLabel(stickerConfig.getName())
              .setAction("vertical flip");
            googleAnalyticsTracker.send(builder.build());
        }
    }


    @OnEvent(value = PESDKEvents.StickerLayerSettings_PLACEMENT_INVALID, ignoreReverts = true)
    protected void onStickerPositionChange(LayerListSettings settings) {
        LayerListSettings.LayerSettings lastLayer = settings.getSelected();
        if (lastLayer instanceof StickerLayerSettings) {
            StickerConfigInterface stickerConfig = ((StickerLayerSettings) lastLayer).getStickerConfig();
            HitBuilders.EventBuilder builder = new HitBuilders.EventBuilder()
              .setCategory(stickerConfig.getType() == StickerConfigInterface.STICKER_TYPE.TEXT ? "text": "sticker")
              .setLabel(stickerConfig.getName())
              .setAction("position changed");
            googleAnalyticsTracker.send(builder.build());
        }
    }


    @OnEvent(value = PESDKEvents.LayerListSettings_BRING_TO_FRONT, ignoreReverts = true)
    protected void onStickerToFront(LayerListSettings settings) {
        LayerListSettings.LayerSettings lastLayer = settings.getSelected();
        if (lastLayer instanceof StickerLayerSettings) {
            StickerConfigInterface stickerConfig = ((StickerLayerSettings) lastLayer).getStickerConfig();
            HitBuilders.EventBuilder builder = new HitBuilders.EventBuilder()
              .setCategory(stickerConfig.getType() == StickerConfigInterface.STICKER_TYPE.TEXT ? "text": "sticker")
              .setLabel(stickerConfig.getName())
              .setAction("bring to front");
            googleAnalyticsTracker.send(builder.build());
        }
    }


    // Many more trackable events ..........


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
