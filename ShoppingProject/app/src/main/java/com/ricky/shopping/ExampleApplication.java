package com.ricky.shopping;

import android.app.Application;

import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.ricky.latte.app.Latte;

/**
 * @author hepengcheng
 * @package_name com.ricky.shopping
 * @date 2017/12/25
 */

public class ExampleApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withIcon(new FontAwesomeModule())
                .withApiHost("http://www.baidu.com")
                .configure();
    }
}
