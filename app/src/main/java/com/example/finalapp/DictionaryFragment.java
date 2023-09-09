package com.example.finalapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class DictionaryFragment extends Fragment {
    ListView dicList;
    ArrayAdapter<String>adapter;
    private String value="Hello EveryOne";
    private FragmentListener listener;
    public DictionaryFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_dictionary, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dicList = view.findViewById(R.id.dictionaryList);
        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, getListOfWords());
        dicList.setAdapter(adapter);
        dicList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(listener!=null)
                    listener.onItemClick(getListOfWords()[position]);
            }
        });

    }
    public void resetDataSource(String[]source){
        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, source);
        dicList.setAdapter(adapter);
    }
            // to filter out the string while search on dictionary
    public void filterValue(String value){
        //  adapter.getFilter().filter(value);
        int size =adapter.getCount();
        for(int i=0;i<size;i++){
            if(adapter.getItem(i).startsWith(value)){
                dicList.setSelection(i);
                break;
            }
        }

    }
    String[] getListOfWords(){
        String[]sources = new String[]{
                "abandon",
                "ability",
                "able",
                "about",
                "about",
                "above",
                "above",
                "abroad",
                "absence",
                "absent",
                "absolute",
                "abstract",
                "abstract",
                "abuse",
                "abuse",
                "abusive",
                "academic",
                "accept",
                "acceptable",
                "acceptance",
                "access",
                "access",
                "accident",
                "accompany",
                "according+to",
                "account",
                "account",
                "accountant",
                "accurate"
        };
        return sources;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();

    }
    public void setOnFragmentListener(FragmentListener listener){
        if(listener!=null)
            this.listener=listener;

    }

}
