package com.example.finalapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class DetailFragment extends Fragment {

    private String value = "";
    private TextView tvWord;
    private ImageButton btnVlm, btnFavorite;
    private WebView tvWordTranslate;
    public DetailFragment() {
        // Required empty public constructor
    }
    public static DetailFragment getNewInstance(String value){
        DetailFragment fragment= new DetailFragment();
        fragment.value = value;
        return fragment;
    }
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //setHasOptionsMenu(true);
        tvWord = (TextView) view.findViewById(R.id.tvWord);
        tvWordTranslate =  (WebView)view.findViewById(R.id.tvWordTranslate);
        btnFavorite = (ImageButton) view.findViewById(R.id.btnFavorite);
        btnVlm =  (ImageButton) view.findViewById(R.id.btnVolume);
        btnFavorite.setTag(0);
        btnFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i=(int)btnFavorite.getTag();
                if(i==0){
                    btnFavorite.setImageResource(R.drawable.ic_favorite_fill);
                    btnFavorite.setTag(1);
                }
                else if(i==1){
                    btnFavorite.setImageResource(R.drawable.ic_favorite);
                    btnFavorite.setTag(0);
                }
            }
        });
       // Toast.makeText(getContext(),this.value,Toast.LENGTH_SHORT).show(); // the text of line is toasted
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
    public void onDetach() {
        super.onDetach();
    }
}
