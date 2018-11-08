package com.nor.yandex.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.nor.yandex.R;
import com.nor.yandex.api.YandexBuilder;
import com.nor.yandex.api.YandexService;
import com.nor.yandex.model.SuggestResponse;
import com.nor.yandex.model.TranslateResponse;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class TranslateViewModel extends AndroidViewModel {
    // dữ liệu translate khi trả về
    private MutableLiveData<TranslateResponse> translate = new MutableLiveData<>();
    // dữ liệu gợi ý từ
    private MutableLiveData<SuggestResponse> suggest = new MutableLiveData<>();
    // stack request translate
    private CompositeDisposable translateDisposable = new CompositeDisposable();
    // stack request gợi ý từ
    private CompositeDisposable suggestDisposable = new CompositeDisposable();

    public TranslateViewModel(@NonNull Application application) {
        super(application);
    }

    public void translate(String text, String lang) {
        // xóa các request trước đó
        translateDisposable.clear();
        // request transalte
        subscribeTranslate(YandexBuilder.build(YandexService.TRANSLATE_URL).translate(getApplication().getString(R.string.api_key_translate),
                text, lang)
                // tạo request trên thread mới
                .subscribeOn(Schedulers.io())
                // nhận dữ liệu trả về trên main thread
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    // gửi dữ liệu trả về
                    translate.postValue(response);
                }, err -> {
                    err.printStackTrace();
                }));
    }

    public void suggest(String text, String lang) {
        // xóa các request trước
        suggestDisposable.clear();
        // request suggest
        subscribeSuggest(YandexBuilder.build(YandexService.SUGGEST_URL).suggest(getApplication().getString(R.string.api_key_suggest),
                text, lang)
                // tạo request trên thread mới
                .subscribeOn(Schedulers.io())
                // nhận dữ liệu trả về trên main thread
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    // gửi dữ liệu trả về
                    suggest.postValue(response);
                }, err -> {
                    err.printStackTrace();
                }));
    }


    private Disposable subscribeTranslate(Disposable disposable) {
        translateDisposable.add(disposable);
        return disposable;
    }

    /**
     * đưa các request vào stack
     */
    private Disposable subscribeSuggest(Disposable disposable) {
        suggestDisposable.add(disposable);
        return disposable;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        // xóa toàn bộ request hiện tại
        translateDisposable.clear();
        suggestDisposable.clear();
    }

    public MutableLiveData<TranslateResponse> getTranslate() {
        return translate;
    }

    public MutableLiveData<SuggestResponse> getSuggest() {
        return suggest;
    }
}
