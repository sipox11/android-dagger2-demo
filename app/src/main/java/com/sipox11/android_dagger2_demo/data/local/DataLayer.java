package com.sipox11.android_dagger2_demo.data.local;

import android.content.Context;
import android.content.res.Resources;

import com.sipox11.android_dagger2_demo.config.qualifiers.AppContext;
import com.sipox11.android_dagger2_demo.data.local.helpers.DBHelper;
import com.sipox11.android_dagger2_demo.data.local.helpers.SharedPrefsHelper;
import com.sipox11.android_dagger2_demo.data.models.User;

import javax.inject.Inject;

public class DataLayer {

    private Context mContext;
    private DBHelper mDbHelper;
    private SharedPrefsHelper mSharedPrefsHelper;

    @Inject
    public DataLayer(@AppContext Context context,
                     DBHelper dbHelper,
                     SharedPrefsHelper sharedPrefsHelper) {
        mContext = context;
        mDbHelper = dbHelper;
        mSharedPrefsHelper = sharedPrefsHelper;
    }

    public void saveAccessToken(String accessToken) {
        mSharedPrefsHelper.put(SharedPrefsHelper.PREF_KEY_ACCESS_TOKEN, accessToken);
    }

    public String getAccessToken(){
        return mSharedPrefsHelper.get(SharedPrefsHelper.PREF_KEY_ACCESS_TOKEN, null);
    }

    public Long createUser(User user) throws Exception {
        return mDbHelper.insertUser(user);
    }

    public User getUser(Long userId) throws Resources.NotFoundException, NullPointerException {
        return mDbHelper.getUser(userId);
    }
}
