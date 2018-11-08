package com.nor.yandex.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * data trả về khi gọi request suggest
 */
public class SuggestResponse {
    // status code trả về có thành công hoặc thất bại
    private int code;
    // nội dung nếu có lỗi
    private String message;
    private int pos;
    // kết quả trả về
    @SerializedName("text")
    private ArrayList<String> results;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
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
