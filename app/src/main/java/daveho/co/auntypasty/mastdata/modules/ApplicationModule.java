package daveho.co.auntypasty.mastdata.modules;

import android.app.Application;
import android.content.Context;

public class ApplicationModule {

    private static Context mAppContext;
    private static Application mApplication;

    public static void init(Application app) {
        mApplication = app;
        mAppContext = app.getApplicationContext();
    }

    public static Application application() {
        return mApplication;
    }

    public static Context applicationContext() {
        return mAppContext;
    }
}
