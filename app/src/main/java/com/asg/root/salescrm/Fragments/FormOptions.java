package com.asg.root.salescrm.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.asg.root.salescrm.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FormOptions#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FormOptions extends BaseFragment {

    private LinearLayout addSubs, addBouncer, addConfig, addCustomField, addUser;
    public FormOptions() {
        // Required empty public constructor
    }

    public static FormOptions newInstance(String param1, String param2) {
        return new FormOptions();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addSubs = view.findViewById(R.id.addSubscriber);
        addBouncer = view.findViewById(R.id.addBouncer);
        addConfig = view.findViewById(R.id.addConfig);
        addCustomField = view.findViewById(R.id.addCustomField);
        addUser = view.findViewById(R.id.addUser);
        addSubs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFragment(new Subscriber(), "Subscriber");
            }
        });

        addBouncer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFragment(new Bouncer(), "Bouncer");
            }
        });

        addConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFragment(new Config(), "Config");
            }
        });

        addCustomField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFragment(new CustomFields(), "Custom");
            }
        });

        addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFragment(new User(), "User");
            }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_form_options, container, false);
    }
}