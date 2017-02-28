package com.sazal.siddiqui.cics;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.sazal.siddiqui.cics.DBHelper.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    ListView list ;
    Intent intent;

    private TextView headtextView1, headtextView2, headtextView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        headtextView1 = (TextView) findViewById(R.id.headtextView1);
        headtextView2 = (TextView) findViewById(R.id.headtextView2);
        headtextView3 = (TextView) findViewById(R.id.headtextView3);


        intent = getIntent();
        list = (ListView) findViewById(R.id.list);


        if (intent.getStringExtra("f").contentEquals("ct"))
        {
            headtextView1.setText("Type Name");
            /*headtextView2.setText("Total Channels");
            headtextView3.setText("Price");*/
            CustomList adp = new CustomList(new DBHelper(this).getAllCustomerType(), this);
            list.setAdapter(adp);
            adp.notifyDataSetChanged();
        }else if (intent.getStringExtra("f").contentEquals("p"))
        {
            headtextView1.setText("Package Name");
            headtextView2.setText("Total Channels");
            headtextView3.setText("Price");
            CustomListPak adp = new CustomListPak(new DBHelper(this).getAllPackage(), this);
            list.setAdapter(adp);
            adp.notifyDataSetChanged();
        }else if (intent.getStringExtra("f").contentEquals("pro"))
        {
            headtextView1.setText("Provider Name");
            headtextView2.setText("Area Name");

            CustomListPro adp = new CustomListPro(new DBHelper(this).getAllPovider(), this);
            list.setAdapter(adp);
            adp.notifyDataSetChanged();

        }else if (intent.getStringExtra("f").contentEquals("cxp"))
        {
            headtextView1.setText("Customer Name");
            headtextView2.setText("Provider Name");
            headtextView3.setText("Package Name");

            CustomCxpList adp = new CustomCxpList(new DBHelper(this).getAllCXP(), this);
            Log.e("check", adp.toString());
            list.setAdapter(adp);
            adp.notifyDataSetChanged();

        }




    }
}
