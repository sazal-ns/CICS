package com.sazal.siddiqui.cics;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.sazal.siddiqui.cics.DBHelper.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    ListView list ;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        intent = getIntent();
        list = (ListView) findViewById(R.id.list);


        if (intent.getStringExtra("f").contentEquals("ct"))
        {
            CustomList adp = new CustomList(new DBHelper(this).getAllCustomerType(), this);
            list.setAdapter(adp);
            adp.notifyDataSetChanged();
        }else if (intent.getStringExtra("f").contentEquals("p"))
        {
            CustomListPak adp = new CustomListPak(new DBHelper(this).getAllPackage(), this);
            list.setAdapter(adp);
            adp.notifyDataSetChanged();
        }else if (intent.getStringExtra("f").contentEquals("pro"))
        {
            CustomListPro adp = new CustomListPro(new DBHelper(this).getAllPovider(), this);
            list.setAdapter(adp);
            adp.notifyDataSetChanged();

        }




    }
}
