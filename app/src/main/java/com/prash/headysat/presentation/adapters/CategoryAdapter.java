package com.prash.headysat.presentation.adapters;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.prash.headysat.R;
import com.prash.headysat.domain.model.Categories;
import com.prash.headysat.domain.model.Rankings;

import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;

/**
 * Created by prash on 10/12/17.
 */

public class CategoryAdapter extends RealmRecyclerViewAdapter<Categories, CategoryAdapter.CategoryViewHolder>{


    public CategoryAdapter(@Nullable OrderedRealmCollection<Categories> data) {
        super(data, true);
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_list_row, parent, false);
        CategoryViewHolder categoryViewHolder = new CategoryViewHolder(itemView);
        return categoryViewHolder;
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position) {

    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder {
        public TextView categoryTitle;


        public CategoryViewHolder(View itemView) {
            super(itemView);

        }
    }
}
