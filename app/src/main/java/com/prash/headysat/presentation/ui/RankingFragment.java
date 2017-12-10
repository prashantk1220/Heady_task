package com.prash.headysat.presentation.ui;


import android.app.Activity;
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
import com.prash.headysat.domain.model.Rankings;
import com.prash.headysat.domain.model.ResponseData;
import com.prash.headysat.presentation.adapters.RankingAdapter;
import com.prash.headysat.presentation.adapters.RealmORMAdapter;

import io.realm.Realm;
import io.realm.RealmList;


/**
 * A simple {@link Fragment} subclass.
 */
public class RankingFragment extends Fragment {

    View mView;
    RecyclerView mRecyclerRankingList;
    RealmORMAdapter mRealmORMAdapter;

    public RankingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRealmORMAdapter = ((MainApplication)(getActivity()).getApplication()).getRealmORMAdapter();
        // Inflate the layout for this fragment
        mView =  inflater.inflate(R.layout.fragment_ranking, container, false);
        mRecyclerRankingList = mView.findViewById(R.id.rankingParentList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerRankingList.setLayoutManager(layoutManager);
        mRecyclerRankingList.setItemAnimator(new DefaultItemAnimator());

        RankingAdapter rankingAdapter = new RankingAdapter(mRealmORMAdapter.getRankings());
        rankingAdapter.setContext(getContext(), mRealmORMAdapter);
        mRecyclerRankingList.setAdapter(rankingAdapter);


        return mView;
    }



}
