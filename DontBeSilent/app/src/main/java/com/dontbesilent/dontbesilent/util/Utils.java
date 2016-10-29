package com.dontbesilent.dontbesilent.util;

import android.content.res.Resources;
import android.os.Build;
import android.os.Environment;

import java.io.File;

/**
 * Created by CuTi on 10/29/2016.
 */

public class Utils {
    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public static int pxToDp(int px) {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }

    public static File getMediaDirectory()
    {
        return new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "Zalo");
    }

    public static boolean materialDesignSupported() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }
}
