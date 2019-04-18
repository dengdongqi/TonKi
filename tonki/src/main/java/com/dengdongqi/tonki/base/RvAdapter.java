package com.dengdongqi.tonki.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * <pre>
 * ◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤
 * ◢══════════════════════════════════════════════════════════════════════
 * |
 * | Created by Tonki on 2019/3/14.
 * |
 * | explain: rv adapter 模板
 * |
 * ◢══════════════════════════════════════════════════════════════════════
 * ◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤
 * </pre>
 */

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.ViewHolder> {
    private Context context;
    private ArrayList<String> data;
    private OnItemClickListener listener;

    public RvAdapter(Context context, ArrayList<String> data) {
        this.context = context;
        this.data = data;
    }

    public void setData(ArrayList<String> data) {
        this.data.clear();
        this.data.addAll(data);
        this.notifyDataSetChanged();
    }

    public void addData(ArrayList<String> data) {
        this.data.addAll(data);
        this.notifyDataSetChanged();
    }

    public void addBean(String data) {
        this.data.add(0, data);
        this.notifyDataSetChanged();
    }

    public ArrayList<String> getData() {
        return data;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(null/*R.layout.item_waitreply*/, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.setData(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvContent, tvTime;

        public ViewHolder(View itemView) {
            super(itemView);
            //init View
        }

        public void setData(final int position) {

        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

}
