package com.example.finalapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Arrays;

public class BookmarkAdapter extends BaseAdapter {
    private ListItemListener listener;
    private ListItemListener listenerBtnDelete;
    Context mContext;
    ArrayList<String> mScource;
    public BookmarkAdapter(Context context, String[] source){
        this.mContext = context;
        this.mScource =  new ArrayList<>(Arrays.asList(source));
    }
    @Override
    public int getCount() {
        return mScource.size();
    }

    @Override
    public Object getItem(int position) {
        return mScource.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;

        if(view == null){
            viewHolder = new ViewHolder();

            view = LayoutInflater.from(mContext).inflate(R.layout.bookmark_layout_item, viewGroup, false);
            viewHolder.textView = (TextView) view.findViewById(R.id.tvWord);
            viewHolder.btnDelete = (ImageView) view.findViewById(R.id.btnDelete);
            view.setTag(viewHolder);
        }
        else {
            viewHolder =(ViewHolder) view.getTag();
        }
        viewHolder.textView.setText(mScource.get(position));
        view.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(listener!=null)
                    listener.onItemClick(position);
            }
        });
        viewHolder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listenerBtnDelete!=null)
                    listenerBtnDelete.onItemClick(position);

            }
        });
        return view;
    }
    public void removeItem(int position){
       mScource.remove(position);
    }
    class ViewHolder{
        TextView textView;
        ImageView btnDelete;
    }
    public void setOnItemClick(ListItemListener listItemListener){
        this.listener = listItemListener;
    }
    public void setOnDeleteItemClick(ListItemListener listItemListener){
        this.listenerBtnDelete = listItemListener;
    }


}
