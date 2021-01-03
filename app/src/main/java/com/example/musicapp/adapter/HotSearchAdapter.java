package com.example.musicapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicapp.R;
import com.example.musicapp.response.search.hot.Hot;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class HotSearchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Hot> hots = new ArrayList<>();
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hot_item, parent, false);
        ButterKnife.bind(this, view);
        return new HotViewPage(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HotViewPage) {
            ((HotViewPage) holder).bind(position);
        }
    }

    @Override
    public int getItemCount() {
        return hots.size();
    }

    public class HotViewPage extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_hot)
        TextView tvHot;

        public HotViewPage(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(int position) {
            tvHot.setText(hots.get(position).getFirst());

            itemView.setOnClickListener((view)->{
                EventBus.getDefault().post(hots.get(position));
            });
        }
    }
}
