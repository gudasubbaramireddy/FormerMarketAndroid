package com.thrymr.formersmarket;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Base64;

import androidx.fragment.app.FragmentActivity;

import com.google.gson.JsonObject;
import com.thrymr.formersmarket.Database.FarmersMarketDatabase;
import com.thrymr.formersmarket.Model.ServerImageEvent;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SessionManager {

    private SharedPreferences preferences;
    private static SessionManager instance;
    private static Context mContext;
    private static DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private SessionManager(Context context){
        int PRIVATE_MODE=0;
        preferences=context.getSharedPreferences(Constants.PREF_NAME,Context.MODE_PRIVATE);
    }
    public static SessionManager getInstance(Context context){
        if(instance==null){
            instance=new SessionManager(context);
            mContext=context;
        }
        return instance;
    }

    public static boolean isOnline(Context context) {

            boolean hasConnected = false;

            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            if (activeNetwork != null) {
                if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                    hasConnected = true;
                } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                    hasConnected = true;
                }
            }
            return hasConnected;
    }

    public String getSelectedLan() {
        return preferences.getString(Constants.SELECTED_LAN,"English");
    }
    public void setSelectedLan(String lang) {
        preferences.edit().putString(Constants.SELECTED_LAN,lang).apply();
    }
    public String getSelectedPos() {
        return preferences.getString(Constants.SELECTED_POS,"0");
    }
    public void setSelectedPos(String pos) {
        preferences.edit().putString(Constants.SELECTED_POS,pos).apply();
    }
    public static void saveImageEvent(String data,String imageByeArray, String mainUrl, String eventCode, String consumerId){
        ServerImageEvent event=new ServerImageEvent();
        Calendar calendar=Calendar.getInstance();
        event.setEventName(eventCode);
        event.setOwnerId(consumerId);
        event.setTimeOfEvent(dateFormat.format(calendar.getTime()));
        event.setStatus(Boolean.TRUE);
        event.setImage(imageByeArray);
        event.setBody("");
        try {
            String body = Base64.encodeToString(data.getBytes("UTF-8"), Base64.NO_WRAP).trim();
            event.setBody(body);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        event.setUrl(mainUrl);
        FarmersMarketDatabase.getInstance(mContext).serverImageEventDao().insert(event);
    }
}
