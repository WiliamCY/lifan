package com.example.qqche.cy.FragmenyMyS;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qqche.cy.Adapter.ExpandFoldTextAdapter;
import com.example.qqche.cy.Adapter.ExpandFoldTextBean;
import com.example.qqche.cy.Class.MyTime;
import com.example.qqche.cy.Gson.LoginUserMessage;
import com.example.qqche.cy.Gson.TimeAxisGson;
import com.example.qqche.cy.Login.LoginActivity;
import com.example.qqche.cy.R;
import com.example.qqche.cy.Utils.Constant;
import com.example.qqche.cy.Utils.StatusBarUtilS;
import com.example.qqche.cy.Utils.Util;
import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;

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
 * Created by Administrator on 2018/5/3.
 */

public class FragmetnTimeAxis extends AppCompatActivity implements ExpandFoldTextAdapter.OnItemClickListener {
    @BindView(R.id.btn_editor)
    ImageView myTimeDelete;
    @BindView(R.id.my_time_cancel)
    TextView myTimeCancel;
    @BindView(R.id.my_time_layout1)
    LinearLayout myTimeLayout1;
    @BindView(R.id.select_all)
    TextView selectAll;
    @BindView(R.id.btn_delete)
    TextView mBtnDelete;
    @BindView(R.id.toolbar_back)
    ImageView toolbarBack;
    private RecyclerView myTimeXrecyview;
    Unbinder unbinder;
    private ExpandFoldTextAdapter adapter = null;
    private boolean isSelectAll = false;
    private boolean editorStatus = false;
    private int index = 0;
    private ArrayList<ExpandFoldTextBean> myTimeList = new ArrayList<>();
    private String uri = Constant.SERVER + "index.php?r=user%2Fumessage&";
    private String user_id, password;
    private String equipmentId = "8";
    private Handler handler;//静态
    private Message msg;
    private TimeAxisGson bean;
    private static String addtime, content,mytime;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_my_main_timeasix);
        StatusBarUtil.setTransparent(this);
        StatusBarUtilS.StatusBarLightMode(this);
        unbinder = ButterKnife.bind(this);
        SharedPreferences preferences1 = this.getSharedPreferences("dara", Context.MODE_PRIVATE);
        user_id = preferences1.getString("userId", "");
        password = preferences1.getString("password", "");
        if (user_id.isEmpty() && password.isEmpty()) {
            Toast.makeText(getApplicationContext(), "发生异常", Toast.LENGTH_SHORT).show();
        } else {
            initDate(user_id, password);
        }
        View vie1 = findViewById(R.id.my_time_toolbar);
        myTimeDelete = vie1.findViewById(R.id.btn_editor);
        myTimeXrecyview = findViewById(R.id.my_time_xrecyview);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        myTimeXrecyview.setLayoutManager(layoutManager);
        adapter = new ExpandFoldTextAdapter(myTimeList, this);
        myTimeXrecyview.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
        handler = new MyHandler(this);
    }

    private static class MyHandler extends Handler {
        private WeakReference<FragmetnTimeAxis> weakReference;

        public MyHandler(FragmetnTimeAxis activity) {
            weakReference = new WeakReference<>(activity);

        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (weakReference.get() != null) {
                switch (msg.what) {
                    case 1:
                        ExpandFoldTextBean expandFoldTextBean = new ExpandFoldTextBean();
                        List<TimeAxisGson.DataBean> list = weakReference.get().bean.getData();
                        for (TimeAxisGson.DataBean lists : list) {
                            addtime = lists.getAddtime();
                            content = lists.getContent();
                            long data = Long.valueOf(addtime) * 1000;
                            SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日 HH:mm");
                            String sd = sdf.format(new Date(Long.parseLong(String.valueOf(data))));

                            long timeStamp = System.currentTimeMillis() / 1000;
                            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
                            String sd2 = sdf2.format(new Date(Long.parseLong(String.valueOf(timeStamp))));

                            long data3 = Long.valueOf(addtime) * 1000;
                            SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd");
                            String sd3 = sdf3.format(new Date(Long.parseLong(String.valueOf(data3))));
                            if(sd2.equals(sd3)){
                             expandFoldTextBean.setMy_time("今");
                            }else {
                                long data4 = Long.valueOf(addtime) * 1000;
                                SimpleDateFormat sdf4 = new SimpleDateFormat("dd");
                                String sd4 = sdf4.format(new Date(Long.parseLong(String.valueOf(data4))));
                                expandFoldTextBean.setMy_time(sd4);
                            }
                            weakReference.get().myTimeList.clear();
                                 weakReference.get().adapter.notifyDataSetChanged();
                         expandFoldTextBean.setTv_nickname(sd);
                         expandFoldTextBean.setContent(content);
                         weakReference.get().myTimeList.add(expandFoldTextBean);
                         weakReference.get().adapter.notifyAdapter(weakReference.get().myTimeList,false);
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
        }
    }

    @OnClick({R.id.btn_editor, R.id.my_time_cancel, R.id.select_all, R.id.btn_delete, R.id.toolbar_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_editor:
                myTimeCancel.setVisibility(View.VISIBLE);
                myTimeDelete.setVisibility(View.GONE);
                adapter.isChangeImage(1);
                myTimeLayout1.setVisibility(View.VISIBLE);
                break;
            case R.id.my_time_cancel:
                myTimeCancel.setVisibility(View.GONE);
                myTimeDelete.setVisibility(View.VISIBLE);
                adapter.isChangeImage(0);
                myTimeLayout1.setVisibility(View.GONE);
                break;
            case R.id.select_all:
                selectAllMain();
                break;
            case R.id.btn_delete:
                deleteVideo();
                break;
            case R.id.toolbar_back:
                finish();
                unbinder.unbind();
        }
    }

    private void initDate(String user_id, String password) {
        long timeStamp = System.currentTimeMillis() / 1000;
        String timestamp = String.valueOf(timeStamp);
        String message = "equipment=" + equipmentId + "&projectId=17&time=" + timestamp + ".K15058835525+.";
        String token = Util.StringToMd5(message);
        String pwasword = "(*&%" + password + "*&)jzy";
        String pwassMd5 = Util.StringToMd5s(pwasword);
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("token", token)
                .add("equipmentId", equipmentId)
                .add("timestamp", timestamp)
                .add("user_id", user_id)
                .add("password", pwassMd5).build();
        Request request = new Request.Builder().post(requestBody).url(uri).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String c = response.body().string();
                    bean = new Gson().fromJson(c, TimeAxisGson.class);
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

    private void selectAllMain() {
        if (adapter == null) return;
        if (!isSelectAll) {
            for (int i = 0, j = adapter.getMyLiveList().size(); i < j; i++) {
                adapter.getMyLiveList().get(i).setSelect(true);
            }
            index = adapter.getMyLiveList().size();
            mBtnDelete.setEnabled(true);
            selectAll.setText("取消全选");
            isSelectAll = true;
        } else {
            for (int i = 0, j = adapter.getMyLiveList().size(); i < j; i++) {
                adapter.getMyLiveList().get(i).setSelect(false);
            }
            index = 0;
            mBtnDelete.setEnabled(false);
            selectAll.setText("全选");
            isSelectAll = false;
        }
        adapter.notifyDataSetChanged();
        setBtnBackground(index);
    }


    private void deleteVideo() {
        if (index == 0) {
            mBtnDelete.setEnabled(false);
            return;
        }
        final AlertDialog builder = new AlertDialog.Builder(this)
                .create();
        builder.show();
        if (builder.getWindow() == null) return;
        builder.getWindow().setContentView(R.layout.pop_user);
        TextView msg = builder.findViewById(R.id.tv_msg);
        Button cancle = builder.findViewById(R.id.btn_cancle);
        Button sure = builder.findViewById(R.id.btn_sure);
        if (msg == null || cancle == null || sure == null) return;

        if (index == 1) {
            msg.setText("删除后不可恢复，是否删除该条目？");
        } else {
            msg.setText("删除后不可恢复，是否删除这" + index + "个条目？");
        }
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.dismiss();
            }
        });
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = adapter.getMyLiveList().size(), j = 0; i > j; i--) {
                    ExpandFoldTextBean expandFoldTextBean = adapter.getMyLiveList().get(i - 1);
                    if (expandFoldTextBean.isSelect()) {
                        adapter.getMyLiveList().remove(expandFoldTextBean);
                        index--;
                    }
                }
                index = 0;
                setBtnBackground(index);
                if (adapter.getMyLiveList().size() == 0) {
                    myTimeLayout1.setVisibility(View.GONE);
                }
                adapter.notifyDataSetChanged();
                builder.dismiss();
            }
        });
    }

    private void setBtnBackground(int size) {
        if (size != 0) {
            mBtnDelete.setTextColor(this.getResources().getColor(R.color.orgin));
            mBtnDelete.setEnabled(true);
        } else {
            mBtnDelete.setEnabled(false);

            mBtnDelete.setTextColor(ContextCompat.getColor(this, R.color.color_b7b8bd));
        }
    }


    @Override
    public void onItemClickListener(int pos, List<ExpandFoldTextBean> myLiveList) {
        if (!editorStatus) {
            ExpandFoldTextBean expandFoldTextBean = myLiveList.get(pos);
            boolean isSelect = expandFoldTextBean.isSelect();
            if (!isSelect) {
                index++;
                expandFoldTextBean.setSelect(true);
                if (index == myLiveList.size()) {
                    isSelectAll = true;
                    selectAll.setText("全选");
                }
            } else {
                expandFoldTextBean.setSelect(false);
                index--;
                isSelectAll = false;
                selectAll.setText("取消全选");
            }
            setBtnBackground(index);
            adapter.notifyDataSetChanged();
        }
    }
}
