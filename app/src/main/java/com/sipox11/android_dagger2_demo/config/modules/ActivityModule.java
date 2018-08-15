package com.sipox11.android_dagger2_demo.config.modules;

import android.app.Activity;
import android.content.Context;

import com.sipox11.android_dagger2_demo.config.qualifiers.ActivityContext;
import com.sipox11.android_dagger2_demo.config.scopes.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    private Activity mActivity;

    public ActivityModule(Activity activity) {
        mActivity = activity;
    }

    @ActivityScope
    @Provides
    @ActivityContext
    public Context provideContext() {
        return mActivity;
    }

    @ActivityScope
    @Provides
    public Activity provideActivity() {
        return mActivity;
    }
}
