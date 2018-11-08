package com.nor.yandex;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.nor.yandex.api.YandexBuilder;
import com.nor.yandex.api.YandexService;
import com.nor.yandex.model.SuggestResponse;
import com.nor.yandex.model.TranslateResponse;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    /**
     * Countdown latch
     */
    private CountDownLatch lock = new CountDownLatch(1);

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.nor.yandex", appContext.getPackageName());
    }
    private String result;
    @Test
    public void translate() throws InterruptedException {
//        Context appContext = InstrumentationRegistry.getTargetContext();
//        String key = appContext.getString(R.string.api_key_translate);
//        String text = "hello";
//        String lang = "en-vi";
//        YandexBuilder.build(YandexService.TRANSLATE_URL).translate(key,text,lang).enqueue(new Callback<TranslateResponse>() {
//            @Override
//            public void onResponse(Call<TranslateResponse> call, Response<TranslateResponse> response) {
//                TranslateResponse translateResponse = response.body();
//                result = translateResponse.toString();
//                lock.countDown();
//            }
//
//            @Override
//            public void onFailure(Call<TranslateResponse> call, Throwable t) {
//                result = t.getMessage();
//                lock.countDown();
//            }
//        });
//        lock.await(20000, TimeUnit.MILLISECONDS);
//        Log.e("TranslateResult", result);
//        System.out.println(result);
//        assertNotNull(result);
    }

    @Test
    public void suggest() throws InterruptedException {
//        Context appContext = InstrumentationRegistry.getTargetContext();
//        String key = appContext.getString(R.string.api_key_suggest);
//        String text = "helloi";
//        String lang = "en";
//        YandexBuilder.build(YandexService.SUGGEST_URL).suggest(key,text,lang).enqueue(new Callback<SuggestResponse>() {
//            @Override
//            public void onResponse(Call<SuggestResponse> call, Response<SuggestResponse> response) {
//                SuggestResponse suggestResponse = response.body();
//                result = suggestResponse.toString();
//                lock.countDown();
//            }
//
//            @Override
//            public void onFailure(Call<SuggestResponse> call, Throwable t) {
//                result = t.getMessage();
//                lock.countDown();
//            }
//        });
//        lock.await(20000, TimeUnit.MILLISECONDS);
//        Log.e("SuggestResult", result);
//        assertNotNull(result);
    }

}
