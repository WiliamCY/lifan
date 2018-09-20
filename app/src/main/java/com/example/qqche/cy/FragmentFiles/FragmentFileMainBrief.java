package com.example.qqche.cy.FragmentFiles;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qqche.cy.R;
import com.example.qqche.cy.Utils.StatusBarUtilS;
import com.jaeger.library.StatusBarUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/4/25.
 */

public class FragmentFileMainBrief extends AppCompatActivity {


    @BindView(R.id.toolbar_back)
    ImageView toolbarBack;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    Unbinder unbinder;
    TextView cc;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_file_main_brief);
        View toolbar = findViewById(R.id.file_mian_briefs);
        toolbarTitle = toolbar.findViewById(R.id.toolbar_title);
        cc = findViewById(R.id.cc);
        StatusBarUtil.setTransparent(this);
        Bundle bundle = getIntent().getExtras();
        String names = bundle.getString("namess");
        String about = bundle.getString("about");
        toolbarTitle.setText(" 平台简介");
        if (!about.isEmpty()) {
            cc.setText("\u3000"+about);

        } else {
            Toast.makeText(getApplicationContext(), "发生异常", Toast.LENGTH_SHORT).show();

        }
        StatusBarUtilS.StatusBarLightMode(this);
        unbinder = ButterKnife.bind(this);
    }

    @OnClick({R.id.toolbar_back, R.id.toolbar_title})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbar_back:
                finish();
                break;
        }
    }
}
