package com.nor.yandex.activity.base;

import android.databinding.BindingAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Binding {
    private static SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:sss");

    @BindingAdapter("app:date_time")
    public static void setDatetime(TextView tv, long time) {
        tv.setText(format.format(new Date(time)));
    }
}
