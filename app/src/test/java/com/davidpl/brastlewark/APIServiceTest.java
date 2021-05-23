package com.davidpl.brastlewark;

import com.davidpl.brastlewark.business.model.Users;
import com.example.display.business.datasource.APIService;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.junit.Assert.assertEquals;

public class APIServiceTest {

    static APIService apiService;

    @BeforeClass
    public static void setUp() {

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor) //httpLoggingInterceptor allows to log json body.
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .client(okHttpClient)
                .build();

        apiService = retrofit.create(APIService.class);
    }

    @Test
    public void testCallUsers() throws IOException {

        Call<Users> call = apiService.fetchBrastlewarkCall();
        Response<Users> movieResponse = call.execute();

        assertEquals(movieResponse.code(), 200);
    }

}
