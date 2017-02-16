package com.sazal.siddiqui.cics;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sazal.siddiqui.cics.model.CustomerType;
import com.sazal.siddiqui.cics.model.Provider;

import java.util.List;

/**
 * Created by sazal on 2017-02-16.
 */

public class CustomListPro extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<Provider> items;

    public CustomListPro(List<Provider> items, Activity activity) {
        this.items = items;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_row, null);

        TextView title = (TextView) convertView.findViewById(R.id.textView1);
        TextView rating = (TextView) convertView.findViewById(R.id.textView2);
        TextView htitle = (TextView) convertView.findViewById(R.id.headtextView1);
        TextView hrating = (TextView) convertView.findViewById(R.id.headtextView2);
        /*TextView genre = (TextView) convertView.findViewById(R.id.genre);
        TextView year = (TextView) convertView.findViewById(R.id.releaseYear);*/



        Provider c = items.get(position);

     /*   htitle.setText("Provider Name");
        hrating.setText("Area Name");*/
        title.setText(c.getProviderName());
        rating.setText(c.getAreaName());

        return convertView;
    }
}
