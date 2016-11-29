#[ShareSDK接入文档](https://github.com/nikugame/share-sdk-adnroid/blob/master/README.md)（ver1.0.1_1129）

 [NKShareSDK](https://github.com/nikugame/share-sdk-adnroid)目前集成了QQ、微信、微博等平台的分享，可以帮您的应用快速接入多平台的分享。

------
##1、SDK说明
当前SDK是Android版本，iOS版本可以参考我们iOS的项目。

##2、接入准备
###（1）平台参数申请
为实现相应平台的分享功能，请前往各平台申请应用的参数信息（主要是appId、appkey等参数）。申请后的参数请按照名称写入**asstes**文件夹下的**share.properties**文件中（注意assets文件夹在Eclipse和Android Studio中所在目录的不同）。
###（2）IDE的选择
本SDK的是在Android Studio上开发和维护的，为了后期维护的方便，建议您采用Android Studio。
####<1>Android Studio的接入
将demo项目下的**libs、jniLibs**下的所有文件复制到自己工程对应的目录下。
####<2>Eclipse的接入
将Demo项目下的**libs、jniLibs**下的所有文件复制到自己工程的**libs**目录下
###（3）配置AndroidManifest文件
####<1>添加权限
```
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
    <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
    <uses-permission android:name="android.permission.ACCESS_LOCATION_EXTRA_COMMANDS" />
    <uses-permission android:name="android.permission.CHANGE_WIFI_STATE" />
    <uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
    <uses-permission android:name="android.permission.READ_PHONE_STATE" />
    <uses-permission android:name="android.permission.GET_TASKS" />
    <!--微信需要-->
    <uses-permission android:name="android.permission.MODIFY_AUDIO_SETTINGS"/>
```
####<2>添加Activity
```
      <!--请务必在调起微博分享的Activity添加如下的intent-filter，此Activity可更换为自己的Activity-->
        <activity android:name=".ShareActivity">
            <intent-filter>
                <action android:name="com.sina.weibo.sdk.action.ACTION_SDK_REQ_ACTIVITY" />

                <category android:name="android.intent.category.DEFAULT" />
            </intent-filter>
        </activity>
        <!--QQ渠道，注意此处的scheme属性，需要替换为自己的申请的appid-->
        <activity
            android:name="com.tencent.tauth.AuthActivity"
            android:launchMode="singleTask"
            android:noHistory="true">
            <intent-filter>
                <action android:name="android.intent.action.VIEW" />

                <category android:name="android.intent.category.DEFAULT" />
                <category android:name="android.intent.category.BROWSABLE" />
                <data android:scheme="tencent1105814454" />
            </intent-filter>
        </activity>
        <activity
            android:name="com.tencent.connect.common.AssistActivity"
            android:configChanges="orientation|keyboardHidden"
            android:screenOrientation="behind"
            android:theme="@android:style/Theme.Translucent.NoTitleBar" />
        <!--微博-->
        <activity
            android:name="com.sina.weibo.sdk.component.WeiboSdkBrowser"
            android:configChanges="keyboardHidden|orientation"
            android:exported="false"
            android:windowSoftInputMode="adjustResize" />
        <!--微信的主要Activity，需要接入时实现此Activity，用于接收微信的返回信息，请务必此格式实现-->
        <activity
            android:name=".wxapi.WXEntryActivity"
            android:exported="true"
            />
```
##3、SDK接口描述
###（1）初始化实例（必接）
```
    NKShareSDK nkShareSDK =NKShareSDK.getInstance();
    nkShareSDK.init(this);
```
说明：需要在调起分享的Activity的OnCreate方法中执行。
###（2）初始化渠道（必接）
####<1>带分享结果回调接口
```
    nkShareSDK.init（String channel，NKShareListener nkListener）;
```
说明：
#####①channel代表初始化的渠道，渠道可从NKShareConstants中选择NKChannel,如：
```
   nkShareSDK.init(NKShareConstants.NKChannel_QQ,new MyListener());
```
#####②NKListener是监听分享返回接口。
```
   private class MyListener implements NKShareListener {

        @Override
        public void onInit(int errorCode, String result) {
            //初始化结果
        }

        @Override
        public void onResult(int errorCode, String result) {
            //分享结果
            NKShareLog.NKGame.e("errorCode:  "+errorCode+"  ,result:  "+result);
        }

        @Override
        public void onAuth(int errorCode, String result) {
            //注册或认证结果，暂时不可用
        }
    }
```

| 参数        | 类型   |  说明  |
| --------   | -----:  | :----:  |
| errcode     | int |   正常返回0，错误返回-1，取消返回1    |
| result        |   String   |   具体的消息   |
**注意**：目前部分渠道在分享后不会返回分享结果，因此可能造成这些渠道无法监听到分享结果，请评估后选择接入
####<2>不带分享结果回调接口
```
    nkShareSDK.init（String channel）;
```
说明：直接初始化渠道
###（3）设置分享类型（必接）
```
   nkShareSDK.setShareType（int shareType）;
```
说明：shareType分享类型，可在NKShareConstans中选择NKSHARE_TYPE_,包含文本（text）、图片（image）、音频（music）、视频（video）、网页（webpage），如：
```
   nkShareSDK.setShareType(NKShareConstans.NKSHARE_TYPE_TEXT);
```
###（4）设置标题或名称（必接）
```
   nkShareSDK.setTitle(String title);
```
如：    
```
    nkShareSDK.setTitle("Test");
```
###（5）设置链接地址（必接）
```
   nkShareSDK.setTargetUrl(String url);
```
说明：用于分享链接
###（6）设置分享图片
```
   nkShareSDK.setImageUrl(String path);
```
说明：path可传递路径、网络链接等，目前仅支持本地链接及应用内图片。
###（7）设置分享具体内容（必接）
```
    nkShareSDK.setText(String text);
```
###（8）设置分享渠道并分享（必接）
```
    nkShareSDK.showShare(String channel);
```
###（9）生成二维码并分享
说明：使用此方法不用再接入showShare方法了。
<1>使用游戏包内资源的图片
```
nkShareSDK.showShareWithQRCode(String channel,String url, String uuid, String gameid,int widthPix,int heightPix,int logoId, Activity activity)
```
说明：
| 参数        | 类型   |  说明  |
| --------   | -----:  | :----:  |
| channel     | String |   渠道    |
| url       |   String   |   二维码中链接主地址，http://game.nikugame.com/qrdownload   |
| uuid     | String |   二维码中链接中分享用户id    |
| gameid     | String |   二维码中链接中分享游戏id    |
| widthPix     | int |   分享二维码的宽，单位像素    |
| heightPix     | int |   分享二维码的高，单位像素    |
| logoId     | int |   二维码中logo的图片在R.class中的id    |
| activity     | Activity |   当前的Activity    |
url、uuid、gameid组成的二维码链接地址形式为：http://game.nikugame.com/qrdownload?uuid=?&gameid=?
<2>使用资源路径的图片
```
nkShareSDK.showShareWithQRCode(String channel,String url, String uuid, String gameid,int widthPix,int heightPix,String imagePath, Activity activity)
```
说明：
| 参数        | 类型   |  说明  |
| --------   | -----:  | :----:  |
| channel     | String |   渠道    |
| url       |   String   |   二维码中链接地址，http://game.nikugame.com/qrdownload   |
| uuid     | String |   分享用户id    |
| gameid     | String |   分享游戏id    |
| widthPix     | int |   分享二维码的宽，单位像素    |
| heightPix     | int |   分享二维码的高，单位像素    |
| imagePath     | String |   二维码中logo的图片所在路径    |
| activity     | Activity |   当前的Activity    |
url、uuid、gameid组成的二维码链接地址形式为：http://game.nikugame.com/qrdownload?uuid=?&gameid=?
###（10）添加生命周期
```
    public void onCreate(Bundle savedInstanceState) {
       if (savedInstanceState!=null){
            nkShareSDK.onCreate(savedInstanceState);
        }
    }

    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        nkShareSDK.onNewIntent(intent);
    }

    public void onStop() {
         super.onStop();
        nkShareSDK.onStop();
    }

    public void onDestroy() {
         super.onDestroy();
        nkShareSDK.onDestroy();
    }

    public void onResume() {
         super.onResume();
        nkShareSDK.onResume();
    }

    public void onPause() {
        super.onPause();
        nkShareSDK.onPause();
    }

    public void onStart() {
         super.onStart();
        nkShareSDK.onStart();
    }

    public void onRestart() {
        super.onRestart();
        nkShareSDK.onRestart();
    }
```

##4、分享结果示例
![QQ分享示例](https://github.com/nikugame/share-sdk-adnroid/tree/master/images/share_demo_1.jpg)

##5、问题说明
1、初始化可能因机器的不同而导致时间上的差异，请点击后耐心等待几秒钟，不要急切多点击几次，而导致程序反应滞后，此问题会在后期版本中优化
2、Demo中的图片为本地路径图片，要想运行必须更改为测试手机内的图片。
3、微信分享图片的大小规格有一定的限制

##6、版本更新说明
ver1.0.0.1121
首个ShareSDK版本，增加了QQ、微信、微博渠道的分享，及分享结果的接口
ver1.0.0.1128
新增生成二维码并分享接口，新增微信分享图片处理方法，新增生命周期
ver1.0.0.1129
调整类名规则，防止与其他SDK产生混淆