package com.example.qqche.cy.Fragment;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qqche.cy.FragmenyMyS.FragmentAlipay;
import com.example.qqche.cy.FragmenyMyS.FragmentBankAdd;
import com.example.qqche.cy.FragmenyMyS.FragmentBankWi;
import com.example.qqche.cy.FragmenyMyS.FragmentCapitalRecord;
import com.example.qqche.cy.FragmenyMyS.FragmentMyFinder;
import com.example.qqche.cy.FragmenyMyS.FragmentMyIndepeDentdocument;
import com.example.qqche.cy.FragmenyMyS.FragmentMyTenderRecord;
import com.example.qqche.cy.FragmenyMyS.FragmentUserMessage;
import com.example.qqche.cy.FragmenyMyS.FragmentUserSetting;
import com.example.qqche.cy.FragmenyMyS.FragmetnTimeAxis;
import com.example.qqche.cy.Gson.MyMessage;
import com.example.qqche.cy.R;
import com.example.qqche.cy.Utils.Constant;
import com.example.qqche.cy.Utils.StatusBarUtilS;
import com.example.qqche.cy.Utils.Util;
import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;

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

import static android.content.Context.MODE_PRIVATE;


/**
 * Created by Administrator on 2018/4/17.
 */

public class MyFragment extends Fragment {
    Unbinder unbinder;
    public TextView getMyId() {
        return myId;
    }
    @BindView(R.id.my_id)
    TextView myId;
    @BindView(R.id.my_news)
    ImageView myNews;
    @BindView(R.id.my_messages)
    RelativeLayout myMessages;
    public TextView getMyTotalMoney() {
        return myTotalMoney;
    }
    @BindView(R.id.my_totalMoney)
    TextView myTotalMoney;
    public TextView getMyBlockedFund() {
        return myBlockedFund;
    }
    @BindView(R.id.my_BlockedFund)
    TextView myBlockedFund;
    public TextView getMyAccumulativeIncome() {
        return myAccumulativeIncome;
    }
    public Unbinder getUnbinder() {
        return unbinder;
    }
    @BindView(R.id.my_AccumulativeIncome)
    TextView myAccumulativeIncome;
    public TextView getMyPresented() {
        return myPresented;
    }
    public ImageView getMyNews() {
        return myNews;
    }
    @BindView(R.id.my_presented)
    TextView myPresented;
    @BindView(R.id.my_moneys)
    LinearLayout myMoneys;
    @BindView(R.id.my_image)
    ImageView myImage;
    @BindView(R.id.my_view1)
    View myView1;
    @BindView(R.id.my_view2)
    View myView2;
    @BindView(R.id.Withdraw)
    LinearLayout Withdraw;
    @BindView(R.id.my_capital_record)
    RelativeLayout myCapitalRecord;
    @BindView(R.id.ny_tender_record)
    RelativeLayout nyTenderRecord;
    @BindView(R.id.my_indepe_dent_document)
    RelativeLayout myIndepeDentDocument;
    @BindView(R.id.my_friend_invitation)
    RelativeLayout myFriendInvitation;
    @BindView(R.id.my_bank_card_record)
    RelativeLayout myBankCardRecord;
    @BindView(R.id.my_image_2)
    ImageView myImage2;
    @BindView(R.id.ic_my_back2)
    ImageView icMyBack2;
    @BindView(R.id.my_setting)
    RelativeLayout mySetting;
    @BindView(R.id.invest_recently)
    TextView investRecently;
    private FragmentManager manager;
    private FragmentTransaction ft;
    private String equipmentId = "8", status;
    private String uri = Constant.SERVER + "index.php?r=user%2Fuindex";
    private static String total, use_money, no_use_money, income, unaudited_num, invest_recently, imperfect_phone, bank_status;
    private static MyMessage.DataBean aclass;
    private static MyMessage bean;
    private Message msg;
    private Handler handler;//静态
    public FragmentActivity activity;
    public MyFragment myFragment;
    private View myView;
    private PopupWindow pw;
    private ImageView wthdrawColse, Alipay, bankcard;


    public static MyFragment newInstance(String param1) {
        MyFragment fragment = new MyFragment();
        Bundle args = new Bundle();
        args.putString("agrs1", param1);
        fragment.setArguments(args);
        return fragment;
    }

public MyFragment(){

}
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        unbinder = ButterKnife.bind(this, view);
        manager = getFragmentManager();
        SharedPreferences preferences = getActivity().getSharedPreferences("dara", MODE_PRIVATE);
        String user_id = preferences.getString("userId", "");
        String password = preferences.getString("password", "");
        initData(user_id, password);
        handler = new MyHandler(getActivity(), this);
        activity = getActivity();
        return view;
    }

    private static class MyHandler extends Handler {
        private WeakReference<FragmentActivity> weakReference;
        private WeakReference<MyFragment> weakReferences;

        public MyHandler(FragmentActivity fragmentActivity, MyFragment fragmentCommend) {
            weakReference = new WeakReference<>(fragmentActivity);
            weakReferences = new WeakReference<>(fragmentCommend);

        }

        public WeakReference<FragmentActivity> getWeakReference() {
            return weakReference;
        }

        @Override
        public void handleMessage(Message msg) {
            if (weakReference.get() != null) {
                switch (msg.what) {
                    case 1:
                        switch (msg.what) {
                            case 1:
                                aclass = bean.getData();
                                total = aclass.getTotal();
                                use_money = aclass.getUse_money();
                                no_use_money = aclass.getNo_use_money();
                                income = aclass.getIncome();
                                unaudited_num = aclass.getUnaudited_num();
                                invest_recently = aclass.getInvest_recently();
                                imperfect_phone = aclass.getImperfect_phone();
                                bank_status = aclass.getBank_status();
                                SharedPreferences.Editor editor = weakReferences.get().getActivity().getSharedPreferences("bank", MODE_PRIVATE).edit();
                                editor.putString("bankStatus", bank_status);
                                editor.apply();
                                weakReferences.get().getMyTotalMoney().setText(total);
                                weakReferences.get().getMyPresented().setText(use_money);
                                weakReferences.get().getMyBlockedFund().setText(no_use_money);
                                weakReferences.get().getMyAccumulativeIncome().setText(income);
                                weakReferences.get().getMyId().setText(imperfect_phone);
                                weakReferences.get().investRecently.setText(invest_recently);
                                break;
                            case 2:
                                Toast.makeText(weakReference.get(), "数据错误", Toast.LENGTH_SHORT).show();
                                break;
                            case 3:
                                Toast.makeText(weakReference.get(), "非法访问", Toast.LENGTH_SHORT).show();
                                break;
                        }
                        super.handleMessage(msg);
                }
            }
        }

    }


    private void initData(final String user_id, final String password) {
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
                .add("password", pwassMd5).build();
        Request request = new Request.Builder().post(body).url(uri).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String respnseData = response.body().string();
                    bean = new Gson().fromJson(respnseData, MyMessage.class);
                    String status = bean.getStatus();
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

                    }
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        handler.removeCallbacksAndMessages(null);
        unbinder.unbind();
    }

    @OnClick({R.id.my_id, R.id.my_news, R.id.my_setting, R.id.my_image,
            R.id.my_friend_invitation, R.id.Withdraw, R.id.my_bank_card_record, R.id.ny_tender_record, R.id.my_indepe_dent_document,
            R.id.my_capital_record})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.my_image:
                Intent intent = new Intent(getContext(), FragmentUserMessage.class);
                startActivity(intent);
                break;
            case R.id.my_id:
                break;
            case R.id.my_news:
                Intent intent2 = new Intent(getContext(), FragmetnTimeAxis.class);
                startActivity(intent2);
                break;
            case R.id.my_setting:
                Intent intent3 = new Intent(getContext(), FragmentUserSetting.class);
                startActivity(intent3);
                break;
            case R.id.my_friend_invitation:
                Intent intent4 = new Intent(getContext(), FragmentMyFinder.class);
                startActivity(intent4);
                break;
            case R.id.Withdraw:
                initDelite();
                break;
            case R.id.my_bank_card_record:
                Intent intent6 = new Intent(getContext(), FragmentBankAdd.class);
                startActivity(intent6);
                break;
            case R.id.my_indepe_dent_document:
                Intent intent7 = new Intent(getContext(), FragmentMyIndepeDentdocument.class);
                startActivity(intent7);
                break;
            case R.id.ny_tender_record:
                Intent intent8 = new Intent(getContext(), FragmentMyTenderRecord.class);
                startActivity(intent8);
                break;
            case R.id.my_capital_record:
                Intent intent10 = new Intent(getContext(), FragmentCapitalRecord.class);
                startActivity(intent10);
                break;
        }
    }

    private void initDelite() {
        myView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_wthdraw_select, null, true);
        wthdrawColse = myView.findViewById(R.id.wthdraw_colse);
        Alipay = myView.findViewById(R.id.Alipay);
        bankcard = myView.findViewById(R.id.bankcard);
        pw = new PopupWindow(getActivity());
        pw.setContentView(myView);
        int popwidth = getResources().getDisplayMetrics().widthPixels;
        int popheight = getResources().getDisplayMetrics().heightPixels;
        pw.setWidth(Math.round(popwidth * 0.8f));
        pw.setHeight(Math.round(popheight * 0.45f));
        pw.setOutsideTouchable(true);
        pw.setFocusable(true);
        ColorDrawable dw = new ColorDrawable(Color.TRANSPARENT);
        darkenBackground(0.8f);
        pw.setBackgroundDrawable(dw);
        pw.showAtLocation(myMessages, Gravity.CENTER | Gravity.CENTER, 0, 0);
        pw.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                darkenBackground(1f);
                pw.dismiss();
            }
        });
        wthdrawColse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pw.dismiss();
            }
        });
        Alipay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent alipay = new Intent(getActivity(), FragmentAlipay.class);
                startActivity(alipay);
            }
        });
        bankcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backCadrk = new Intent(getActivity(), FragmentBankWi.class);
                startActivity(backCadrk);
            }
        });
    }

    private void darkenBackground(Float bgcolor) {
        WindowManager.LayoutParams ip = getActivity().getWindow().getAttributes();
        ip.alpha = bgcolor;
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getActivity().getWindow().setAttributes(ip);
    }
}
