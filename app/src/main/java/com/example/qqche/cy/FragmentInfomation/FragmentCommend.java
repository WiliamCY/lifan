package com.example.qqche.cy.FragmentInfomation;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qqche.cy.Adapter.BannerViewHolders;
import com.example.qqche.cy.Adapter.CommendAdapter;
import com.example.qqche.cy.Class.Commend;
import com.example.qqche.cy.Gson.MainNews;
import com.example.qqche.cy.Gson.MainNotic;
import com.example.qqche.cy.Gson.MianImageBanner;
import com.example.qqche.cy.R;
import com.example.qqche.cy.Utils.Constant;
import com.example.qqche.cy.Utils.CustomLinearLayoutManager;
import com.example.qqche.cy.Utils.UPMarqueeView;
import com.example.qqche.cy.Utils.Util;
import com.example.qqche.cy.WebView.CommentNotic;
import com.example.qqche.cy.WebView.MZBannerWebView;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.ButterKnife;
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

public class FragmentCommend extends Fragment {
    private static final String TAG = "FragmentCommend";
    Unbinder unbinder;

    public MZBannerView getMZBanner() {
        return mMZBanner;
    }

    private  MZBannerView mMZBanner ;

    public UPMarqueeView getBannerView() {
        return bannerView;
    }

    private   UPMarqueeView bannerView;
    private XRecyclerView commendXecyview;
    private CommendAdapter commendAdapter;
    private String equipmentId = "8", status;
    public static String uriImage;
    public static List<Commend> commendList = new ArrayList<>();
    public  BannerViewHolders bannerViewHolder;
    private Message msg;
    public  TextView mBannerMessage;
    public static String title, abstracts, content, addtime, release_time, add_user, hot, url, uris, newName, newImage, newMessage, newUri, newTime, newLook, times,newUris;
    //滚动图
    private String uri1 = Constant.SERVER + "index.php?r=picture%2Fgetbanner";
    //滚动通知
    private String uri2 = Constant.SERVER + "index.php?r=index%2Fgetnotice";
    //新闻
    private String uri3 = Constant.SERVER + "index.php?r=index%2Fgetarticle";
    public static MainNotic statusNotic;
    public static MianImageBanner bean;
    public static MainNews beanNews;
    private  Handler handler;//静态
    public  FragmentActivity activity ;
    public FragmentCommend fragmentCommend;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_commend, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i(TAG, "banner =" + mMZBanner);
        //滚动图方法
        initImage();
        //滚动通知
        initNoticMessage();
        commendXecyview = view.findViewById(R.id.commend_xecyview);
        mMZBanner = view.findViewById(R.id.banner);
        bannerView = view.findViewById(R.id.banner_view);
        CustomLinearLayoutManager linearLayoutManager = new CustomLinearLayoutManager(getContext());
        linearLayoutManager.setScrollEndabled(false);
        commendXecyview.setLayoutManager(linearLayoutManager);
        commendXecyview.setRefreshProgressStyle(ProgressStyle.BallZigZag);
        commendXecyview.setLoadingMoreProgressStyle(ProgressStyle.BallZigZag);
        commendAdapter = new CommendAdapter(commendList);
        commendXecyview.setAdapter(commendAdapter);
        handler = new MyHandler(getActivity(),this);
        activity = getActivity();
        commendXecyview.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                commendList.clear();
                initNews();
                commendXecyview.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                commendList.clear();
                initNews();
                commendXecyview.loadMoreComplete();
            }
        });

        commendAdapter.setOnItemClickListener(new CommendAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                String uris = commendList.get(position-1).getNewUri();
                String names = commendList.get(position-1).getCommendH1();
                Intent intent = new Intent(getActivity(), MZBannerWebView.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                Bundle bundle = new Bundle();
                bundle.putString("title", names);
                bundle.putString("uri", uris);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    private static class MyHandler extends Handler {
        private WeakReference<FragmentActivity> weakReference;
        private WeakReference<FragmentCommend> weakReferences;
        public MyHandler(FragmentActivity fragmentActivity, FragmentCommend fragmentCommend){
            weakReference = new WeakReference<>(fragmentActivity);
            weakReferences = new WeakReference<>(fragmentCommend);

        }



        @Override
        public void handleMessage(Message msg) {
            if(weakReference.get() != null) {
                switch (msg.what) {
                    case 1:
                        List<MianImageBanner.DataBean> beans = bean.getData();
                        List<String> list = new ArrayList<>();
                        for (MianImageBanner.DataBean bean1 : beans) {
                            uriImage = bean1.getImg();
                            uris = bean1.getUrl();
                            list.add(uriImage);
                        }

                        Log.i(TAG, "banner =" + weakReferences.get().getMZBanner());
                        if (weakReferences.get().getMZBanner() == null) {
                            return;
                        }
                        weakReferences.get().mMZBanner.setPages(list, new MZHolderCreator<BannerViewHolders>() {
                            @Override
                            public BannerViewHolders createViewHolder() {
                                return new BannerViewHolders();

                            }
                        });
                        weakReferences.get().getMZBanner().start();
                        weakReferences.get().mMZBanner.setBannerPageClickListener(new MZBannerView.BannerPageClickListener() {
                            @Override
                            public void onPageClick(View view, int i) {
                                Intent intent = new Intent(weakReference.get(), MZBannerWebView.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                                Bundle bundle = new Bundle();
                                bundle.putString("title", title);
                                bundle.putString("uri", uris);
                                intent.putExtras(bundle);
                                weakReference.get().startActivity(intent);
                            }
                        });
                        break;
                    case 2:
                        Toast.makeText(weakReference.get(), "数据错误", Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        Toast.makeText(weakReference.get(), "非法访问", Toast.LENGTH_SHORT).show();
                        break;
                    case 4:
                        Toast.makeText(weakReference.get(), "无通知，不需弹窗", Toast.LENGTH_SHORT).show();
                        break;
                    case 5:
                        List<MainNotic.DataBean> beanNotic = statusNotic.getData();
                        for (MainNotic.DataBean bean : beanNotic) {
                            title = bean.getTitle();
                            abstracts = bean.getAbstractX();
                            content = bean.getContent();
                            addtime = bean.getAddtime();
                            release_time = bean.getRelease_time();
                            add_user = bean.getAdd_user();
                            hot = bean.getHot();
                            url = bean.getUrl();
                            ArrayList<View> verticalBannerView = new ArrayList<>();
                            for (int i = 0; i < 10; i++) {
                                View view = LayoutInflater.from(weakReference.get()).inflate(R.layout.viewfilpper, null);
                                ImageView mBannerImage = view.findViewById(R.id.banner_image);
                                weakReferences.get().mBannerMessage = view.findViewById(R.id.banner_message);
                                weakReferences.get().mBannerMessage.setText(title);
                                verticalBannerView.add(view);

                            }
                            weakReferences.get().bannerView.setViews(verticalBannerView);
                            weakReferences.get().bannerView.setOnItemClickListener(new UPMarqueeView.OnItemClickListener() {
                                @Override
                                public void onItemClick(int position, View view) {
                                    Intent intent = new Intent(weakReference.get(), CommentNotic.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                                    Bundle bundle = new Bundle();
                                    bundle.putString("title", title);
                                    bundle.putString("uri", url);
                                    intent.putExtras(bundle);
                                    weakReference.get().startActivity(intent);
                                }
                            });

                        }
                        break;
                    case 6:
                        weakReferences.get().commendList.clear();
                        weakReferences.get().commendAdapter.notifyDataSetChanged();
                        List<MainNews.DataBean> beansNews = beanNews.getData();
                        for (MainNews.DataBean dataBean : beansNews) {
                            newName = dataBean.getName();
                            newImage = dataBean.getImg();
                            newMessage = dataBean.getAbstractX();
                            newUri = dataBean.getUrl();
                            newTime = dataBean.getAddtime();
                            newLook = dataBean.getOrder();
                            newUris = dataBean.getUrl();
                            String imageUri = Constant.SERVER + newImage;
                            long data = Long.valueOf(newTime) * 1000;
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            String sd = sdf.format(new Date(Long.parseLong(String.valueOf(data))));
                            Commend commend = new Commend(newName, newMessage, newLook, sd, imageUri,newUris);
                            commendList.add(commend);

                        }
                        break;


                }
                super.handleMessage(msg);
            }
        }

    }

    private void initImage() {
        Long time = System.currentTimeMillis() / 1000;
        String timestamp = String.valueOf(time);
        String message = "equipment=" + equipmentId + "&projectId=17&time=" + timestamp + ".K15058835525+.";
        String token = Util.StringToMd5(message);
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("token", token)
                .add("timestamp", timestamp)
                .add("equipmentId", equipmentId).build();
        Request request = new Request.Builder().post(body).url(uri1).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseData = response.body().string();
                    bean = new Gson().fromJson(responseData, MianImageBanner.class);
                    switch (bean.getStatus()) {
                        case "1":

                            msg = new Message();
                            msg.what = 1;
                            handler.sendMessage(msg);
                            initNews();
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


    private void initNoticMessage() {
                Long time = System.currentTimeMillis() / 1000;
                String timestamp = String.valueOf(time);
                String message = "equipment=" + equipmentId + "&projectId=17&time=" + timestamp + ".K15058835525+.";
                String token = Util.StringToMd5(message);
                OkHttpClient client = new OkHttpClient();
                RequestBody body = new FormBody.Builder()
                        .add("token", token)
                        .add("timestamp", timestamp)
                        .add("equipmentId", equipmentId).build();
                Request request = new Request.Builder().post(body).url(uri2).build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String resopnseBody = response.body().string();
                        if (response.isSuccessful()) {
                            statusNotic = new Gson().fromJson(resopnseBody, MainNotic.class);
                            switch (statusNotic.getStatus()) {
                                case "1":
                                    msg = new Message();
                                    msg.what = 5;
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
                                case "4":
                                    msg = new Message();
                                    msg.what = 4;
                                    handler.sendMessage(msg);

                            }
                        }
                    }
                });


    }

    private void initNews() {
                Long time = System.currentTimeMillis() / 1000;
                String timestamp = String.valueOf(time);
                String message = "equipment=" + equipmentId + "&projectId=17&time=" + timestamp + ".K15058835525+.";
                String token = Util.StringToMd5(message);
                OkHttpClient client = new OkHttpClient();
                RequestBody body = new FormBody.Builder()
                        .add("token", token)
                        .add("timestamp", timestamp)
                        .add("equipmentId", equipmentId).build();
                Request request = new Request.Builder().post(body).url(uri3).build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }
                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (response.isSuccessful()) {
                            String responData = response.body().string();
                            System.out.print(responData);
                            beanNews = new Gson().fromJson(responData, MainNews.class);
                            switch (beanNews.getStatus()) {
                                case "1":
                                    msg = new Message();
                                    msg.what = 6;
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
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        handler.removeCallbacksAndMessages(null);
        Log.i(TAG, "onDestroyView");
    }


    @Override
    public void onPause() {
        super.onPause();
        mMZBanner.pause();
    }

    @Override
    public void onResume() {
        super.onResume();
        mMZBanner.start();
    }

}
