package com.prash.headysat.presentation.adapters;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    Context mContext;
    RealmORMAdapter mRealmORMAdapter;
    RealmList<Products> mProductsList;

    public ProductAdapter() {

    }

    public void setList(RealmList<Products> data){
        mProductsList = data;
        this.notifyDataSetChanged();
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_list_row, parent, false);

        ProductViewHolder productViewHolder = new ProductViewHolder(view);
        return productViewHolder;
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        Products products = mProductsList.get(position);
        holder.productName.setText(products.getName());
        holder.productTax.setText(products.getTax().getName() + ": " + products.getTax().getValue() + "%");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                mContext,
                LinearLayoutManager.HORIZONTAL,
                false
        );
        VariantAdapter variantAdapter = new VariantAdapter(mRealmORMAdapter.getVariants(products.getId()));
        holder.variantsList.setLayoutManager(linearLayoutManager);
        holder.variantsList.setAdapter(variantAdapter);

    }

    @Override
    public int getItemCount() {
        return mProductsList.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder{
        public Products data;
        public TextView productName;
        public TextView productTax;
        public RecyclerView variantsList;
        public ProductViewHolder(View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.productName);
            productTax = itemView.findViewById(R.id.tax);
            variantsList = itemView.findViewById(R.id.variants_recycler_view);
        }
    }

    public void setContext(Context context, RealmORMAdapter realmORMAdapter){
        mContext = context;
        mRealmORMAdapter = realmORMAdapter;
    }



}
