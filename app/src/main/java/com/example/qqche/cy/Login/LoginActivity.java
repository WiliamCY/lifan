package com.example.qqche.cy.Login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qqche.cy.Gson.LoginUserMessage;
import com.example.qqche.cy.Main.MainActivity;
import com.example.qqche.cy.R;
import com.example.qqche.cy.Utils.Constant;
import com.example.qqche.cy.Utils.StatusBarUtilS;
import com.example.qqche.cy.Utils.Util;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.jaeger.library.StatusBarUtil;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.xml.transform.Result;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/4/17.
 */

public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.login_logo)
    ImageView loginLogo;
    @BindView(R.id.login_Edittext_UserName)
    EditText loginEdittextUserName;
    @BindView(R.id.login_Edittext_Pwd)
    EditText loginEdittextPwd;
    @BindView(R.id.login_register)
    TextView loginRegister;
    @BindView(R.id.login_forgetPwd)
    TextView loginForgetPwd;
    @BindView(R.id.login_loginButton)
    Button loginLoginButton;
    private Message msg;
    private String loginName, loginPwd, userId, username, realStatus, realname, idcard, phoneStatus, phone, email, eamilStatus, sex, level, addytime,userIDS;
    private String equipmentId = "8", status;
    private String uri = Constant.SERVER+"index.php?r=site%2Flogin";
    private  Handler handler;//静态
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        StatusBarUtil.setTransparent(this);
        handler = new MyHandler(this);
        }
        @OnClick({R.id.login_register, R.id.login_forgetPwd, R.id.login_loginButton})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_register:
                Intent intent = new Intent(this, RegisterActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_in, R.anim.anim_out);
                break;
            case R.id.login_forgetPwd:
                Intent intent1 = new Intent(this, ForgetActivity.class);
                startActivity(intent1);
                overridePendingTransition(R.anim.anim_in, R.anim.anim_out);
                break;
            case R.id.login_loginButton:
                loginData();
                break;
        }
    }

    private void loginData() {
        initView();
        if (loginName.isEmpty() || loginPwd.isEmpty()) {
            Toast toast1 = Toast.makeText(getApplicationContext(), "请输入完整", Toast.LENGTH_SHORT);
            Util.showMyToast(toast1, 500);
        } else {
            login(loginName, loginPwd);
        }
    }



    private static  class MyHandler extends Handler{
        private  WeakReference<LoginActivity> weakReference;
        public  MyHandler(LoginActivity activity) {
            weakReference = new WeakReference<>(activity);
        }
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(weakReference.get()!=null){
                switch (msg.what){
                    case 1:
                        weakReference.get().loginLoginButton.setEnabled(false);
                        Toast toast = Toast.makeText(weakReference.get(), "登录成功", Toast.LENGTH_SHORT);
                        Util.showMyToast(toast, 500);
                        Intent intent = new Intent(weakReference.get(), MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                        weakReference.get().startActivity(intent);
                        weakReference.get().overridePendingTransition(R.anim.anim_in, R.anim.anim_out);
                        weakReference.get().finish();
                        break;
                    case 2:
                        Toast toast1 = Toast.makeText(weakReference.get(), "参数错误", Toast.LENGTH_SHORT);
                        Util.showMyToast(toast1, 500);
                        break;
                    case 3:
                        Toast toast2 = Toast.makeText(weakReference.get(), "非法访问", Toast.LENGTH_SHORT);
                        Util.showMyToast(toast2, 500);
                        break;
                    case 4:
                        Toast toast3 = Toast.makeText(weakReference.get(), "用户名/手机号码 不存在", Toast.LENGTH_SHORT);
                        Util.showMyToast(toast3, 500);
                        break;
                    case 5:
                        Toast toast4 = Toast.makeText(weakReference.get(), "用户名/手机号码/密码 错误", Toast.LENGTH_SHORT);
                        Util.showMyToast(toast4, 500);
                        break;
                    case 6:
                        Toast toast5 = Toast.makeText(weakReference.get(), "该用户被锁定", Toast.LENGTH_SHORT);
                        Util.showMyToast(toast5, 500);
                        break;
                    case 7:
                        Toast toast6 = Toast.makeText(weakReference.get(), "错误0420,请联系客服", Toast.LENGTH_SHORT);
                        Util.showMyToast(toast6, 500);
                        break;
                }
                super.handleMessage(msg);
            }

        }
    }

    private void login(final String loginName, final String loginPwd) {
        long timeStamp = System.currentTimeMillis() / 1000;
        String timestamp = String.valueOf(timeStamp);
        String message = "equipment=" + equipmentId + "&projectId=17&time=" + timestamp + ".K15058835525+.";
        String token = Util.StringToMd5(message);
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("token", token)
                .add("timestamp", timestamp)
                .add("equipmentId", equipmentId)
                .add("username", loginName)
                .add("pwd", loginPwd).build();
        Request request = new Request.Builder().post(body).url(uri).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()){
                    String resopnseDate = response.body().string();
                    LoginUserMessage bean = new Gson().fromJson(resopnseDate,LoginUserMessage.class);
                    String  status = bean.getStatus();
                    switch (status) {
                        case "1":
                            LoginUserMessage.DataBean aClass = bean.getData();
                            realStatus = aClass.getReal_status();
                            realname = aClass.getRealname();
                            idcard = aClass.getIdcard();
                            phoneStatus = aClass.getPhone_status();
                            phone = aClass.getPhone();
                            email = aClass.getEmail();
                            eamilStatus = aClass.getEmail_status();
                            sex = aClass.getSex();
                            level = aClass.getLevel();
                            addytime = aClass.getAddtime();
                            userIDS = aClass.getUser_id();
                            System.out.print(userIDS);
                            System.out.print(userIDS);
                            SharedPreferences.Editor editor = getSharedPreferences("dara", MODE_PRIVATE).edit();
                            editor.putString("user", userId);
                            editor.putString("userId",userIDS);
                            editor.putString("username", loginName);
                            editor.putString("realStatus", realStatus);
                            editor.putString("realname", realname);
                            editor.putString("idcard", idcard);
                            editor.putString("phoneStatus", phoneStatus);
                            editor.putString("phone", phone);
                            editor.putString("email", email);
                            editor.putString("eamilStatus", eamilStatus);
                            editor.putString("sex", sex);
                            editor.putString("level", level);
                            editor.putString("password",loginPwd);
                            editor.putString("addytime", addytime);
                            editor.apply();
                            msg = new Message();
                            msg.what = 1;
                            handler.sendMessage(msg);
                            break;
                        case "2":
                            msg = new Message();
                            msg.what = 2;
                            handler.sendMessage(msg);
                            break;
                        case "3":
                            msg = new Message();
                            msg.what = 3;
                            handler.sendMessage(msg);
                            break;
                        case "12":
                            msg = new Message();
                            msg.what = 4;
                            handler.sendMessage(msg);
                            break;
                        case "13":
                            msg = new Message();
                            msg.what = 5;
                            handler.sendMessage(msg);
                            break;
                        case "14":
                            msg = new Message();
                            msg.what = 6;
                            handler.sendMessage(msg);
                            break;
                        case "20":
                            msg = new Message();
                            msg.what = 7;
                            handler.sendMessage(msg);
                            break;

                    }
                }

            }
        });

    }
    private void initView() {
        loginName = loginEdittextUserName.getText().toString();
        loginPwd = loginEdittextPwd.getText().toString();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}
