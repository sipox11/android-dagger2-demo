package com.sipox11.android_dagger2_demo.config;

import android.app.Application;
import android.content.Context;

import com.sipox11.android_dagger2_demo.config.components.AppComponent;
import com.sipox11.android_dagger2_demo.config.components.DaggerAppComponent;
import com.sipox11.android_dagger2_demo.config.modules.AppModule;
import com.sipox11.android_dagger2_demo.data.local.DataLayer;

import javax.inject.Inject;

public class GlobalApplication extends Application {

    protected AppComponent appComponent;

    @Inject
    DataLayer dataLayer;

    public static GlobalApplication get(Context context) {
        return (GlobalApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .build();
        appComponent.inject(this);
    }

    public AppComponent getComponent(){
        return appComponent;
    }
}
