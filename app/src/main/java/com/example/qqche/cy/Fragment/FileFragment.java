package com.example.qqche.cy.Fragment;


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
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
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
import com.example.qqche.cy.Class.File;
import com.example.qqche.cy.FragmentFiles.FragmentFileMain;
import com.example.qqche.cy.Gson.FileGson;
import com.example.qqche.cy.R;
import com.example.qqche.cy.Utils.Constant;
import com.example.qqche.cy.Utils.FitStateUI;
import com.example.qqche.cy.Utils.StatusBarUtil;
import com.example.qqche.cy.Utils.StatusBarUtilS;
import com.example.qqche.cy.Utils.Util;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
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
 * Created by Administrator on 2018/4/17.
 */

public class FileFragment extends Fragment {
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
//    @BindView(R.id.toolbar_title)
//    TextView toolbarTitle;
    @BindView(R.id.file_xrecyview)
    XRecyclerView fileXrecyview;
    private PopupWindow pw;
    private ArrayList<String> list;
    private int clickPsition = -1;
    private MotionEvent Event;
    private View myFileToolBar;
    private Animation rotate;
    private FragmentManager manager;
    private FragmentTransaction ft;
    private FileAdapter fileAdapter;
    private List<File> fileList = new ArrayList<>();
    private String uri = Constant.SERVER + "index.php?r=archives%2Fgetarchiveslist";
    private String equipmentId = "8";
    private Message msg;
    private Handler handler;//静态
    public FragmentActivity activity;
    public FileFragment fileFragment;
    private ImageView back;
    private View view1;
    private TextView WiTitleButton,toolbar_title;
    private static List<FileGson.DataBean> message;
    private static String name, company, logo, online_time, depository, paid_money, asset_type, product_limit, apr, debt_support, fileid,id;

    public FileGson getBean() {
        return bean;
    }

    private FileGson bean;


    public static FileFragment newInstance(String param1) {
        FileFragment fragment = new FileFragment();
        Bundle args = new Bundle();
        args.putString("agrs1", param1);
        fragment.setArguments(args);
        return fragment;
    }

    public FileFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_file, container, false);
        manager = getFragmentManager();
        unbinder = ButterKnife.bind(this, view);
        myFileToolBar = view.findViewById(R.id.popList);
         view1 = view.findViewById(R.id.file_toolbar);
        WiTitleButton = view1.findViewById(R.id.WiTitleButton);
        back = view1.findViewById(R.id.toolbar_back);
        toolbar_title = view1.findViewById(R.id.toolbar_title);
        toolbar_title.setText("档案");
        WiTitleButton.setVisibility(View.GONE);
        back.setVisibility(View.GONE);
        rotate = AnimationUtils.loadAnimation(getActivity(), R.anim.rotate);//创建动画
        onTouchEvent(Event);
        initView();
        if(Build.VERSION.SDK_INT> Build.VERSION_CODES.KITKAT){
            WindowManager.LayoutParams params = getActivity().getWindow().getAttributes();
            params.flags= (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS|params.flags);
        }
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 1);
        fileXrecyview.setLayoutManager(layoutManager);
        fileXrecyview.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        fileXrecyview.setLoadingMoreProgressStyle(ProgressStyle.SquareSpin);
        fileAdapter = new FileAdapter(fileList);
        fileXrecyview.setAdapter(fileAdapter);
        fileAdapter.setOnItemClickListener(new FileAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                String name = fileList.get(position-1).getFileTitle();
                String id = fileList.get(position-1).getId();
                Intent intent = new Intent(getActivity(), FragmentFileMain.class);
                Bundle bundle = new Bundle();
                bundle.putString("userId", id);
                bundle.putString("name",name);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });
        handler = new MyHandler(getActivity(), this);
        activity = getActivity();
        fileXrecyview.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                fileList.clear();
                fileAdapter.notifyDataSetChanged();
                initView();
                fileXrecyview.refreshComplete();
                Toast.makeText(getActivity(),"刷新完成",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLoadMore() {
                fileList.clear();
                fileAdapter.notifyDataSetChanged();
                initView();
                fileXrecyview.loadMoreComplete();
                Toast.makeText(getActivity(),"加载完成",Toast.LENGTH_SHORT).show();
            }
        });


        return view;
    }

    private static class MyHandler extends Handler {
        private WeakReference<FragmentActivity> weakReference;
        private WeakReference<FileFragment> weakReferences;

        public MyHandler(FragmentActivity fragmentActivity, FileFragment fileFragment) {
            weakReference = new WeakReference<>(fragmentActivity);
            weakReferences = new WeakReference<>(fileFragment);
        }

        @Override
        public void handleMessage(Message msg) {
            if (weakReferences.get() != null) {
                switch (msg.what) {
                    case 1:
                        weakReferences.get().fileList.clear();
                        weakReferences.get().fileAdapter.notifyDataSetChanged();
                        message = weakReferences.get().getBean().getData();
                        for (FileGson.DataBean about : message) {
                            name = about.getName();
                            company = about.getCompany();
                            logo = about.getLogo();
                            online_time = about.getOnline_time();
                            depository = about.getDepository();
                            paid_money = about.getPaid_money();
                            asset_type = about.getAsset_type();
                            product_limit = about.getProduct_limit();
                            id = about.getId();
                            String uri = Constant.SERVER + logo;
                            fileid = about.getId();
                            File file = new File(name, company, depository, asset_type, product_limit, paid_money, online_time, uri,id);
                            weakReferences.get().fileList.add(file);

                        }


                }
            }
            super.handleMessage(msg);
        }
    }

    private void initView() {
        initdepository(depository);
        String d = initdepository(depository);
        initasset_type(asset_type);
        String a = initasset_type(asset_type);
        initdebt_support(debt_support);
        String su = initdebt_support(debt_support);
        initapr(apr);
        String aprs = initapr(apr);
        long timeStamp = System.currentTimeMillis() / 1000;
        String timestamp = String.valueOf(timeStamp);
        String message = "equipment=" + equipmentId + "&projectId=17&time=" + timestamp + ".K15058835525+.";
        String token = Util.StringToMd5(message);
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("token", token)
                .add("timestamp", timestamp)
                .add("equipmentId", equipmentId)
                .add("depository", d)
                .add("asset_type", a)
                .add("debt_support", su)
                .add("apr", aprs).build();
        Request request = new Request.Builder().post(body).url(uri).build();
        try {
            Response re = client.newCall(request).execute();
            String bpdy = re.body().string();
            System.out.print(bpdy);
        } catch (Exception e) {
            e.printStackTrace();
        }
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                String c = e.toString();
                System.out.print(c);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String resopnbody = response.body().string();
                    System.out.print(resopnbody);
                    if (!resopnbody.isEmpty()) {
                        bean = new Gson().fromJson(resopnbody, FileGson.class);
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

                        }

                    }
                }
            }
        });
    }

    private String initdepository(String depository) {
        String d = popText1.getText().toString();
        if (d.equals("江西银行")) {
            return "jiangxi";
        } else if (d.equals("上海银行")) {
            return "shanghai";
        } else if (d.equals("恒丰银行")) {
            return "hengfeng";
        } else if (d.equals("平安银行")) {
            return "pingan";
        } else if (d.equals("其它")) {
            return "other";
        } else {
            return "";
        }

    }

    private String initasset_type(String asset_type) {
        String a = popText2.getText().toString();
        if (a.equals("房贷")) {
            return "1";
        } else if (a.equals("车贷")) {
            return "2";
        } else if (a.equals("信贷")) {
            return "3";
        } else if (a.equals("其它")) {
            return "4";
        } else {
            return "";
        }
    }

    private String initdebt_support(String initdebt_support) {
        String s = popText3.getText().toString();
        if (s.equals("支持")) {
            return "yes";
        } else if (s.equals("不支持")) {
            return "no";
        } else {
            return "";
        }
    }

    private String initapr(String apr) {
        String aprs = popText4.getText().toString();
        if (aprs.equals("1%-10%")) {
            return "1-10";
        } else if (aprs.equals("11%-15%")) {
            return "11-15";
        } else if (aprs.equals("16%-20%")) {
            return "16-20";
        } else if (aprs.equals("20%以上")) {
            return "20-999";
        } else {
            return "";
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
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
                pw.showAsDropDown(myFileToolBar);
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
                pw.showAsDropDown(myFileToolBar);
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
                pw.showAsDropDown(myFileToolBar);
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
                                           }
                );
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
                pw.showAsDropDown(myFileToolBar);
                pw.update();
                ListView lv4 = myView4.findViewById(R.id.lv_pop);
                lv4.setAdapter(new ListViewAdapter(getActivity(), list));
                lv4.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        popText4.setText(list.get(position));
                        if (clickPsition != position) {
                            clickPsition = position;

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
        list.add("江西银行");
        list.add("上海银行");
        list.add("恒丰银行");
        list.add("平安银行");
        list.add("其它");
        return list;
    }

    private ArrayList<String> getList2() {
        ArrayList<String> list = new ArrayList<String>();
        list.add("全部");
        list.add("房贷");
        list.add("车贷");
        list.add("信贷");
        list.add("其它");
        return list;
    }

    private ArrayList<String> getList3() {
        ArrayList<String> list = new ArrayList<String>();
        list.add("全部");
        list.add("支持");
        list.add("不支持");
        return list;
    }


    private ArrayList<String> getList4() {
        ArrayList<String> list = new ArrayList<String>();
        list.add("全部");
        list.add("1%-10%");
        list.add("11%-15%");
        list.add("16%-20%");
        list.add("20%以上");
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
        unbinder.unbind();
    }
}
