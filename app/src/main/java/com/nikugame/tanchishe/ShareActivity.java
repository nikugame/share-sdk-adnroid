package com.nikugame.tanchishe;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.nkgame.nksharesdk.NKShareConstants;
import com.nkgame.nksharesdk.NKShareListener;
import com.nkgame.nksharesdk.NKShareLog;
import com.nkgame.nksharesdk.NKShareQREncodingUtils;
import com.nkgame.nksharesdk.NKShareSDK;


public class ShareActivity extends AppCompatActivity implements View.OnClickListener {

    private ProgressBar progressBar;

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
        NKShareLog.NKGame.e("nkShareSDK:  "+nkShareSDK);
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
        progressBar= (ProgressBar) findViewById(R.id.progress);
        progressBar.setVisibility(View.VISIBLE);
//生成二维码存储在filepath下，文件保存在应用的缓存目录
        String filePath= NKShareQREncodingUtils.getImagePath(ShareActivity.this);
        NKShareQREncodingUtils.createQRImage("http://shouji.baidu.com/software/item?pid=1858121943&from=web_alad_multi",400,400, BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher),filePath);

        switch (view.getId()){
            case R.id.QQshare:
                nkShareSDK.init(NKShareConstants.NKChannel_QQ);
                nkShareSDK.setText("this test");
                nkShareSDK.setTitle("Test");
                nkShareSDK.showShareWithQRCode(NKShareConstants.NKChannel_QQ,"http://shouji.baidu.com/software/item","1","2",400,400,R.mipmap.ic_launcher,ShareActivity.this);
/*//                nkShareSDK.init(NKConstants.NKChannel_QQ);
                nkShareSDK.init(NKConstants.NKChannel_QQ,new MyListener());
                nkShareSDK.setShareType(2);
                nkShareSDK.setAppName("ShareDemo");
                nkShareSDK.setTitle("Test");
                nkShareSDK.setTargetUrl("http://baike.baidu.com");
//                nkShareSDK.setImageUrl("/storage/emulated/0/DCIM/Screenshots/Screenshot_2016-09-24-18-58-57.png");
//                nkShareSDK.setImageUrl("/storage/emulated/0/UCDownloads/八卦.jpg");
                nkShareSDK.setImageUrl(filePath);
                nkShareSDK.setText("this test");
                nkShareSDK.showShare(NKConstants.NKChannel_QQ);*/
                break;
            case R.id.QQZoneshare:
//                nkShareSDK.init(NKShareConstants.NKChannel_QQ);
                nkShareSDK.init(NKShareConstants.NKChannel_QQ,new MyListener());
                nkShareSDK.setTitle("Test");
                nkShareSDK.setTargetUrl("http://baike.baidu.com");
                nkShareSDK.setShareType(2);
//                nkShareSDK.setImageUrl("/storage/emulated/0/DCIM/Screenshots/Screenshot_2016-10-25-14-55-11-123_com.nikugame.game4.png");
//                nkShareSDK.setImageUrl("/storage/emulated/0/DCIM/Screenshots/Screenshot_2016-09-24-18-58-57.png");
//                nkShareSDK.setImageUrl("/storage/emulated/0/UCDownloads/八卦.jpg");
                nkShareSDK.setImageUrl(filePath);
                nkShareSDK.setText("this test");
                nkShareSDK.showShare(NKShareConstants.NKChannel_QQzone);
                break;
            case R.id.wechatShare:
//                nkShareSDK.init(NKConstants.NKChannel_Wechat);
                nkShareSDK.init(NKShareConstants.NKChannel_Wechat,new MyListener());
                nkShareSDK.setTitle("Test");
                nkShareSDK.setTargetUrl("http://baike.baidu.com");
                nkShareSDK.setShareType(2);
//                nkShareSDK.setImageUrl("/storage/emulated/0/UCDownloads/八卦.jpg");
                nkShareSDK.setImageUrl(filePath);

                nkShareSDK.setText("this test");
                nkShareSDK.showShare(NKShareConstants.NKChannel_Wechat);
                break;
            case R.id.wetchatFrendsShare:
//                nkShareSDK.init(NKConstants.NKChannel_Wechat);
                nkShareSDK.init(NKShareConstants.NKChannel_Wechat,new MyListener());
                nkShareSDK.setTitle("Test");
                nkShareSDK.setTargetUrl("http://baike.baidu.com");
                nkShareSDK.setShareType(2);
//                nkShareSDK.setImageUrl("/storage/emulated/0/UCDownloads/八卦.jpg");
                nkShareSDK.setImageUrl(filePath);

                nkShareSDK.setText("this test");
                nkShareSDK.showShare(NKShareConstants.NKChannel_WechatTimeline);
                break;
            case R.id.wetchatFavoriteShare:
//                nkShareSDK.init(NKConstants.NKChannel_Wechat);
                nkShareSDK.init(NKShareConstants.NKChannel_Wechat,new MyListener());
                nkShareSDK.setTitle("Test");
                nkShareSDK.setTargetUrl("http://baike.baidu.com");
                nkShareSDK.setShareType(2);
                nkShareSDK.setText("this test");
//                nkShareSDK.setImageUrl("/storage/emulated/0/UCDownloads/八卦.jpg");
                nkShareSDK.setImageUrl(filePath);

                nkShareSDK.showShare(NKShareConstants.NKChannel_WechatFavorite);
                break;
            case  R.id.weiboShare:
//                nkShareSDK.init(NKConstants.NKChannel_Weibo);
                nkShareSDK.init(NKShareConstants.NKChannel_Weibo,new MyListener());
                nkShareSDK.setTitle("Test");
                nkShareSDK.setTargetUrl("http://baike.baidu.com");
                nkShareSDK.setShareType(2);
//                nkShareSDK.setImageUrl("/storage/emulated/0/UCDownloads/八卦.jpg");
                nkShareSDK.setImageUrl(filePath);

                nkShareSDK.setText("this test");
                nkShareSDK.showShare(NKShareConstants.NKChannel_Weibo);
                break;
        }

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        nkShareSDK.onNewIntent(intent);
    }

    private class MyListener implements NKShareListener {

        @Override
        public void onInit(int errorCode, String result) {
            NKShareLog.NKGame.e("onInit :  errorCode:  "+errorCode+"  ,result:  "+result);
        }

        @Override
        public void onResult(int errorCode, String result) {
            NKShareLog.NKGame.e("onResult :  errorCode:  "+errorCode+"  ,result:  "+result);
        }

        @Override
        public void onAuth(int errorCode, String result) {

        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        NKShareLog.NKGame.e("onPause");
//        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        NKShareLog.NKGame.e("onStop");
        progressBar.setVisibility(View.INVISIBLE);
    }
}
