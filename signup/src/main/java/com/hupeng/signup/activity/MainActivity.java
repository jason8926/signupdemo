package com.hupeng.signup.activity;

import android.app.Activity;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
}
