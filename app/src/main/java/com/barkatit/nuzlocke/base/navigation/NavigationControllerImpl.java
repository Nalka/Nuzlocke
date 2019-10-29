package com.barkatit.nuzlocke.base.navigation;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

public class NavigationControllerImpl implements NavigationController {

    private NavigationOnResultListener navigationOnResultListener;


    public void startActivity(Context context, Class<Activity> activityClass) {
        startActivity(context, activityClass, null);
    }

    public void startActivity(Context context, Class<Activity> activityClass, Bundle data) {

    }

    public void startActivityForResult(Context context, Class<Activity> activityClass, NavigationOnResultListener resultListener) {
        startActivityForResult(context, activityClass, null, resultListener);
    }

    public void startActivityForResult(Context context, Class<Activity> activityClass, Bundle data, NavigationOnResultListener resultListener) {
        this.navigationOnResultListener = resultListener;

    }

    public void onActivityResult(int result, int requestCode, Bundle data) {
        if(navigationOnResultListener != null) {
            navigationOnResultListener.onResult(result, requestCode, data);
        }
    }

}
