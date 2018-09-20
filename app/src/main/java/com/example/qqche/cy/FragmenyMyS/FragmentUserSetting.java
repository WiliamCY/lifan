package com.example.qqche.cy.FragmenyMyS;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.qqche.cy.Login.ForgetActivity;
import com.example.qqche.cy.Login.LoginActivity;
import com.example.qqche.cy.R;
import com.example.qqche.cy.Utils.CleanMessageUtil;
import com.example.qqche.cy.Utils.StatusBarUtilS;
import com.jaeger.library.StatusBarUtil;
import com.zhy.android.percent.support.PercentRelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/4/18.
 */

public class FragmentUserSetting extends AppCompatActivity {
    @BindView(R.id.setting_back)
    ImageView settingBack;
    Unbinder unbinder;
    @BindView(R.id.setting_fixpwd)
    PercentRelativeLayout settingFixpwd;
    @BindView(R.id.my_setting_quit)
    TextView mySettingQuit;
    @BindView(R.id.cleanMessage)
    PercentRelativeLayout cleanMessage;
    @BindView(R.id.messageSize)
    TextView messageSize;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragmetn_my_setting);
        StatusBarUtil.setTransparent(this);
        StatusBarUtilS.StatusBarLightMode(this);
        unbinder = ButterKnife.bind(this);
        try {
            String message = CleanMessageUtil.getTotalCacheSize(this);
        messageSize.setText(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @OnClick({R.id.setting_back, R.id.setting_fixpwd, R.id.my_setting_quit, R.id.cleanMessage})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.setting_back:
                finish();
                break;
            case R.id.setting_fixpwd:
                Intent intent = new Intent(this, ForgetActivity.class);
                startActivity(intent);
                break;
            case R.id.my_setting_quit:
                final AlertDialog builder = new AlertDialog.Builder(this)
                        .create();
                builder.show();
                if (builder.getWindow() == null) return;
                builder.getWindow().setContentView(R.layout.pop_user);
                TextView msg = builder.findViewById(R.id.tv_msg);
                Button cancle = builder.findViewById(R.id.btn_cancle);
                Button sure = builder.findViewById(R.id.btn_sure);
                msg.setText("是否确定退出？");
                cancle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        builder.dismiss();
                    }
                });
                sure.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent2 = new Intent(FragmentUserSetting.this, LoginActivity.class);
                        startActivity(intent2);
                        finish();
                        builder.dismiss();
                    }
                });
                break;
            case R.id.cleanMessage:
                final AlertDialog builder2 = new AlertDialog.Builder(this)
                        .create();
                builder2.show();
                if (builder2.getWindow() == null) return;
                builder2.getWindow().setContentView(R.layout.pop_user);
                TextView msg2 = builder2.findViewById(R.id.tv_msg);
                Button cancle2 = builder2.findViewById(R.id.btn_cancle);
                Button sure2 = builder2.findViewById(R.id.btn_sure);
                msg2.setText("是否清除本地缓存？");
                cancle2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        builder2.dismiss();
                    }
                });
                sure2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CleanMessageUtil.clearAllCache(getApplicationContext());
                        String message = null;
                        try {
                            message = CleanMessageUtil.getTotalCacheSize(getApplicationContext());
                            messageSize.setText(message);
                            builder2.dismiss();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                });

        }
    }


}
