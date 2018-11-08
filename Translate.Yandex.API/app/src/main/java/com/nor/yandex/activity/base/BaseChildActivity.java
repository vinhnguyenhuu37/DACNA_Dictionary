package com.nor.yandex.activity.base;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;

/**
 * activity cho các màn hình con
 */
public abstract class BaseChildActivity<BD extends ViewDataBinding> extends BaseActivity<BD> {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // hiển thị back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // khi click vào back button sẽ quay lại màn hình trước
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
