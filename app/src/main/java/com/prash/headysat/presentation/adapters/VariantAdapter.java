package com.prash.headysat.presentation.adapters;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.prash.headysat.R;
import com.prash.headysat.domain.model.Products;
import com.prash.headysat.domain.model.Variants;

import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;

/**
 * Created by prash on 10/12/17.
 */

public class VariantAdapter extends RealmRecyclerViewAdapter<Variants, VariantAdapter.VariantViewHolder>{


    public VariantAdapter(@Nullable OrderedRealmCollection<Variants> data) {
        super(data, true);
    }

    @Override
    public VariantViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.variant_list_row, parent, false);

        VariantViewHolder variantViewHolder = new VariantViewHolder(view);
        return variantViewHolder;
    }

    @Override
    public void onBindViewHolder(VariantViewHolder holder, int position) {
        final Variants obj = getItem(position);
        holder.data = obj;
        holder.color.setText(obj.getColor());
        holder.size.setText(obj.getSize());
        holder.price.setText(obj.getPrice());
    }

    public static class VariantViewHolder extends RecyclerView.ViewHolder{
        public Variants data;
        public TextView color;
        public TextView size;
        public TextView price;

        public VariantViewHolder(View itemView) {
            super(itemView);
            color = itemView.findViewById(R.id.color);
            size = itemView.findViewById(R.id.size);
            price = itemView.findViewById(R.id.price);
        }
    }
}
