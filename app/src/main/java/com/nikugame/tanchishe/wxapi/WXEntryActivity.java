package com.nikugame.tanchishe.wxapi;

import android.app.Activity;
import android.os.Bundle;

import com.nkgame.nksharesdk.NKShareConfig;
import com.nkgame.nksharesdk.NKShareConstants;
import com.nkgame.nksharesdk.NKShareListener;
import com.nkgame.nksharesdk.NKShareLog;
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
        mApi = WXAPIFactory.createWXAPI(this, NKShareConfig.getInstatnce().getAppId_Wechat(), true);
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
                NKShareLog.NKGame.e("分享成功");
                listener.onResult(NKShareConstants.ResultOK,NKShareConstants.ShareSuccess);
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                listener.onResult(NKShareConstants.ResultUserCancel,NKShareConstants.ShareCanceled);
                NKShareLog.NKGame.e("分享取消");
                //发送取消
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                //发送被拒绝
                NKShareLog.NKGame.e("认证被拒绝");
               listener.onResult(NKShareConstants.ResultUnknownError,NKShareConstants.SharetFailed);
                break;
            default:
                //发送返回
                NKShareLog.NKGame.e("分享返回");
                listener.onResult(NKShareConstants.ResultUnknownError,NKShareConstants.SharetFailed);

                break;
        }
        finish();
    }
    private class MyListener implements NKShareListener {

        @Override
        public void onInit(int errorCode, String result) {

        }

        @Override
        public void onResult(int errorCode, String result) {
            NKShareLog.NKGame.e("errorCode:  "+errorCode+"  ,result:  "+result);
//            WXEntryActivity.this.finish();
        }

        @Override
        public void onAuth(int errorCode, String result) {

        }
    }
}
