package com.umbrella.plugin.storebridge;

import com.umbrella.game.ubsdk.callback.UBADCallback;
import com.umbrella.game.ubsdk.pluginimpl.UBAD;
import com.umbrella.game.ubsdk.plugintype.ad.ADType;
import com.umbrella.game.ubsdk.utils.UBLogUtil;

public class ADBridge {
	private final String TAG=ADBridge.class.getSimpleName();
	
	public void showADWithADType(int adType){
		UBLogUtil.logI(TAG+"----->showADWithADType");
		UBAD.getInstance().showADWithADType(adType,mUBADCallback);
	}
	
	public void hideADWithADType(int adType){
		UBLogUtil.logI(TAG+"----->hideADWithADType");
		UBAD.getInstance().hideADWithADType(adType);
	}
	
	public boolean isSupportADType(int adType){
		UBLogUtil.logI(TAG+"----->isSupportADType");
		return UBAD.getInstance().isSupportADType(adType);
	}
	
	private UBADCallback mUBADCallback=new UBADCallback(){
		@Override
		public void onComplete(int adType, String msg) {
			switch (adType) {
			case ADType.AD_TYPE_REWARDVIDEO:
				UBLogUtil.logI(TAG+"----->UBAD----->onComplete rewardVideo AD");
//				TODO
				break;

			default:
				UBLogUtil.logI(TAG+"----->UBAD----->onComplete with adType="+adType);
				break;
			}
		}

		@Override
		public void onFailed(int adType, String msg) {
			switch (adType) {
			case ADType.AD_TYPE_REWARDVIDEO:
				UBLogUtil.logI(TAG+"----->UBAD----->onFailed rewardVideo AD");
//				TODO
				break;

			default:
				UBLogUtil.logI(TAG+"----->UBAD----->onFailed with adType="+adType);
				break;
			}
		}
		
		@Override
		public void onShow(int adType, String msg) {
			switch (adType) {
			case ADType.AD_TYPE_REWARDVIDEO:
				UBLogUtil.logI(TAG+"----->UBAD----->onShow rewardVideo AD");
//				TODO
				break;
				
			default:
				UBLogUtil.logI(TAG+"----->UBAD-----onShow with adType="+adType);
				break;
			}
		}
		
		@Override
		public void onClick(int adType, String msg) {
			UBLogUtil.logI(TAG+"----->UBAD----->onClick");
			switch (adType) {
			case ADType.AD_TYPE_REWARDVIDEO:
				UBLogUtil.logI(TAG+"----->UBAD----->onClick RewardVideo AD");
//				TODO
				break;

			default:
				UBLogUtil.logI(TAG+"----->UBAD----->onClick with adType="+adType);
				break;
			}
		}

		@Override
		public void onClosed(int adType, String msg) {
			switch (adType) {
			case ADType.AD_TYPE_REWARDVIDEO:
				UBLogUtil.logI(TAG+"----->UBAD----->onClosed rewardVideo AD");
//				TODO
				break;

			default:
				UBLogUtil.logI(TAG+"----->UBAD----->onClosed with adType="+adType);
				break;
			}
		}

	};
	
}
