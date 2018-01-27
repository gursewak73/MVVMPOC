package com.architecturepoc.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.architecturepoc.R;
import com.architecturepoc.db.entity.EntityUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gursewaksingh on 27/01/18.
 */

public class RecyclerAdapterUserData extends RecyclerView.Adapter<RecyclerAdapterUserData.ViewHolder> {

    private List<EntityUser> entityUsers = new ArrayList<>();
    private Context context;

    public RecyclerAdapterUserData(List<EntityUser> entityUsers, Context context) {
        this.entityUsers.addAll(entityUsers);
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_data, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvData.setText(entityUsers.get(position).getId()  + " || " + entityUsers.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return entityUsers.size();
    }

    public void refresh(List<EntityUser> entityUsers){
        this.entityUsers.clear();
        this.entityUsers.addAll(entityUsers);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvData;

        public ViewHolder(View itemView) {
            super(itemView);
            tvData = itemView.findViewById(R.id.tvData);
        }
    }
}
