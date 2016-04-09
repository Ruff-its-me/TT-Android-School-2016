package com.thumbtack2016.chat.app;

import android.app.Activity;
import android.app.Application;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.thumbtack2016.chat.di.AppComponent;
import com.thumbtack2016.chat.di.AppModule;
import com.thumbtack2016.chat.di.DaggerAppComponent;


/**
 * Created by pasencukviktor on 06/02/16
 */
public class App extends Application {

    AppComponent appComponent;

    public static App getApp(Activity activity) {
        return (App) activity.getApplication();
    }

    public static App getApp(Fragment fragment) {
        final FragmentActivity activity = fragment.getActivity();
        if (activity != null)
            return (App) activity.getApplication();
        throw new IllegalStateException("Fragment must be attached to activity!");
    }

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .build();
    }


    public AppComponent getAppComponent() {
        return appComponent;
    }
}
