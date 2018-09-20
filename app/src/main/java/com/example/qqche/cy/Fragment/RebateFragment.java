package com.example.qqche.cy.Fragment;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qqche.cy.Adapter.FileAdapter;
import com.example.qqche.cy.Adapter.ListViewAdapter;
import com.example.qqche.cy.Adapter.RebateAdapter;
import com.example.qqche.cy.Class.Commend;
import com.example.qqche.cy.Class.Rebate;
import com.example.qqche.cy.FragmentInfomation.FragmentCommend;
import com.example.qqche.cy.FragmentRebate.FragmentRebateMain;
import com.example.qqche.cy.Gson.RebateGson;
import com.example.qqche.cy.R;
import com.example.qqche.cy.Utils.Constant;
import com.example.qqche.cy.Utils.StatusBarUtilS;
import com.example.qqche.cy.Utils.Util;
import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.zhy.android.percent.support.PercentLinearLayout;

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
import qiu.niorgai.StatusBarCompat;


/**
 * Created by Administrator on 2018/4/17.
 */

public class RebateFragment extends Fragment {
    @BindView(R.id.popw1)
    LinearLayout popw1;
    @BindView(R.id.popw2)
    LinearLayout popw2;
    @BindView(R.id.popw3)
    LinearLayout popw3;
    @BindView(R.id.popw4)
    LinearLayout popw4;
    Unbinder unbinder;
    @BindView(R.id.popText1)
    TextView popText1;
    @BindView(R.id.popText2)
    TextView popText2;
    @BindView(R.id.popText3)
    TextView popText3;
    @BindView(R.id.popText4)
    TextView popText4;
    @BindView(R.id.ic1)
    ImageView ic1;
    @BindView(R.id.ic2)
    ImageView ic2;
    @BindView(R.id.ic3)
    ImageView ic3;
    @BindView(R.id.ic4)
    ImageView ic4;
    @BindView(R.id.rebateXrecyview)
    XRecyclerView rebateXrecyview;
    private PopupWindow pw;
    private ArrayList<String> list;
    private int clickPsition = -1;
    private MotionEvent Event;
    private View myFileToolBar;
    private Animation rotate;
    private RebateAdapter rebateAdapter;
    private List<Rebate> rebateList = new ArrayList<>();
    private FragmentManager manager;
    private FragmentTransaction ft;
    private String equipmentId = "8";
    private String uri = Constant.SERVER + "index.php?r=cooperation%2Fgetcooperationlist";
    private Message msg;
    private static String name, summary, apr_pharse, fanli_pharse, product_distribution, paid_money, tender_safe, safe_mode, service_lift;
    private String tender_limit_s, tender_limit_e, borrow_limit_type, borrow_apr, fanli_type, fanli_num, invest_area, ivnest_term, fanli_money;
    private String product, money, fanli, redcat, interest, coupon, total;
    private String online_time, register_money, company, website, about;
    private String legal_repren, operating_state, register_time, record_sub;
    private static String logo, etime, id;
    private static String borrow_limit, retender, background, safe_level;
    private ImageView back;
    private TextView WiTitleButton, toolbar_title;
    private View view1,view2;
    private RebateGson rebateGson;
    private Handler handler;//静态
    public FragmentActivity activity;
    public RebateFragment rebateFragment;

    public static RebateFragment newInstance(String param1) {
        RebateFragment fragment = new RebateFragment();
        Bundle args = new Bundle();
        args.putString("agrs1", param1);
        fragment.setArguments(args);
        return fragment;
    }

    public RebateFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("ResourceAsColor")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rebate, container, false);
         view2 = view.findViewById(R.id.popList);
        manager = getFragmentManager();
        unbinder = ButterKnife.bind(this, view);
        initView();
        view1 = view.findViewById(R.id.rebate_toolbar);
        WiTitleButton = view1.findViewById(R.id.WiTitleButton);
        back = view1.findViewById(R.id.toolbar_back);
        toolbar_title = view1.findViewById(R.id.toolbar_title);
        toolbar_title.setText("返利");
        WiTitleButton.setVisibility(View.GONE);
        back.setVisibility(View.GONE);
        rebateXrecyview = view.findViewById(R.id.rebateXrecyview);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 1);
        rebateXrecyview.setLayoutManager(layoutManager);
        rebateXrecyview.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        rebateXrecyview.setLoadingMoreProgressStyle(ProgressStyle.SquareSpin);
        rebateAdapter = new RebateAdapter(rebateList);
        rebateXrecyview.setAdapter(rebateAdapter);
        rotate = AnimationUtils.loadAnimation(getActivity(), R.anim.rotate);//创建动画
        rebateAdapter.setOnItemClickListener(new FileAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                String id = rebateList.get(position - 1).getId();
                String name = rebateList.get(position - 1).getRebate_name();
                Intent intent = new Intent(getActivity(), FragmentRebateMain.class);
                Bundle bundle = new Bundle();
                bundle.putString("id", id);
                bundle.putString("name", name);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        onTouchEvent(Event);
        handler = new MyHandler(getActivity(), this);
        activity = getActivity();
        rebateXrecyview.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                rebateList.clear();
                rebateAdapter.notifyDataSetChanged();
                initView();
                rebateXrecyview.refreshComplete();
                Toast.makeText(getActivity(),"刷新完成",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLoadMore() {
                rebateList.clear();
                rebateAdapter.notifyDataSetChanged();
                initView();
                rebateXrecyview.loadMoreComplete();
                Toast.makeText(getActivity(),"加载完成",Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private static class MyHandler extends Handler {
        private WeakReference<FragmentActivity> weakReference;
        private WeakReference<RebateFragment> weakReferences;

        public MyHandler(FragmentActivity fragmentActivity, RebateFragment fragmentCommend) {
            weakReference = new WeakReference<>(fragmentActivity);
            weakReferences = new WeakReference<>(fragmentCommend);
        }

        @Override
        public void handleMessage(Message msg) {
            if (weakReferences.get() != null) {
                switch (msg.what) {
                    case 1:
                        weakReferences.get().rebateList.clear();
                        weakReferences.get().rebateAdapter.notifyDataSetChanged();
                        List<RebateGson.DataBean> bean = weakReferences.get().rebateGson.getData();
                        for (RebateGson.DataBean beans : bean) {
                            name = beans.getName();
                            logo = beans.getLogo();
                            apr_pharse = beans.getApr_pharse();
                            fanli_pharse = beans.getFanli_pharse();
                            service_lift = beans.getService_lift();
                            etime = beans.getEtime();
                            paid_money = beans.getPaid_money();
                            product_distribution = beans.getProduct_distribution();
                            summary = beans.getSummary();
                            tender_safe = beans.getTender_safe();
                            safe_mode = beans.getSafe_mode();
                            service_lift = beans.getService_lift();
                            id = beans.getId();
                            String imageUri = Constant.SERVER + logo;
                            long data = Long.valueOf(service_lift) * 1000;
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            String sd = sdf.format(new Date(Long.parseLong(String.valueOf(data))));
                            Rebate commend = new Rebate(apr_pharse, name, "X" + safe_level, product_distribution, fanli_pharse, sd, imageUri, id);
                            weakReferences.get().rebateList.add(commend);
                            }
                            break;
                    case 2:
                        Toast toast1 = Toast.makeText(weakReference.get(), "参数错误", Toast.LENGTH_SHORT);
                        Util.showMyToast(toast1, 500);
                        break;
                    case 3:
                        Toast toast2 = Toast.makeText(weakReference.get(), "非法访问", Toast.LENGTH_SHORT);
                        Util.showMyToast(toast2, 500);
                        break;
                }
            }
            super.handleMessage(msg);
        }
    }

    private void initView() {
        initretender(retender);
        String a = initretender(retender);
        initsafelevel(safe_level);
        String b = initsafelevel(safe_level);
        initBackground(background);
        String c = initBackground(background);
        initborrowlimit(borrow_limit);
        String d = initborrowlimit(borrow_limit);
        long timeStamp = System.currentTimeMillis() / 1000;
        String timestamp = String.valueOf(timeStamp);
        String message = "equipment=" + equipmentId + "&projectId=17&time=" + timestamp + ".K15058835525+.";
        String token = Util.StringToMd5(message);
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("token", token)
                .add("timestamp", timestamp)
                .add("equipmentId", "8")
                .add("borrow_limit", d)
                .add("retender", a)
                .add("safe_level", b)
                .add("background", c).build();
        Request request = new Request.Builder().post(body).url(uri).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseDate = response.body().string();
                    rebateGson = new Gson().fromJson(responseDate, RebateGson.class);
                    switch (rebateGson.getStatus()) {
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

    private String initsafelevel(String safe_level) {
        String s = popText2.getText().toString();
        if (s.equals("X9")) {
            return "9";
        } else if (s.equals("X8")) {
            return "8";
        } else if (s.equals("X7")) {
            return "7";
        } else if (s.equals("X6")) {
            return "6";
        } else if (s.equals("X5")) {
            return "5";
        } else {
            return "";
        }
    }

    private String initretender(String retender) {
        String c = popText1.getText().toString();
        if (c.equals("支持")) {
            return "1";
        } else if (c.equals("不支持")) {
            return "0";
        } else {
            return "";
        }
    }

    private String initBackground(String background) {
        String b = popText3.getText().toString();
        if (b.equals("上市系")) {
            return "1";
        } else if (b.equals("风投系")) {
            return "2";
        } else if (b.equals("民营系")) {
            return "3";
        } else if (b.equals("国资系")) {
            return "4";
        } else {
            return "";
        }

    }

    private String initborrowlimit(String borrow_limit) {
        String d = popText4.getText().toString();
        if (d.equals("1个月以内")) {
            return "1";
        } else if (d.equals("1-3月标")) {
            return "2";
        } else if (d.equals("4-6月标")) {
            return "3";
        } else if (d.equals("7-12月标")) {
            return "4";
        } else if (d.equals("12月以上")) {
            return "5";
        } else {
            return "";
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        handler.removeCallbacksAndMessages(null);
        unbinder.unbind();
    }

    @OnClick({R.id.popw1, R.id.popw2, R.id.popw3, R.id.popw4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.popw1:
                list = getList1();
                ic1.animate().setDuration(1000).rotation(180).start();
                View myView = getActivity().getLayoutInflater().inflate(R.layout.pop, null);
                pw = new PopupWindow(myView, ViewGroup.LayoutParams.MATCH_PARENT, 700, true);
                pw.setFocusable(true);
                pw.setOutsideTouchable(true);
                ColorDrawable dw = new ColorDrawable(getActivity().getResources().getColor(R.color.white));
                pw.setBackgroundDrawable(dw);
                pw.showAsDropDown(view2);
                pw.update();
                ListView lv = myView.findViewById(R.id.lv_pop);
                lv.setAdapter(new ListViewAdapter(getActivity(), list));
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        popText1.setText(list.get(position));
                        if (clickPsition != position) {
                            clickPsition = position;
                            initView();

                        }
                        pw.dismiss();
                        ic1.animate().setDuration(1000).rotation(0).start();
                    }
                });
                pw.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        ic1.animate().setDuration(1000).rotation(0).start();
                    }
                });
                break;
            case R.id.popw2:
                list = getList2();
                ic2.animate().setDuration(1000).rotation(180).start();
                View myView2 = getActivity().getLayoutInflater().inflate(R.layout.pop, null);
                pw = new PopupWindow(myView2, ViewGroup.LayoutParams.MATCH_PARENT, 700, true);
                pw.setFocusable(true);
                pw.setOutsideTouchable(true);
                ColorDrawable dw2 = new ColorDrawable(getActivity().getResources().getColor(R.color.white));
                pw.setBackgroundDrawable(dw2);
                pw.showAsDropDown(view2);
                pw.update();
                ListView lv2 = myView2.findViewById(R.id.lv_pop);
                lv2.setAdapter(new ListViewAdapter(getActivity(), list));
                lv2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        popText2.setText(list.get(position));
                        if (clickPsition != position) {
                            clickPsition = position;
                            initView();

                        }
                        pw.dismiss();
                        ic2.animate().setDuration(1000).rotation(0).start();
                    }
                });
                pw.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        ic2.animate().setDuration(1000).rotation(0).start();
                    }
                });
                break;
            case R.id.popw3:
                list = getList3();
                ic3.animate().setDuration(1000).rotation(180).start();
                View myView3 = getActivity().getLayoutInflater().inflate(R.layout.pop, null);
                pw = new PopupWindow(myView3, ViewGroup.LayoutParams.MATCH_PARENT, 700, true);
                pw.setFocusable(true);
                ColorDrawable dw3 = new ColorDrawable(getActivity().getResources().getColor(R.color.white));
                pw.setBackgroundDrawable(dw3);
                pw.setOutsideTouchable(true);
                pw.showAsDropDown(view2);
                pw.update();
                ListView lv3 = myView3.findViewById(R.id.lv_pop);
                lv3.setAdapter(new ListViewAdapter(getActivity(), list));
                lv3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        popText3.setText(list.get(position));
                        if (clickPsition != position) {
                            clickPsition = position;
                            initView();

                        }
                        pw.dismiss();
                        ic3.animate().setDuration(1000).rotation(0).start();
                    }
                });
                pw.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        ic3.animate().setDuration(1000).rotation(0).start();
                    }
                });
                break;
            case R.id.popw4:
                list = getList4();
                ic4.animate().setDuration(1000).rotation(180).start();
                View myView4 = getActivity().getLayoutInflater().inflate(R.layout.pop, null);
                pw = new PopupWindow(myView4, ViewGroup.LayoutParams.MATCH_PARENT, 700, true);
                pw.setFocusable(true);
                pw.setOutsideTouchable(true);
                ColorDrawable dw4 = new ColorDrawable(getActivity().getResources().getColor(R.color.white));
                pw.setBackgroundDrawable(dw4);
                pw.showAsDropDown(view2);
                pw.update();
                ListView lv4 = myView4.findViewById(R.id.lv_pop);
                lv4.setAdapter(new ListViewAdapter(getActivity(), list));
                lv4.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        popText4.setText(list.get(position));
                        if (clickPsition != position) {
                            clickPsition = position;
                            initView();

                        }
                        pw.dismiss();
                        ic4.animate().setDuration(1000).rotation(0).start();
                    }
                });
                pw.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        ic4.animate().setDuration(1000).rotation(0).start();
                    }
                });
                break;
        }
    }

    private ArrayList<String> getList1() {
        ArrayList<String> list = new ArrayList<String>();
        list.add("全部");
        list.add("支持");
        list.add("不支持");
        return list;
    }

    private ArrayList<String> getList2() {
        ArrayList<String> list = new ArrayList<String>();
        list.add("全部");
        list.add("x9");
        list.add("x8");
        list.add("x7");
        list.add("x6");
        list.add("x5");
        return list;
    }

    private ArrayList<String> getList3() {
        ArrayList<String> list = new ArrayList<String>();
        list.add("全部");
        list.add("上市系");
        list.add("风投系");
        list.add("民营系");
        list.add("国资系");
        list.add("其它");
        return list;
    }

    private ArrayList<String> getList4() {
        ArrayList<String> list = new ArrayList<String>();
        list.add("全部");
        list.add("1个月内");
        list.add("1-3月标");
        list.add("4-6月标");
        list.add("12月标以上");
        return list;
    }

    public boolean onTouchEvent(MotionEvent event) {
// TODO Auto-generated method stub
        if (pw != null && pw.isShowing()) {
            pw.dismiss();
            pw = null;
        }
        return super.getActivity().onTouchEvent(event);
    }

}
