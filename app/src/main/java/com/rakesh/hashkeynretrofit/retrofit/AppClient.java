package com.rakesh.hashkeynretrofit.retrofit;

import com.rakesh.hashkeynretrofit.util.Constant;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by Admin on 10-02-2017.
 */

public class AppClient {

    private static Retrofit retrofit = null;

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request original = chain.request();

            Request request = original.newBuilder()
                    .header("Host", "android.rnyoo.ws")
                    .header("content-type", "application/json")
                    .header("x-rnyoo-client", "RnyooAndroid")
                    .method(original.method(), original.body())
                    .build();

            return chain.proceed(request);
        }
    });

    private static OkHttpClient client = httpClient.build();

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constant.BASE_URL)
                    .addConverterFactory(JacksonConverterFactory.create())
                    .client(client)
                    .build();
        }
        return retrofit;
    }
}
