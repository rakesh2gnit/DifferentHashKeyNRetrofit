package com.rakesh.hashkeynretrofit.retrofit;

import com.rakesh.hashkeynretrofit.retrofit.model.Channels;
import com.rakesh.hashkeynretrofit.retrofit.model.Country;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

/**
 * Created by Admin on 10-02-2017.
 */

public interface AppApi {

    @Headers({
            "Host: android.rnyoo.ws",
            "Content-Type: application/json",
            "x-rnyoo-client: RnyooAndroid",
            "User-Agent: Rnyoo-App"
    })

    /*this method will return us the list of Countries
    */
    @GET("countries")
    Call<List<Country>> listCountries();

    @Headers({
            "Host: android.rnyoo.ws",
            "Content-Type: application/json",
            "x-rnyoo-client: RnyooAndroid",
            "User-Agent: Rnyoo-App"
    })
    /*this method will return us the list of Channels
    */
    @GET("channels")
    Call<Channels> listChannels();
}
