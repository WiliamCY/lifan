package com.example.qqche.cy.FragmenyMyS;


import android.annotation.TargetApi;
import android.app.LocalActivityManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.qqche.cy.FragmentDocument.FragmentDocuemntOne;
import com.example.qqche.cy.FragmentDocument.FragmentDocuemntThree;
import com.example.qqche.cy.FragmentDocument.FragmentDocuemntTwo;
import com.example.qqche.cy.R;
import com.example.qqche.cy.Utils.BaseTransparentBarActivity;
import com.example.qqche.cy.Utils.ExamplePagerAdapter;
import com.example.qqche.cy.Utils.StatusBarUtilS;
import com.jaeger.library.StatusBarUtil;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ClipPagerTitleView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import qiu.niorgai.StatusBarCompat;

/**
 * Created by Administrator on 2018/4/23.
 */

public class FragmentMyIndepeDentdocument extends BaseTransparentBarActivity {
    private static final String[] CHANNELS = new String[]{"审核中", "已通过", "未通过"};
    private TextView toolbarTitle,WiTitleButton;
    private List<String> mDataList = Arrays.asList(CHANNELS);
    private ExamplePagerAdapter mExamplePagerAdapter = new ExamplePagerAdapter(mDataList);
    @BindView(R.id.magic_indicator1)
    MagicIndicator magicIndicator;
    @BindView(R.id.vp)
    ViewPager vp;
    private List<View> views = new ArrayList<>();
    private LocalActivityManager manager;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_my_indepe_dent_document);
        ButterKnife.bind(this);
//        toolbarTitle.setText("交单记录");
        vp.setAdapter(mExamplePagerAdapter);
        manager = new LocalActivityManager(this, true);
        manager.dispatchCreate(savedInstanceState);
        View view = findViewById(R.id.toolbar);
        toolbarTitle = view.findViewById(R.id.toolbar_title);
        WiTitleButton = view.findViewById(R.id.WiTitleButton);
        toolbarTitle.setText("交单记录");
        initData();
        initView();
        WiTitleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),FragmentFillintheform.class);
                startActivity(intent);
            }
        });
    }

    private void initData() {
        Intent intentOne = new Intent(getApplicationContext(), FragmentDocuemntOne.class);
        views.add(manager.startActivity("intentOne", intentOne).getDecorView());
        Intent intentTwo = new Intent(getApplicationContext(), FragmentDocuemntTwo.class);
        views.add(manager.startActivity("intentTwo", intentTwo).getDecorView());
        Intent intentThree = new Intent(getApplicationContext(), FragmentDocuemntThree.class);
        views.add(manager.startActivity("intentThree", intentThree).getDecorView());
        vp.setAdapter(new MyAdapter());
    }

    class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return views.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            View v = views.get(position);
            container.addView(v);
            return v;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            View v = views.get(position);
            container.removeView(v);
        }
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    private void initView() {
        magicIndicator.setBackgroundResource(R.drawable.round_indicator_bg);
        CommonNavigator commonNavigator = new CommonNavigator(this);
        commonNavigator.setAdjustMode(true);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return mDataList == null ? 0 : mDataList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                ClipPagerTitleView clipPagerTitleView = new ClipPagerTitleView(context);
                clipPagerTitleView.setText(mDataList.get(index));
                clipPagerTitleView.setTextColor(Color.parseColor("#EE9A00"));
                clipPagerTitleView.setClipColor(Color.WHITE);
                clipPagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        vp.setCurrentItem(index);
                    }
                });
                return clipPagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                float navigatorHeight = context.getResources().getDimension(R.dimen.common_navigator_height);
                float borderWidth = UIUtil.dip2px(context, 1);
                float lineHeight = navigatorHeight - 2 * borderWidth;
                indicator.setLineHeight(lineHeight);
                indicator.setRoundRadius(3);
                indicator.setYOffset(borderWidth);
                indicator.setColors(Color.parseColor("#EE9A00"));
                return indicator;
            }
        });
        magicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(magicIndicator, vp);
    }


}