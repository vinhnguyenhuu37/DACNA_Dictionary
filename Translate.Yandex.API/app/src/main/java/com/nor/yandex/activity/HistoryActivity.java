package com.nor.yandex.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;

import com.nor.yandex.R;
import com.nor.yandex.activity.base.AdapterHistory;
import com.nor.yandex.activity.base.BaseChildActivity;
import com.nor.yandex.dao.AppDao;
import com.nor.yandex.dao.HistoryDao;
import com.nor.yandex.databinding.ActivityHistoryBinding;
import com.nor.yandex.model.History;

/**
 * hiển thị danh sách history và favorite
 */
public class HistoryActivity extends BaseChildActivity<ActivityHistoryBinding> implements AdapterHistory.OnItemClick {
    public static final String EXTRA_HISTORY = "extra_history";
    private AdapterHistory adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // xác định màn hình là history hoặc favorite
        boolean isHistory = getIntent().hasExtra(EXTRA_HISTORY);
        adapter = new AdapterHistory(this, isHistory);
        binding.lvHistory.setAdapter(adapter);
        HistoryDao dao = AppDao.getInstance(this).historyDao();
        // nếu là history
        if (isHistory) {
            adapter.setData(dao.getHistories());
            adapter.setOnItemClick(this);
            setTitle(R.string.history);
        } else {
            // nếu là favorite
            adapter.setData(dao.getFavorite(true));
            setTitle(R.string.favorite);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_history;
    }

    @Override
    public void onLongClick(History history) {
        // khi click vào item xác nhận xóa
        new AlertDialog.Builder(this)
                .setMessage(R.string.confirm_delete)
                .setNegativeButton(R.string.cancel, (dialog, which) -> {
                })
                .setPositiveButton(R.string.ok, (dialog, which) -> {
                    // xóa item và cập nhập giao diện
                    AppDao.getInstance(this).historyDao().delete(history);
                    adapter.notifyItemRemoved(history);
                })
                .create().show();

    }
}
