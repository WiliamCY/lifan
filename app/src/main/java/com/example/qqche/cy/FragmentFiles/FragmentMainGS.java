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

public class FragmentMainGS extends AppCompatActivity {

    @BindView(R.id.toolbar_back)
    ImageView toolbarBack;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    Unbinder unbinder;
    @BindView(R.id.company)
    TextView companyss;
    @BindView(R.id.legal_repren)
    TextView legalReprens;
    @BindView(R.id.enterprise_type)
    TextView enterpriseTypes;
    @BindView(R.id.register_money)
    TextView registerMoneys;
    @BindView(R.id.paid_money)
    TextView paidMoneys;
    @BindView(R.id.reg_add)
    TextView regAdds;
    @BindView(R.id.register_time)
    TextView registerTimes;
    @BindView(R.id.approval)
    TextView approvals;
    @BindView(R.id.register_autn)
    TextView registerAutns;
    @BindView(R.id.credit_code)
    TextView creditCodes;
    @BindView(R.id.icp)
    TextView icpss;
    private String companys,register_moneys,paid_money,icps,legal_reprens,enterprise_types,reg_adds,register_times,approval,register_autn,credit_code;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_file_main_gs);
        View toolbar = findViewById(R.id.file_mian_gs);
        toolbarTitle = toolbar.findViewById(R.id.toolbar_title);

        StatusBarUtil.setTransparent(this);
        StatusBarUtilS.StatusBarLightMode(this);
        unbinder = ButterKnife.bind(this);
        Bundle bundle = getIntent().getExtras();
        companys = bundle.getString("companys");
        register_moneys = bundle.getString("register_moneys");
        paid_money = bundle.getString("paid_money");
        icps = bundle.getString("icps");
        legal_reprens = bundle.getString("legal_reprens");
        enterprise_types = bundle.getString("enterprise_types");
        reg_adds = bundle.getString("reg_adds");
        register_times = bundle.getString("register_times");
        approval = bundle.getString("approval");
        register_autn = bundle.getString("register_autn");
        credit_code=  bundle.getString("credit_code");
        String names = bundle.getString("namess");
        if(!companys.isEmpty() && !register_moneys.isEmpty() && !paid_money.isEmpty() && ! icps.isEmpty() && !legal_reprens.isEmpty() && !enterprise_types.isEmpty() && !reg_adds.isEmpty() && !register_times.isEmpty() && !approval.isEmpty()
                && !register_autn.isEmpty() && !credit_code.isEmpty()){
            companyss.setText(companys);
            legalReprens.setText(legal_reprens);
            paidMoneys.setText(paid_money);
            icpss.setText(icps);
            legalReprens.setText(legal_reprens);
            enterpriseTypes.setText(enterprise_types);
            regAdds.setText(reg_adds);
            registerTimes.setText(register_times);
            approvals.setText(approval);
            registerAutns.setText(register_autn);
            creditCodes.setText(credit_code);
            toolbarTitle.setText("工商备案");
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
