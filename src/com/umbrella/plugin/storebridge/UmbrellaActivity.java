package com.umbrella.plugin.storebridge;

import com.umbrella.game.ubsdk.UBSDK;
import com.umbrella.game.ubsdk.callback.UBInitCallback;
import com.umbrella.game.ubsdk.callback.UBSwitchAccountCallback;
import com.umbrella.game.ubsdk.plugintype.user.UBUserInfo;
import com.umbrella.game.ubsdk.utils.UBLogUtil;
import com.unity3d.player.UnityPlayerActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

/**
 * author:qingshanliao
 * date:2018/3/1
 */
//!!!!!!!!!!!!  attention please  .The actual access please inherit from the game's main activity

public class UmbrellaActivity extends UnityPlayerActivity{
    private final String TAG="Umbrella";

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
//      step1 :setListener
        setListener();
//      step2:init
        UBSDK.getInstance().init(this, new UBInitCallback() {
            @Override
            public void onSuccess() {

                UBLogUtil.logI(TAG,"init----->onSuccess");
            }

            @Override
            public void onFailed(String s, String s1) {
                UBLogUtil.logI(TAG,"init----->onFailed");
            }
        });

        UBSDK.getInstance().onCreate(savedInstanceState);
    }

    private void setListener() {
        //      setSwitchAccountCallbackListener
        UBSDK.getInstance().setUBSwitchAccountCallback(new UBSwitchAccountCallback() {
            @Override
            public void onSuccess(UBUserInfo ubUserInfo) {
//                TODO
                UBLogUtil.logI(TAG,"switchAccount----->onSuccess");
            }

            @Override
            public void onCancel() {
                UBLogUtil.logI(TAG,"switchAccount----->onCancel");
//                TODO
            }

            @Override
            public void onFailed(String s, String s1) {
//                TODO
                UBLogUtil.logI(TAG,"switchAccount----->onFailed");
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        UBLogUtil.logI(TAG,"onStart");
        UBSDK.getInstance().onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        UBLogUtil.logI(TAG,"onRestart");
        UBSDK.getInstance().onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        UBLogUtil.logI(TAG,"onResume");
        UBSDK.getInstance().onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        UBLogUtil.logI(TAG,"onPause");
        UBSDK.getInstance().onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        UBLogUtil.logI(TAG,"onStop");
        UBSDK.getInstance().onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        UBLogUtil.logI(TAG,"onDestroy");
        UBSDK.getInstance().onDestroy();
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        UBLogUtil.logI(TAG,"onBackPressed");
        UBSDK.getInstance().onBackPressed();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        UBLogUtil.logI(TAG,"onNewIntent");
        UBSDK.getInstance().onNewIntent(intent);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        UBLogUtil.logI(TAG,"onConfigurationChanged");
        UBSDK.getInstance().onConfigurationChanged(newConfig);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UBLogUtil.logI(TAG,"onActivityResult");
        UBSDK.getInstance().onActivityResult(requestCode,resultCode,data);
    }

}
