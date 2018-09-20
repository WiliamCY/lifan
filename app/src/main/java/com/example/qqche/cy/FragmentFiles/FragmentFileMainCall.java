package com.example.qqche.cy.FragmentFiles;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
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

public class FragmentFileMainCall extends AppCompatActivity {
    @BindView(R.id.toolbar_back)
    ImageView toolbarBack;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.my_view1)
    View myView1;
    Unbinder unbinder;
    @BindView(R.id.file_mian_call_1)
    ImageView fileMianCall1;
    @BindView(R.id.file_mian_call_2)
    ImageView fileMianCall2;
    final public static int REQUEST_CODE_ASK_CALL_PHONE = 123;
    @BindView(R.id.company_tel)
    TextView companyTels;
    @BindView(R.id.company_com)
    TextView companyComs;
    @BindView(R.id.address)
    TextView addresss;
    private String company_tels,company_coms,address;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_file_main_call);
        View tooblar = findViewById(R.id.file_mian_call);
        toolbarTitle = tooblar.findViewById(R.id.toolbar_title);
        StatusBarUtil.setTransparent(this);
        StatusBarUtilS.StatusBarLightMode(this);
        unbinder = ButterKnife.bind(this);
        Bundle bundle = getIntent().getExtras();
        String names = bundle.getString("namess");
        company_tels = bundle.getString("company_tels");
        company_coms = bundle.getString("company_com");
        address = bundle.getString("address");
        if(!company_tels.isEmpty() && !company_coms.isEmpty() && !address.isEmpty()){
            companyTels.setText(company_tels);
            companyComs.setText(company_coms);
            addresss.setText(address);
            toolbarTitle.setText("联系平台");
        }else {
            Toast.makeText(getApplicationContext(),"",Toast.LENGTH_SHORT).show();
        }


    }

    @OnClick({R.id.toolbar_back, R.id.file_mian_call_1, R.id.file_mian_call_2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbar_back:
                finish();
                break;
            case R.id.file_mian_call_1:

                final AlertDialog builder = new AlertDialog.Builder(this)
                        .create();
                builder.show();
                if (builder.getWindow() == null) return;
                builder.getWindow().setContentView(R.layout.pop_users);
                TextView msg = builder.findViewById(R.id.tv_msg);
                Button cancle = builder.findViewById(R.id.btn_cancle);
                Button sure = builder.findViewById(R.id.btn_sure);
                if(!company_tels.isEmpty()){
                msg.setText(company_tels);
                cancle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        builder.dismiss();
                    }
                });
                sure.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PermissionCall(company_tels);
                        builder.dismiss();
                    }
                });
                }else {
                    Toast.makeText(getApplicationContext(),"没号码",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.file_mian_call_2:
                final AlertDialog builder2 = new AlertDialog.Builder(this).create();
                builder2.show();
                if (builder2.getWindow() == null) return;
                builder2.getWindow().setContentView(R.layout.pop_users);
                TextView msg2 = builder2.findViewById(R.id.tv_msg);
                Button cancle2 = builder2.findViewById(R.id.btn_cancle);
                Button sure2 = builder2.findViewById(R.id.btn_sure);
                msg2.setText(company_tels);
                if(!company_tels.isEmpty()) {
                    cancle2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            builder2.dismiss();
                        }
                    });
                    sure2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            PermissionCall(company_tels);
                            builder2.dismiss();
                        }
                    });
                }else {
                    Toast.makeText(getApplicationContext(),"没号码",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void PermissionCall(String company_tels) {
        if (Build.VERSION.SDK_INT >= 23) {
            int checkCallPhonePermission = ContextCompat.checkSelfPermission(this,
                    Manifest.permission.CALL_PHONE);
            if (checkCallPhonePermission != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{
                        Manifest.permission.CALL_PHONE
                }, REQUEST_CODE_ASK_CALL_PHONE);
                return;
            } else {
                // 上面已经写好的拨号方法 callDirectly(mobile);
                callDirectly(company_tels);
            }
        } else {
            // 上面已经写好的拨号方法 callDirectly(mobile);
            callDirectly(company_tels);
        }


    }


    private void callDirectly(String mobile) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.CALL");
        intent.setData(Uri.parse("tel:" + mobile));
        this.startActivity(intent);
    }

    //动态权限申请后处理
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_CALL_PHONE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    callDirectly(company_tels);

                } else {
                    Toast.makeText(getApplicationContext(), "请同意权限", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}
