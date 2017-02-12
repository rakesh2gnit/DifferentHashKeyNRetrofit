package com.rakesh.hashkeynretrofit.retrofit;

import com.rakesh.hashkeynretrofit.retrofit.model.Channels;
import com.rakesh.hashkeynretrofit.retrofit.model.Country;
import com.rakesh.hashkeynretrofit.retrofit.model.Forgot;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by Admin on 10-02-2017.
 */

public interface AppApi {

    /*this method will return us the list of Countries
    */
    @GET("countries")
    Call<List<Country>> listCountries();

    /*this method will return us the list of Channels
    */
    @GET("channels")
    Call<Channels> listChannels();

    /*this method will return forget password response
    */
    @POST("users/forgotpassword")
    Call<Forgot> forgetPassword(@Body RequestBody email);

    /*this method will return login response
    */
    @POST("users/login/nooauth")
    Call login();
}
