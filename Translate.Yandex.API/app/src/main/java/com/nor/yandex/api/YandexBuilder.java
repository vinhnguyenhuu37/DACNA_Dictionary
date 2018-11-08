package com.nor.yandex.api;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Class build retrofit service
 *
 * @retrofit là thư viện request api
 */
public class YandexBuilder {
    private static YandexService service;

    /**
     * @param baseUrl url cho request truy vấn
     */
    public static YandexService build(String baseUrl) {
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                // setup base url cho retrofit
                .baseUrl(baseUrl)
                // tự động parse data trả về thành objext
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        // build class service
        service = retrofit.create(YandexService.class);
        return service;
    }
}
