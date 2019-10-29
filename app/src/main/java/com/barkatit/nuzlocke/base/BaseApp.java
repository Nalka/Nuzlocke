package com.barkatit.nuzlocke.base;

import com.barkatit.nuzlocke.di.AppComponent;
import com.barkatit.nuzlocke.di.DaggerAppComponent;
import com.facebook.stetho.Stetho;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

public class BaseApp extends DaggerApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        AppComponent component = DaggerAppComponent.builder().application(this).build();
        component.inject(this);

        return component;
    }
}
