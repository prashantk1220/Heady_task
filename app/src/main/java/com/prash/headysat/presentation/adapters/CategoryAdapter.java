package com.prash.headysat.presentation.adapters;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.transition.TransitionManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.prash.headysat.R;
import com.prash.headysat.domain.model.Categories;

import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;

/**
 * Created by prash on 10/12/17.
 */

public class CategoryAdapter extends RealmRecyclerViewAdapter<Categories, CategoryAdapter.CategoryViewHolder>{

    public Context mContext;
    public RealmORMAdapter mRealmORMAdapter;

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
    public void onBindViewHolder(final CategoryViewHolder holder, int position) {
        final Categories obj = getItem(position);
        holder.data = obj;
        holder.categoryTitle.setText(obj.getName());
        LinearLayoutManager layoutManager = new LinearLayoutManager(
                mContext,
                LinearLayoutManager.VERTICAL,
                false
        );
        holder.recyclerList.setLayoutManager(layoutManager);

        if(obj.getProducts().size() > 0) {
            ProductAdapter productAdapter = new ProductAdapter();
            productAdapter.setContext(mContext, mRealmORMAdapter);
            productAdapter.setList(obj.getProducts());
            holder.recyclerList.setAdapter(productAdapter);
        }
        else{
            SubCategoryAdapter subCategoryAdapter = new SubCategoryAdapter();
            subCategoryAdapter.setList(mRealmORMAdapter.getCategoriesfromChild(obj.getChild_categories()));
            subCategoryAdapter.setContext(mContext, mRealmORMAdapter);
            holder.recyclerList.setAdapter(subCategoryAdapter);
        }

        holder.titleCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TransitionManager.beginDelayedTransition(holder.titleCard);
                expandList(holder.titleCard, !holder.isCardExpanded, holder.recyclerList);
                holder.isCardExpanded = !holder.isCardExpanded;
            }
        });
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder {
        public TextView categoryTitle;
        public CardView titleCard;
        public RecyclerView recyclerList;
        public RecyclerView subList;
        public boolean isCardExpanded;
        public Categories data;

        public CategoryViewHolder(View itemView) {
            super(itemView);
            isCardExpanded = false;
            categoryTitle = itemView.findViewById(R.id.categoryTitle);
            recyclerList = itemView.findViewById(R.id.recyclerview);
            titleCard = itemView.findViewById(R.id.titleCard);
        }
    }

    public void expandList(View v, boolean expand, RecyclerView list) {

        ImageView dropDownArrow = v.findViewById(R.id.arrow);

        list.setVisibility(expand ? View.VISIBLE : View.GONE);
        Animation rotate = AnimationUtils.loadAnimation(mContext, R.anim.rotate_image);
        Animation inverseRotate = AnimationUtils.loadAnimation(mContext, R.anim.rotate_image_reverse);
        dropDownArrow.startAnimation(expand ? rotate : inverseRotate);
    }

    public void setContext(Context context, RealmORMAdapter realmORMAdapter){
        mContext = context;
        mRealmORMAdapter = realmORMAdapter;
    }

}
