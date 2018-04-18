package com.umbrella.plugin.storebridge;

import com.umbrella.game.ubsdk.pluginimpl.UBAD;
import com.umbrella.game.ubsdk.utils.UBLogUtil;

public class ADBridge {
	private final String TAG=ADBridge.class.getSimpleName();
	
	public void showADWithADType(int adType){
		UBLogUtil.logI(TAG+"----->showADWithADType");
		UBAD.getInstance().showADWithADType(adType);
	}
	
	public void hideADWithADType(int adType){
		UBLogUtil.logI(TAG+"----->hideADWithADType");
		UBAD.getInstance().hideADWithADType(adType);
	}
	
	public boolean isSupportADType(int adType){
		UBLogUtil.logI(TAG+"----->isSupportADType");
		return UBAD.getInstance().isSupportADType(adType);
	}
}
