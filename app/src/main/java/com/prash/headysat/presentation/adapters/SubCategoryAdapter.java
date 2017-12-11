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
import com.prash.headysat.domain.model.Products;

import io.realm.OrderedRealmCollection;
import io.realm.RealmList;
import io.realm.RealmRecyclerViewAdapter;

/**
 * Created by prash on 10/12/17.
 */

public class SubCategoryAdapter extends RecyclerView.Adapter<SubCategoryAdapter.SubCategoryViewHolder> {

    public Context mContext;
    public RealmORMAdapter mRealmORMAdapter;
    RealmList<Categories> mCategoriesList;

    public SubCategoryAdapter() {

    }

    public void setList(RealmList<Categories> categoriesList){
        mCategoriesList = categoriesList;
    }

    @Override
    public SubCategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_list_row, parent, false);
        SubCategoryViewHolder subCategoryViewHolder = new SubCategoryViewHolder(itemView);
        return subCategoryViewHolder;
    }

    @Override
    public void onBindViewHolder(final SubCategoryViewHolder holder, int position) {
        Categories category = mCategoriesList.get(position);
        holder.categoryTitle.setText(category.getName());
        LinearLayoutManager layoutManager = new LinearLayoutManager(
                mContext,
                LinearLayoutManager.VERTICAL,
                false
        );
        holder.recyclerList.setLayoutManager(layoutManager);
        if(category.getProducts().size() > 0) {
            ProductAdapter productAdapter = new ProductAdapter();
            productAdapter.setContext(mContext, mRealmORMAdapter);
            productAdapter.setList(category.getProducts());
            holder.recyclerList.setAdapter(productAdapter);
        }
        else{
            SubCategoryAdapter subCategoryAdapter = new SubCategoryAdapter();
            subCategoryAdapter.setList(mRealmORMAdapter.getCategoriesfromChild(category.getChild_categories()));
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

    @Override
    public int getItemCount() {
        return mCategoriesList.size();
    }

    public static class SubCategoryViewHolder extends RecyclerView.ViewHolder{
        public TextView categoryTitle;
        public CardView titleCard;
        public RecyclerView recyclerList;
        public boolean isCardExpanded;

        public SubCategoryViewHolder(View itemView) {
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
