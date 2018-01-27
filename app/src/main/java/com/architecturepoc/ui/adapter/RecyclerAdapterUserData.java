package com.architecturepoc.ui.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.architecturepoc.R;
import com.architecturepoc.databinding.ItemDataBinding;
import com.architecturepoc.db.entity.EntityUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gursewaksingh on 27/01/18.
 */

public class RecyclerAdapterUserData extends RecyclerView.Adapter<RecyclerAdapterUserData.ViewHolder> {

    private List<EntityUser> entityUsers = new ArrayList<>();

    public RecyclerAdapterUserData(List<EntityUser> entityUsers, Context context) {
        this.entityUsers.addAll(entityUsers);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_data, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.binding.setUser(entityUsers.get(position));
    }

    @Override
    public int getItemCount() {
        return entityUsers.size();
    }

    public void refresh(List<EntityUser> entityUsers) {
        this.entityUsers.clear();
        this.entityUsers.addAll(entityUsers);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ItemDataBinding binding;

        public ViewHolder(ItemDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}