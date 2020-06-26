package com.gauravtak.scheduler_assignment.network_call;



import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public final class APICommon {
    private static  String BASE_URL = "http://fathomless-shelf-5846.herokuapp.com/api/";
    private static  String token = "<SCHEDULE_PROJECT_USER_TOKEN>";

    static Gson gson = new GsonBuilder()
            .setLenient()
            .create();
    private static OkHttpClient.Builder okClientBuilder = new OkHttpClient.Builder().connectTimeout(1, TimeUnit.MINUTES)
                                                                                    .readTimeout(1, TimeUnit.MINUTES);
    private static OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                                                  .connectTimeout(60, TimeUnit.SECONDS)
                                                  .readTimeout(60, TimeUnit.SECONDS)
                                                  .writeTimeout(60, TimeUnit.SECONDS)
                                                  .build();
    ;
    private static Retrofit.Builder retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)

            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson));

    static {


            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            okClientBuilder.interceptors().add(httpLoggingInterceptor);



       okClientBuilder.interceptors().add(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException
            {
                Request request = chain.request();
                Request newRequest;

                newRequest = request.newBuilder()
                                    .addHeader("content-type", "application/json")
    //                                .addHeader("Authorization", "Bearer "+token)
                                    .build();
                return chain.proceed(newRequest);
            }
        });

    }

    public static ApiInterface getInstance() {
        return retrofit.client(okClientBuilder.build()).build().create(ApiInterface.class);
    }
}

