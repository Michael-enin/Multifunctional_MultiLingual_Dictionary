package com.example.finalapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class GameZone extends Fragment {
    Activity context;
    FragmentListener listener;
    private Button btnDescription;

    public GameZone() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context=getActivity();
        View view = inflater.inflate(R.layout.fragment_game_zone, container, false);
        btnDescription = (Button) view.findViewById(R.id.btnDescription);
        btnDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               btnDescription = (Button)context.findViewById(R.id.btnDescription);
               Intent intent = new Intent(context, GameActivity.class);
               intent.putExtra("text", btnDescription.getText().toString());
               startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void setOnFragmentListener(FragmentListener listener) {
        if (listener != null)
            this.listener = listener;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);


    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void startDescription() {


    }
}
