package com.example.qqche.cy.FragmenyMyS;


import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.example.qqche.cy.R;
import com.example.qqche.cy.Utils.GetJsonDataUtil;
import com.example.qqche.cy.Utils.JsonBean;
import com.example.qqche.cy.Utils.Util;
import com.google.gson.Gson;
import com.zhy.android.percent.support.PercentLinearLayout;

import org.json.JSONArray;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import qiu.niorgai.StatusBarCompat;

/**
 * Created by Administrator on 2018/4/18.
 */

public class FragmentUserMessage extends AppCompatActivity {

    @BindView(R.id.my_userMessage_back)
    ImageView myUserMessageBack;
    @BindView(R.id.my_usermessage_logo)
    PercentLinearLayout myUsermessageLogo;
    @BindView(R.id.my_usermessage1)
    PercentLinearLayout myUsermessage1;
    @BindView(R.id.my_usermessage2)
    PercentLinearLayout myUsermessage2;
    @BindView(R.id.my_usermessage3)
    PercentLinearLayout myUsermessage3;
    @BindView(R.id.my_usermessage4)
    PercentLinearLayout myUsermessage4;
    Unbinder unbinder;
    @BindView(R.id.my_usermessage_birthday)
    PercentLinearLayout myUsermessageBirthday;
    @BindView(R.id.my_usermessage_address)
    PercentLinearLayout myUsermessageAddress;
    @BindView(R.id.my_h1)
    EditText myH1;
    @BindView(R.id.my_h2)
    EditText myH2;
    @BindView(R.id.my_h3)
    EditText myH3;
    @BindView(R.id.my_h4)
    EditText myH4;
    @BindView(R.id.my_h5)
    EditText myH5;
    @BindView(R.id.my_h6)
    EditText myH6;
    @BindView(R.id.my_save)
    TextView mySave;
    private String h1, h2, h3, h4, h5, h6;
    private TimePickerView pickerView;
    private ArrayList<JsonBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();
    private Thread thread;
    private static final int MSG_LOAD_DATA = 0x0001;
    private static final int MSG_LOAD_SUCCESS = 0x0002;
    private static final int MSG_LOAD_FAILED = 0x0003;

    private boolean isLoaded = false;
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_LOAD_DATA:
                    if (thread == null) {
                        thread = new Thread(new Runnable() {
                            @Override
                            public void run() {

                                initJsonData();
                            }
                        });
                        thread.start();
                    }
                    break;

                case MSG_LOAD_SUCCESS:
                    isLoaded = true;
                    break;

                case MSG_LOAD_FAILED:
                    break;
            }
        }
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_my_user_message);
        StatusBarCompat.translucentStatusBar(this);
        unbinder = ButterKnife.bind(this);
        mHandler.sendEmptyMessage(MSG_LOAD_DATA);
    }



    @OnClick({R.id.my_userMessage_back, R.id.my_usermessage_logo, R.id.my_usermessage_birthday, R.id.my_usermessage_address,R.id.my_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.my_userMessage_back:
                finish();
                if (mHandler != null) {
                    mHandler.removeCallbacksAndMessages(null);
                }
                unbinder.unbind();
                break;
            case R.id.my_usermessage_logo:
                break;
            case R.id.my_usermessage_birthday:
                //时间选择器
                initBirthday();
                break;
            case R.id.my_usermessage_address:
                if (isLoaded) {
                    initAddress();
                } else {
                    Toast.makeText(getApplicationContext(), "获取地址失败", Toast.LENGTH_SHORT).show();
                }
         break;
            case R.id.my_save:
                h1 = myH1.getText().toString();
                h2 = myH2.getText().toString();
                h3 = myH3.getText().toString();
                h4 = myH4.getText().toString();
                h5 = myH5.getText().toString();
                h6 = myH6.getText().toString();
                if (h1.isEmpty() || h2.isEmpty() || h3.isEmpty() || h4.isEmpty() || h5.isEmpty() || h6.isEmpty()) {
                    Toast toast1 = Toast.makeText(getApplicationContext(), "请输入完整", Toast.LENGTH_SHORT);
                    Util.showMyToast(toast1, 500);
                }else {
                    Toast.makeText(getApplicationContext(),"保存成功！",Toast.LENGTH_SHORT).show();
                    finish();
                    unbinder.unbind();
                }


        }
    }

    private void initAddress() {
        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                String tx = options1Items.get(options1).getPickerViewText() +
                        options2Items.get(options1).get(options2) +
                        options3Items.get(options1).get(options2).get(options3);

                Toast.makeText(getApplicationContext(), tx, Toast.LENGTH_SHORT).show();
            }
        })
                .setSubmitColor(ContextCompat.getColor(this, R.color.orgin))
                .setCancelColor(ContextCompat.getColor(this, R.color.orgin))
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK)
                .setContentTextSize(20)
                .build();

        pvOptions.setPicker(options1Items, options2Items, options3Items);
        pvOptions.show();
    }
    private String getTime(Date date) {//可根据需要自行截取数据显示
        Log.d("getTime()", "choice date millis: " + date.getTime());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    private void initBirthday() {
        Calendar selectedDate = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();
        startDate.set(1900, 0, 1);
        endDate.set(2020, 11, 31);
        pickerView = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
               Toast.makeText(getApplicationContext(),getTime(date),Toast.LENGTH_SHORT).show();
            }
        })
                .setSubmitColor(ContextCompat.getColor(this, R.color.orgin))
                .setCancelColor(ContextCompat.getColor(this, R.color.orgin))
                .setRangDate(startDate, endDate)
                .build();
        pickerView.show();

    }

    private void initJsonData() {//解析数据


        String JsonData = new GetJsonDataUtil().getJson(this, "province.json");

        ArrayList<JsonBean> jsonBean = parseData(JsonData);
        options1Items = jsonBean;

        for (int i = 0; i < jsonBean.size(); i++) {
            ArrayList<String> CityList = new ArrayList<>();
            ArrayList<ArrayList<String>> Province_AreaList = new ArrayList<>();

            for (int c = 0; c < jsonBean.get(i).getCityList().size(); c++) {
                String CityName = jsonBean.get(i).getCityList().get(c).getName();
                CityList.add(CityName);
                ArrayList<String> City_AreaList = new ArrayList<>();


                if (jsonBean.get(i).getCityList().get(c).getArea() == null
                        || jsonBean.get(i).getCityList().get(c).getArea().size() == 0) {
                    City_AreaList.add("");
                } else {
                    City_AreaList.addAll(jsonBean.get(i).getCityList().get(c).getArea());
                }
                Province_AreaList.add(City_AreaList);
            }


            options2Items.add(CityList);


            options3Items.add(Province_AreaList);
        }

        mHandler.sendEmptyMessage(MSG_LOAD_SUCCESS);

    }


    public ArrayList<JsonBean> parseData(String result) {
        ArrayList<JsonBean> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                JsonBean entity = gson.fromJson(data.optJSONObject(i).toString(), JsonBean.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            mHandler.sendEmptyMessage(MSG_LOAD_FAILED);
        }
        return detail;
    }

}
