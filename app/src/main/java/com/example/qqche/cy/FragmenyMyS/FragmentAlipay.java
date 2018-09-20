package com.example.qqche.cy.FragmenyMyS;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qqche.cy.Gson.MyBankWi;
import com.example.qqche.cy.R;
import com.example.qqche.cy.Utils.Constant;
import com.example.qqche.cy.Utils.Util;
import com.google.gson.Gson;

import java.io.IOException;
import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import qiu.niorgai.StatusBarCompat;

/**
 * Created by Administrator on 2018/4/18.
 */

public class FragmentAlipay extends AppCompatActivity implements TextWatcher {

    Unbinder unbinder;
//    @BindView(R.id.toolbar)
//    Toolbar toolbar;
//    @BindView(R.id.toolbar_back)
//    ImageView toolbarBack;
//    @BindView(R.id.toolbar_title)
//    TextView toolbarTitle;

    @BindView(R.id.my_moneys_listener)
    EditText myMoneysListener;
    @BindView(R.id.my_moneys_draw)
    Button myMoneysDraw;
//    @BindView(R.id.WiTitleButton)
//    TextView WiTitleButton;
    private String money, user_id, password;
    private String equipmentId = "8";
    private View toolabrView;
    private String uri = Constant.SERVER+"index.php?r=user%2Fucashnew";
    private Message msg;
    private  Handler handler;//静态
    private static  class MyHandler extends Handler{
        private WeakReference<FragmentAlipay> weakReference;
        public  MyHandler(FragmentAlipay activity) {
            weakReference = new WeakReference<>(activity);
        }
        @Override
        public void handleMessage(Message msg) {
            if (weakReference.get() != null) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 1:
                        Toast.makeText(weakReference.get(), "提现成功", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(weakReference.get(), "未获取银行卡", Toast.LENGTH_SHORT).show();
                        break;

                }
                super.handleMessage(msg);
            }
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_my_alipy);
        StatusBarCompat.translucentStatusBar(this);
        toolabrView = findViewById(R.id.my_bank_toolbar);
//        toolbarBack = toolabrView.findViewById(R.id.toolbar_back);
        unbinder = ButterKnife.bind(this);
//        toolbar = toolabrView.findViewById(R.id.toolbar);
        myMoneysDraw.setEnabled(false);
        myMoneysListener.addTextChangedListener(this);
        SharedPreferences preferences = this.getSharedPreferences("dara", Context.MODE_PRIVATE);
        user_id = preferences.getString("userId", "");
        password = preferences.getString("password", "");
        handler = new MyHandler(this);
    }

//    @OnClick({R.id.toolbar_back, R.id.my_moneys_draw, R.id.WiTitleButton})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.toolbar_back:
//                finish();
//                unbinder.unbind();
//                break;
//            case R.id.my_moneys_draw:
//                money = myMoneysListener.getText().toString();
//                if (!money.isEmpty()) {
//                    initData(money);
//                } else {
//                    Toast.makeText(getApplicationContext(), "提现金额不能为空", Toast.LENGTH_SHORT).show();
//                }
//                break;
//            case R.id.WiTitleButton:
//                Intent intent = new Intent(this,FramgnetBankWiS.class);
//                Bundle bundle = new Bundle();
//                bundle.putString("user_id",user_id);
//                bundle.putString("password",password);
//                intent.putExtras(bundle);
//                startActivity(intent);
//                break;
//
//
//        }
//    }

    private void initData(final String money) {
                long timeStamp = System.currentTimeMillis() / 1000;
                String timestamp = String.valueOf(timeStamp);
                String message = "equipment=" + equipmentId + "&projectId=17&time=" + timestamp + ".K15058835525+.";
                String token = Util.StringToMd5(message);
                String pwasword = "(*&%" + password + "*&)jzy";
                String pwassMd5 = Util.StringToMd5s(pwasword);
                OkHttpClient client = new OkHttpClient();
                RequestBody body = new FormBody.Builder()
                        .add("token", token)
                        .add("equipmentId", equipmentId)
                        .add("timestamp", timestamp)
                        .add("user_id", user_id)
                        .add("password", pwassMd5)
                        .add("action", "display")
                        .add("money", money).build();
                Request request = new Request.Builder().post(body).url(uri).build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }
                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (response.isSuccessful()) {
                            String responData = response.body().string();
                            MyBankWi bean = new Gson().fromJson(responData, MyBankWi.class);
                            String status = bean.getStatus();
                            switch (status) {
                                case "1":
                                    msg = new Message();
                                    msg.what = 1;
                                    handler.sendMessage(msg);
                                    break;
                                case "31":
                                    msg = new Message();
                                    msg.what = 2;
                                    handler.sendMessage(msg);
                            }
                        }
                    }

                });

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        String money = myMoneysListener.getText().toString().trim();
        if (!money.isEmpty()) {
            long moneys = Integer.parseInt(money);
            if (moneys > 0) {
                myMoneysDraw.setEnabled(true);
                myMoneysDraw.setBackgroundResource(R.color.my_tiixan);
            }


        } else {
            myMoneysDraw.setEnabled(false);
            myMoneysDraw.setBackgroundResource(R.color.my_tiixans);
        }

    }


    @Override
    public void afterTextChanged(Editable s) {

    }
}
