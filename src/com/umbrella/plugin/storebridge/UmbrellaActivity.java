package com.umbrella.plugin.storebridge;

import com.umbrella.game.ubsdk.UBSDK;
import com.umbrella.game.ubsdk.callback.UBADCallback;
import com.umbrella.game.ubsdk.callback.UBInitCallback;
import com.umbrella.game.ubsdk.callback.UBSwitchAccountCallback;
import com.umbrella.game.ubsdk.pluginimpl.UBAD;
import com.umbrella.game.ubsdk.plugintype.ad.ADType;
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
    	
//    	设置广告监听
    	UBAD.getInstance().setUBADCallback(new UBADCallback(){

			@Override
			public void onInit(boolean isInitSuccess, String msg) {
				UBLogUtil.logI(TAG+"----->UBAD----->onInit");
				if (isInitSuccess) {
					UBLogUtil.logI(TAG+"----->UBAD----->init success!");
				}else{
					UBLogUtil.logI(TAG+"----->UBAD----->init fail!");
				}
			}

			@Override
			public void onClick(int adType, String msg) {
				UBLogUtil.logI(TAG+"----->UBAD----->onClick");
				switch (adType) {
				case ADType.AD_TYPE_BANNER:
					UBLogUtil.logI(TAG+"----->UBAD----->onClick banner AD");
					break;
				case ADType.AD_TYPE_INTERSTITIAL:
					UBLogUtil.logI(TAG+"----->UBAD----->onClick fullscreen AD");
					break;
				case ADType.AD_TYPE_REWARDVIDEO:
					UBLogUtil.logI(TAG+"----->UBAD----->onClick rewardVideo AD");
					break;
				case ADType.AD_TYPE_SPLASH:
					UBLogUtil.logI(TAG+"----->UBAD----->onClick splash AD");
					break;

				default:
					break;
				}
			}

			@Override
			public void onComplete(int adType, String msg) {
				switch (adType) {
				case ADType.AD_TYPE_BANNER:
					UBLogUtil.logI(TAG+"----->UBAD----->onComplete banner AD");
					break;
				case ADType.AD_TYPE_INTERSTITIAL:
					UBLogUtil.logI(TAG+"----->UBAD----->onComplete fullscreen AD");
					break;
				case ADType.AD_TYPE_REWARDVIDEO:
					UBLogUtil.logI(TAG+"----->UBAD----->onComplete rewardVideo AD");
					break;
				case ADType.AD_TYPE_SPLASH:
					UBLogUtil.logI(TAG+"----->UBAD----->onComplete splash AD");
					break;

				default:
					break;
				}
			}

			@Override
			public void onShow(int adType, String msg) {
				switch (adType) {
				case ADType.AD_TYPE_BANNER:
					UBLogUtil.logI(TAG+"----->UBAD----->onShow banner AD");
					break;
				case ADType.AD_TYPE_INTERSTITIAL:
					UBLogUtil.logI(TAG+"----->UBAD----->onShow fullscreen AD");
					break;
				case ADType.AD_TYPE_REWARDVIDEO:
					UBLogUtil.logI(TAG+"----->UBAD----->onShow rewardVideo AD");
					break;
				case ADType.AD_TYPE_SPLASH:
					UBLogUtil.logI(TAG+"----->UBAD----->onShow splash AD");
					break;

				default:
					break;
				}
			}

			@Override
			public void onClosed(int adType, String msg) {
				switch (adType) {
				case ADType.AD_TYPE_BANNER:
					UBLogUtil.logI(TAG+"----->UBAD----->onClosed banner AD");
					break;
				case ADType.AD_TYPE_INTERSTITIAL:
					UBLogUtil.logI(TAG+"----->UBAD----->onClosed fullscreen AD");
					break;
				case ADType.AD_TYPE_REWARDVIDEO:
					UBLogUtil.logI(TAG+"----->UBAD----->onClosed rewardVideo AD");
					break;
				case ADType.AD_TYPE_SPLASH:
					UBLogUtil.logI(TAG+"----->UBAD----->onClosed splash AD");
					break;

				default:
					break;
				}
			}

			@Override
			public void onFailed(int adType, String msg) {
				switch (adType) {
				case ADType.AD_TYPE_BANNER:
					UBLogUtil.logI(TAG+"----->UBAD----->onFailed banner AD");
					break;
				case ADType.AD_TYPE_INTERSTITIAL:
					UBLogUtil.logI(TAG+"----->UBAD----->onFailed fullscreen AD");
					break;
				case ADType.AD_TYPE_REWARDVIDEO:
					UBLogUtil.logI(TAG+"----->UBAD----->onFailed rewardVideo AD");
					break;
				case ADType.AD_TYPE_SPLASH:
					UBLogUtil.logI(TAG+"----->UBAD----->onFailed splash AD");
					break;

				default:
					break;
				}
			}});
		
	}


    @Override
    protected void onStart() {
        super.onStart();
        UBSDK.getInstance().onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        UBSDK.getInstance().onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        UBSDK.getInstance().onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        UBSDK.getInstance().onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        UBSDK.getInstance().onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        UBSDK.getInstance().onDestroy();
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        UBSDK.getInstance().onBackPressed();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        UBSDK.getInstance().onNewIntent(intent);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        UBSDK.getInstance().onConfigurationChanged(newConfig);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UBSDK.getInstance().onActivityResult(requestCode,resultCode,data);
    }

}
