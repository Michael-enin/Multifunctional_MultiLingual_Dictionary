package com.example.finalapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class BookmarkFragment extends Fragment {

    private FragmentListener listener;
    private String value="Hello EveryBody";
    public BookmarkFragment() {
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
        return inflater.inflate(R.layout.fragment_bookmark, container, false);
    }
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true); // set true if you navigate to other fragment
        ListView bookmarkList = (ListView) view.findViewById(R.id.bookmarkList);
        final BookmarkAdapter adapter = new BookmarkAdapter(getActivity(), getListOfWords());
        bookmarkList.setAdapter(adapter);

        adapter.setOnItemClick(new ListItemListener() {
            @Override
            public void onItemClick(int position) {
                if(listener!=null)
                    listener.onItemClick(String.valueOf(adapter.getItem(position)));
            }
        });
        adapter.setOnDeleteItemClick(new ListItemListener() {
            @Override
            public void onItemClick(int position) {
//                String value= String.valueOf(adapter.getItem(position));
//                Toast.makeText(getContext(), value + " item is deleted", Toast.LENGTH_SHORT).show();
//                if(listener!=null)
//                    listener.onItemClick(String.valueOf(adapter.getItem(position)));
                adapter.removeItem(position);
                adapter.notifyDataSetChanged();
            }
        });
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }
    public void setOnFragmentListener(FragmentListener listener) {
        this.listener = listener;

    }
    String[] getListOfWords (){
        String[] sources = new String[]{
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
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_clear, menu);
    }
}