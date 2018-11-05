package com.example.vinhdknow.yandexdictionary;

import java.util.List;

public class TranslateText {

    private String sourceLanguage;
    private String targetLanguage;

    //
    private List<String> text;

    public TranslateText(String sourceLanguage, String targetLanguage, List<String> text) {
        this.sourceLanguage = sourceLanguage;
        this.targetLanguage = targetLanguage;
        this.text = text;
    }

    public String getSourceLanguage() {
        return sourceLanguage;
    }

    public void setSourceLanguage(String sourceLanguage) {
        this.sourceLanguage = sourceLanguage;
    }

    public String getTargetLanguage() {
        return targetLanguage;
    }

    public void setTargetLanguage(String targetLanguage) {
        this.targetLanguage = targetLanguage;
    }

    public List<String> getText() {
        return text;
    }

    public void setText(List<String> text) {
        this.text = text;
    }

    public String toString() {
        return "TranslatedText{" +
                "text=" + text +
                '}';
    }
}
