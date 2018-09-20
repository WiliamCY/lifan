package com.example.qqche.cy.FragmentRebate;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.qqche.cy.R;
import com.example.qqche.cy.Utils.StatusBarUtilS;
import com.jaeger.library.StatusBarUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/4/27.
 */

public class FragmentRebateMessage extends AppCompatActivity {
    @BindView(R.id.toolbar_back)
    ImageView toolbarBack;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    Unbinder unbinder;
    @BindView(R.id.online_time)
    TextView onlineTimes;
    @BindView(R.id.register_money)
    TextView registerMoneys;
    @BindView(R.id.paid_money)
    TextView paidMoneys;
    @BindView(R.id.legal_repren)
    TextView legalReprens;
    @BindView(R.id.register_time)
    TextView registerTimes;
    @BindView(R.id.company)
    TextView companys;
    @BindView(R.id.record_sub)
    TextView recordSubs;
    @BindView(R.id.website)
    TextView websites;
    @BindView(R.id.aboutd)
    TextView aboutds;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setTransparent(this);
        StatusBarUtilS.StatusBarLightMode(this);
        setContentView(R.layout.fragment_rebate_message);
        View toolbar = findViewById(R.id.rebate_message_title);
        toolbarTitle = toolbar.findViewById(R.id.toolbar_title);
        toolbarTitle.setText("甬e贷平台信息");
        unbinder = ButterKnife.bind(this);
        Bundle bundle = getIntent().getExtras();
        String online_time = bundle.getString("online_time", "");
        String register_money = bundle.getString("register_money", "");
        String paid_money = bundle.getString("paid_money", "");
        String legal_repren = bundle.getString("legal_repren", "");
        String register_time = bundle.getString("register_time", "");
        String company = bundle.getString("company", "");
        String record_sub = bundle.getString("record_sub", "");
        String website = bundle.getString("website", "");
        String about = bundle.getString("about", "");
        long data = Long.valueOf(online_time) * 1000;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        String sd = sdf.format(new Date(Long.parseLong(String.valueOf(data))));
        onlineTimes.setText(sd);
        long data2 = Long.valueOf(register_time) * 1000;
        String sd2 = sdf.format(new Date(Long.parseLong(String.valueOf(data2))));
        registerMoneys.setText(register_money);
        paidMoneys.setText(paid_money);
        legalReprens.setText(legal_repren);
        registerTimes.setText(sd2);
        companys.setText(company);
        recordSubs.setText(record_sub);
        websites.setText(website);
        aboutds.setText(about);
    }


    @OnClick({R.id.toolbar_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbar_back:
                finish();
                break;

        }
    }
}
