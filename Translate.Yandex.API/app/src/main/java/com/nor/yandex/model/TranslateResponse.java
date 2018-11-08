package com.nor.yandex.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * dữ liệu trả về cho api dịch
 */
public class TranslateResponse {
    // status code trả về có thành công hoặc thất bại
    private int code;
    // ngôn ngữ dịch
    private String lang;
    // nội dung nếu có lỗi
    private String message;
    // kết quả trả về
    @SerializedName("text")
    private ArrayList<String> results;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<String> getResults() {
        return results;
    }

    public void setResults(ArrayList<String> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        String result = "";
        if (results != null) {
            for (String s : results) {
                result += s + "\n";
            }
            return result;
        } else {
            return message;
        }
    }
}
