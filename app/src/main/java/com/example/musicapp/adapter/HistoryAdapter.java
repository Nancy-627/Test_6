package com.example.musicapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.LogUtils;
import com.example.musicapp.R;
import com.example.musicapp.model.entities.HistoryEvent;
import com.example.musicapp.util.TagUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class HistoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private String TAG = TagUtils.getTag(this.getClass());
    private List<String> histories = new ArrayList<>();

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hot_item, parent, false);
        ButterKnife.bind(this, view);
        return new HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HistoryViewHolder) {
            ((HistoryViewHolder) holder).bind(position);
        }
    }

    @Override
    public int getItemCount() {
        LogUtils.d(TAG, histories.size());
        return histories.size();
    }

    public class HistoryViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_hot)
        TextView tvHistory;

        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(int position) {
            tvHistory.setText(histories.get(position));

            itemView.setOnClickListener((view) -> {
                EventBus.getDefault().post(new HistoryEvent(histories.get(position)));
            });
        }
    }

}