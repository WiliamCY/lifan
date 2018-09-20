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

public class FragmentMainPY extends AppCompatActivity {

    @BindView(R.id.toolbar_back)
    ImageView toolbarBack;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    Unbinder unbinder;
    @BindView(R.id.recharge_cost)
    TextView rechargeCost;
    @BindView(R.id.withdraw_cost)
    TextView withdrawCost;
    @BindView(R.id.management_cost)
    TextView managementCost;
    private String recharge_costs,withdraw_costs,management_costs;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_file_mian_py);
        View toolbar = findViewById(R.id.file_main_py_toolbar);
        toolbarTitle = toolbar.findViewById(R.id.toolbar_title);

        StatusBarUtil.setTransparent(this);
        StatusBarUtilS.StatusBarLightMode(this);
        unbinder = ButterKnife.bind(this);
        Bundle bundle  = getIntent().getExtras();
        recharge_costs = bundle.getString("recharge_costs");
        withdraw_costs = bundle.getString("withdraw_costs");
        management_costs = bundle.getString("management_costs");
        String names = bundle.getString("namess");
        if(!recharge_costs.isEmpty() && !withdraw_costs.isEmpty() && !management_costs.isEmpty()){
            rechargeCost.setText(recharge_costs);
            withdrawCost.setText(withdraw_costs);
            managementCost.setText(management_costs);
            toolbarTitle.setText("平台费用");
        }else {
            Toast.makeText(getApplicationContext(),"发生异常",Toast.LENGTH_SHORT).show();
        }


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