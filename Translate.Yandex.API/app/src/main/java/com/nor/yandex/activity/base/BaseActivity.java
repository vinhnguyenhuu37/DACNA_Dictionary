package com.nor.yandex.activity.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.util.Calendar;
public abstract class BaseActivity<BD extends ViewDataBinding> extends AppCompatActivity {
    protected BD binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, getLayoutId());
    }

    protected abstract int getLayoutId();
}
