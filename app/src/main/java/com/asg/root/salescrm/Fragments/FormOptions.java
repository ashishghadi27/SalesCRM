package com.asg.root.salescrm.Fragments;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.asg.root.salescrm.Activities.Authentication;
import com.asg.root.salescrm.R;

import java.util.Objects;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FormOptions#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FormOptions extends BaseFragment {

    private LinearLayout addSubs, addResp, addSales, addCustomField, addUser;
    private ImageView logout;
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
        addResp = view.findViewById(R.id.addBouncer);
        addSales = view.findViewById(R.id.addConfig);
        addCustomField = view.findViewById(R.id.addCustomField);
        addUser = view.findViewById(R.id.addUser);
        logout = view.findViewById(R.id.logout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = Objects.requireNonNull(getActivity()).getSharedPreferences("user", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("loggedIn", false);
                editor.apply();
                toAuth();
            }
        });

        addSubs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFragment(new Subscriber(), "Subscriber");
            }
        });

        addResp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFragment(new Responder(), "Responder");
            }
        });

        addSales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
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

    private void toAuth(){
        Intent intent = new Intent(getActivity(), Authentication.class);
        startActivity(intent);
        Objects.requireNonNull(getActivity()).finish();
    }

    private void showDialog(){
        final Dialog dialog = new Dialog(Objects.requireNonNull(getContext()));
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.choice);

        TextView message1 = (TextView) dialog.findViewById(R.id.item1);
        TextView message2 = (TextView) dialog.findViewById(R.id.item2);
        TextView dismiss = (TextView) dialog.findViewById(R.id.dismiss);

        message1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        message2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        dialog.show();
    }
}