package com.example.qqche.cy.FragmentFiles;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qqche.cy.Gson.FileMainGson;
import com.example.qqche.cy.R;
import com.example.qqche.cy.Utils.Constant;
import com.example.qqche.cy.Utils.StatusBarUtilS;
import com.example.qqche.cy.Utils.Util;
import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Date;

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

/**
 * Created by Administrator on 2018/4/24.
 */

public class FragmentFileMain extends AppCompatActivity {

    @BindView(R.id.toolbar_back)
    ImageView toolbarBack;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.file_mian_h2)
    TextView fileMianH2;
    @BindView(R.id.my_file_layout_1)
    LinearLayout myFileLayout1;
    @BindView(R.id.my_file_lable_time)
    TextView myFileLableTime;
    @BindView(R.id.file_main_brief)
    LinearLayout fileMainBrief;
    @BindView(R.id.file_mian_ceo)
    LinearLayout fileMianCeo;
    @BindView(R.id.file_mian_AIC)
    LinearLayout fileMianAIC;
    @BindView(R.id.file_mian_py)
    LinearLayout fileMianPy;
    @BindView(R.id.file_mian_honor)
    LinearLayout fileMianHonor;
    @BindView(R.id.file_mian_call)
    LinearLayout fileMianCall;
    Unbinder unbinder;

    public TextView getNames() {
        return names;
    }

    @BindView(R.id.name)
    TextView names;

    public TextView getDepositorys() {
        return depositorys;
    }

    @BindView(R.id.depository)
    TextView depositorys;

    public TextView getAprs() {
        return aprs;
    }

    @BindView(R.id.apr)
    TextView aprs;

    public TextView getPaidMoneys() {
        return paidMoneys;
    }

    @BindView(R.id.paid_money)
    TextView paidMoneys;

    public TextView getCriditAttorns() {
        return criditAttorns;
    }

    @BindView(R.id.cridit_attorn)
    TextView criditAttorns;

    public TextView getProductLimits() {
        return productLimits;
    }

    @BindView(R.id.product_limit)
    TextView productLimits;

    public TextView getAssetTypes() {
        return assetTypes;
    }

    @BindView(R.id.asset_type)
    TextView assetTypes;

    public TextView getOnlineTimes() {
        return onlineTimes;
    }

    @BindView(R.id.online_time)
    TextView onlineTimes;

    public TextView getWebsites() {
        return websites;
    }

    @BindView(R.id.website)
    TextView websites;
    private String uri = Constant.SERVER + "index.php?r=archives%2Farinfo";
    private String user_id, namess;
    private String equipmentId = "8";
    private Handler handler;//静态
    private Message msg;
    private static String name, depository, apr, paid_money, cridit_attorn, product_limit, asset_type, online_time, website;
    private static String about, company_exe;
    private static String control_evaluation, cast_background, join_association;
    private static String recharge_cost, withdraw_cost, management_cost;
    private static String company_tel, company_com, address, company, icp;
    private static String legal_repren, enterprise_type, reg_add, register_time, approval, register_autn, register_num, credit_code;
    private FileMainGson bean;
    private  Bundle bundle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_file_main);
        StatusBarUtil.setTransparent(this);
        StatusBarUtilS.StatusBarLightMode(this);
        unbinder = ButterKnife.bind(this);
         bundle = getIntent().getExtras();
        user_id = bundle.getString("userId", "");
        namess = bundle.getString("name", "");
        handler = new MyHandler(this);
        if (!user_id.isEmpty() && !namess.isEmpty()) {
            initData(user_id);
            toolbarTitle.setText(namess);
        } else {
            Toast.makeText(getApplicationContext(), "发生异常", Toast.LENGTH_SHORT).show();
        }


    }

    private static class MyHandler extends Handler {
        private WeakReference<FragmentFileMain> weakReference;

        public MyHandler(FragmentFileMain activity) {
            weakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (weakReference.get() != null) {
                switch (msg.what) {
                    case 1:
                        FileMainGson.DataBean.ArchivesBean data = weakReference.get().bean.getData().getArchives();
                        name = data.getName();
                        depository = data.getDepository();
                        apr = data.getApr();
                        paid_money = data.getPaid_money();
                        cridit_attorn = data.getCridit_attorn();
                        product_limit = data.getProduct_limit();
                        asset_type = data.getAsset_type();
                        online_time = data.getOnline_time();
                        website = data.getWebsite();
                        about = data.getAbout();
                        company_exe = data.getCompany_exe();
                        company = data.getCompany();
                        control_evaluation = data.getControl_evaluation();
                        cast_background = data.getCast_background();
                        join_association = data.getJoin_association();
                        recharge_cost = data.getRecharge_cost();
                        withdraw_cost = data.getWithdraw_cost();
                        icp = data.getIcp();
                        management_cost = data.getManagement_cost();
                        weakReference.get().getNames().setText(name);
                        weakReference.get().getDepositorys().setText(depository);
                        weakReference.get().getAprs().setText(apr);
                        weakReference.get().getPaidMoneys().setText(paid_money);
                        weakReference.get().getCriditAttorns().setText(cridit_attorn);
                        weakReference.get().getProductLimits().setText(product_limit);
                        weakReference.get().getAssetTypes().setText(asset_type);
                        long datas = Long.valueOf(online_time) * 1000;
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
                        String sd = sdf.format(new Date(Long.parseLong(String.valueOf(datas))));
                        weakReference.get().getOnlineTimes().setText(sd);
                        weakReference.get().getWebsites().setText(website);
                        FileMainGson.DataBean.ArchivesInfoBean data2 = weakReference.get().bean.getData().getArchives_info();
                        legal_repren = data2.getLegal_repren();
                        enterprise_type = data2.getEnterprise_type();
                        reg_add = data2.getReg_add();
                        register_time = data2.getRegister_time();
                        approval = data2.getApproval();
                        register_autn = data2.getRegister_autn();
                        register_num = data2.getRegister_num();
                        company_tel = data2.getCompany_tel();
                        company_com = data2.getCompany_com();
                        address = data2.getAddress();
                        credit_code = data2.getCredit_code();


                        break;
                    case 2:
                        Toast.makeText(weakReference.get().getApplicationContext(), "数据错误", Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        Toast.makeText(weakReference.get().getApplicationContext(), "非法档案", Toast.LENGTH_SHORT).show();
                        break;

                }
            }

        }
    }

    @OnClick({R.id.toolbar_back, R.id.my_file_layout_1, R.id.file_main_brief, R.id.file_mian_ceo, R.id.file_mian_AIC, R.id.file_mian_py, R.id.file_mian_honor, R.id.file_mian_call})
    public void onViewClicked(View view) {
        namess = bundle.getString("name", "");
        switch (view.getId()) {
            case R.id.toolbar_back:
                finish();
                break;
            case R.id.my_file_layout_1:
                break;
            case R.id.file_main_brief:
                FileMainGson.DataBean.ArchivesBean data = bean.getData().getArchives();
                String abouts = data.getAbout();
                Intent intent1 = new Intent(this, FragmentFileMainBrief.class);
                Bundle bundle = new Bundle();
                bundle.putString("about", abouts);
                bundle.putString(" namess", namess);
                intent1.putExtras(bundle);
                startActivity(intent1);
                break;
            case R.id.file_mian_ceo:
                FileMainGson.DataBean.ArchivesBean data2 = bean.getData().getArchives();
                String ceo = data2.getCompany_exe();
                Intent intent2 = new Intent(this, FragmentCEO.class);
                Bundle bundle2 = new Bundle();
                bundle2.putString("ceo", ceo);
                bundle2.putString(" namess", namess);
                intent2.putExtras(bundle2);
                startActivity(intent2);
                break;
            case R.id.file_mian_AIC:
                FileMainGson.DataBean.ArchivesBean data6 = bean.getData().getArchives();
                String companys = data6.getCompany();
                String register_moneys = data6.getRegister_money();
                String paid_money = data6.getPaid_money();
                String icps = data6.getIcp();
                FileMainGson.DataBean.ArchivesInfoBean data7 = bean.getData().getArchives_info();
                String legal_reprens = data7.getLegal_repren();
                String enterprise_types = data7.getEnterprise_type();
                String reg_adds = data7.getReg_add();
                String register_times = data7.getRegister_time();
                String approval = data7.getApproval();
                String register_autn = data7.getRegister_autn();
                String credit_code = data7.getCredit_code();
                Intent intent3 = new Intent(this, FragmentMainGS.class);
                Bundle bundle7 = new Bundle();
                bundle7.putString("companys", companys);
                bundle7.putString("register_moneys", register_moneys);
                bundle7.putString("paid_money", paid_money);
                bundle7.putString("icps", icps);
                bundle7.putString("legal_reprens", legal_reprens);
                bundle7.putString("enterprise_types", enterprise_types);
                bundle7.putString("reg_adds", reg_adds);
                bundle7.putString("register_times", register_times);
                bundle7.putString("approval", approval);
                bundle7.putString("register_autn", register_autn);
                bundle7.putString("credit_code", credit_code);
                bundle7.putString(" namess", namess);
                intent3.putExtras(bundle7);
                startActivity(intent3);
                break;
            case R.id.file_mian_py:
                FileMainGson.DataBean.ArchivesBean data3 = bean.getData().getArchives();
                String recharge_costs = data3.getRecharge_cost();
                String withdraw_costs = data3.getWithdraw_cost();
                String management_costs = data3.getManagement_cost();
                Intent intent4 = new Intent(this, FragmentMainPY.class);
                Bundle bundle3 = new Bundle();
                bundle3.putString("recharge_costs", recharge_costs);
                bundle3.putString("withdraw_costs", withdraw_costs);
                bundle3.putString("management_costs", management_costs);
                bundle3.putString(" namess", namess);
                intent4.putExtras(bundle3);
                startActivity(intent4);
                break;
            case R.id.file_mian_honor:
                FileMainGson.DataBean.ArchivesBean data4 = bean.getData().getArchives();
                String control_evaluations = data4.getControl_evaluation();
                String cast_backgrounds = data4.getCast_background();
                String join_associations = data4.getJoin_association();
                Intent intent5 = new Intent(this, FragmentFileMainHonor.class);
                Bundle bundle4 = new Bundle();
                bundle4.putString("control_evaluation", control_evaluations);
                bundle4.putString("cast_background", cast_backgrounds);
                bundle4.putString("join_association", join_associations);
                bundle4.putString(" namess", namess);
                intent5.putExtras(bundle4);
                startActivity(intent5);
                break;
            case R.id.file_mian_call:
                FileMainGson.DataBean.ArchivesInfoBean dataCall = bean.getData().getArchives_info();
                String company_tels = dataCall.getCompany_tel();
                String company_com = dataCall.getCompany_com();
                String address = dataCall.getAddress();
                Intent intent6 = new Intent(this, FragmentFileMainCall.class);
                Bundle bundle5 = new Bundle();
                bundle5.putString("company_tels", company_tels);
                bundle5.putString("company_com", company_com);
                bundle5.putString("address", address);
                bundle5.putString(" namess", namess);
                intent6.putExtras(bundle5);
                startActivity(intent6);
                break;
        }
    }

    private void initData(final String user_id) {

        long timeStamp = System.currentTimeMillis() / 1000;
        String timestamp = String.valueOf(timeStamp);
        String message = "equipment=" + equipmentId + "&projectId=17&time=" + timestamp + ".K15058835525+.";
        String token = Util.StringToMd5(message);
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("token", token)
                .add("timestamp", timestamp)
                .add("equipmentId", equipmentId)
                .add("id", user_id).build();
        Request request = new Request.Builder().post(body).url(uri).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String resopnseDate = response.body().string();
                    bean = new Gson().fromJson(resopnseDate, FileMainGson.class);
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

}
