package com.sipox11.android_dagger2_demo.config.components;

import com.sipox11.android_dagger2_demo.config.modules.ActivityModule;
import com.sipox11.android_dagger2_demo.config.scopes.ActivityScope;
import com.sipox11.android_dagger2_demo.ui.MainActivity;

import dagger.Component;

@ActivityScope
@Component(
        dependencies = {AppComponent.class},
        modules = {ActivityModule.class})
public interface ActivityComponent {

    void inject(MainActivity activity);
}
