package com.rakesh.hashkeynretrofit.retrofit;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.rakesh.hashkeynretrofit.AppSingleton;
import com.rakesh.hashkeynretrofit.R;
import com.rakesh.hashkeynretrofit.retrofit.activity.ChannelActivity;
import com.rakesh.hashkeynretrofit.retrofit.activity.CountryActivity;
import com.rakesh.hashkeynretrofit.retrofit.activity.ForgotPassword;
import com.rakesh.hashkeynretrofit.retrofit.activity.LoginActivity;
import com.rakesh.hashkeynretrofit.retrofit.model.Channels;
import com.rakesh.hashkeynretrofit.retrofit.model.Country;
import com.rakesh.hashkeynretrofit.util.Constant;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Admin on 10-02-2017.
 */

public class RetrofitActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_country, btn_channel, btn_login, btn_forgotpwd, btn_changepwd, btn_register, btn_verify, btn_deactivate, btn_logout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);

        btn_country = (Button) findViewById(R.id.btn_country);
        btn_channel = (Button) findViewById(R.id.btn_channel);
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_forgotpwd = (Button) findViewById(R.id.btn_forgotpwd);
        btn_changepwd = (Button) findViewById(R.id.btn_changepwd);
        btn_register = (Button) findViewById(R.id.btn_register);
        btn_verify = (Button) findViewById(R.id.btn_verify);
        btn_deactivate = (Button) findViewById(R.id.btn_deactivate);
        btn_logout = (Button) findViewById(R.id.btn_logout);

        btn_country.setOnClickListener(this);
        btn_channel.setOnClickListener(this);
        btn_login.setOnClickListener(this);
        btn_forgotpwd.setOnClickListener(this);
        btn_changepwd.setOnClickListener(this);
        btn_register.setOnClickListener(this);
        btn_verify.setOnClickListener(this);
        btn_deactivate.setOnClickListener(this);
        btn_logout.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_country) {
            getCountries();
        }
        if (v.getId() == R.id.btn_channel) {
            getChannels();
        }
        if (v.getId() == R.id.btn_login) {
            Intent intentCountry = new Intent(RetrofitActivity.this, LoginActivity.class);
            startActivityForResult(intentCountry, Constant.LoginActivity);
        }
        if (v.getId() == R.id.btn_forgotpwd) {
            Intent intentCountry = new Intent(RetrofitActivity.this, ForgotPassword.class);
            startActivityForResult(intentCountry, Constant.ForgotPassword);
        }
        if (v.getId() == R.id.btn_changepwd) {

        }
        if (v.getId() == R.id.btn_register) {

        }
        if (v.getId() == R.id.btn_verify) {

        }
        if (v.getId() == R.id.btn_deactivate) {

        }
        if (v.getId() == R.id.btn_logout) {

        }
    }

    private void getChannels() {

        AppApi apiService =
                AppClient.getClient().create(AppApi.class);

        Call<Channels> channelsList = apiService.listChannels();

        channelsList.enqueue(new Callback<Channels>() {
            @Override
            public void onResponse(Call<Channels> call, Response<Channels> response) {
                Log.e("call: ", "" + call.toString());
                Log.e("response message: ", response.message());
                Log.e("response body: ", "" + response.body());
                Log.e("response code: ", "" + response.code());
                Log.e("response errorBody: ", "" + response.errorBody());
                Log.e("response headers: ", "" + response.headers());
                Log.e("response isSuccessful: ", "" + response.isSuccessful());
                Log.e("response raw: ", "" + response.raw());

                AppSingleton.sSavePreferences(RetrofitActivity.this, Constant.sharePrefChannelKey, "");
                AppSingleton.sSavePreferences(RetrofitActivity.this, Constant.sharePrefChannelKey, response.body().toString());
                Intent intentCountry = new Intent(RetrofitActivity.this, ChannelActivity.class);
                //intentCountry.putParcelableArrayListExtra("channellist", new ArrayList<Channels>(response.body()));
                startActivityForResult(intentCountry, Constant.ChannelActivity);
            }

            @Override
            public void onFailure(Call<Channels> call, Throwable t) {
                Log.e("call: ", "" + call.toString());
                Log.e("response: ", t.toString());
            }
        });
        //call.cancel();//if want to cancel the call
    }

    private void getCountries() {
        AppApi apiService =
                AppClient.getClient().create(AppApi.class);

        Call<List<Country>> countriesList = apiService.listCountries();
        countriesList.enqueue(new Callback<List<Country>>() {
            @Override
            public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {
                Log.e("call: ", "" + call.toString());
                Log.e("response message: ", response.message());
                Log.e("response body: ", "" + response.body());
                Log.e("response code: ", "" + response.code());
                Log.e("response errorBody: ", "" + response.errorBody());
                Log.e("response headers: ", "" + response.headers());
                Log.e("response isSuccessful: ", "" + response.isSuccessful());
                Log.e("response raw: ", "" + response.raw());

                Intent intentCountry = new Intent(RetrofitActivity.this, CountryActivity.class);
                intentCountry.putParcelableArrayListExtra("countrylist", new ArrayList<Country>(response.body()));
                startActivityForResult(intentCountry, Constant.CountryActivity);
            }

            @Override
            public void onFailure(Call<List<Country>> call, Throwable t) {
                Log.e("call: ", "" + call.toString());
                Log.e("response: ", t.toString());
            }
        });
        //call.cancel();//if want to cancel the call
    }
}
