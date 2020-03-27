package org.openobservatory.ooniprobe.common.alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import org.openobservatory.ooniprobe.R;

public class OnBootReceiver extends BroadcastReceiver {
    private static final String DEBUG_TAG = "OnBootReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
            System.out.println(DEBUG_TAG);
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
            if (preferences.getBoolean(context.getString(R.string.automated_testing), false)){
                AlarmService.setRecurringAlarm(context);
            }
        }
    }

}