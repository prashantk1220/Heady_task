package com.prash.headysat.presentation.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prash.headysat.R;
import com.prash.headysat.presentation.adapters.CategoryAdapter;
import com.prash.headysat.presentation.adapters.ProductAdapter;
import com.prash.headysat.presentation.adapters.RealmORMAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductsFragment extends Fragment {


    public ProductsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        RealmORMAdapter mRealmORMAdapter = new RealmORMAdapter();
        View view = inflater.inflate(R.layout.fragment_products, container, false);
        RecyclerView recyclerProdcutView = view.findViewById(R.id.recyclerview);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerProdcutView.setLayoutManager(layoutManager);
        recyclerProdcutView.setItemAnimator(new DefaultItemAnimator());

        ProductAdapter productAdapter = new ProductAdapter();
        productAdapter.setContext(getActivity(), mRealmORMAdapter);
        productAdapter.setList(mRealmORMAdapter.getProducts());
        recyclerProdcutView.setAdapter(productAdapter);

        return view;
    }

}
