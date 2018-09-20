package com.example.qqche.cy.FragmenyMyS;

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
import com.example.qqche.cy.Login.LoginActivity;
import com.example.qqche.cy.R;
import com.example.qqche.cy.Utils.Constant;
import com.example.qqche.cy.Utils.Util;
import com.google.gson.Gson;

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

public class FragmentFixPwd extends AppCompatActivity {

    @BindView(R.id.forget_back)
    ImageView forgetBack;
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
    private String phone,phoneCode,pwd,repwd,status;
    private String equipmentId = "8";
    private Message msg;
    private String uriCode = Constant.SERVER+"index.php?r=site%2Fsendphonecode";
    private String fixUri = Constant.SERVER+"index.php?r=site%2Fforget";
    private  Handler handler;//静态

    private static  class MyHandler extends Handler{
        private WeakReference<FragmentFixPwd> weakReference;
        public  MyHandler(FragmentFixPwd activity) {
            weakReference = new WeakReference<>(activity);
        }
        @Override
        public void handleMessage(Message msg) {
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
                    case 4:
                        Toast.makeText(weakReference.get(), "发生失败9003", Toast.LENGTH_SHORT).show();
                        break;
                    case 5:
                        Toast.makeText(weakReference.get(), "短信发送失败，数据库错误", Toast.LENGTH_SHORT).show();
                        break;
                    case 6:
                        Toast.makeText(weakReference.get(), "修改成功", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(weakReference.get(), FragmentUserSetting.class);
                        weakReference.get().startActivity(intent);
                        weakReference.get().finish();
                        break;
                    case 7:
                        Toast.makeText(weakReference.get(), "数据错误", Toast.LENGTH_SHORT).show();
                        break;
                    case 8:
                        Toast.makeText(weakReference.get(), "非法访问", Toast.LENGTH_SHORT).show();
                        break;
                    case 9:
                        Toast.makeText(weakReference.get(), "该手机号未注册", Toast.LENGTH_SHORT).show();
                        break;
                    case 10:
                        Toast.makeText(weakReference.get(), "验证码错误", Toast.LENGTH_SHORT).show();
                        break;
                    case 11:
                        Toast.makeText(weakReference.get(), "修改失败", Toast.LENGTH_SHORT).show();
                        break;


                }
                super.handleMessage(msg);
            }
        }
    }
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
        handler = new MyHandler(this);

    }

    @OnClick({R.id.forget_back, R.id.forget_usersendYZM, R.id.forget_forget})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.forget_back:
                finish();
                break;
            case R.id.forget_usersendYZM:
                String phones = forgetUserPhone.getText().toString();
                if(!Util.isMobileNO(phone) || phones.isEmpty()){
                    Toast.makeText(getApplicationContext(),"请输入完整手机号或者正确的手机格式",Toast.LENGTH_SHORT).show();
                }else {
                    getCode(phones);
                }
                break;
            case R.id.forget_forget:
                initData();
                if(phone.isEmpty() || phoneCode.isEmpty() || pwd.isEmpty() ||repwd.isEmpty()){
                       Toast.makeText(getApplicationContext(),"请输入完整",Toast.LENGTH_SHORT).show();
                }else if(!Util.isMobileNO(phone)){
                    Toast.makeText(getApplicationContext(),"请输入正确的手机格式",Toast.LENGTH_SHORT).show();
                }else {
                    fixPwd(phone,phoneCode,pwd,repwd);
                }
                break;
        }
    }

    private void initData(){
        phone = forgetUserPhone.getText().toString();
        phoneCode = forgetUserYZM.getText().toString();
        pwd = forgetUserPwd.getText().toString();
        repwd = forgetUserPwdS.getText().toString();

    }

    private void  getCode(final String phone){
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
              client.newCall(request).enqueue(new Callback() {
                  @Override
                  public void onFailure(Call call, IOException e) {

                  }

                  @Override
                  public void onResponse(Call call, Response response) throws IOException {
                      if(response.isSuccessful()){
                          time.start();
                          String responseData = response.body().string();
                          PhoneCode code = new Gson().fromJson(responseData,PhoneCode.class);
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
    private void  fixPwd(final String phone, final String phoneCode, final String pwd, final String repwd){
                long timeStamp = System.currentTimeMillis() / 1000;
                String timestamp = String.valueOf(timeStamp);
                String message = "equipment=" + equipmentId + "&projectId=17&time=" + timestamp + ".K15058835525+.";
                String token = Util.StringToMd5(message);
                OkHttpClient client = new OkHttpClient();
                RequestBody body = new FormBody.Builder()
                        .add("token", token)
                        .add("timestamp", timestamp)
                        .add("equipmentId", equipmentId)
                        .add("phone", phone)
                        .add("pwd", pwd)
                        .add("repwd", repwd)
                        .add("phonecode", phoneCode).build();
                Request request = new Request.Builder().post(body).url(fixUri).build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if(response.isSuccessful()){
                            String resopnData = response.body().string();
                            PhoneCode code = new Gson().fromJson(resopnData,PhoneCode.class);
                            String status = code.getStatus();
                            switch (status){
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
}
