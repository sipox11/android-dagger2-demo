package com.sipox11.android_dagger2_demo.config.modules;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.sipox11.android_dagger2_demo.config.qualifiers.AppContext;
import com.sipox11.android_dagger2_demo.config.qualifiers.DatabaseInfo;
import com.sipox11.android_dagger2_demo.data.local.helpers.SharedPrefsHelper;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private final Application app;

    public AppModule(Application app) {
        this.app = app;
    }

    @Provides
    @AppContext
    public Context provideContext() {
        return app;
    }

    @Provides
    public Application provideApp() {
        return app;
    }

    @Provides
    @DatabaseInfo
    public String provideDatabaseName() {
        return "demo-dagger2.db";
    }

    @Provides
    @DatabaseInfo
    public int provideDatabaseVersion() {
        return 1;
    }

    @Provides
    public SharedPreferences provideSharedPreferences() {
        return app.getSharedPreferences("demo-prefs", Context.MODE_PRIVATE);
    }


}
