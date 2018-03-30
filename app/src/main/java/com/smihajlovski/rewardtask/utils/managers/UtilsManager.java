package com.smihajlovski.rewardtask.utils.managers;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Stefan on 29-Mar-18.
 */

public class UtilsManager {

    private static int screenWidth = 0;
    private static int screenHeight = 0;

    /**
     * Check if it has an active connection.
     *
     * @param context some Context.
     * @return does it have an active Network connection.
     */
    public static boolean hasActiveNetworkConnection(Context context) {
        ConnectivityManager connMgr = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return ((networkInfo != null) && networkInfo.isConnected());
    }
}
