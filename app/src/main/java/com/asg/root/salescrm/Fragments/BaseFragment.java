package com.asg.root.salescrm.Fragments;

import androidx.fragment.app.Fragment;

import com.asg.root.salescrm.R;

import java.util.Objects;

public class BaseFragment extends Fragment {

    public void addFragment(Fragment fragment, String tag){
        Objects.requireNonNull(getActivity()).getSupportFragmentManager()
                .beginTransaction().add(R.id.fragment_container, fragment, tag).addToBackStack(tag)
                .commit();
    }

    public void replace(Fragment fragment, String tag){
        Objects.requireNonNull(getActivity()).getSupportFragmentManager()
                .beginTransaction().replace(R.id.fragment_container, fragment, tag)
                .commit();
    }
}
