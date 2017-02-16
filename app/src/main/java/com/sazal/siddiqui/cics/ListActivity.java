package com.sazal.siddiqui.cics;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.sazal.siddiqui.cics.DBHelper.DBHelper;

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
        {CustomList adp = new CustomList(new DBHelper(this).getAllCustomerType(), this);
            list.setAdapter(adp);
            adp.notifyDataSetChanged();}




    }
}
