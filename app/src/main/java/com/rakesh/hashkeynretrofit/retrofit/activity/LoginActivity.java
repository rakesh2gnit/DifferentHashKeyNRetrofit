package com.rakesh.hashkeynretrofit.retrofit.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.rakesh.hashkeynretrofit.AppSingleton;
import com.rakesh.hashkeynretrofit.R;
import com.rakesh.hashkeynretrofit.retrofit.AppApi;
import com.rakesh.hashkeynretrofit.retrofit.AppClient;
import com.rakesh.hashkeynretrofit.retrofit.RetrofitActivity;
import com.rakesh.hashkeynretrofit.retrofit.model.Channels;
import com.rakesh.hashkeynretrofit.util.Constant;
import com.rakesh.hashkeynretrofit.util.HashGeneratorUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Admin on 11-02-2017.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextPwd;
    private Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextPwd = (EditText) findViewById(R.id.editTextPwd);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button) {
            Log.e("pwd: ", HashGeneratorUtils.rnyoomd5(editTextPwd.getText().toString()));
            getLogin();
            //"uid": "40EVOB5iTSEDdLFKp4wCouBN3Sl",
            //"sid": "LGxcgxTTCUJjlON9uR5NBJGr0rN",
        }
    }

    private void getLogin(){
        AppApi apiService =
                AppClient.getClient().create(AppApi.class);

        Call<Response> login = apiService.login();

        login.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, Response<Response> response) {
                Log.e("call: ", "" + call.toString());
                Log.e("response message: ", response.message());
                Log.e("response body: ", "" + response.body());
                Log.e("response code: ", "" + response.code());
                Log.e("response errorBody: ", "" + response.errorBody());
                Log.e("response headers: ", "" + response.headers());
                Log.e("response isSuccessful: ", "" + response.isSuccessful());
                Log.e("response raw: ", "" + response.raw());
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Log.e("call: ", "" + call.toString());
                Log.e("response: ", t.toString());
            }
        });
        //call.cancel();//if want to cancel the call
    }
}
