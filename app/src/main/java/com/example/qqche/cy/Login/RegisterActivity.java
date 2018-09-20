package com.example.qqche.cy.Login;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qqche.cy.Gson.PhoneCode;
import com.example.qqche.cy.Gson.RegisterGson;
import com.example.qqche.cy.R;
import com.example.qqche.cy.Utils.Constant;
import com.example.qqche.cy.Utils.StatusBarUtilS;
import com.example.qqche.cy.Utils.Util;
import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;

import org.json.JSONObject;

import java.io.IOException;
import java.lang.ref.WeakReference;

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

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.register_logo)
    ImageView registerLogo;
    @BindView(R.id.register_userName)
    EditText registerUserName;
    @BindView(R.id.register_userPhone)
    EditText registerUserPhone;
    @BindView(R.id.register_userPwd)
    EditText registerUserPwd;
    @BindView(R.id.register_userYZM)
    EditText registerUserYZM;
    @BindView(R.id.register_userSendYZM)
    TextView registerUserSendYZM;
    @BindView(R.id.register_userYQM)
    EditText registerUserYQM;
    @BindView(R.id.register_register)
    Button registerRegister;
    @BindView(R.id.register_back)
    ImageView registerBack;
    private Message msg;
    private String userName, userPhone, userPwd, userYZM, userYQR;
    private String Uri1 = Constant.SERVER + "index.php?r=site%2Fsendphonecode";
    private String Uri2 = Constant.SERVER + "index.php?r=site%2Freg";
    private String equipmentId = "8", status;
    private Handler handler;//静态
    @SuppressLint("HandlerLeak")
    private CountDownTimer time = new CountDownTimer(60000, 1000) {

        @Override
        public void onTick(long millisUntilFinished) {
            registerUserSendYZM.setText((millisUntilFinished / 1000) + "");
            registerUserSendYZM.setEnabled(false);
        }

        @Override
        public void onFinish() {
            registerUserSendYZM.setEnabled(true);
            registerUserSendYZM.setText("发送");
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        StatusBarUtil.setTransparent(this);
        StatusBarUtilS.StatusBarLightMode(this);
        handler = new MyHandler(this);

    }


    @OnClick({R.id.register_userSendYZM, R.id.register_register, R.id.register_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.register_userSendYZM:
                userPhone = registerUserPhone.getText().toString();
                String s = userPhone;
                System.out.print(s);
                if (userPhone.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "请输入完整的手机号", Toast.LENGTH_SHORT).show();
                } else if (!Util.isMobileNO(userPhone)) {
                    Toast.makeText(getApplicationContext(), "手机号格式不正确", Toast.LENGTH_SHORT).show();
                } else {
                    initSendYZM(userPhone);
                }

                break;
            case R.id.register_register:
                initView();
                if (userName.isEmpty() || userPhone.isEmpty() || userPwd.isEmpty() || userYZM.isEmpty()) {
                    Toast toast1 = Toast.makeText(getApplicationContext(), "请输入完整", Toast.LENGTH_SHORT);
                    Util.showMyToast(toast1, 500);
                } else if (!Util.isMobileNO(userPhone)) {
                    Toast toast1 = Toast.makeText(getApplicationContext(), "请输入正确的手机格式", Toast.LENGTH_SHORT);
                    Util.showMyToast(toast1, 500);
                } else if (Util.isNumbser(userName)) {
                    Toast toast1 = Toast.makeText(getApplicationContext(), "用户名不能全是数字", Toast.LENGTH_SHORT);
                    Util.showMyToast(toast1, 500);
                } else if (userPwd.length() <= 5 && userPwd.length() >= 21) {
                    Toast toast1 = Toast.makeText(getApplicationContext(), "密码的长度在6-20位", Toast.LENGTH_SHORT);
                    Util.showMyToast(toast1, 500);
                } else {
                    registerData(userName, userPhone, userPwd, userYZM, userYQR);
                }
                break;
            case R.id.register_back:
                overridePendingTransition(R.anim.anim_in, R.anim.anim_out);
                finish();
                break;
        }
    }

    private static class MyHandler extends Handler {
        private WeakReference<RegisterActivity> weakReference;

        public MyHandler(RegisterActivity activity) {
            weakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (weakReference.get() != null) {
                switch (msg.what) {
                    case 1:
                        Toast.makeText(weakReference.get(), "发送成功", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(weakReference.get(), "数据错误", Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        Toast.makeText(weakReference.get(), "非法访问", Toast.LENGTH_SHORT).show();
                        break;
                    case 12:
                        Toast.makeText(weakReference.get(), "发生失败9003", Toast.LENGTH_SHORT).show();
                        break;
                    case 13:
                        Toast.makeText(weakReference.get(), "短信发送失败，数据库错误", Toast.LENGTH_SHORT).show();
                        break;
                    case 0:
                        Toast.makeText(weakReference.get(), "愿爱你的人如同日头出现，光辉烈烈", Toast.LENGTH_SHORT).show();
                        break;
                    case 15:
                        Toast.makeText(weakReference.get(), "注册成功", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(weakReference.get(), LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                        weakReference.get().startActivity(intent);
                        weakReference.get().overridePendingTransition(R.anim.anim_in, R.anim.anim_out);
                        weakReference.get().finish();
                        break;
                    case 16:
                        Toast.makeText(weakReference.get(), "数据错误", Toast.LENGTH_SHORT).show();
                        break;
                    case 17:
                        Toast.makeText(weakReference.get(), "非法访问", Toast.LENGTH_SHORT).show();
                        break;
                    case 18:
                        Toast.makeText(weakReference.get(), "手机号无效", Toast.LENGTH_SHORT).show();
                        break;
                    case 19:
                        Toast.makeText(weakReference.get(), "用户名已存在", Toast.LENGTH_SHORT).show();
                        break;
                    case 20:
                        Toast.makeText(weakReference.get(), "手机号已存在", Toast.LENGTH_SHORT).show();
                        break;
                    case 21:
                        Toast.makeText(weakReference.get(), "注册失败", Toast.LENGTH_SHORT).show();
                        break;
                }

                super.handleMessage(msg);
            }
        }
    }

    private void initSendYZM(final String userPhone) {
                long timeStamp = System.currentTimeMillis() / 1000;
                String timestamp = String.valueOf(timeStamp);
                String message = "equipment=" + equipmentId + "&projectId=17&time=" + timestamp + ".K15058835525+.";
                String token = Util.StringToMd5(message);
                OkHttpClient client = new OkHttpClient();
                RequestBody body = new FormBody.Builder()
                        .add("token", token)
                        .add("timestamp", timestamp)
                        .add("equipmentId", equipmentId)
                        .add("phone", userPhone).build();
                Request request = new Request.Builder().post(body).url(Uri1).build();
                time.start();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (response.isSuccessful()) {
                            String responseData = response.body().string();
                            PhoneCode code = new Gson().fromJson(responseData, PhoneCode.class);
                            String status = code.getStatus();
                            switch (status) {
                                case "1":
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
                                    msg.what = 12;
                                    handler.sendMessage(msg);
                                    break;
                                case "13":
                                    msg = new Message();
                                    msg.what = 13;
                                    handler.sendMessage(msg);
                                    break;
                            }
                        }
                    }
                });
    }

    private void registerData(final String userName, final String userPhone, final String userPwd, final String userYZM, String userYQR) {
                long timeStamp = System.currentTimeMillis() / 1000;
                String timestamp = String.valueOf(timeStamp);
                String message = "equipment=" + equipmentId + "&projectId=17&time=" + timestamp + ".K15058835525+.";
                String token = Util.StringToMd5(message);
                OkHttpClient client = new OkHttpClient();
                RequestBody body = new FormBody.Builder()
                        .add("token", token)
                        .add("timestamp", timestamp)
                        .add("equipmentId", equipmentId)
                        .add("username", userName)
                        .add("pwd", userPwd)
                        .add("repwd", userPwd)
                        .add("phone", userPhone)
                        .add("phonecode", userYZM)
                        .build();
                Request request = new Request.Builder().post(body).url(Uri2).build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (response.isSuccessful()) {
                            String responseData = response.body().string();
                            RegisterGson code = new Gson().fromJson(responseData, RegisterGson.class);
                            String status = code.getStatus();
                            switch (status) {
                                case "1":
                                    msg = new Message();
                                    msg.what = 15;
                                    handler.sendMessage(msg);
                                    break;
                                case "2":
                                    msg = new Message();
                                    msg.what = 16;
                                    handler.sendMessage(msg);
                                    break;
                                case "3":
                                    msg = new Message();
                                    msg.what = 17;
                                    handler.sendMessage(msg);
                                    break;
                                case "15":
                                    msg = new Message();
                                    msg.what = 18;
                                    handler.sendMessage(msg);
                                    break;
                                case "16":
                                    msg = new Message();
                                    msg.what = 19;
                                    handler.sendMessage(msg);
                                    break;
                                case "17":
                                    msg = new Message();
                                    msg.what = 20;
                                    handler.sendMessage(msg);
                                    break;
                                case "20":
                                    msg = new Message();
                                    msg.what = 21;
                                    handler.sendMessage(msg);
                                    break;


                            }
                        }

                    }
                });

    }

    private void initView() {
        userPwd = registerUserPwd.getText().toString();
        userName = registerUserName.getText().toString();
        userPhone = registerUserPhone.getText().toString();
        userYZM = registerUserYZM.getText().toString();
        userYQR = registerUserYQM.getText().toString();
    }

    @Override
    protected void onDestroy() {
        if (time != null) {
            time.cancel();
            time = null;
        }
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}
