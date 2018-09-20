package com.example.qqche.cy.FragmenyMyS;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.qqche.cy.R;
import com.example.qqche.cy.Utils.Constant;
import com.example.qqche.cy.Utils.Util;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class FramgnetBankWiS extends AppCompatActivity {
    private String userId,password;
    private String uri = Constant.SERVER+"index.php?r=user%2Fucashnewlog";
    private String  equipmentId = "8";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_bank_wi_money);
      Bundle bundle = getIntent().getExtras();
       userId = bundle.getString("user_id");
       password = bundle.getString("password");
      if(!userId.isEmpty() && !password.isEmpty()){
          getData(userId,password);
      }else {
          Toast.makeText(getApplication(),"发生意外",Toast.LENGTH_SHORT).show();
      }
    }
   private void getData(final String userId, final String password){
           long timeStamp = System.currentTimeMillis() / 1000;
           String timestamp = String.valueOf(timeStamp);
           String message = "equipment=" + equipmentId + "&projectId=17&time=" + timestamp + ".K15058835525+.";
           String token = Util.StringToMd5(message);
           String pwasword = "(*&%" + password + "*&)jzy";
           String pwassMd5 = Util.StringToMd5s(pwasword);
           OkHttpClient client = new OkHttpClient();
           RequestBody body = new FormBody.Builder()
                   .add("token",token)
                   .add("equipmentId",equipmentId)
                   .add("timestamp",timestamp)
                   .add("user_id",userId)
                   .add("password",pwassMd5)
                   .add("page","1")
                   .add("napage","10").build();
           Request request = new Request.Builder().post(body).url(uri).build();
           client.newCall(request).enqueue(new Callback() {
               @Override
               public void onFailure(Call call, IOException e) {

               }

               @Override
               public void onResponse(Call call, Response response) throws IOException {
                   if(response.isSuccessful()){
                       String responseDate = response.body().string();
                   }

               }
           });
   }
}
