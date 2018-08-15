package com.sipox11.android_dagger2_demo.ui;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.sipox11.android_dagger2_demo.R;
import com.sipox11.android_dagger2_demo.config.GlobalApplication;
import com.sipox11.android_dagger2_demo.data.local.DataLayer;
import com.sipox11.android_dagger2_demo.data.models.User;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    DataLayer dataLayer;

    private ActivityComponent activityComponent;

    private TextView mTvUserInfo;
    private TextView mTvAccessToken;

    public ActivityComponent getActivityComponent() {
        if (activityComponent == null) {
            activityComponent = DaggerActivityComponent.builder()
                    .activityModule(new ActivityModule(this))
                    .applicationComponent(GlobalApplication.get(this).getComponent())
                    .build();
        }
        return activityComponent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getActivityComponent().inject(this);

        mTvUserInfo = findViewById(R.id.tv_user_info);
        mTvAccessToken = findViewById(R.id.tv_access_token);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        createUser();
        getUser();
        dataLayer.saveAccessToken("ASDR12443JFDJF43543J543H3K543");

        String token = dataLayer.getAccessToken();
        if(token != null){
            mTvAccessToken.setText(token);
        }
    }

    private void createUser() {
        try {
            dataLayer.createUser(new User("JC", "19, Street, City, Spain"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getUser() {
        try {
            User user = dataLayer.getUser(1L);
            mTvUserInfo.setText(user.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
