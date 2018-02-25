package com.ricky.latte.utils;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.ricky.latte.app.Latte;

/**
 * @author hepengcheng
 * @package_name com.ricky.latte.utils
 * @date 2018/1/12
 */

public class DimenUtil {

    public static int getScreenWidth() {
        final Resources resources = Latte.getApplication().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int getScreenHeight() {
        final Resources resources = Latte.getApplication().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;
    }

}
