package com.example.homework3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.* ;

import com.example.homework3.DATA_LINE;
import com.example.homework3.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class listview_adapter extends BaseAdapter {

    private Context context ;
    private ArrayList<DATA_LINE> data_line ;
    private LayoutInflater inflater ;

    static class ViewHolder {
        ImageView icon ;
        TextView name ;
    }

    public listview_adapter(Context context , ArrayList<DATA_LINE> data_line , LayoutInflater li ) {
        this.context = context ;
        this.data_line = data_line ;
        this.inflater = li ;
    }

    @Override
    public int getCount() { return data_line.size() ; }

    @Override
    public Object getItem(int position) { return data_line.get(position) ; }

    @Override
    public long getItemId(int position) { return position ; }

    @Override
    public View getView(int position , View convertView , ViewGroup parent ) {
        ViewHolder holder ;
        if ( convertView == null ) {
            holder = new ViewHolder() ;
            convertView = inflater.inflate(R.layout.listview_itemstyle,parent,false) ;
            holder.icon = (ImageView) convertView.findViewById(R.id.list_icon) ;
            holder.name = (TextView) convertView.findViewById(R.id.list_text) ;
            convertView.setTag(holder) ;
        }else
            holder = (ViewHolder) convertView.getTag() ;

        DATA_LINE data = data_line.get(position) ;
        holder.name.setText(data.getName()) ;
        String url = data.getUrl() ;

        Picasso.with(context)
                .load(url)
                .resize(220,300)
                .into(holder.icon) ;

        return convertView ;

    }

}