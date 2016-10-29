package com.dontbesilent.dontbesilent.util;

import android.content.res.Resources;

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
}
