package com.thrymr.farmersmarket;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class OtpService extends FirebaseMessagingService {
    private static final String TAG="FirebaseMessageService";

    public OtpService() {
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        Log.d(TAG, "From: " + remoteMessage.getFrom());
        Log.e(TAG, "NotificationData : QueryParam " +remoteMessage.getData().toString());
            // Check if message contains a notification payload.
            if (remoteMessage.getData() != null) {
                Log.d(TAG, "Notification Title  : " + remoteMessage.getData().get("title"));
                Log.d(TAG, "Notification Message  : " + remoteMessage.getData().get("msg"));
                Log.d(TAG, "Notification Tag  : " + remoteMessage.getData().get("tag"));


            }

        }
}
