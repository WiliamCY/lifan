package com.example.qqche.cy.Fragment;


import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.LinearLayout;

import com.example.qqche.cy.Adapter.BaseViewPagerAdapter;
import com.example.qqche.cy.FragmentInfomation.FragmenTinance;
import com.example.qqche.cy.FragmentInfomation.FragmentBestnews;
import com.example.qqche.cy.FragmentInfomation.FragmentCommend;
import com.example.qqche.cy.FragmentInfomation.FragmentHotPost;
import com.example.qqche.cy.FragmentInfomation.FragmetPolitics;
import com.example.qqche.cy.R;
import com.example.qqche.cy.Utils.StatusBarUtilS;
import com.githang.statusbar.StatusBarCompat;
import com.jaeger.library.StatusBarUtil;

import net.lucode.hackware.magicindicator.FragmentContainerHelper;
import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by Administrator on 2018/4/17.
 */

public class InformationFragment extends Fragment {
    List<String> mTitleDataList = new ArrayList<>();
    Unbinder unbinder;
    List<String> mDataList;
    List<View> viewList;
    List<Fragment> fragmentList = new ArrayList<>();
    @BindView(R.id.magic_indicator)
    MagicIndicator mMagicIndicator ;
    @BindView(R.id.view_pager)
    ViewPager mViewPager ;
    private View mView;
    private FragmentActivity activity;
    private CommonNavigator mCommonNavigator;


    public static InformationFragment newInstance(String param1) {
        InformationFragment fragment = new InformationFragment();
        Bundle args = new Bundle();
        args.putString("agrs1", param1);
        fragment.setArguments(args);
        return fragment;
    }

    public InformationFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_information, container, false);
        unbinder = ButterKnife.bind(this, view);
        mCommonNavigator = new CommonNavigator(getActivity().getApplication());
        mCommonNavigator.setAdjustMode(true);
        activity = getActivity();
        initData(inflater);
        initEvent();
        fragmentList.add(new FragmentCommend());
        fragmentList.add(new FragmentHotPost());
        fragmentList.add(new FragmentBestnews());
        fragmentList.add(new FragmenTinance());
        fragmentList.add(new FragmetPolitics());
        mViewPager.setAdapter(new BaseViewPagerAdapter(getChildFragmentManager(),fragmentList));
        return view;

    }

    private void initEvent() {
        mCommonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return mTitleDataList == null ? 0:mDataList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new ColorTransitionPagerTitleView(context);
                simplePagerTitleView.setTextSize(18);
                simplePagerTitleView.setNormalColor(Color.parseColor("#0A0A0A"));
                simplePagerTitleView.setSelectedColor(Color.rgb(225,158,53));
                simplePagerTitleView.setText(mDataList.get(index));
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mViewPager.setCurrentItem(index);
                    }
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator linePagerIndicator = new LinePagerIndicator(context);
                linePagerIndicator.setMode(LinePagerIndicator.MODE_EXACTLY);
                linePagerIndicator.setYOffset(UIUtil.dip2px(context,5));
                linePagerIndicator.setLineWidth(UIUtil.dip2px(context, 35));
                linePagerIndicator.setColors(Color.rgb(225,158,53));
                return linePagerIndicator;
            }
        });
        mMagicIndicator.setNavigator(mCommonNavigator);
        LinearLayout titleContainer = mCommonNavigator.getTitleContainer(); // must after setNavigator
        titleContainer.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        titleContainer.setDividerDrawable(new ColorDrawable() {
            @Override
            public int getIntrinsicWidth() {
                return UIUtil.dip2px(getActivity().getApplication(), 5);
            }
        });

        final FragmentContainerHelper fragmentContainerHelper = new FragmentContainerHelper(mMagicIndicator);
        fragmentContainerHelper.setInterpolator(new OvershootInterpolator(2.0f));
        fragmentContainerHelper.setDuration(300);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                mMagicIndicator.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                mMagicIndicator.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                mMagicIndicator.onPageScrollStateChanged(state);
            }
        });

    }

    private void initData(LayoutInflater inflater) {
        mDataList = new ArrayList<>();
        mDataList.add("推荐");
        mDataList.add("热点");
        mDataList.add("最新");
        mDataList.add("财经");
        mDataList.add("政治");
        View view1 = inflater.inflate(R.layout.fragment_commend,null);
        View view2 = inflater.inflate(R.layout.fragment_hot_post, null);
        View view3 = inflater.inflate(R.layout.fragment_bestnews, null);
        View view4 = inflater.inflate(R.layout.fragment_finance, null);
        View view5 = inflater.inflate(R.layout.fragment_politics, null);
        viewList = new ArrayList<View>();
        viewList.add(view1);
        viewList.add(view2);
        viewList.add(view3);
        viewList.add(view4);
        viewList.add(view5);
    }
    @Override
    public void setMenuVisibility(boolean menuVisibile) {
        super.setMenuVisibility(menuVisibile);
        if (this.getView() == null) {
            this.getView().setVisibility(menuVisibile ? View.VISIBLE : View.GONE);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        Log.i("Fragment","InformationFragment onDestroyView");
    }

}
