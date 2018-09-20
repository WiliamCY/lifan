package com.example.qqche.cy.FragmenyMyS;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.qqche.cy.Adapter.MyInvestAdapter;
import com.example.qqche.cy.Class.MyInvest;
import com.example.qqche.cy.R;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import qiu.niorgai.StatusBarCompat;

/**
 * Created by Administrator on 2018/4/23.
 */

public class FragmentMyInvest extends AppCompatActivity {
    @BindView(R.id.toolbar_back)
    ImageView toolbarBack;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.xrecyview_invest)
    XRecyclerView xrecyviewInvest;
    Unbinder unbinder;
    private MyInvestAdapter myInvestAdapter;
    private List<MyInvest> myInvestList = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     setContentView(R.layout.fragment_my_invese);
        View toolbar = findViewById(R.id.my_invest);
        toolbarTitle = toolbar.findViewById(R.id.toolbar_title);
        toolbarBack = toolbar.findViewById(R.id.toolbar_back);
        toolbarTitle.setText("最近投标");
        StatusBarCompat.translucentStatusBar(this);

        xrecyviewInvest = findViewById(R.id.xrecyview_invest);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        xrecyviewInvest.setLayoutManager(layoutManager);
        myInvestAdapter = new MyInvestAdapter(myInvestList);
        StatusBarCompat.translucentStatusBar(this);
        xrecyviewInvest.setAdapter(myInvestAdapter);
        unbinder = ButterKnife.bind(this);
    }





    @OnClick({R.id.toolbar_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbar_back:
              finish();
                unbinder.unbind();
                break;
        }
    }
}
