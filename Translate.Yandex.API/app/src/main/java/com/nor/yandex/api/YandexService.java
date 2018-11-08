package com.nor.yandex.api;

import com.nor.yandex.model.SuggestResponse;
import com.nor.yandex.model.TranslateResponse;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface YandexService {
    // api base for translate request
    String TRANSLATE_URL = "https://translate.yandex.net/api/v1.5/tr.json/";
    // api base for suggest request
    String SUGGEST_URL = "https://predictor.yandex.net/api/v1/predict.json/";

    /**
     * @param key  api access key
     * @param text từ, câu để dịch
     * @param lang ngôn ngữ dịch ví dụ: en-vi thì tiếng gốc là en tiếng cần dịch là vi
     */
    @GET("translate")
    Single<TranslateResponse> translate(@Query("key") String key, @Query("text") String text, @Query("lang") String lang);

    /**
     * @param key  api access key
     * @param text từ khóa cần suggest
     * @param lang ngôn ngữ suggest
     */
    @GET("complete")
    Single<SuggestResponse> suggest(@Query("key") String key, @Query("q") String text, @Query("lang") String lang);
}
