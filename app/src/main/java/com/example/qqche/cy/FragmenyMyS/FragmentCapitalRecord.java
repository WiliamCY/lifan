package com.example.qqche.cy.FragmenyMyS;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qqche.cy.Adapter.MyCapitaRecordAdapter;
import com.example.qqche.cy.Class.MyCapitaRecord;
import com.example.qqche.cy.Gson.CaptalRecordGson;
import com.example.qqche.cy.Login.LoginActivity;
import com.example.qqche.cy.R;
import com.example.qqche.cy.Utils.BaseTransparentBarActivity;
import com.example.qqche.cy.Utils.Constant;
import com.example.qqche.cy.Utils.Util;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.xlibs.xrv.LayoutManager.XLinearLayoutManager;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
import okhttp3.ResponseBody;
import qiu.niorgai.StatusBarCompat;

/**
 * Created by Administrator on 2018/4/23.
 */

public class FragmentCapitalRecord extends BaseTransparentBarActivity {
  private TextView toolbarTitle,WiTitleButton;
    @BindView(R.id.mycapitalrecored)
    XRecyclerView mycapitalrecored;
    Unbinder unbinder;
    private MyCapitaRecordAdapter myCapitaRecordAdapter;
    private static  List<MyCapitaRecord> myCapitaRecordList = new ArrayList<>();
    private String uri = Constant.SERVER + "index.php?r=user%2Fuaccountlog";
    private String equipmentId = "8";
    private String user_id, password;
    private Message msg;
    private Handler handler;
    private CaptalRecordGson bean;
    private static  String type_name,money,total,use_money,no_use_money,remark,addtime;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_capital_recored);
        View view = findViewById(R.id.toolbar);
        toolbarTitle = view.findViewById(R.id.toolbar_title);
        WiTitleButton = view.findViewById(R.id.WiTitleButton);
       WiTitleButton.setVisibility(View.GONE);
        toolbarTitle.setText("资金记录");
        StatusBarCompat.translucentStatusBar(this);
        SharedPreferences preferences = this.getSharedPreferences("dara", Context.MODE_PRIVATE);
        user_id = preferences.getString("userId", "");
        password = preferences.getString("password", "");
        if(!user_id.isEmpty() && !password.isEmpty()) {
            initView(user_id, password);
        }else {
            Toast.makeText(getApplicationContext(),"发生异常",Toast.LENGTH_SHORT).show();
        }
        mycapitalrecored = findViewById(R.id.mycapitalrecored);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mycapitalrecored.setLayoutManager(layoutManager);
        mycapitalrecored.setRefreshProgressStyle(ProgressStyle.BallZigZag); //设定下拉刷新样式
        mycapitalrecored.setLoadingMoreProgressStyle(ProgressStyle.BallZigZag);//设定上拉加载样式
        myCapitaRecordAdapter = new MyCapitaRecordAdapter(myCapitaRecordList);
        mycapitalrecored.setAdapter(myCapitaRecordAdapter);
        unbinder = ButterKnife.bind(this);
        handler = new MyHandler(this);
        mycapitalrecored.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                myCapitaRecordList.clear();
                myCapitaRecordAdapter.notifyDataSetChanged();
                initView(user_id, password);
                mycapitalrecored.refreshComplete();
                Toast.makeText(getApplicationContext(),"刷新完成",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLoadMore() {
                myCapitaRecordList.clear();
                myCapitaRecordAdapter.notifyDataSetChanged();
                initView(user_id, password);
                mycapitalrecored.loadMoreComplete();
                Toast.makeText(getApplicationContext(),"加载完成",Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void initView(String user_id,String password) {

            long timeStamp = System.currentTimeMillis() / 1000;
                String timestamp = String.valueOf(timeStamp);
                String message = "equipment=" + equipmentId + "&projectId=17&time=" + timestamp + ".K15058835525+.";
                String token = Util.StringToMd5(message);
                String pwasword = "(*&%" + password + "*&)jzy";
                String pwassMd5 = Util.StringToMd5s(pwasword);
                OkHttpClient client = new OkHttpClient();
                RequestBody body = new FormBody.Builder()
                        .add("tokean", token)
                        .add("timestamp", timestamp)
                        .add("user_id", user_id)
                        .add("password", pwassMd5).build();
                Request request = new Request.Builder().post(body).url(uri).build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (response.isSuccessful()) {
                            String responDate = response.body().string();
                             bean = new Gson().fromJson(responDate,CaptalRecordGson.class);
                            String  status = bean.getStatus();
                            switch (status){
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
    private static  class MyHandler extends Handler{
        private WeakReference<FragmentCapitalRecord> weakReference;
        public  MyHandler(FragmentCapitalRecord activity) {
            weakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        if(weakReference.get() != null){
            switch (msg.what){
                case 1:
                    myCapitaRecordList.clear();
                    weakReference.get().myCapitaRecordAdapter.notifyDataSetChanged();
                    List<CaptalRecordGson.DataBean> lists = weakReference.get().bean.getData();
             for(CaptalRecordGson.DataBean beans : lists){
                             type_name = beans.getType_name();
                             money = beans.getMoney();
                             total = beans.getTotal();
                             use_money = beans.getUse_money();
                             no_use_money = beans.getNo_use_money();
                             remark = beans.getRemark();
                             addtime = beans.getAddtime();
                             MyCapitaRecord  myCapitaRecord = new MyCapitaRecord(type_name,total,money,use_money,no_use_money,remark);
                 myCapitaRecordList.add(myCapitaRecord);
             }
            }
        }
        }
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