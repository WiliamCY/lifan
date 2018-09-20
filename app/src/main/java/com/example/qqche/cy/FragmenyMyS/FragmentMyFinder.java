package com.example.qqche.cy.FragmenyMyS;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TabHost;

import com.example.qqche.cy.Adapter.MyFinderAdapter;
import com.example.qqche.cy.Class.MyFinder;
import com.example.qqche.cy.R;
import com.example.qqche.cy.Utils.CustomLinearLayoutManager;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import qiu.niorgai.StatusBarCompat;

/**
 * Created by Administrator on 2018/4/18.
 */

public class FragmentMyFinder extends AppCompatActivity {

    @BindView(R.id.my_finder_back)
    ImageView myFinderBack;
    Unbinder unbinder;
    @BindView(R.id.my_finder_recyview)
    XRecyclerView myFinderRecyview;
    private MyFinderAdapter myFinderAdapter;
    private List<MyFinder> myFinderList = new ArrayList<>();
   private MyFinder[]  myFinders = {new MyFinder("辉月","130*****7852","50000","500",
           "500",R.mipmap.my_logo_background),
           new MyFinder("巧克力","130*****7852","50000","500",
                   "500",R.mipmap.my_logo_background),
           new MyFinder("红玫瑰","130*****7852","50000","500",
                   "500",R.mipmap.my_logo_background),
   };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     setContentView(R.layout.fragment_my_finder);
        initView();
        myFinderRecyview = findViewById(R.id.my_finder_recyview);
        myFinderRecyview.setHasFixedSize(true);
        myFinderRecyview.setNestedScrollingEnabled(false);
        CustomLinearLayoutManager linearLayoutManager = new CustomLinearLayoutManager(this);
        linearLayoutManager.setScrollEndabled(false);
        myFinderRecyview.setLayoutManager(linearLayoutManager);
        myFinderAdapter = new MyFinderAdapter(myFinderList);
        myFinderRecyview.setAdapter(myFinderAdapter);
        StatusBarCompat.translucentStatusBar(this);
        unbinder = ButterKnife.bind(this);
    }

    private void  initView(){
        myFinderList.clear();
        for(int i = 0 ;i < 20;i++){
            Random random = new Random();
            int index = random.nextInt(myFinders.length);
            myFinderList.add(myFinders[index]);
        }
  }


    @OnClick(R.id.my_finder_back)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.my_finder_back:
               finish();
               unbinder.unbind();
                break;
        }
    }
}
