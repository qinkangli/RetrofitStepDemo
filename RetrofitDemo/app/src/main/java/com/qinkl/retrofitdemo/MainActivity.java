package com.qinkl.retrofitdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }

    public void onClick(View view) {
        request();
    }

    private void request(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://fy.iciba.com/")
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();

        MyApiService service = retrofit.create(MyApiService.class);

        Call<Bean> call = service.getCall();//对网络请求进行封装，可以传入参数之类的

        call.enqueue(new Callback<Bean>() {//异步请求
            @Override
            public void onResponse(Call<Bean> call, Response<Bean> response) {//请求成功回调
                response.body().show();
            }

            @Override
            public void onFailure(Call<Bean> call, Throwable t) {//请求失败回调
                Log.i("MainActivity","请求失败");
            }
        });


    }

}
