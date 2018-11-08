package com.nor.yandex.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.nor.yandex.R;
import com.nor.yandex.activity.base.BaseActivity;
import com.nor.yandex.dao.AppDao;
import com.nor.yandex.databinding.ActivityMainBinding;
import com.nor.yandex.model.History;
import com.nor.yandex.model.SuggestResponse;
import com.nor.yandex.viewmodel.TranslateViewModel;

public class MainActivity extends BaseActivity<ActivityMainBinding> implements TextWatcher, View.OnClickListener {
    private TranslateViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(TranslateViewModel.class);
        addDataListener();
        addAction();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    private void addDataListener() {
        // nhận dữ liệu transalte
        viewModel.getTranslate().observe(this, response -> {
            if (response.getResults().size() > 0) {
                // hiển thị lên output
                String result = response.getResults().get(0);
                binding.tvOutPut.setText(result);
                // lưu vào history
                if (!result.equalsIgnoreCase(binding.edtInput.getText().toString())) {
                    History history = new History();
                    history.setLang(getTranslateLang());
                    history.setStrFrom(binding.edtInput.getText().toString());
                    history.setStrTo(result);
                    AppDao.getInstance(this).historyDao().insert(history);
                }
            }
        });
        // nhận dữ liệu suggest
        viewModel.getSuggest().observe(this, response -> {
            if (response.getResults().size() > 0) {
                binding.tvSuggest.setText(getString(R.string.suggest_result, response.getResults().get(0)));
            }
        });
    }

    /**
     * lắng nghe sự kiện của view
     */
    private void addAction() {
        binding.edtInput.addTextChangedListener(this);
        binding.imExchange.setOnClickListener(this);
        binding.imHistory.setOnClickListener(this);
        binding.imFavorite.setOnClickListener(this);
        binding.imVolumeInput.setOnClickListener(this);
        binding.imVolumeOut.setOnClickListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        // dịch khi người dùng nhập text
        translate(s.toString());
    }

    private void translate(String s) {
        // xóa output
        if (s.isEmpty()) {
            binding.tvOutPut.setText("");
            return;
        }
        // dịch
        viewModel.translate(s, getTranslateLang());
        // gợi ý từ đúng
        viewModel.suggest(s, getSuggestLang());
    }

    /**
     * lấy ngôn ngữ nhập
     */
    private String getSuggestLang() {
        if (binding.tvFrom.getText().toString().equals(getString(R.string.english))) {
            return "en";
        }
        return "vi";
    }

    /**
     * lấy ngôn ngữ dịch
     */
    private String getTranslateLang() {
        if (binding.tvFrom.getText().toString().equals(getString(R.string.english))) {
            return "en-vi";
        }
        return "vi-en";
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, HistoryActivity.class);
        switch (v.getId()) {
            // đổi loại dịch
            // en - vi
            // vi - en
            case R.id.im_exchange:
                String from = binding.tvFrom.getText().toString();
                binding.tvFrom.setText(binding.tvTo.getText());
                binding.tvTo.setText(from);
                translate(binding.edtInput.getText().toString());
                break;
            case R.id.im_history:
                // show màn hình history
                intent.putExtra(HistoryActivity.EXTRA_HISTORY, true);
                startActivity(intent);
                break;
            case R.id.im_favorite:
                // show màn hình favorite
                startActivity(intent);
                break;
            case R.id.im_volume_input:
                // đọc từ nhập
                TextToSpeechUtils.getInstance(this).speek(binding.edtInput.getText().toString());
                break;
            case R.id.im_volume_out:
                // đọc từ dịch
                TextToSpeechUtils.getInstance(this).speek(binding.tvOutPut.getText().toString());
                break;
        }
    }
}
