package com.sipox11.android_dagger2_demo.config.components;

import android.content.Context;

import com.sipox11.android_dagger2_demo.config.GlobalApplication;
import com.sipox11.android_dagger2_demo.config.modules.AppModule;
import com.sipox11.android_dagger2_demo.config.qualifiers.AppContext;
import com.sipox11.android_dagger2_demo.config.scopes.AppScope;
import com.sipox11.android_dagger2_demo.data.local.DataLayer;
import com.sipox11.android_dagger2_demo.data.local.helpers.DBHelper;
import com.sipox11.android_dagger2_demo.data.local.helpers.SharedPrefsHelper;

import dagger.Component;

@AppScope
@Component(modules = {AppModule.class})
public interface AppComponent {

    void inject(GlobalApplication app);

    @AppContext
    Context getContext();

    GlobalApplication getApp();

    DataLayer getDataLayer();

    SharedPrefsHelper getSharedPrefsHelper();

    DBHelper getDBHelper();

}
