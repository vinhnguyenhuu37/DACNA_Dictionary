package com.nor.yandex.activity;

import android.content.Context;
import android.os.Build;
import android.speech.tts.TextToSpeech;

import java.util.Locale;

public class TextToSpeechUtils {
    private static TextToSpeechUtils instance;
    // object để đọc
    private TextToSpeech speech;

    public static TextToSpeechUtils getInstance(Context context) {
        if (instance == null) {
            // load bộ đọc
            instance = new TextToSpeechUtils();
            instance.speech = new TextToSpeech(context, status -> {
            });
        }
        return instance;
    }

    /**
     * phát âm
     *
     * @param text từ cần đọc
     */
    public void speek(String text) {
        speech.setLanguage(Locale.US);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            speech.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
        } else {
            speech.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        }
    }
}
