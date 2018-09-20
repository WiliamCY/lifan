package com.example.qqche.cy.FragmentInfomation;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.qqche.cy.Adapter.CommendAdapter;
import com.example.qqche.cy.Adapter.HotPostAdapter;
import com.example.qqche.cy.Class.Commend;
import com.example.qqche.cy.R;
import com.example.qqche.cy.Utils.Constant;
import com.example.qqche.cy.Utils.Util;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.json.JSONObject;

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

/**
 * Created by Administrator on 2018/4/28.
 */

public class FragmentBestnews extends Fragment {
    Unbinder unbinder;
    private HotPostAdapter commendAdapter;
    private XRecyclerView commendXecyview;
    private List<Commend> commendList = new ArrayList<>();
    private String equipmentId ="8",status;
    private Message msg;
    private String uri = Constant.SERVER+"index.php?r=index%2Fgetarticle";
    private  Handler handler;//静态
    private FragmentBestnews fragmentBestnews;
    public  FragmentActivity activity ;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bestnews, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        commendXecyview = view.findViewById(R.id.new_xecyview);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(),1);
        commendXecyview.setLayoutManager(layoutManager);
        commendAdapter = new HotPostAdapter(commendList);
        commendXecyview.setAdapter(commendAdapter);
        handler = new MyHandler(activity,this);
        return view;
    }
    private void initView() {
        Long time = System.currentTimeMillis()/1000;
                String timestamp = String.valueOf(time);
                String message = "equipment=" + equipmentId + "&projectId=17&time=" + timestamp + ".K15058835525+.";
                String token = Util.StringToMd5(message);
                OkHttpClient client = new OkHttpClient();
                RequestBody body = new FormBody.Builder()
                        .add("token",token)
                        .add("timestamp",timestamp)
                        .add("equipmentId",equipmentId).build();
                Request request = new Request.Builder().post(body).url(uri).build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                            if(response.isSuccessful()){

                            }
                    }
                });

            }

    private static class MyHandler extends Handler {
        private WeakReference<FragmentActivity> weakReference;
        private WeakReference<FragmentBestnews> weakReferences;
        public MyHandler(FragmentActivity fragmentActivity, FragmentBestnews fragmentCommend) {
            weakReference = new WeakReference<>(fragmentActivity);
            weakReferences = new WeakReference<>(fragmentCommend);

        }

        @Override
        public void handleMessage(Message msg) {
            if(weakReference.get() != null){
                switch (msg.what){
                    case 1:
                        Toast.makeText(weakReference.get(),"数据获取成功",Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(weakReference.get(),"数据错误",Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        Toast.makeText(weakReference.get(),"非法访问",Toast.LENGTH_SHORT).show();
                        break;


                }
            }
            super.handleMessage(msg);
        }
    }
        @Override
    public void onDestroyView() {
        super.onDestroyView();
        handler.removeCallbacksAndMessages(null);
        unbinder.unbind();
    }


}
