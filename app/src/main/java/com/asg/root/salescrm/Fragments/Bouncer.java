package com.asg.root.salescrm.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.asg.root.salescrm.R;
import com.asg.root.salescrm.Views.DataView;

import org.json.JSONObject;

public class Bouncer extends BaseFragment implements DataView {

    public Bouncer() {
        // Required empty public constructor
    }

    public static Bouncer newInstance(String param1, String param2) {
        return new Bouncer();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bouncer, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDataFetched(JSONObject jsonObject) {

    }

    @Override
    public void onDataFetchedError(String err) {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
}