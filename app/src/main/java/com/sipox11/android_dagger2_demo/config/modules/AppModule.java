package com.sipox11.android_dagger2_demo.config.modules;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.sipox11.android_dagger2_demo.config.GlobalApplication;
import com.sipox11.android_dagger2_demo.config.qualifiers.AppContext;
import com.sipox11.android_dagger2_demo.config.qualifiers.DatabaseInfo;
import com.sipox11.android_dagger2_demo.config.scopes.AppScope;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private final GlobalApplication app;

    public AppModule(GlobalApplication app) {
        this.app = app;
    }

    @AppScope
    @Provides
    @AppContext
    public Context provideContext() {
        return app;
    }

    @AppScope
    @Provides
    public GlobalApplication provideApp() {
        return app;
    }

    @AppScope
    @Provides
    @DatabaseInfo
    public String provideDatabaseName() {
        return "demo-dagger2.db";
    }

    @AppScope
    @Provides
    @DatabaseInfo
    public int provideDatabaseVersion() {
        return 1;
    }

    @AppScope
    @Provides
    public SharedPreferences provideSharedPreferences() {
        return app.getSharedPreferences("demo-prefs", Context.MODE_PRIVATE);
    }


}
