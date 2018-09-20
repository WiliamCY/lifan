package com.example.qqche.cy.FragmenyMyS;

import android.Manifest;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.example.qqche.cy.Gson.CooperationlistGson;
import com.example.qqche.cy.Gson.FilinthefromGson;
import com.example.qqche.cy.R;
import com.example.qqche.cy.Utils.BaseTransparentBarActivity;
import com.example.qqche.cy.Utils.Constant;
import com.example.qqche.cy.Utils.Util;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class FragmentFillintheform extends BaseTransparentBarActivity {
    private static final int CHOOSE_PHOTO = 2;
    @BindView(R.id.file_logo)
    ImageView fileLogo;
    @BindView(R.id.cooperation_id)
    TextView cooperationId;
    @BindView(R.id.popDown1)
    RelativeLayout popDown1;
    @BindView(R.id.money)
    EditText money;
    @BindView(R.id.p2p_borrowname)
    TextView p2pBorrowname;
    @BindView(R.id.time_limit)
    EditText timeLimit;
    @BindView(R.id.tender_time)
    TextView tenderTime;
    @BindView(R.id.popDown2)
    RelativeLayout popDown2;
    @BindView(R.id.tender_phone)
    EditText tenderPhone;
    @BindView(R.id.file_image)
    ImageView fileImage;
    @BindView(R.id.remark)
    TextView remark;
    public static final MediaType MEDIA_TYPE_MARKDOWN
            = MediaType.parse("text/x-markdown; charset=utf-8");
    private TimePickerView pickerView;
    private String cooperation_ids, moneys, p2p_borrownames, time_limits, time_limit_types, tender_times, tender_phones, imagePath;
    private String user_id, password;
    private String uri = Constant.SERVER + "index.php?r=user%2Futenderself";
    private String uricooperation = Constant.SERVER + "index.php?r=cooperation%2Fgetsimplecooperationlist";
    private String equipmentId = "8";
    private Handler handler;//静态
    private Message msg;
    private CooperationlistGson bean;
    private static String id, name;
    private OptionsPickerView optionsPickerView;//条件选择器
    private static ArrayList<String> names;
    private static ArrayList<String> ids;
    private static String coolid, times, timeStamp;
    private TextView toolbarTitle,WiTitleButton;
    private ImageView toolbar_back;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fileinthefrom);
        ButterKnife.bind(this);
        View view = findViewById(R.id.toolbar);
        toolbarTitle = view.findViewById(R.id.toolbar_title);
        WiTitleButton = view.findViewById(R.id.WiTitleButton);
        WiTitleButton.setText("提交");
        toolbar_back = view.findViewById(R.id.toolbar_back);
        toolbarTitle.setText("提交订单");
        SharedPreferences preferences1 = this.getSharedPreferences("dara", Context.MODE_PRIVATE);
        user_id = preferences1.getString("userId", "");
        password = preferences1.getString("password", "");
        handler = new MyHandler(this);
        toolbar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
         WiTitleButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 initView();
                 if (moneys.isEmpty() || tender_phones.isEmpty()) {
                     Toast.makeText(getApplicationContext(), "请输入完整", Toast.LENGTH_SHORT).show();
                 } else if (!Util.isMobileNO(tender_phones)) {
                     Toast.makeText(getApplicationContext(), "请输入正确的手机格式", Toast.LENGTH_SHORT).show();
                 } else if (imagePath.isEmpty()) {
                     Toast.makeText(getApplicationContext(), "请选择提交投资的订单截图", Toast.LENGTH_SHORT).show();

                 } else {
                     initAddData(coolid, moneys, time_limits, timeStamp, tender_phones, p2p_borrownames, imagePath, fileLogo);
                 }
             }
         });

    }

    private static class MyHandler extends Handler {
        private WeakReference<FragmentFillintheform> weakReference;

        public MyHandler(FragmentFillintheform activity) {
            weakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (weakReference.get() != null) {
                switch (msg.what) {
                    case 1:
                        List<CooperationlistGson.DataBean> list = weakReference.get().bean.getData();
                        names = new ArrayList<>();
                        ids = new ArrayList<>();
                        for (CooperationlistGson.DataBean lists : list) {
                            id = lists.getId();
                            name = lists.getName();
                            names.add(name);
                            ids.add(id);

                        }
                        weakReference.get().initOptionPicker(names, ids);
                        break;
                    case 2:
                        Toast toast1 = Toast.makeText(weakReference.get(), "参数错误", Toast.LENGTH_SHORT);
                        Util.showMyToast(toast1, 500);
                        break;
                    case 3:
                        Toast toast2 = Toast.makeText(weakReference.get(), "非法访问", Toast.LENGTH_SHORT);
                        Util.showMyToast(toast2, 500);
                        break;
                    case 4:
                        Toast toast3 = Toast.makeText(weakReference.get(), "提交成功", Toast.LENGTH_SHORT);
                        Util.showMyToast(toast3, 500);
                        weakReference.get().finish();
                        break;
                    case 5:
                        Toast toast5 = Toast.makeText(weakReference.get(), "项目名称不能为空", Toast.LENGTH_SHORT);
                        Util.showMyToast(toast5, 500);
                        weakReference.get().finish();
                        break;
                    case 6:
                        Toast toast6 = Toast.makeText(weakReference.get(), "平台名称不能为空", Toast.LENGTH_SHORT);
                        Util.showMyToast(toast6, 500);
                        weakReference.get().finish();
                        break;
                    case 7:
                        Toast toast7 = Toast.makeText(weakReference.get(), "投资期限不能为空", Toast.LENGTH_SHORT);
                        Util.showMyToast(toast7, 500);
                        weakReference.get().finish();
                        break;
                    case 8:
                        Toast toast8 = Toast.makeText(weakReference.get(), "期限单位不能为空", Toast.LENGTH_SHORT);
                        Util.showMyToast(toast8, 500);
                        weakReference.get().finish();
                        break;
                    case 9:
                        Toast toast9 = Toast.makeText(weakReference.get(), "投资金额不能为空", Toast.LENGTH_SHORT);
                        Util.showMyToast(toast9, 500);
                        weakReference.get().finish();
                        break;
                    case 10:
                        Toast toast10 = Toast.makeText(weakReference.get(), "投资时间不能为空", Toast.LENGTH_SHORT);
                        Util.showMyToast(toast10, 500);
                        weakReference.get().finish();
                        break;
                    case 11:
                        Toast toast11 = Toast.makeText(weakReference.get(), "投资号码不能为空", Toast.LENGTH_SHORT);
                        Util.showMyToast(toast11, 500);
                        weakReference.get().finish();
                        break;
                    case 12:
                        Toast toast12 = Toast.makeText(weakReference.get(), "手机号码格式错误", Toast.LENGTH_SHORT);
                        Util.showMyToast(toast12, 500);
                        weakReference.get().finish();
                        break;
                    case 13:
                        Toast toast13 = Toast.makeText(weakReference.get(), "投资金额格式错误", Toast.LENGTH_SHORT);
                        Util.showMyToast(toast13, 500);
                        weakReference.get().finish();
                        break;
                    case 14:
                        Toast toast14 = Toast.makeText(weakReference.get(), "投资号码必须与注册号码一致", Toast.LENGTH_SHORT);
                        Util.showMyToast(toast14, 500);
                        weakReference.get().finish();
                        break;
                    case 15:
                        Toast toast15 = Toast.makeText(weakReference.get(), "截图不能为空", Toast.LENGTH_SHORT);
                        Util.showMyToast(toast15, 500);
                        weakReference.get().finish();
                        break;
                    case 16:
                        Toast toast16 = Toast.makeText(weakReference.get(), "提交失败", Toast.LENGTH_SHORT);
                        Util.showMyToast(toast16, 500);
                        weakReference.get().finish();
                        break;
                }
            }
        }
    }

    private void initOptionPicker(final List<String> name, final List<String> ids) {
        System.out.print(name);
        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                p2pBorrowname.setText(name.get(options1));
                cooperationId.setText(name.get(options1));
                coolid = ids.get(options1);
                p2p_borrownames = name.get(options1);
            }
        })
                .setSubmitColor(ContextCompat.getColor(this, R.color.orgin))
                .setCancelColor(ContextCompat.getColor(this, R.color.orgin))
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK)
                .setContentTextSize(20)
                .build();

        pvOptions.setPicker(name);
        pvOptions.show();


    }

    @OnClick({ R.id.file_logo, R.id.popDown1, R.id.popDown2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.file_logo:
                initChoosePhoto();
                break;
            case R.id.popDown1:
                initPop1();
                break;
            case R.id.popDown2:
                initBirthday();
                break;
        }
    }

    private void initView() {
        cooperation_ids = cooperationId.getText().toString();
        moneys = money.getText().toString();
        tender_times = timeLimit.getText().toString();
        tender_times = tenderTime.getText().toString();
        tender_phones = tenderPhone.getText().toString();
        p2p_borrownames = p2pBorrowname.getText().toString();
    }

    private void initAddData(String cooperation_ids, String moneys, String time_limits, String tender_times, String tender_phones, String p2p_borrownames, String imagePath, ImageView fileLogo) {
        long timeStamp = System.currentTimeMillis() / 1000;
        String timestamp = String.valueOf(timeStamp);
        String message = "equipment=" + equipmentId + "&projectId=17&time=" + timestamp + ".K15058835525+.";
        String token = Util.StringToMd5(message);
        String pwasword = "(*&%" + password + "*&)jzy";
        String pwassMd5 = Util.StringToMd5s(pwasword);
        OkHttpClient client = new OkHttpClient();
        File file = new File(imagePath);
        final RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("token", token)
                .addFormDataPart("equipmentId", equipmentId)
                .addFormDataPart("timestamp", timestamp)
                .addFormDataPart("user_id", user_id)
                .addFormDataPart("password", pwassMd5)
                .addFormDataPart("cooperation_id", cooperation_ids)
                .addFormDataPart("p2p_borrowname", p2p_borrownames)
                .addFormDataPart("time_limit", "1")
                .addFormDataPart("time_limit_type", "1")
                .addFormDataPart("money", moneys)
                .addFormDataPart("tender_time", tender_times)
                .addFormDataPart("tender_phone", tender_phones)
                .addFormDataPart("file", file.getName(), RequestBody.create(MEDIA_TYPE_MARKDOWN, file)).build();
        Request request = new Request.Builder().url(uri).post(requestBody).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String c = response.body().string();
                    FilinthefromGson bean = new Gson().fromJson(c, FilinthefromGson.class);
                    String status = bean.getStatus();
                    switch (status) {
                        case "1":
                            msg = new Message();
                            msg.what = 4;
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
                        case "11":
                            msg = new Message();
                            msg.what = 5;
                            handler.sendMessage(msg);
                            break;
                        case "12":
                            msg = new Message();
                            msg.what = 6;
                            handler.sendMessage(msg);
                            break;
                        case "13":
                            msg = new Message();
                            msg.what = 7;
                            handler.sendMessage(msg);
                            break;
                        case "14":
                            msg = new Message();
                            msg.what = 8;
                            handler.sendMessage(msg);
                            break;
                        case "15":
                            msg = new Message();
                            msg.what = 9;
                            handler.sendMessage(msg);
                            break;
                        case "16":
                            msg = new Message();
                            msg.what = 10;
                            handler.sendMessage(msg);
                            break;
                        case "17":
                            msg = new Message();
                            msg.what = 11;
                            handler.sendMessage(msg);
                            break;
                        case "18":
                            msg = new Message();
                            msg.what = 12;
                            handler.sendMessage(msg);
                            break;
                        case "19":
                            msg = new Message();
                            msg.what = 13;
                            handler.sendMessage(msg);
                            break;
                        case "20":
                            msg = new Message();
                            msg.what = 14;
                            handler.sendMessage(msg);
                            break;
                        case "21":
                            msg = new Message();
                            msg.what = 15;
                            handler.sendMessage(msg);
                            break;
                        case "-1":
                            msg = new Message();
                            msg.what = 16;
                            handler.sendMessage(msg);
                            break;
                    }
                }

            }
        });
        Map<String, String> params = new HashMap<>();
        Map<String, String> headers = new HashMap<>();
        headers.put("token", token);
        headers.put("equipmentId", equipmentId);
        headers.put("timestamp", timestamp);
        headers.put("user_id", user_id);
        headers.put("password", pwassMd5);
        headers.put("cooperation_id", cooperation_ids);
        headers.put("p2p_borrowname", p2p_borrownames);
        headers.put("time_limit", "1");
        headers.put("time_limit_type", time_limit_types);
        headers.put("money", moneys);
        headers.put("tender_time", tender_times);
        headers.put("tender_phone", tender_phones);


    }

    private void initPop1() {
        long timeStamp = System.currentTimeMillis() / 1000;
        String timestamp = String.valueOf(timeStamp);
        String message = "equipment=" + equipmentId + "&projectId=17&time=" + timestamp + ".K15058835525+.";
        String token = Util.StringToMd5(message);
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("token", token)
                .add("equipmentId", equipmentId)
                .add("timestamp", timestamp).build();
        Request request = new Request.Builder().post(body).url(uricooperation).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responData = response.body().string();
                    bean = new Gson().fromJson(responData, CooperationlistGson.class);
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

    private void initBirthday() {
        Calendar selectedDate = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();
        startDate.set(2000, 0, 1);
        endDate.set(2020, 11, 31);
        pickerView = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                tenderTime.setText(getTime(date));
                Long timedata = date.getTime() / 1000;
                timeStamp = String.valueOf(timedata);
            }
        })
                .setSubmitColor(ContextCompat.getColor(this, R.color.orgin))
                .setCancelColor(ContextCompat.getColor(this, R.color.orgin))
                .setRangDate(startDate, endDate)
                .build();
        pickerView.show();

    }

    private void initChoosePhoto() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        } else {
            openAlbum();
        }
    }

    private void openAlbum() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent, CHOOSE_PHOTO);


    }

    private String getTime(Date date) {//可根据需要自行截取数据显示
        Log.d("getTime()", "choice date millis: " + date.getTime());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {

            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openAlbum();
                } else {
                    Toast.makeText(getApplicationContext(), "你必须同意该权限", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case CHOOSE_PHOTO:
                if (resultCode == RESULT_OK) {
                    if (Build.VERSION.SDK_INT >= 19) {
                        handleImageOnKitkat(data);
                    } else {
                        handleImageBeForeKitKat(data);
                    }
                }
                break;
            default:
                break;
        }
    }

    private void handleImageOnKitkat(Intent data) {
        imagePath = null;
        Uri uri = data.getData();
        if (DocumentsContract.isDocumentUri(this, uri)) {
            String docId = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String id = docId.split(":")[1];
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                imagePath = getImagePath(contentUri, null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            imagePath = getImagePath(uri, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            imagePath = uri.getPath();
        }
        displayImage(imagePath);
    }

    private void handleImageBeForeKitKat(Intent data) {
        Uri uri = data.getData();
        imagePath = getImagePath(uri, null);
        System.out.print(imagePath);
        displayImage(imagePath);
    }

    private String getImagePath(Uri uri, String selection) {
        String path = null;
        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    private void displayImage(String imagePath) {
        if (imagePath != null) {
            System.out.print(imagePath);
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            fileLogo.setImageBitmap(bitmap);
        } else {
            Toast.makeText(getApplicationContext(), "发生错误", Toast.LENGTH_SHORT).show();
        }
    }


}