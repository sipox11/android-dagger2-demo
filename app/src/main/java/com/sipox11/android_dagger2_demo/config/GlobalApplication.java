package com.sipox11.android_dagger2_demo.config;

import android.app.Application;
import android.content.Context;

import com.sipox11.android_dagger2_demo.data.local.DataLayer;

import javax.inject.Inject;

public class GlobalApplication extends Application {

    protected AppComponent applicationComponent;

    @Inject
    DataLayer dataLayer;

    public static GlobalApplication get(Context context) {
        return (GlobalApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        applicationComponent.inject(this);
    }

    public AppComponent getComponent(){
        return applicationComponent;
    }
}
