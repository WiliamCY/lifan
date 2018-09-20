package com.example.qqche.cy.FragmentFiles;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qqche.cy.R;
import com.example.qqche.cy.Utils.StatusBarUtilS;
import com.jaeger.library.StatusBarUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/4/25.
 */

public class FragmentFileMainHonor extends AppCompatActivity {


    @BindView(R.id.toolbar_back)
    ImageView toolbarBack;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    Unbinder unbinder;
    @BindView(R.id.control_evaluation)
    TextView controlEvaluations;
    @BindView(R.id.cast_background)
    TextView castBackgrounds;
    @BindView(R.id.join_association)
    TextView joinAssociations;
    private String control_evaluation, cast_background, join_association;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main_file_honor);
        View honorToolbar = findViewById(R.id.file_main_honor_toolabr);
        toolbarTitle = honorToolbar.findViewById(R.id.toolbar_title);
        StatusBarUtil.setTransparent(this);
        StatusBarUtilS.StatusBarLightMode(this);
        unbinder = ButterKnife.bind(this);
        Bundle bundle = getIntent().getExtras();
        control_evaluation = bundle.getString("control_evaluation");
        cast_background = bundle.getString("cast_background");
        join_association = bundle.getString("cast_background");
        String names = bundle.getString("namess");
        if (!control_evaluation.isEmpty() && !cast_background.isEmpty() && !join_association.isEmpty()) {
            controlEvaluations.setText(control_evaluation);
            castBackgrounds.setText(cast_background);
            joinAssociations.setText(join_association);
            toolbarTitle.setText("平台荣誉");
        } else {
            Toast.makeText(getApplicationContext(), "发生异常", Toast.LENGTH_SHORT).show();
        }

    }

    @OnClick({R.id.toolbar_back, R.id.toolbar_title})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbar_back:
                finish();
                break;
            case R.id.toolbar_title:
                break;
        }
    }
}
