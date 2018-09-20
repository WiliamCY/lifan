package com.example.qqche.cy.FragmentRebate;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qqche.cy.Adapter.RebateMainRequirementAdapter;
import com.example.qqche.cy.Adapter.RebateMessageItemAdapter;
import com.example.qqche.cy.Class.RebateMessageItem;
import com.example.qqche.cy.Class.RebateRequirement;
import com.example.qqche.cy.Gson.RebateMessageGson;
import com.example.qqche.cy.R;
import com.example.qqche.cy.Utils.Constant;
import com.example.qqche.cy.Utils.StatusBarUtilS;
import com.example.qqche.cy.Utils.Util;
import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
 * Created by Administrator on 2018/4/25.
 */

public class FragmentRebateMain extends AppCompatActivity {
    @BindView(R.id.rebate_message_recyview)
    XRecyclerView rebateMessageRecyview;
    Unbinder unbinder;
    @BindView(R.id.toolbar_back)
    ImageView toolbarBack;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.rebate_message_tab1_button)
    LinearLayout rebateMessageTab1Button;
    @BindView(R.id.name)
    TextView names;
    @BindView(R.id.background)
    TextView backgrounds;
    @BindView(R.id.summary)
    TextView summarys;
    @BindView(R.id.safe_level)
    TextView safeLevels;
    @BindView(R.id.apr_pharse)
    TextView aprPharses;
    @BindView(R.id.fanli_pharse)
    TextView fanliPharses;
    @BindView(R.id.product_distribution)
    TextView productDistributions;
    @BindView(R.id.paid_money)
    TextView paidMoneys;
    @BindView(R.id.tender_safe)
    TextView tenderSafes;
    @BindView(R.id.safe_mode)
    TextView safeModes;
    @BindView(R.id.service_lift)
    TextView serviceLifts;

    @BindView(R.id.rv)
    RecyclerView rv;
    private View toolbar, myView;
    private PopupWindow pw;
    private TextView popColse;
    private RebateMainRequirementAdapter mainRequirementAdapter;
    private  List<RebateRequirement> requirements = new ArrayList<>();
    private RebateMessageItemAdapter rebateMessageItemAdapter;
    private List<RebateMessageItem> mrebateMessageItems = new ArrayList<>();
    private String uri = Constant.SERVER + "index.php?r=cooperation%2Fcoinfo";
    private String equipmentId = "8";
    private String user_id, namess;
    private Message msg;
    private RebateMessageGson bean;
    private static String name, background, summary, apr_pharse, fanli_pharse, product_distribution, paid_money, tender_safe, safe_mode, service_lift, safe_level;
    private static String borrow_limit, borrow_limit_type, borrow_apr, fanli_type, fanli_num, retender, invest_area;
    private static String product, money, fanli, redcat, interest, coupon, total, remark;
    private static String online_time, register_money, legal_repren, register_time, company, record_sub, website, about;
    private Handler handler;//静态
    public FragmentActivity activity;
    private static RebateMessageGson.DataBean dataBean;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_rebate_main);
        StatusBarUtil.setTransparent(this);
        StatusBarUtilS.StatusBarLightMode(this);
        unbinder = ButterKnife.bind(this);
        Intent i = getIntent();
        Bundle data = i.getExtras();
        user_id = data.getString("id");
        namess = data.getString("name");
        if (!user_id.isEmpty() && !namess.isEmpty()) {
            iniotView(user_id);
        } else {
            Toast.makeText(getApplicationContext(), "发生异常", Toast.LENGTH_SHORT).show();
        }
        toolbar = findViewById(R.id.file_mian_call);
        toolbarTitle = toolbar.findViewById(R.id.toolbar_title);
        toolbarTitle.setText(namess);
        rv = findViewById(R.id.rv);
        View recyview = findViewById(R.id.rebate_4);
        rebateMessageRecyview = recyview.findViewById(R.id.rebate_message_recyview);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        rebateMessageRecyview.setLayoutManager(layoutManager);
        rebateMessageItemAdapter = new RebateMessageItemAdapter(mrebateMessageItems);
        rebateMessageRecyview.setAdapter(rebateMessageItemAdapter);
        rebateMessageItemAdapter.setOnItemClickListener(new RebateMessageItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                msg = new Message();
                msg.what = 2;
                handler.sendMessage(msg);

            }
        });
        handler = new MyHandler(this);

    }

    private static class MyHandler extends Handler {
        private WeakReference<FragmentRebateMain> weakReference;

        public MyHandler(FragmentRebateMain activity) {
            weakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (weakReference.get() != null) {
                switch (msg.what) {
                    case 1:

                        dataBean = weakReference.get().bean.getData();
                        RebateMessageGson.DataBean.CooperationBean cooperation = dataBean.getCooperation();
                        name = cooperation.getName();
                        background = cooperation.getBackground();
                        summary = cooperation.getSummary();
                        apr_pharse = cooperation.getApr_pharse();
                        fanli_pharse = cooperation.getFanli_pharse();
                        product_distribution = cooperation.getProduct_distribution();
                        paid_money = cooperation.getPaid_money();
                        tender_safe = cooperation.getTender_safe();
                        safe_mode = cooperation.getSafe_mode();
                        service_lift = cooperation.getService_lift();
                        long data = Long.valueOf(service_lift) * 1000;
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        String sd = sdf.format(new Date(Long.parseLong(String.valueOf(data))));
                        safe_level = cooperation.getSafe_level();
                        weakReference.get().names.setText(name);
                        weakReference.get().backgrounds.setText(background);
                        weakReference.get().summarys.setText(summary);
                        weakReference.get().aprPharses.setText(apr_pharse);
                        weakReference.get().fanliPharses.setText(fanli_pharse);
                        weakReference.get().productDistributions.setText(product_distribution);
                        weakReference.get().paidMoneys.setText(paid_money);
                        weakReference.get().tenderSafes.setText(tender_safe);
                        weakReference.get().safeModes.setText(safe_mode);
                        weakReference.get().serviceLifts.setText(sd);
                        weakReference.get().safeLevels.setText("X" + safe_level);
                        List<RebateMessageGson.DataBean.TenderBean> tender = dataBean.getTender();
                        for (RebateMessageGson.DataBean.TenderBean bean : tender) {
                            invest_area = bean.getInvest_area();
                            borrow_limit = bean.getBorrow_limit();
                            borrow_limit_type = bean.getBorrow_limit_type();
                            borrow_apr = bean.getBorrow_apr();
                            fanli_type = bean.getFanli_type();
                            fanli_num = bean.getFanli_num();
                            retender = bean.getRetender();
                            remark = bean.getRemark();
                            RebateRequirement list = new RebateRequirement(borrow_limit,borrow_apr + "%",fanli_type + "%",retender,invest_area,remark);
                            weakReference.get().requirements.add(list);


                        }

                        LinearLayoutManager layoutManagers = new LinearLayoutManager(weakReference.get().getApplicationContext());
                        layoutManagers.setOrientation(LinearLayoutManager.HORIZONTAL);
                        weakReference.get().rv.setLayoutManager(layoutManagers);
                        weakReference.get().mainRequirementAdapter = new RebateMainRequirementAdapter(weakReference.get().requirements);
                        weakReference.get().rv.setAdapter(weakReference.get().mainRequirementAdapter);
                        List<RebateMessageGson.DataBean.RectenderBean> rectender = dataBean.getRectender();
                        for (RebateMessageGson.DataBean.RectenderBean rectenderBeans : rectender) {
                            product = rectenderBeans.getProduct();
                            money = rectenderBeans.getMoney();
                            fanli = rectenderBeans.getFanli();
                            redcat = rectenderBeans.getRedcat();
                            total = rectenderBeans.getTotal();
                            coupon = rectenderBeans.getCoupon();
                            interest = rectenderBeans.getInterest();
                            weakReference.get().rebateMessageItemAdapter.notifyDataSetChanged();
                            RebateMessageItem rebateMessageItem = new RebateMessageItem(product, money);
                            weakReference.get().mrebateMessageItems.add(rebateMessageItem);

                        }
                        break;
                    case 2:
                        weakReference.get().myView = LayoutInflater.from(weakReference.get().getApplicationContext()).inflate(R.layout.fragment_rebate_main_popwindows, null, true);
                        TextView pop_text_close = weakReference.get().myView.findViewById(R.id.pop_text_close);
                        TextView totals = weakReference.get().myView.findViewById(R.id.total);
                        TextView interests = weakReference.get().myView.findViewById(R.id.interest);
                        TextView fanlis = weakReference.get().myView.findViewById(R.id.fanli);
                        TextView coupons = weakReference.get().myView.findViewById(R.id.coupon);
                        TextView redcats = weakReference.get().myView.findViewById(R.id.redcat);
                        totals.setText(total);
                        interests.setText(interest);
                        fanlis.setText(fanli);
                        coupons.setText(coupon);
                        redcats.setText(redcat);
                        weakReference.get().popColse = weakReference.get().myView.findViewById(R.id.pop_text_close);
                        weakReference.get().pw = new PopupWindow(weakReference.get().getApplicationContext());
                        weakReference.get().pw.setContentView(weakReference.get().myView);
                        int popwidth = weakReference.get().getResources().getDisplayMetrics().widthPixels;
                        int popheight = weakReference.get().getResources().getDisplayMetrics().heightPixels;
                        weakReference.get().pw.setWidth(Math.round(popwidth * 0.8f));
                        weakReference.get().pw.setHeight(Math.round(popheight * 0.6f));
                        weakReference.get().pw.setOutsideTouchable(true);
                        weakReference.get().pw.setFocusable(true);
                        ColorDrawable dw = new ColorDrawable(Color.TRANSPARENT);
                        weakReference.get().darkenBackground(0.8f);
                        weakReference.get().pw.setBackgroundDrawable(dw);
                        weakReference.get().pw.showAtLocation(weakReference.get().toolbar, Gravity.CENTER | Gravity.CENTER, 0, 0);
                        weakReference.get().pw.setOnDismissListener(new PopupWindow.OnDismissListener() {
                            @Override
                            public void onDismiss() {
                                weakReference.get().darkenBackground(1f);
                                weakReference.get().pw.dismiss();
                            }
                        });
                        pop_text_close.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                weakReference.get().pw.dismiss();
                            }
                        });

                }
                super.handleMessage(msg);
            }

        }
    }


    private void iniotView(final String user_id) {
        mrebateMessageItems.clear();
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
        Request request = new Request.Builder().url(uri).post(body).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responsebody = response.body().string();
                    bean = new Gson().fromJson(responsebody, RebateMessageGson.class);
                    String status = bean.getStatus();
                    switch (status) {
                        case "1":
                            msg = new Message();
                            msg.what = 1;
                            handler.sendMessage(msg);
                            break;
                    }
                }

            }
        });
    }

    @OnClick({R.id.toolbar_back, R.id.rebate_message_tab1_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbar_back:
                finish();
                break;
            case R.id.rebate_message_tab1_button:
                RebateMessageGson.DataBean.ArchivesBean archivesBean = dataBean.getArchives();
                online_time = archivesBean.getOnline_time();
                register_money = archivesBean.getRegister_money();
                paid_money = archivesBean.getPaid_money();
                company = archivesBean.getCompany();
                website = archivesBean.getWebsite();
                about = archivesBean.getAbout();
                RebateMessageGson.DataBean.ArchivesinfoBean archivesinfoBean = dataBean.getArchivesinfo();
                legal_repren = archivesinfoBean.getLegal_repren();
                register_time = archivesinfoBean.getRegister_time();
                record_sub = archivesinfoBean.getRecord_sub();
                Intent intent = new Intent(this, FragmentRebateMessage.class);
                Bundle bundle = new Bundle();
                bundle.putString("online_time", online_time);
                bundle.putString("register_money", register_money);
                bundle.putString("paid_money", paid_money);
                bundle.putString("legal_repren", legal_repren);
                bundle.putString("register_time", register_time);
                bundle.putString("company", company);
                bundle.putString("record_sub", record_sub);
                bundle.putString("website", website);
                bundle.putString("about", about);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
        }

    }

    private void darkenBackground(Float bgcolor) {
        WindowManager.LayoutParams ip = this.getWindow().getAttributes();
        ip.alpha = bgcolor;
        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        this.getWindow().setAttributes(ip);
    }
}
