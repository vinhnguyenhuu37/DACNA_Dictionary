package com.nor.yandex.activity.base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nor.yandex.dao.AppDao;
import com.nor.yandex.databinding.ItemHistoryBinding;
import com.nor.yandex.model.History;

import java.util.List;

public class AdapterHistory extends RecyclerView.Adapter<AdapterHistory.ViewHolder> {
    private LayoutInflater inflater;
    private List<History> data;
    // bắt sự kiện khi click vào item
    private OnItemClick onItemClick;
    // là loại history hoặc favorite
    private boolean isHistory;

    public AdapterHistory(Context context, boolean isHistory) {
        this.inflater = LayoutInflater.from(context);
        this.isHistory = isHistory;
    }

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    /**
     * cập nhập giao diện khi data thay đổi
     */
    public void setData(List<History> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(ItemHistoryBinding.inflate(inflater));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        History history = data.get(i);
        // hiển thị data lên giao diện
        viewHolder.binding.setItem(history);
        // nếu là favorite thì image favorite sẽ sáng
        viewHolder.binding.imFavorite.setSelected(history.isFavorite());
        viewHolder.binding.imFavorite.setOnClickListener(v -> {
            // nếu đang là favorite thì bỏ favorite và ngược lại
            history.setFavorite(!history.isFavorite());
            AppDao.getInstance(viewHolder.itemView.getContext()).historyDao().update(history);
            if (isHistory) {
                notifyItemChanged(i);
            } else {
                // cập nhập giao diện nếu bỏ favorite
                data.remove(i);
                notifyItemRemoved(i);
                notifyItemRangeChanged(i, getItemCount());
            }
        });
        if (onItemClick != null) {
            viewHolder.itemView.setOnLongClickListener(v -> {
                onItemClick.onLongClick(history);
                return true;
            });
        }
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    /**
     * xóa phần tử
     */
    public void notifyItemRemoved(History history) {
        int index = data.indexOf(history);
        data.remove(index);
        notifyItemRemoved(index);
        notifyItemRangeChanged(index, getItemCount());
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemHistoryBinding binding;

        public ViewHolder(@NonNull ItemHistoryBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public interface OnItemClick {
        void onLongClick(History history);
    }
}
