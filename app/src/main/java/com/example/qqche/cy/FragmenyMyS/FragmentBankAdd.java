package com.example.qqche.cy.FragmenyMyS;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qqche.cy.Gson.BankGson;
import com.example.qqche.cy.Gson.BankMessage;
import com.example.qqche.cy.Gson.PhoneCode;
import com.example.qqche.cy.R;
import com.example.qqche.cy.Utils.Constant;
import com.example.qqche.cy.Utils.Util;
import com.google.gson.Gson;
import com.zhy.android.percent.support.PercentLinearLayout;

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
import qiu.niorgai.StatusBarCompat;

/**
 * Created by Administrator on 2018/5/14.
 */

public class FragmentBankAdd extends AppCompatActivity {
    @BindView(R.id.my_bank_add_card)
    ImageView myBankAddCard;
    @BindView(R.id.my_bank_add_cards)
    PercentLinearLayout myBankAddCards;
    @BindView(R.id.toolbar_back)
    ImageView toolbarBack;
    @BindView(R.id.h1)
    EditText h1;
    @BindView(R.id.h2)
    EditText h2;
    @BindView(R.id.h3)
    EditText h3;
    @BindView(R.id.h4)
    EditText h4;
    @BindView(R.id.h5)
    EditText h5;
    @BindView(R.id.add_button)
    Button addButton;
    @BindView(R.id.getCode)
    TextView getCode;
    @BindView(R.id.my_bank_add_card_alipay)
    ImageView myBankAddCardAlipay;
    @BindView(R.id.back_number_2)
    TextView backNumber2;
    @BindView(R.id.back_number_3)
    TextView backNumber3;
    @BindView(R.id.back_number_4)
    TextView backNumber4;
    @BindView(R.id.alipy_message)
    EditText alipyMessage;
    @BindView(R.id.add_button_alipy)
    Button addButtonAlipy;
    @BindView(R.id.h4_alipy)
    EditText h4Alipy;
    @BindView(R.id.getCode_alipy)
    TextView getCodeAlipy;
    @BindView(R.id.bank_message)
    PercentLinearLayout bankMessage;
    @BindView(R.id.h5_alipy)
    EditText h5Alipy;
    @BindView(R.id.alipay_name)
    EditText alipayName;
    @BindView(R.id.alipay_account)
    TextView alipayAccount;
    @BindView(R.id.bank_card_id_alipay)
    TextView bankCardIdAlipay;
    @BindView(R.id.view_aall)
    TextView viewAall;

    public TextView getBankMessages() {
        return bankMessages;
    }

    @BindView(R.id.bank_messages)
    TextView bankMessages;

    public CardView getAboutAlipay() {
        return aboutAlipay;
    }

    @BindView(R.id.about_alipay)
    CardView aboutAlipay;

    public PercentLinearLayout getAddAlipay() {
        return addAlipay;
    }

    @BindView(R.id.add_alipay)
    PercentLinearLayout addAlipay;

    public TextView getBankCardId() {
        return bankCardId;
    }

    @BindView(R.id.bank_card_id)
    TextView bankCardId;

    public TextView getBankCardType() {
        return bankCardType;
    }

    @BindView(R.id.bank_card_type)
    TextView bankCardType;

    public TextView getBackNumber1() {
        return backNumber1;
    }

    @BindView(R.id.back_number_1)
    TextView backNumber1;
    private View view1, view2, view3, view4;
    private String bankStatus, equipmentId = "8";
    private String h1s, h2s, h3s, h4s, h5s, user_id, password, status, h4Alipys;
    private String uri1 = Constant.SERVER + "index.php?r=user%2Fubankedit";
    private String uri2 = Constant.SERVER + "index.php?r=user%2Fubankedit";
    private String uriCode = Constant.SERVER + "index.php?r=site%2Fsendphonecode";
    private Message msg;
    private BankGson bean;
    private PhoneCode phoneCode;
    private static String account, bank, branch;
    private Handler handler;//静态
    private boolean isShowOrNot = false;

    private BankMessage beans;
    private CountDownTimer time = new CountDownTimer(60000, 1000) {

        @Override
        public void onTick(long millisUntilFinished) {
            int x = view1.getVisibility();
            if (x == 0) {
                getCode.setText((millisUntilFinished / 1000) + "");
                getCode.setEnabled(false);
            } else {
                getCodeAlipy.setText((millisUntilFinished / 1000) + "");
                getCodeAlipy.setEnabled(false);
            }
        }

        @Override
        public void onFinish() {
            int x = view1.getVisibility();
            if (x == 0) {
                getCode.setEnabled(true);
                getCode.setText("发送");
            } else {
                getCodeAlipy.setEnabled(true);
                getCodeAlipy.setText("发送");
            }
        }
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_my_bank_add);
        ButterKnife.bind(this);
        view1 = findViewById(R.id.bank_view1);
        view2 = findViewById(R.id.back_add_card);
        view3 = findViewById(R.id.bank_message);
        view4 = findViewById(R.id.bank_view2);
        StatusBarCompat.translucentStatusBar(this);
        SharedPreferences preferences = getSharedPreferences("bank", MODE_PRIVATE);
        bankStatus = preferences.getString("bankStatus", "");
        SharedPreferences preferences1 = this.getSharedPreferences("dara", Context.MODE_PRIVATE);
        user_id = preferences1.getString("userId", "");
        password = preferences1.getString("password", "");
        initDate();
        if (!user_id.isEmpty() || !password.isEmpty()) {
            getBankMessage(user_id, password);
        } else {
            Toast.makeText(getApplicationContext(), "发生意外", Toast.LENGTH_SHORT).show();
        }
        handler = new MyHandler(this);
    }

    private static class MyHandler extends Handler {
        private WeakReference<FragmentBankAdd> weakReference;

        public MyHandler(FragmentBankAdd activity) {
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
                    case 4:
                        Toast.makeText(weakReference.get(), "发生失败9003", Toast.LENGTH_SHORT).show();
                        break;
                    case 5:
                        Toast.makeText(weakReference.get(), "短信发送失败，数据库错误", Toast.LENGTH_SHORT).show();
                        break;
                    case 6:
                        Toast.makeText(weakReference.get(), "注册成功", Toast.LENGTH_SHORT).show();
                        weakReference.get().myBankAddCard.animate().setDuration(1000).rotation(90).start();
                        weakReference.get().view1.setVisibility(View.GONE);
                        weakReference.get().view3.setVisibility(View.GONE);
                        weakReference.get().myBankAddCards.setVisibility(View.GONE);
                        weakReference.get().backNumber1.setText(weakReference.get().beans.getData().getBank().getAccount());
                        weakReference.get().bankCardId.setText(weakReference.get().beans.getData().getBank().getBank());
                        weakReference.get().bankCardType.setText(weakReference.get().beans.getData().getBank().getBranch());
                        weakReference.get().view2.setVisibility(View.VISIBLE);
                        BankMessage.DataBean.AlipayBean alipayBeanss = weakReference.get().beans.getData().getAlipay();
                        String alipayBeana = alipayBeanss.getStatus();
                        if (alipayBeana.equals("1")) {
                            weakReference.get().view4.setVisibility(View.GONE);
                            weakReference.get().getAboutAlipay().setVisibility(View.VISIBLE);
                            weakReference.get().addAlipay.setVisibility(View.GONE);
                            weakReference.get().isShowOrNot = false;
                            weakReference.get().alipayAccount.setText(weakReference.get().beans.getData().getAlipay().getAlipay_account());
                            weakReference.get().bankCardIdAlipay.setText(weakReference.get().beans.getData().getAlipay().getAlipay_realname());
                        } else {
                            weakReference.get().view4.setVisibility(View.GONE);
                            weakReference.get().getAboutAlipay().setVisibility(View.GONE);
                            weakReference.get().addAlipay.setVisibility(View.VISIBLE);
                            weakReference.get().isShowOrNot = false;
                        }
                        break;
                    case 7:
                        Toast.makeText(weakReference.get(), "银行卡号错误", Toast.LENGTH_SHORT).show();
                        break;

                    case 8:
                        Toast.makeText(weakReference.get(), "添加失败", Toast.LENGTH_SHORT).show();
                        break;
                    case 9:
                        BankMessage.DataBean.BankBean bankBeans = weakReference.get().beans.getData().getBank();
                        BankMessage.DataBean.AlipayBean alipayBeans = weakReference.get().beans.getData().getAlipay();
                        String bankBean = bankBeans.getStatus();
                        String alipayBean = alipayBeans.getStatus();
                        if (bankBean.equals("0") && alipayBean.equals("0")) {
                            weakReference.get().view3.setVisibility(View.VISIBLE);
                            weakReference.get().myBankAddCards.setVisibility(View.VISIBLE);
                            weakReference.get().getAddAlipay().setVisibility(View.VISIBLE);
                            weakReference.get().view2.setVisibility(View.GONE);
                            weakReference.get().getAboutAlipay().setVisibility(View.GONE);
                            weakReference.get().view4.setVisibility(View.GONE);
                            weakReference.get().view1.setVisibility(View.GONE);
                        } else if (bankBean.equals("1") && alipayBean.equals("0")) {
                            weakReference.get().view3.setVisibility(View.GONE);
                            weakReference.get().myBankAddCards.setVisibility(View.GONE);
                            weakReference.get().getAddAlipay().setVisibility(View.VISIBLE);
                            weakReference.get().view2.setVisibility(View.VISIBLE);
                            weakReference.get().getAboutAlipay().setVisibility(View.GONE);
                            weakReference.get().view4.setVisibility(View.GONE);
                            weakReference.get().view1.setVisibility(View.GONE);
                          weakReference.get().bankCardId.setText(bankBeans.getBank());
                          weakReference.get().bankCardType.setText(bankBeans.getBranch());
                        } else if (bankBean.equals("0") && alipayBean.equals("1")) {
                            weakReference.get().view3.setVisibility(View.VISIBLE);
                            weakReference.get().myBankAddCards.setVisibility(View.VISIBLE);
                            weakReference.get().view4.setVisibility(View.GONE);
                            weakReference.get().view1.setVisibility(View.GONE);
                            weakReference.get().view2.setVisibility(View.GONE);
                            weakReference.get().getAddAlipay().setVisibility(View.GONE);
                            weakReference.get().getAboutAlipay().setVisibility(View.VISIBLE);
                            weakReference.get().alipayAccount.setText(weakReference.get().beans.getData().getAlipay().getAlipay_account());
                            weakReference.get().bankCardIdAlipay.setText(weakReference.get().beans.getData().getAlipay().getAlipay_realname());
                        } else if (bankBean.equals("1") && alipayBean.equals("1")) {
                            weakReference.get().view3.setVisibility(View.GONE);
                            weakReference.get().myBankAddCards.setVisibility(View.GONE);
                            weakReference.get().getAddAlipay().setVisibility(View.GONE);
                            weakReference.get().view2.setVisibility(View.VISIBLE);
                            weakReference.get().getAboutAlipay().setVisibility(View.VISIBLE);
                            weakReference.get().view4.setVisibility(View.GONE);
                            weakReference.get().view1.setVisibility(View.GONE);
                            weakReference.get().backNumber1.setText(weakReference.get().beans.getData().getBank().getAccount());
                            weakReference.get().bankCardId.setText(weakReference.get().beans.getData().getBank().getBank());
                            weakReference.get().bankCardType.setText(weakReference.get().beans.getData().getBank().getBranch());
                            weakReference.get().alipayAccount.setText(weakReference.get().beans.getData().getAlipay().getAlipay_account());
                            weakReference.get().bankCardIdAlipay.setText(weakReference.get().beans.getData().getAlipay().getAlipay_realname());
                            weakReference.get().bankCardId.setText(bankBeans.getBank());
                            weakReference.get().bankCardType.setText(bankBeans.getBranch());
                        } else {
                            Toast.makeText(weakReference.get(), "发生异常", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 10:
                        Toast.makeText(weakReference.get(), "验证码错误", Toast.LENGTH_SHORT).show();
                        break;
                    case 11:
                        Toast.makeText(weakReference.get(), "支付宝账号不能为空", Toast.LENGTH_SHORT).show();
                        break;
                    case 12:
                        Toast.makeText(weakReference.get(), "支付宝账号姓名不能为空", Toast.LENGTH_SHORT).show();
                        break;
                    case 13:
                        Toast.makeText(weakReference.get(), "注册成功", Toast.LENGTH_SHORT).show();
                        weakReference.get().myBankAddCardAlipay.animate().setDuration(1000).rotation(90).start();
                        weakReference.get().view4.setVisibility(View.GONE);
                        weakReference.get().getAboutAlipay().setVisibility(View.VISIBLE);
                        weakReference.get().getAddAlipay().setVisibility(View.GONE);
                        weakReference.get().alipayAccount.setText(weakReference.get().beans.getData().getAlipay().getAlipay_account());
                        weakReference.get().bankCardIdAlipay.setText(weakReference.get().beans.getData().getAlipay().getAlipay_realname());
                        BankMessage.DataBean.BankBean bankBean1 = weakReference.get().beans.getData().getBank();
                        String bankBean1Status = bankBean1.getStatus();
                        if (bankBean1Status.equals("1")) {
                            weakReference.get().backNumber1.setText(weakReference.get().beans.getData().getBank().getAccount());
                            weakReference.get().bankCardId.setText(weakReference.get().beans.getData().getBank().getBank());
                            weakReference.get().bankCardType.setText(weakReference.get().beans.getData().getBank().getBranch());
                            weakReference.get().view3.setVisibility(View.GONE);
                            weakReference.get().view2.setVisibility(View.VISIBLE);
                            weakReference.get().view1.setVisibility(View.GONE);
                            weakReference.get().myBankAddCards.setVisibility(View.GONE);
                            weakReference.get().isShowOrNot = false;
                        } else {
                            weakReference.get().view1.setVisibility(View.GONE);
                            weakReference.get().view3.setVisibility(View.VISIBLE);
                            weakReference.get().view2.setVisibility(View.GONE);
                            weakReference.get().myBankAddCards.setVisibility(View.VISIBLE);
                            weakReference.get().isShowOrNot = false;
                        }

                        break;


                }
                super.handleMessage(msg);
            }

        }
    }

    private void initDate() {
        h1s = h1.getText().toString();
        h2s = h2.getText().toString();
        h3s = h3.getText().toString();
        h4s = h4.getText().toString();
        h5s = h5.getText().toString();

    }

    @OnClick({R.id.my_bank_add_cards, R.id.toolbar_back, R.id.my_bank_add_card, R.id.add_button, R.id.getCode, R.id.add_alipay, R.id.add_button_alipy, R.id.getCode_alipy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.my_bank_add_cards:
                BankMessage.DataBean.BankBean bankBean = beans.getData().getBank();
                BankMessage.DataBean.AlipayBean alipay = beans.getData().getAlipay();
                String c = bankBean.getStatus();
                String a = alipay.getStatus();
                if (c.equals("0")) {
                    if (isShowOrNot == false) {
                        myBankAddCard.animate().setDuration(1000).rotation(45).start();
                        view1.setVisibility(View.VISIBLE);
                        view3.setVisibility(View.GONE);
                        addAlipay.setVisibility(View.GONE);
                        aboutAlipay.setVisibility(View.GONE);
                        isShowOrNot = true;
                    } else {
                        if (a.equals("1")) {
                            myBankAddCard.animate().setDuration(1000).rotation(90).start();
                            view3.setVisibility(View.VISIBLE);
                            myBankAddCards.setVisibility(View.VISIBLE);
                            view1.setVisibility(View.GONE);
                            getAboutAlipay().setVisibility(View.VISIBLE);
                            alipayAccount.setText(beans.getData().getAlipay().getAlipay_account());
                            bankCardIdAlipay.setText(beans.getData().getAlipay().getAlipay_realname());
                            isShowOrNot = false;
                        } else {
                            myBankAddCard.animate().setDuration(1000).rotation(90).start();
                            myBankAddCards.setVisibility(View.VISIBLE);
                            view3.setVisibility(View.VISIBLE);
                            view1.setVisibility(View.GONE);
                            addAlipay.setVisibility(View.VISIBLE);
                            isShowOrNot = false;
                        }

                    }
                }
                break;
            case R.id.toolbar_back:
                finish();
                break;
            case R.id.add_button:
                initDate();
                if (h1s.isEmpty() || h2s.isEmpty() || h3s.isEmpty() || h4s.isEmpty() || h5s.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "请输入完整", Toast.LENGTH_SHORT).show();
                } else {
                    initAddBank(h1s, h2s, h3s, h5s);
                }
                break;
            case R.id.getCode:
                h4s = h4.getText().toString();
                if (h4s.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "请输入完整", Toast.LENGTH_SHORT).show();
                } else if (!Util.isMobileNO(h4s)) {
                    Toast.makeText(getApplicationContext(), "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                } else {
                    getPhoneCode(h4s);
                }
                break;
            case R.id.getCode_alipy:
                h4Alipys = h4Alipy.getText().toString();
                if (h4Alipys.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "请输入完整", Toast.LENGTH_SHORT).show();
                } else if (!Util.isMobileNO(h4Alipys)) {
                    Toast.makeText(getApplicationContext(), "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                } else {
                    getPhoneCode(h4Alipys);
                }
                break;
            case R.id.add_alipay:
                BankMessage.DataBean.AlipayBean alipays = beans.getData().getAlipay();
                BankMessage.DataBean.BankBean bank = beans.getData().getBank();
                String sa = alipays.getStatus();
                String b = bank.getStatus();
                if (sa.equals("0")) {
                    if (isShowOrNot == false) {
                        myBankAddCardAlipay.animate().setDuration(1000).rotation(45).start();
                        view4.setVisibility(View.VISIBLE);
                        view2.setVisibility(View.GONE);
                        myBankAddCards.setVisibility(View.GONE);
                        view3.setVisibility(View.GONE);
                        isShowOrNot = true;
                    } else {
                        if (b.equals("1")) {
                            myBankAddCardAlipay.animate().setDuration(1000).rotation(90).start();
                            backNumber1.setText(beans.getData().getBank().getAccount());
                            bankCardId.setText(beans.getData().getBank().getBank());
                            bankCardType.setText(beans.getData().getBank().getBranch());
                            view3.setVisibility(View.GONE);
                            view2.setVisibility(View.VISIBLE);
                            view1.setVisibility(View.GONE);
                            view4.setVisibility(View.GONE);
                            myBankAddCards.setVisibility(View.GONE);
                            isShowOrNot = false;
                        } else {
                            myBankAddCardAlipay.animate().setDuration(1000).rotation(90).start();
                            isShowOrNot = false;
                            myBankAddCards.setVisibility(View.VISIBLE);
                            view3.setVisibility(View.VISIBLE);
                            view4.setVisibility(View.GONE);
                            addAlipay.setVisibility(View.VISIBLE);
                        }

                    }
                }
                break;
            case R.id.add_button_alipy:
                String alipayNames = alipayName.getText().toString();
                String alipyMessages = alipyMessage.getText().toString();
                String alipayCode = h5Alipy.getText().toString();
                if (alipyMessages.isEmpty() || alipayCode.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "请输入完整", Toast.LENGTH_SHORT).show();
                } else {
                    registerAlipy(alipyMessages, alipayCode, alipayNames);
                }
                break;

        }
    }

    private void registerAlipy(String alipyMessages, String alipayCode, String alipayNames) {
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
                .add("action", "operation")
                .add("alipay_status", "1")
                .add("alipay_account", alipyMessages)
                .add("alipay_realname", alipayNames)
                .add("phonecode", alipayCode).build();
        Request request = new Request.Builder().post(body).url(uri1).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String resopnDate = response.body().string();
                    bean = new Gson().fromJson(resopnDate, BankGson.class);
                    String status = bean.getStatus();
                    switch (status) {
                        case "1":
                            msg = new Message();
                            msg.what = 13;
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
                        case "16":
                            msg = new Message();
                            msg.what = 10;
                            handler.sendMessage(msg);
                            break;
                        case "17":
                            msg = new Message();
                            msg.what = 11;
                            handler.sendMessage(msg);
                            break;
                        case "18":
                            msg = new Message();
                            msg.what = 12;
                            handler.sendMessage(msg);
                            break;

                        case "20":
                            msg = new Message();
                            msg.what = 8;
                            handler.sendMessage(msg);
                            break;


                    }
                }
            }
        });

    }

    private void getPhoneCode(final String h4s) {
        long timeStamp = System.currentTimeMillis() / 1000;
        String timestamp = String.valueOf(timeStamp);
        String message = "equipment=" + equipmentId + "&projectId=17&time=" + timestamp + ".K15058835525+.";
        String token = Util.StringToMd5(message);
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("token", token)
                .add("timestamp", timestamp)
                .add("equipmentId", equipmentId)
                .add("phone", h4s).build();
        Request request = new Request.Builder().post(body).url(uriCode).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseData = response.body().string();
                    phoneCode = new Gson().fromJson(responseData, PhoneCode.class);
                    String status = phoneCode.getStatus();
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
        time.start();

    }

    private void initAddBank(final String h1s, final String h2s, final String h3s, final String h5s) {

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
                .add("action", "operation")
                .add("bank", h1s)
                .add("banknum", h2s)
                .add("bankacc", h3s)
                .add("phonecode", h5s).build();
        Request request = new Request.Builder().post(body).url(uri1).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String resopnDate = response.body().string();
                    bean = new Gson().fromJson(resopnDate, BankGson.class);
                    String status = bean.getStatus();
                    switch (status) {
                        case "1":
                            msg = new Message();
                            msg.what = 6;
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
                        case "15":
                            msg = new Message();
                            msg.what = 7;
                            handler.sendMessage(msg);
                            break;
                        case "20":
                            msg = new Message();
                            msg.what = 8;
                            handler.sendMessage(msg);
                            break;

                    }
                }
            }
        });

    }

    private void getBankMessage(String user_id, String password) {
        long timeStamp = System.currentTimeMillis() / 1000;
        String timestamp = String.valueOf(timeStamp);
        String message = "equipment=" + equipmentId + "&projectId=17&time=" + timestamp + ".K15058835525+.";
        String token = Util.StringToMd5(message);
        String pwasword = "(*&%" + password + "*&)jzy";
        String pwassMd5 = Util.StringToMd5s(pwasword);
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("token", token)
                .add("timestamp", timestamp)
                .add("equipmentId", equipmentId)
                .add("user_id", user_id)
                .add("password", pwassMd5)
                .add("action", "display").build();
        Request request = new Request.Builder().post(body).url(uri2).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String respondate = response.body().string();
                    System.out.print(respondate);
                    beans = new Gson().fromJson(respondate, BankMessage.class);
                    String status = beans.getStatus();
                    if (!status.isEmpty()) {
                        switch (status) {
                            case "1":
                                msg = new Message();
                                msg.what = 9;
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
                        }
                    }

                }

            }
        });


    }

}