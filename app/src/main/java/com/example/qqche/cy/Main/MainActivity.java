package com.example.qqche.cy.Main;


import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.example.qqche.cy.Fragment.FileFragment;
import com.example.qqche.cy.Fragment.InformationFragment;
import com.example.qqche.cy.Fragment.MyFragment;
import com.example.qqche.cy.Fragment.RebateFragment;
import com.example.qqche.cy.R;
import com.example.qqche.cy.Utils.BaseActivity;
import com.example.qqche.cy.Utils.BaseTransparentBarActivity;
import com.example.qqche.cy.Utils.ImmersiveStatusBar;
import com.example.qqche.cy.Utils.StatusBarUtilS;
import com.githang.statusbar.StatusBarCompat;
import com.jaeger.library.StatusBarUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.qqche.cy.R.color.orgin;

public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener {
    @BindView(R.id.tb)
    LinearLayout tb;
    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationBar bottomNavigationBar;
    int lastSelectedPosition = 0;

    private String TAG = MainActivity.class.getSimpleName();
    private MyFragment myFragment;
    private InformationFragment informationFragment;
    private FileFragment fileFragment;
    private RebateFragment rebateFragment;
    private Fragment mCurrentFragment;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        StatusBarUtilS.StatusBarLightMode(this);
        bottomNavigationBar.setTabSelectedListener(this).setMode(BottomNavigationBar.MODE_FIXED)
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC).setActiveColor("#EEAD0E"); //选中颜色;
        bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.informations, "资讯").setInactiveIconResource(R.drawable.information))
                .addItem(new BottomNavigationItem(R.drawable.rebates, "返利").setInactiveIconResource(R.drawable.rebate))
                .addItem(new BottomNavigationItem(R.drawable.files, "档案").setInactiveIconResource(R.drawable.file))
                .addItem(new BottomNavigationItem(R.drawable.mys, "我的").setInactiveIconResource(R.drawable.my))
                .setFirstSelectedPosition(lastSelectedPosition).initialise();
        setDefaultFragment();
    }

    private void setDefaultFragment() {
        Log.i("MainActivity", "setDefaultFragment");
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        informationFragment = InformationFragment.newInstance("资讯");
        if (!informationFragment.isAdded()) {
            transaction.add(R.id.tb, informationFragment);
        }
        transaction.commit();
        mCurrentFragment = informationFragment;

    }

    @Override
    public void onTabSelected(int position) {
        Fragment lastFragment = mCurrentFragment;
        Log.i("MainActivity", "fragment onTabSelected position = " + position);
        FragmentManager fm = this.getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        switch (position) {
            case 0:
                if (informationFragment == null) {
                    informationFragment = InformationFragment.newInstance("首页");
                }
                mCurrentFragment = informationFragment;
                break;
            case 1:
                if (rebateFragment == null) {
                    rebateFragment = RebateFragment.newInstance("返利");
                }
                mCurrentFragment = rebateFragment;
                break;
            case 2:
                if (fileFragment == null) {
                    fileFragment = FileFragment.newInstance("档案");
                }
                mCurrentFragment = fileFragment;
                break;
            case 3:
                if (myFragment == null) {
                    myFragment = MyFragment.newInstance("我的");
                }
                mCurrentFragment = myFragment;
                break;
            default:
                break;
        }
        if (lastFragment == null) {
            transaction.add(R.id.tb, mCurrentFragment);
        } else {
            if (lastFragment.isAdded()) {
                transaction.hide(lastFragment);
            }
            if (mCurrentFragment.isAdded()) {
                transaction.show(mCurrentFragment);
            } else {
                transaction.add(R.id.tb, mCurrentFragment);
            }
        }
        transaction.commit();


    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }


}
