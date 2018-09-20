package com.example.qqche.cy.Login;

import android.content.Intent;
import android.net.Uri;
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
import com.example.qqche.cy.Main.MainActivity;
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

public class ForgetActivity extends AppCompatActivity {

    @BindView(R.id.forget_userPhone)
    EditText forgetUserPhone;
    @BindView(R.id.forget_userYZM)
    EditText forgetUserYZM;
    @BindView(R.id.forget_usersendYZM)
    TextView forgetUsersendYZM;
    @BindView(R.id.forget_userPwd)
    EditText forgetUserPwd;
    @BindView(R.id.forget_userPwdS)
    EditText forgetUserPwdS;
    @BindView(R.id.forget_forget)
    Button forgetForget;
    @BindView(R.id.forget_back)
    ImageView forgetBack;
    private String equipmentId = "8", status;
    private String phone, yzm, pwd, pwds;
    private Message msg;
    private String uri = Constant.SERVER + "index.php?r=site%2Fforget";
    private String uriCode = Constant.SERVER + "index.php?r=site%2Fsendphonecode";
    private  Handler handler;//静态

    private CountDownTimer time = new CountDownTimer(60000, 1000) {

        @Override
        public void onTick(long millisUntilFinished) {
            forgetUsersendYZM.setText((millisUntilFinished / 1000) + "");
            forgetUsersendYZM.setEnabled(false);
        }

        @Override
        public void onFinish() {
            forgetUsersendYZM.setEnabled(true);
            forgetUsersendYZM.setText("发送");
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);
        ButterKnife.bind(this);
        StatusBarUtil.setTransparent(this);
        StatusBarUtilS.StatusBarLightMode(this);
        handler = new MyHandler(this);
    }

    @OnClick({R.id.forget_usersendYZM, R.id.forget_forget, R.id.forget_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.forget_usersendYZM:
                phone = forgetUserPhone.getText().toString();
                if (!Util.isMobileNO(phone)) {
                    Toast toast1 = Toast.makeText(getApplicationContext(), "输入手机号的格式不正确或者输入手机号空", Toast.LENGTH_SHORT);
                    Util.showMyToast(toast1, 500);
                } else {
                    getYZM(phone);
                }
                break;
            case R.id.forget_forget:
                initView();
                if (phone.isEmpty() || yzm.isEmpty() || pwd.isEmpty() || pwds.isEmpty()) {
                    Toast toast1 = Toast.makeText(getApplicationContext(), "请输入完整", Toast.LENGTH_SHORT);
                    Util.showMyToast(toast1, 500);
                } else if (pwd.length() < 6 || pwd.length() > 20) {
                    Toast toast2 = Toast.makeText(getApplicationContext(), "请输入完整", Toast.LENGTH_SHORT);
                    Util.showMyToast(toast2, 500);
                } else if (!pwds.equals(pwds)) {
                    Toast toast3 = Toast.makeText(getApplicationContext(), "两次输入的密码不一致", Toast.LENGTH_SHORT);
                    Util.showMyToast(toast3, 500);
                } else {
                    fixPwd(phone, yzm, pwd, pwds);
                }
                break;
            case R.id.forget_back:
                overridePendingTransition(R.anim.anim_in, R.anim.anim_out);
                finish();
                break;
        }
    }

    private void initView() {
        phone = forgetUserPhone.getText().toString();
        yzm = forgetUserYZM.getText().toString();
        pwd = forgetUserPwd.getText().toString();
        pwds = forgetUserPwdS.getText().toString();
    }


    private static class MyHandler extends Handler {
        private WeakReference<ForgetActivity> weakReference;
        public MyHandler(ForgetActivity activity) {
            weakReference = new WeakReference<>(activity);
        }
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (weakReference.get() != null) {
                switch (msg.what) {
                    case 1:
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

    private void getYZM(final String phone) {
                long timeStamp = System.currentTimeMillis() / 1000;
                String timestamp = String.valueOf(timeStamp);
                String message = "equipment=" + equipmentId + "&projectId=17&time=" + timestamp + ".K15058835525+.";
                String token = Util.StringToMd5(message);
                OkHttpClient client = new OkHttpClient();
                RequestBody body = new FormBody.Builder()
                        .add("token", token)
                        .add("timestamp", timestamp)
                        .add("equipmentId", equipmentId)
                        .add("phone", phone).build();
                Request request = new Request.Builder().post(body).url(uriCode).build();
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
                                    msg.what = 4;
                                    handler.sendMessage(msg);
                                    break;
                                case "13":
                                    msg = new Message();
                                    msg.what = 5;
                                    handler.sendMessage(msg);
                                    break;
                            }
                        }

                    }
                });

    }


    private void fixPwd(final String phones, final String yzms, final String pwds, final String pwdss) {
                long timeStamp = System.currentTimeMillis() / 1000;
                String timestamp = String.valueOf(timeStamp);
                String message = "equipment=" + equipmentId + "&projectId=17&time=" + timestamp + ".K15058835525+.";
                String token = Util.StringToMd5(message);
                OkHttpClient client = new OkHttpClient();
                RequestBody body = new FormBody.Builder()
                        .add("token", token)
                        .add("timestamp", timestamp)
                        .add("equipmentId", equipmentId)
                        .add("phone", phones)
                        .add("pwd", pwds)
                        .add("repwd", pwdss)
                        .add("phonecode", yzms).build();
                Request request = new Request.Builder().post(body).url(uri).build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (response.isSuccessful()) {
                            String resopnData = response.body().string();
                            PhoneCode code = new Gson().fromJson(resopnData, PhoneCode.class);
                            String status = code.getStatus();
                            switch (status) {
                                case "1":
                                    msg = new Message();
                                    msg.what = 6;
                                    handler.sendMessage(msg);
                                    break;
                                case "2":
                                    msg = new Message();
                                    msg.what = 7;
                                    handler.sendMessage(msg);
                                    break;
                                case "3":
                                    msg = new Message();
                                    msg.what = 8;
                                    handler.sendMessage(msg);
                                    break;
                                case "15":
                                    msg = new Message();
                                    msg.what = 9;
                                    handler.sendMessage(msg);
                                    break;
                                case "16":
                                    msg = new Message();
                                    msg.what = 10;
                                    handler.sendMessage(msg);
                                    break;
                                case "20":
                                    msg = new Message();
                                    msg.what = 11;
                                    handler.sendMessage(msg);
                                    break;


                            }
                        }

                    }
                });

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}
