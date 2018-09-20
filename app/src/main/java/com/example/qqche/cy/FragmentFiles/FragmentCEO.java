package com.example.qqche.cy.FragmentFiles;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.qqche.cy.R;
import com.example.qqche.cy.Utils.StatusBarUtilS;
import com.jaeger.library.StatusBarUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/4/24.
 */

public class FragmentCEO extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_back)
    ImageView toolbarBack;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    Unbinder unbinder;
    TextView ceos;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_file_ceo);
        unbinder = ButterKnife.bind(this);
        ceos = findViewById(R.id.ceo);
        Bundle bundle = getIntent().getExtras();
        String ceo = bundle.getString("ceo");
        String names = bundle.getString("namess");
        if (!ceo.isEmpty()) {
               ceos.setText("\u3000"+ceo);
            toolbarTitle.setText("高管简介");
        }
        StatusBarUtil.setTransparent(this);
        StatusBarUtilS.StatusBarLightMode(this);


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
