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
    	getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
//      step1 :setSDKListener
        setSDKListener();
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
    
	private void setSDKListener() {
//		切换账号监听
    	UBSDK.getInstance().setUBSwitchAccountCallback(new UBSwitchAccountCallback() {
			
			@Override
			public void onSuccess(UBUserInfo ubUserInfo) {
				UBLogUtil.logI(TAG+"----->switchAccount----->onSuccess");
			}
			
			@Override
			public void onFailed(String message, String trace) {
				UBLogUtil.logI(TAG+"----->switchAccount----->onFailed");
			}
			
			@Override
			public void onCancel() {
				UBLogUtil.logI(TAG+"----->switchAccount----->onCancel");
			}
		});
	}


    @Override
    protected void onStart() {
        UBLogUtil.logI(TAG,"onStart");
        UBSDK.getInstance().onStart();
        super.onStart();
    }

    @Override
    protected void onRestart() {
        UBLogUtil.logI(TAG,"onRestart");
        UBSDK.getInstance().onRestart();
        super.onRestart();
    }

    @Override
    protected void onResume() {
        UBLogUtil.logI(TAG,"onResume");
        UBSDK.getInstance().onResume();
        super.onResume();
    }

    @Override
    protected void onPause() {
        UBLogUtil.logI(TAG+"----->onPause");
        UBSDK.getInstance().onPause();
        super.onPause();
    }

    @Override
    protected void onStop() {
        UBLogUtil.logI(TAG+"----->onStop");
        UBSDK.getInstance().onStop();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        UBLogUtil.logI(TAG+"----->onDestroy");
        UBSDK.getInstance().onDestroy();
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        UBLogUtil.logI(TAG+"----->onBackPressed");
        UBSDK.getInstance().onBackPressed();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        UBLogUtil.logI(TAG+"----->onNewIntent");
        UBSDK.getInstance().onNewIntent(intent);
        super.onNewIntent(intent);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        UBLogUtil.logI(TAG+"----->onConfigurationChanged");
        UBSDK.getInstance().onConfigurationChanged(newConfig);
        super.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        UBLogUtil.logI(TAG+"----->onActivityResult");
        UBSDK.getInstance().onActivityResult(requestCode,resultCode,data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
    	UBLogUtil.logI(TAG+"----->onRequestPermissionsResult");
    	super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
