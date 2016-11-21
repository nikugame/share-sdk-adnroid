package com.nikugame.tanchishe.wxapi;

import android.app.Activity;
import android.os.Bundle;

import com.nkgame.nksharesdk.NKConfig;
import com.nkgame.nksharesdk.NKConstants;
import com.nkgame.nksharesdk.NKListener;
import com.nkgame.nksharesdk.NKLog;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

/**
 * Created by zhangzhen on 2016/11/21.
 */

public class WXEntryActivity extends Activity implements IWXAPIEventHandler {
    private IWXAPI mApi;
    private MyListener listener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApi = WXAPIFactory.createWXAPI(this, NKConfig.getInstatnce().getAppId_Wechat(), true);
        mApi.handleIntent(this.getIntent(), this);

    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {
        listener=new MyListener();
        switch (baseResp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                //发送成功
                NKLog.NKGame.e("分享成功");
                listener.onResult(NKConstants.ResultOK,NKConstants.ShareSuccess);
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                listener.onResult(NKConstants.ResultUserCancel,NKConstants.ShareCanceled);
                NKLog.NKGame.e("分享取消");
                //发送取消
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                //发送被拒绝
                NKLog.NKGame.e("认证被拒绝");
               listener.onResult(NKConstants.ResultUnknownError,NKConstants.SharetFailed);
                break;
            default:
                //发送返回
                NKLog.NKGame.e("分享返回");
                listener.onResult(NKConstants.ResultUnknownError,NKConstants.SharetFailed);

                break;
        }
        finish();
    }
    private class MyListener implements NKListener {

        @Override
        public void onInit(int errorCode, String result) {

        }

        @Override
        public void onResult(int errorCode, String result) {
            NKLog.NKGame.e("errorCode:  "+errorCode+"  ,result:  "+result);
//            WXEntryActivity.this.finish();
        }

        @Override
        public void onAuth(int errorCode, String result) {

        }
    }
}
