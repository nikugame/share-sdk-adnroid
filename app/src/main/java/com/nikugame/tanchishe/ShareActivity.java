package com.nikugame.tanchishe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.nkgame.nksharesdk.NKConstants;
import com.nkgame.nksharesdk.NKListener;
import com.nkgame.nksharesdk.NKLog;
import com.nkgame.nksharesdk.NKShareSDK;


public class ShareActivity extends AppCompatActivity implements View.OnClickListener {

    private NKShareSDK nkShareSDK;
    private Button QQshare;
    private Button QQZoneShare;
    private Button wechatShare;
    private Button wechatFrendsShare;
    private Button wechatFavoriteShare;
    private Button weiboShare;
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

        setListener();

        nkShareSDK = NKShareSDK.getInstance();
        NKLog.NKGame.e("nkShareSDK:  "+nkShareSDK);
        nkShareSDK.init(this);

        if (savedInstanceState!=null){
            nkShareSDK.onCreate(savedInstanceState);
        }

    }

    private void setListener(){
        QQshare= (Button) findViewById(R.id.QQshare);
        QQZoneShare= (Button) findViewById(R.id.QQZoneshare);
        wechatShare= (Button) findViewById(R.id.wechatShare);
        wechatFrendsShare= (Button) findViewById(R.id.wetchatFrendsShare);
        wechatFavoriteShare= (Button) findViewById(R.id.wetchatFavoriteShare);
        weiboShare= (Button) findViewById(R.id.weiboShare);


        QQshare.setOnClickListener(this);
        QQZoneShare.setOnClickListener(this);
        wechatShare.setOnClickListener(this);
        wechatFrendsShare.setOnClickListener(this);
        wechatFavoriteShare.setOnClickListener(this);
        weiboShare.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.QQshare:
//                nkShareSDK.init(NKConstants.NKChannel_QQ);
                nkShareSDK.init(NKConstants.NKChannel_QQ,new MyListener());
                nkShareSDK.setShareType(1);
                nkShareSDK.setAppName("ShareDemo");
                nkShareSDK.setTitle("Test");
                nkShareSDK.setTargetUrl("http://baike.baidu.com");
//                nkShareSDK.setImageUrl("/storage/emulated/0/DCIM/Screenshots/Screenshot_2016-09-24-18-58-57.png");
                nkShareSDK.setImageUrl("/storage/emulated/0/UCDownloads/八卦.jpg");
                nkShareSDK.setText("this test");
                nkShareSDK.showShare(NKConstants.NKChannel_QQ);
                break;
            case R.id.QQZoneshare:
//                nkShareSDK.init(NKConstants.NKChannel_QQ);
                nkShareSDK.init(NKConstants.NKChannel_QQ,new MyListener());
                nkShareSDK.setTitle("Test");
                nkShareSDK.setTargetUrl("http://baike.baidu.com");
                nkShareSDK.setShareType(6);
//                nkShareSDK.setImageUrl("/storage/emulated/0/DCIM/Screenshots/Screenshot_2016-10-25-14-55-11-123_com.nikugame.game4.png");
//                nkShareSDK.setImageUrl("/storage/emulated/0/DCIM/Screenshots/Screenshot_2016-09-24-18-58-57.png");
                nkShareSDK.setImageUrl("/storage/emulated/0/UCDownloads/八卦.jpg");
                nkShareSDK.setText("this test");
                nkShareSDK.showShare(NKConstants.NKChannel_QQzone);
                break;
            case R.id.wechatShare:
//                nkShareSDK.init(NKConstants.NKChannel_Wechat);
                nkShareSDK.init(NKConstants.NKChannel_Wechat,new MyListener());
                nkShareSDK.setTitle("Test");
                nkShareSDK.setTargetUrl("http://baike.baidu.com");
                nkShareSDK.setShareType(1);
                nkShareSDK.setImageUrl("/storage/emulated/0/UCDownloads/八卦.jpg");
                nkShareSDK.setText("this test");
                nkShareSDK.showShare(NKConstants.NKChannel_Wechat);
                break;
            case R.id.wetchatFrendsShare:
//                nkShareSDK.init(NKConstants.NKChannel_Wechat);
                nkShareSDK.init(NKConstants.NKChannel_Wechat,new MyListener());
                nkShareSDK.setTitle("Test");
                nkShareSDK.setTargetUrl("http://baike.baidu.com");
                nkShareSDK.setShareType(1);
                nkShareSDK.setImageUrl("/storage/emulated/0/UCDownloads/八卦.jpg");
                nkShareSDK.setText("this test");
                nkShareSDK.showShare(NKConstants.NKChannel_WechatTimeline);
                break;
            case R.id.wetchatFavoriteShare:
//                nkShareSDK.init(NKConstants.NKChannel_Wechat);
                nkShareSDK.init(NKConstants.NKChannel_Wechat,new MyListener());
                nkShareSDK.setTitle("Test");
                nkShareSDK.setTargetUrl("http://baike.baidu.com");
                nkShareSDK.setShareType(1);
                nkShareSDK.setText("this test");
                nkShareSDK.setImageUrl("/storage/emulated/0/UCDownloads/八卦.jpg");
                nkShareSDK.showShare(NKConstants.NKChannel_WechatFavorite);
                break;
            case  R.id.weiboShare:
//                nkShareSDK.init(NKConstants.NKChannel_Weibo);
                nkShareSDK.init(NKConstants.NKChannel_Weibo,new MyListener());
                nkShareSDK.setTitle("Test");
                nkShareSDK.setTargetUrl("http://baike.baidu.com");
                nkShareSDK.setShareType(1);
                nkShareSDK.setImageUrl("/storage/emulated/0/UCDownloads/八卦.jpg");
                nkShareSDK.setText("this test");
                nkShareSDK.showShare(NKConstants.NKChannel_Weibo);
                break;

        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        nkShareSDK.onNewIntent(intent);
    }

    private class MyListener implements NKListener {

        @Override
        public void onInit(int errorCode, String result) {

        }

        @Override
        public void onResult(int errorCode, String result) {
            NKLog.NKGame.e("errorCode:  "+errorCode+"  ,result:  "+result);
        }

        @Override
        public void onAuth(int errorCode, String result) {

        }
    }

}
