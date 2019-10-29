package com.barkatit.nuzlocke.base;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class BaseActivityModule {

    @Binds
    abstract BaseView provideBaseView(BaseActivity baseActivity);
}
