package com.example.qqche.cy.FragmentDocument;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.widget.Toast;

import com.example.qqche.cy.Adapter.DocumentAdapter;
import com.example.qqche.cy.Class.Document;
import com.example.qqche.cy.FragmenyMyS.FragmentBankWi;
import com.example.qqche.cy.Gson.FragmentDocumentOneGson;
import com.example.qqche.cy.Login.LoginActivity;
import com.example.qqche.cy.R;
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
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import qiu.niorgai.StatusBarCompat;

/**
 * Created by Administrator on 2018/5/15.
 */

public class FragmentDocuemntOne extends AppCompatActivity {


    @BindView(R.id.document_xrecyview)
    XRecyclerView documentXrecyview;
    private DocumentAdapter documentAdapter;
    private static List<Document> list = new ArrayList<>();
    private String uri = Constant.SERVER + "index.php?r=user%2Futenderselflist";
    private String user_id, password;
    private String equipmentId = "8";
    private Message msg;
    private Handler handler;//静态
    private FragmentDocumentOneGson bean;
    private static String platform_name, money, tender_time, pic, addtime;
    private static String picUri, time, times;

    private static class MyHandler extends Handler {
        private WeakReference<FragmentDocuemntOne> weakReference;

        public MyHandler(FragmentDocuemntOne activity) {
            weakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (weakReference.get() != null) {
                switch (msg.what) {
                    case 1:
                        weakReference.get().list.clear();
                               weakReference.get().documentAdapter.notifyDataSetChanged();
                        List<FragmentDocumentOneGson.DataBean> listc = weakReference.get().bean.getData();
                        for (FragmentDocumentOneGson.DataBean lists : listc) {
                            platform_name = lists.getPlatform_name();
                            money = lists.getMoney();
                            tender_time = lists.getTender_time();
                            pic = lists.getPic();
                            addtime = lists.getAddtime();
                            picUri = Constant.SERVER + pic;
                            long data = Long.valueOf(addtime) * 1000;
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            time = sdf.format(new Date(Long.parseLong(String.valueOf(data))));
                            long data2 = Long.valueOf(tender_time) * 1000;
                            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
                            times = sdf2.format(new Date(Long.parseLong(String.valueOf(data2))));
                            Document documents = new Document(picUri, time, platform_name, money, times);
                            list.add(documents);

                        }
                        break;
                    case 2:
                        Toast.makeText(weakReference.get(), "数据错误", Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        Toast.makeText(weakReference.get(), "非法访问", Toast.LENGTH_SHORT).show();
                        break;

                }
            }
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.documentone);
        StatusBarCompat.translucentStatusBar(this);
        ButterKnife.bind(this);
        SharedPreferences preferences = this.getSharedPreferences("dara", Context.MODE_PRIVATE);
        user_id = preferences.getString("userId", "");
        password = preferences.getString("password", "");
        if (!user_id.isEmpty() && !password.isEmpty()) {
            initView(user_id, password);
        } else {
            Toast.makeText(getApplicationContext(), "发生异常", Toast.LENGTH_SHORT).show();
        }
        documentXrecyview = findViewById(R.id.document_xrecyview);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        documentXrecyview.setLayoutManager(layoutManager);
        documentAdapter = new DocumentAdapter(list);
        documentXrecyview.setAdapter(documentAdapter);
        handler = new MyHandler(this);
        documentXrecyview.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                list.clear();
                documentAdapter.notifyDataSetChanged();
                initView(user_id, password);
                documentXrecyview.refreshComplete();
                Toast.makeText(getApplicationContext(),"刷新完成",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLoadMore() {
                list.clear();
                documentAdapter.notifyDataSetChanged();
                initView(user_id, password);
                documentXrecyview.loadMoreComplete();
                Toast.makeText(getApplicationContext(),"加载完成",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView(String user_id, String password) {
        long timeStamp = System.currentTimeMillis() / 1000;
        String timestamp = String.valueOf(timeStamp);
        String message = "equipment=" + equipmentId + "&projectId=17&time=" + timestamp + ".K15058835525+.";
        String token = Util.StringToMd5(message);
        String pwasword = "(*&%" + password + "*&)jzy";
        String pwassMd5 = Util.StringToMd5s(pwasword);
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("token", token)
                .add("equipmentId", equipmentId)
                .add("timestamp", timestamp)
                .add("user_id", user_id)
                .add("password", pwassMd5)
                .add("status", "0").build();
        Request request = new Request.Builder().post(body).url(uri).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responDate = response.body().string();
                    bean = new Gson().fromJson(responDate, FragmentDocumentOneGson.class);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}
