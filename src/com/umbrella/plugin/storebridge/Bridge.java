package com.umbrella.plugin.storebridge;

import org.json.JSONException;
import org.json.JSONObject;

import com.umbrella.game.ubsdk.UBSDK;
import com.umbrella.game.ubsdk.callback.UBExitCallback;
import com.umbrella.game.ubsdk.callback.UBGamePauseCallback;
import com.umbrella.game.ubsdk.callback.UBLoginCallback;
import com.umbrella.game.ubsdk.callback.UBLogoutCallback;
import com.umbrella.game.ubsdk.callback.UBPayCallback;
import com.umbrella.game.ubsdk.plugintype.pay.UBOrderInfo;
import com.umbrella.game.ubsdk.plugintype.user.UBUserInfo;
import com.umbrella.game.ubsdk.utils.UBLogUtil;

/**
 * Created by Joshua on 2018-02-12.
 */
public class Bridge {
	private String TAG= "Umbrella";

	public String getStoreName() {
		return UBSDK.getInstance().getPlatformName();
	}

	public void login(final Callback onResult) {
		UBLogUtil.logI(TAG, "login() called");
		UBSDK.getInstance().login(new UBLoginCallback()
		{
			@Override
			public void onSuccess(UBUserInfo ubUserInfo)
			{
//				If the store asks for login, then you can start the game, otherwise it will be useless
//				TODO
				String loginSuccessStr="login success:" + "\n\r" + "UserID:  " + ubUserInfo.getUid() + "\n\r" + "UserName:  "
					                       + ubUserInfo.getUserName() + "\n\r" + "Token:  " + ubUserInfo.getToken() + "\n\r"+"extra:" + ubUserInfo.getExtra();

				UBLogUtil.logI(TAG,loginSuccessStr);
				onResult.Activate(1, loginSuccessStr);
			}

			@Override
			public void onFailed(String msg, String trace)
			{
//				TODO
				String loginFailStr="login fail:" + "\n\r" + "msg :  " + msg + "\n\r" + "trace:  " + trace;
				UBLogUtil.logI(TAG,loginFailStr);

				onResult.Activate(0, loginFailStr);
			}

			@Override
			public void onCancel()
			{
//				TODO
				UBLogUtil.logI(TAG,"cancel by user");

				onResult.Activate(-1, "canceled");
			}
		});

		UBLogUtil.logI(TAG, "login() finished");
	}

	public void onUserClickedExit(final Callback onResult) {
		UBLogUtil.logI(TAG, "onUserClickedExit()");

		UBSDK.getInstance().exit(new UBExitCallback() {
			@Override
			public void onExit() {
//              TODO
				UBLogUtil.logI(TAG,"exit----->onExit");
				if (onResult!=null){
					onResult.Activate(1,"Exit confirmed");
				}
			}

			@Override
			public void onCancel(String s, String s1) {
//              TODO
				UBLogUtil.logI(TAG,"exit----->onCancel");

				if (onResult!=null){
					onResult.Activate(-1,"Exit canceled");
				}
			}

			@Override
			public void noImplement() {
				if (onResult!=null){
					onResult.Activate(0,"store noImplement");
				}
			}
		});

	}


	public void gamePause(final Callback onResult){
		UBSDK.getInstance().gamePause(new UBGamePauseCallback() {
			@Override
			public void onGamePause() {
				if (onResult!=null){
					onResult.Activate(1,"gamePause");
				}
			}

			@Override
			public void onFailed(String s) {
				if (onResult!=null){
					onResult.Activate(0,"gamePause failed");
				}
			}
		});
	}

	public void logout(final Callback onResult) {
		UBSDK.getInstance().logout(new UBLogoutCallback()
		{
			@Override
			public void onSuccess()
			{
//				If the store asks for login, go back to the place where you want to login, but do not have to login
				UBLogUtil.logI(TAG,"logout success");

				onResult.Activate(1,"logout success");
			}

			@Override
			public void onFailed(String msg, String trace)
			{
				String loginFailStr="logout fail ： " + "\n\r" + "msg : " + msg +"\n\r"+  "trace : " + trace;
				UBLogUtil.logI(TAG,loginFailStr);
				onResult.Activate(0,"logout failed");
			}
		});
	}

	public void doPurchase(String goodsID, String cpOrderId, String extra, final Callback onResult) {
		UBOrderInfo orderInfo= new UBOrderInfo();
		orderInfo.setCpOrderID(cpOrderId);
		orderInfo.setGoodsID(goodsID); //goodsId
		orderInfo.setExtrasParams(extra); // Additional expansion parameters

		doPurchase(orderInfo, onResult);
	}

	public void doPurchase_old(String cpOrderId, String goodsID, String goodsName, String goodsDesc, int goodsCount, double cost, String extra, String callbackUrl, final Callback onResult) {
//		If there is no role stand-alone game,set null
//		UBRoleInfo roleInfo = new UBRoleInfo();

		UBOrderInfo orderInfo = new UBOrderInfo();
		orderInfo.setCpOrderID(cpOrderId);
		orderInfo.setGoodsID(goodsID); //goodsId
		orderInfo.setGoodsName(goodsName);// goodsName
		orderInfo.setGoodsDesc(goodsDesc);// goodsDesc
		orderInfo.setCount(goodsCount);// goodsCount usually 1
		orderInfo.setAmount(cost); // total cost
		orderInfo.setExtrasParams(extra); // Additional expansion parameters
		orderInfo.setCallbackUrl(callbackUrl);//callback url :Commonly used for server notifications

		doPurchase(orderInfo, onResult);
	}

	public void doPurchase(UBOrderInfo orderInfo, final Callback onResult) {
		//UBLogUtil.logI(TAG, "setting payType to 1, for no good reason.");
		orderInfo.setPayType(1);

		UBSDK.getInstance().pay(null,orderInfo, new UBPayCallback() {

			@Override
			public void onSuccess(String cpOrderID, String orderID, String goodsID, String goodsName, String goodsPrice, String extrasParams) {
	//				If you do not have access to the server, you can give the player made a props
				try {
					UBLogUtil.logI(TAG,"pay:success----->cpOrderID:"+cpOrderID);
					UBLogUtil.logI(TAG,"pay:success----->orderID:"+orderID);
					UBLogUtil.logI(TAG,"pay:success----->goodsID:"+goodsID);
					UBLogUtil.logI(TAG,"pay:success----->goodsName:"+goodsName);
					UBLogUtil.logI(TAG,"pay:success----->goodsPrice:"+goodsPrice);
					UBLogUtil.logI(TAG,"pay:success----->extrasParams:"+extrasParams);

					JSONObject jsob = new JSONObject();
					jsob.put("cpOrderID",cpOrderID);
					jsob.put("orderID",orderID);
					jsob.put("goodsID",goodsID);
					jsob.put("goodsName",goodsName);
					jsob.put("goodsPrice",goodsPrice);
					jsob.put("extrasParams",extrasParams);
					if (onResult!=null){
						onResult.Activate(1,jsob.toString());
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void onFailed(String cpOrderID, String message, String trace)
			{

				try {
					UBLogUtil.logI(TAG,"pay:fail----->cpOrderID:"+cpOrderID);
					UBLogUtil.logI(TAG,"pay:fail----->message:"+message);

					JSONObject jsob = new JSONObject();
					jsob.put("cpOrderID",cpOrderID);
					jsob.put("errorMsg",message);
					if (onResult!=null){
						onResult.Activate(0,jsob.toString());
					}
				}catch (JSONException e) {
						e.printStackTrace();
				}
			}


			@Override
			public void onCancel(String cpOrderID)
			{

				try {
					UBLogUtil.logI(TAG,"pay:cancel----->cpOrderID:"+cpOrderID);

					JSONObject jsob = new JSONObject();
					jsob.put("cpOrderID",cpOrderID);
					jsob.put("errorMsg","cancel");
					if (onResult!=null){
						onResult.Activate(-1,jsob.toString());
					}
				}catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});
	}


	/**
	 * This method is usually called to enter the game, create a role (if there is a role), the role of upgrading
	 * @param roleType
	 */
	public void setGameDataInfo(int roleType) {
		throw new UnsupportedOperationException();

//		If there is no role stand-alone game,set null
//		UBRoleInfo roleInfo = new UBRoleInfo();
		/*

		UBRoleInfo roleInfo = new UBRoleInfo();
		roleInfo.setServerID("1");// 服务器ID
		roleInfo.setServerName("服务器1");// 服务器名称
		roleInfo.setRoleName("冰上上的王者");// 角色名称
		roleInfo.setRoleID("2666255");// 角色ID
		roleInfo.setRoleLevel("8");// 等级
		roleInfo.setVipLevel("Vip1");// VIP等级
		roleInfo.setGameBalance("300");// 角色现有金额
		roleInfo.setPartyName("partyName"); // 公会名字

		UBSDK.getInstance().setGameDataInfo(null, roleType);
		*/
	}

}
