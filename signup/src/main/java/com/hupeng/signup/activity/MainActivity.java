package com.hupeng.signup.activity;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.hupeng.signup.R;
import com.hupeng.signup.model.UserModel;
import com.hupeng.signup.model.param.SmsCodeParam;
import com.litesuits.android.log.Log;
import com.litesuits.http.exception.HttpException;
import com.litesuits.http.response.Response;
import com.litesuits.http.response.handler.HttpModelHandler;


public class MainActivity extends Activity {

    public static String APPID = "";
    public static String APPKEY = "";
    public static String APP_VERSION = "";
    public static String APP_CHANNEL_ID = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initPackageInfo(MainActivity.this);
        Button registerBtn = (Button)findViewById(R.id.register);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserModel.getSmscode(new SmsCodeParam("15001867241", 1, "123456"), new HttpModelHandler<String>() {
                    @Override
                    protected void onSuccess(String data, Response res) {
                        Log.i("onSuccess : " + data);
                    }

                    @Override
                    protected void onFailure(HttpException e, Response res) {
                        Log.i("onFailure: " + e);
                    }
                });
            }
        });

        Button smsBtn = (Button)findViewById(R.id.smscode);
        smsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }


    private void initPackageInfo(Context context)
    {
        PackageManager manager = context.getPackageManager();
        try {
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            APP_VERSION = info.versionName; // 版本名，versionCode同理

            ApplicationInfo appInfo = manager.getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            Object object = appInfo.metaData.get("UMENG_CHANNEL");
            if (object instanceof String)
                APP_CHANNEL_ID = appInfo.metaData.getString("UMENG_CHANNEL");
            else if (object instanceof Integer)
                APP_CHANNEL_ID = appInfo.metaData.getInt("UMENG_CHANNEL", 0)+"";
            else if (object instanceof Long)
                APP_CHANNEL_ID = appInfo.metaData.getLong("UMENG_CHANNEL", 0)+"";

            Log.d("APP_VERSION: " + APP_VERSION + " APP_CHANNEL_ID: " + APP_CHANNEL_ID);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        catch (ClassCastException e) {
            e.printStackTrace();
        }
    }
}
