package com.example.apipaises.retrofit;


import com.example.apipaises.service.IPaisService;


import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitConfig {
    private static final String BASE_URL = "https://falabr.cgu.gov.br/api/";
    public static IPaisService getPaisService() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .build()
                .create(IPaisService.class);
    }
}
