package com.example.qqche.cy.FragmenyMyS;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import android.widget.Toast;

import com.example.qqche.cy.Adapter.MyInvestAdapter;
import com.example.qqche.cy.Class.MyInvest;
import com.example.qqche.cy.Gson.ThenderRecordGson;
import com.example.qqche.cy.Main.MainActivity;
import com.example.qqche.cy.R;
import com.example.qqche.cy.Utils.BaseTransparentBarActivity;
import com.example.qqche.cy.Utils.Constant;
import com.example.qqche.cy.Utils.Util;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import qiu.niorgai.StatusBarCompat;

/**
 * Created by Administrator on 2018/4/23.
 */

public class FragmentMyTenderRecord extends BaseTransparentBarActivity {
    @BindView(R.id.xrecyview_invest)
    XRecyclerView xrecyviewInvest;
    Unbinder unbinder;
    private MyInvestAdapter myInvestAdapter;
    private List<MyInvest> myInvestList = new ArrayList<>();
    private String user_id,password;
    private String equipmentId="8";
    private String uri = Constant.SERVER+"index.php?r=user%2Futenderlog";
    private Message msg;
    private  Handler handler;//静态
    private static String logo,money,fanli_num,addtime,logouri;
    private ThenderRecordGson bean;
    private TextView toolbarTitle,WiTitleButton;
    private static  class MyHandler extends Handler{
        private WeakReference<FragmentMyTenderRecord> weakReference;
        public  MyHandler(FragmentMyTenderRecord activity) {
            weakReference = new WeakReference<>(activity);
        }
        @Override
        public void handleMessage(Message msg) {
            if (weakReference.get() != null) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 1:
                        weakReference.get().myInvestList.clear();
                        weakReference.get().myInvestAdapter.notifyDataSetChanged();
                        List<ThenderRecordGson.DataBean> list = weakReference.get().bean.getData();
                       for(ThenderRecordGson.DataBean beans : list){
                           logo = beans.getLogo();
                         money = beans.getMoney();
                         fanli_num = beans.getFanli_num();
                         addtime = beans.getAddtime();
                      logouri = Constant.SERVER+logo;
                           long data = Long.valueOf(addtime) * 1000;
                           SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                           String sd = sdf.format(new Date(Long.parseLong(String.valueOf(data))));
                           MyInvest myInvest = new MyInvest(logouri,money,fanli_num,sd);
                           weakReference.get().myInvestList.add(myInvest);
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
                super.handleMessage(msg);
            }
        }
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_my_invese);
        View toolbar = findViewById(R.id.my_invest);
        View view = findViewById(R.id.toolbar);
        toolbarTitle = view.findViewById(R.id.toolbar_title);
        toolbarTitle.setText("投标记录");
        WiTitleButton = view.findViewById(R.id.WiTitleButton);
        WiTitleButton.setVisibility(View.GONE);
        StatusBarCompat.translucentStatusBar(this);
        xrecyviewInvest = findViewById(R.id.xrecyview_invest);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        xrecyviewInvest.setLayoutManager(layoutManager);
        myInvestAdapter = new MyInvestAdapter(myInvestList);
        StatusBarCompat.translucentStatusBar(this);
        xrecyviewInvest.setAdapter(myInvestAdapter);
        unbinder = ButterKnife.bind(this);
        handler = new MyHandler(this);
        SharedPreferences preferences1 = this.getSharedPreferences("dara", Context.MODE_PRIVATE);
        user_id = preferences1.getString("userId", "");
        password = preferences1.getString("password", "");
        if(!user_id.isEmpty() && !password.isEmpty()){
            initData(user_id,password);
        }


    }

    private void initData(final String user_id, final String password){
                long timeStamp = System.currentTimeMillis() / 1000;
                String timestamp = String.valueOf(timeStamp);
                String message = "equipment=" + equipmentId + "&projectId=17&time=" + timestamp + ".K15058835525+.";
                String token = Util.StringToMd5(message);
                String pwasword = "(*&%" + password + "*&)jzy";
                String pwassMd5 = Util.StringToMd5s(pwasword);
                OkHttpClient client = new OkHttpClient();
                RequestBody body = new FormBody.Builder()
                        .add("token",token)
                        .add("timestamp",timestamp)
                        .add("equipmentId",equipmentId)
                        .add("user_id",user_id)
                        .add("password",pwassMd5).build();
                Request request = new Request.Builder().post(body).url(uri).build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if(response.isSuccessful()){
                            String responDate = response.body().string();
                         bean = new Gson().fromJson(responDate,ThenderRecordGson.class);
                            String status =bean.getStatus();
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