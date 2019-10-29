package com.barkatit.nuzlocke.base.navigation;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

public interface NavigationController {

    void startActivity(Context context, Class<Activity> activityClass);

    void startActivity(Context context, Class<Activity> activityClass, Bundle data);

    void startActivityForResult(Context context, Class<Activity> activityClass, NavigationOnResultListener resultListener);

    void startActivityForResult(Context context, Class<Activity> activityClass, Bundle data, NavigationOnResultListener resultListener);
}
