package com.prash.headysat.presentation.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prash.headysat.MainApplication;
import com.prash.headysat.R;
import com.prash.headysat.presentation.adapters.CategoryAdapter;
import com.prash.headysat.presentation.adapters.RankingAdapter;
import com.prash.headysat.presentation.adapters.RealmORMAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends Fragment {


    public CategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        RealmORMAdapter realmORMAdapter = ((MainApplication)(getActivity()).getApplication()).getRealmORMAdapter();
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_category, container, false);
        RecyclerView recyclerCategoryList = view.findViewById(R.id.categoryList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerCategoryList.setLayoutManager(layoutManager);
        recyclerCategoryList.setItemAnimator(new DefaultItemAnimator());

        CategoryAdapter categoryAdapter = new CategoryAdapter(realmORMAdapter.getCategories());
        categoryAdapter.setContext(getContext(), realmORMAdapter);
        recyclerCategoryList.setAdapter(categoryAdapter);

        return  view;
    }

}
