package com.thumbtack2016.chat.network.gcm;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;
import com.thumbtack2016.chat.R;
import com.thumbtack2016.chat.app.App;
import com.thumbtack2016.chat.app.AppPreferences;

import java.io.IOException;

import javax.inject.Inject;

/**
 * Created by wildf on 15.10.2015
 */
public class RegistrationIntentService extends IntentService {
    
    @Inject
    AppPreferences appPreferences;

    public RegistrationIntentService() {
        super("RegistrationIntentService");
    }

    @Override
    public void onCreate() {
        super.onCreate();

        ((App) getApplication()).getAppComponent().inject(this);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        final String name = android.os.Build.MODEL;
        String registrationId;
        try {
            InstanceID instanceID = InstanceID.getInstance(this);
            registrationId = instanceID.getToken(getString(R.string.gcm_defaultSenderId), GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);

            appPreferences
                    .setGcmId(registrationId)
                    .setGcmRegistered(false);
            Log.d("GCM", registrationId);
        } catch (IOException e) {
            appPreferences
                    .setGcmId(null)
                    .setGcmRegistered(false);
        }

    }

}
