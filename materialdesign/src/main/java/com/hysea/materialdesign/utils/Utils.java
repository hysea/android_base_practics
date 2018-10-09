package com.hysea.materialdesign.utils;

import android.content.Context;

public class Utils {

    public static int dp2px(Context context, int dpValue) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * density + 0.5f);
    }
}
