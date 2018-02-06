package com.photoeditorsdk.android.app;

import android.os.Parcel;
import android.util.Log;

import ly.img.android.PESDKEvents;
import ly.img.android.sdk.models.state.EditorMenuState;
import ly.img.android.sdk.models.state.manager.EventTracker;
import ly.img.sdk.android.annotations.OnEvent;

/**
 * Created by malte on 06.02.18.
 */

@ly.img.sdk.android.annotations.StateEvents
public class DummyEventTracker extends EventTracker {

    /*
     * This annotated method tracks any tool change like opening the brush tool
     */
    @OnEvent(PESDKEvents.EditorMenuState_TOOL_STACK_CHANGED)
    protected void changeToolView(EditorMenuState menuState) {
        Log.d("TAG", "MESSAGE");
    }

    // Has to be a Parcalable. For example:
    private String trackerId;
    public DummyEventTracker(String trackerId) {
        init(trackerId);
    }
    private void init(String trackerId) {
        this.trackerId = trackerId;
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
    protected DummyEventTracker(Parcel in) {
        super(in);
        init(in.readString());
    }
    public static final Creator<DummyEventTracker> CREATOR = new Creator<DummyEventTracker>() {
        @Override
        public DummyEventTracker createFromParcel(Parcel source) {
            return new DummyEventTracker(source);
        }
        @Override
        public DummyEventTracker[] newArray(int size) {
            return new DummyEventTracker[size];
        }
    };
}
