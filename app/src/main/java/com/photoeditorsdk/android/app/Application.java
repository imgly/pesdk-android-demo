package com.photoeditorsdk.android.app;

import android.content.res.AssetManager;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import net.hockeyapp.android.CrashManager;
import net.hockeyapp.android.CrashManagerListener;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import ly.img.android.ImgLySdk;
import ly.img.android.plugins.analytics.AbstractAnalyticsPlugin;


public class Application extends android.app.Application {

    private static Tracker googleAnalyticsTracker;

    private static Properties properties;
    private static final String PROPERTIES_FILE = "nongit.properties";

    public static final String HOCKEYAPP_API_KEY_PROPERTY = "HOCKEYAPP_API_KEY";
    public static final String ANALYTICS_TRACK_ID_PROPERTY = "ANALYTICS_TRACK_ID";

    @Override
    public void onCreate() {
        super.onCreate();

        CrashManager.register(this, readPropertyValue(HOCKEYAPP_API_KEY_PROPERTY), new CrashManagerListener() {

            @Override
            public boolean shouldAutoUploadCrashes() {
                return true;
            }
        });

        ImgLySdk.init(this);

        GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);

        googleAnalyticsTracker = analytics.newTracker(readPropertyValue(ANALYTICS_TRACK_ID_PROPERTY));

        ImgLySdk.setTrackingPlugin(new AbstractAnalyticsPlugin() {
            @Override
            public void changeScreen(String screenName) {
                googleAnalyticsTracker.setScreenName(screenName);
                googleAnalyticsTracker.send(new HitBuilders.ScreenViewBuilder().build());
            }

            @Override
            public void sendEvent(String category, String action) {
                sendEvent(category, action, null);
            }

            @Override
            public void sendEvent(String category, String action, String label) {
                HitBuilders.EventBuilder builder = new HitBuilders.EventBuilder().setCategory(category).setAction(action);
                if (label != null) {
                    builder.setLabel(label);
                }
                googleAnalyticsTracker.send(builder.build());
            }
        });
    }


    protected String readPropertyValue(String name) {
        if (properties == null) {
            properties = new Properties();
            try {
                new Properties();
                AssetManager assetManager = getAssets();

                InputStream in = assetManager.open(PROPERTIES_FILE);
                properties.load(in);
                in.close();

            } catch (IOException e) {
                throw new RuntimeException("Error while loading the properties file", e);
            }
        }
        return properties.getProperty(name);
    }
}
