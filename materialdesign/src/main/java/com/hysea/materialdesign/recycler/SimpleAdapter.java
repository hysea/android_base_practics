package com.hysea.materialdesign.recycler;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hysea.materialdesign.R;

import java.util.List;

public class SimpleAdapter extends RecyclerView.Adapter<SimpleAdapter.SimpleViewHolder> {

    private Context context;
    private List<SimpleBean> list;

    public SimpleAdapter(Context context, List<SimpleBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public SimpleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_content, parent, false);
        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SimpleViewHolder simpleViewHolder, int position) {
        simpleViewHolder.tvTitle.setText(list.get(position).getTitle());
        simpleViewHolder.tvDes.setText(list.get(position).getDesc());
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    static class SimpleViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvDes;

        public SimpleViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDes = itemView.findViewById(R.id.tv_desc);
        }
    }
}
