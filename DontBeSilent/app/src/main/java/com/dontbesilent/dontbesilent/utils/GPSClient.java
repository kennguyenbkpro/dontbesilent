package com.dontbesilent.dontbesilent.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;

/**
 * Created by lamhx on 29/10/2016.
 */
public class GPSClient {

    public static void showSettingsLocation(final Context context) {
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                context);
        alertDialog.setTitle("SETTINGS");
        alertDialog.setMessage("Enable Location Provider! Go to settings menu?");
        alertDialog.setPositiveButton("Settings",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent = new Intent(
                                Settings.ACTION_LOCATION_SOURCE_SETTINGS);

                        context.startActivity(intent);


                    }
                });
        alertDialog.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
                    }
                });
        alertDialog.setCancelable(true);
        alertDialog.show();
    }
}
