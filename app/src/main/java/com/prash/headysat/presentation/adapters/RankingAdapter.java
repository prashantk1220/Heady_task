package com.prash.headysat.presentation.adapters;

import android.content.Context;
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
import com.prash.headysat.domain.model.Products;
import com.prash.headysat.domain.model.Rankings;


import io.realm.OrderedRealmCollection;
import io.realm.RealmList;
import io.realm.RealmRecyclerViewAdapter;


/**
 * Created by prash on 10/12/17.
 */

public class RankingAdapter extends RealmRecyclerViewAdapter<Rankings, RankingAdapter.RankingViewHolder>{

    RealmORMAdapter mRealmORMAdapter;
    Context mContext;
    ProductAdapter mProductAdapter;

    public RankingAdapter(OrderedRealmCollection<Rankings> data){
       super(data, true);
    }

    @Override
    public RankingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rank_list_row, parent, false);

        RankingViewHolder rankingViewHolder = new RankingViewHolder(itemView);

        return rankingViewHolder;
    }

    @Override
    public void onBindViewHolder(final RankingViewHolder holder, final int position) {
        final Rankings obj = getItem(position);
        holder.data = obj;
        holder.rankTitle.setText(obj.getRankingname());
        mProductAdapter = new ProductAdapter();
        mProductAdapter.setContext(mContext, mRealmORMAdapter);
        mProductAdapter.setList(mRealmORMAdapter.getRankingsCategory(position));
        LinearLayoutManager layoutManager = new LinearLayoutManager(
                mContext,
                LinearLayoutManager.VERTICAL,
                false
        );
        holder.productsList.setLayoutManager(layoutManager);
        holder.productsList.setAdapter(mProductAdapter);
        holder.sortIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mProductAdapter.setList(sortData(position, holder.isDecending));
                holder.isDecending = !holder.isDecending;
            }
        });
        holder.titleCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TransitionManager.beginDelayedTransition(holder.titleCard);
                expandList(holder.titleCard, !holder.isCardExpanded, holder.productsList);
                holder.isCardExpanded = !holder.isCardExpanded;
            }
        });

    }

    public void setContext(Context context, RealmORMAdapter realmORMAdapter){
        mContext = context;
        mRealmORMAdapter = realmORMAdapter;
    }

    public static class RankingViewHolder extends RecyclerView.ViewHolder {
        public Rankings data;
        public TextView rankTitle;
        public CardView titleCard;
        public ImageView sortIcon;
        public RecyclerView productsList;
        public boolean isCardExpanded;
        public boolean isDecending;

        public RankingViewHolder(View view) {
            super(view);
            rankTitle = view.findViewById(R.id.rankingTitle);
            titleCard = view.findViewById(R.id.titleCard);
            productsList = view.findViewById(R.id.productsListrank);
            sortIcon = view.findViewById(R.id.sort);
            isCardExpanded = false;
            isDecending = false;
        }
    }

    public void expandList(View v, boolean expand, RecyclerView list) {

        ImageView dropDownArrow = v.findViewById(R.id.arrow);

        list.setVisibility(expand ? View.VISIBLE:View.GONE);
        Animation rotate = AnimationUtils.loadAnimation(mContext, R.anim.rotate_image);
        Animation inverseRotate = AnimationUtils.loadAnimation(mContext, R.anim.rotate_image_reverse);
        dropDownArrow.startAnimation(expand? rotate:inverseRotate);


    }

    public RealmList<Products> sortData(int filter, boolean decend){
       return mRealmORMAdapter.sortRankings(filter, decend);

    }

}
