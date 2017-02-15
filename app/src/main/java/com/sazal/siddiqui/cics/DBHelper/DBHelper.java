package com.sazal.siddiqui.cics.DBHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.sazal.siddiqui.cics.model.CustomerInformation;
import com.sazal.siddiqui.cics.model.CustomerType;
import com.sazal.siddiqui.cics.model.Package;
import com.sazal.siddiqui.cics.model.Provider;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by sazal on 2017-02-12.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final String TAG = "DBHelper";

    private static final int DATABASE_VERSION =1;
    private static final String DATABASE_NAME = "CICS";

    private static final String TABLE_PACKAGE = "package";
    private static final String TABLE_PROVIDER ="provider";
    private static final String TABLE_CUSTOMER_TYPE ="customerType";
    private static final String TABLE_CUSTOMER_INFO = "customerInfo";
    private static final String TABLE_CUSTOMER_PACKAGE = "customerXpackage";

    private static final String KEY_CREATED_ON = "createdOn";
    private static final String KEY_CREATED_BY = "createdBy";
    private static final String KEY_UPDATED_ON = "updatedON";
    private static final String KEY_UPDATED_BY = "updatedBy";

    private static final String KEY_CUSTOM_FIELD1 = "customField1";
    private static final String KEY_CUSTOM_FIELD2 = "customField2";
    private static final String KEY_CUSTOM_FIELD3 = "customField3";
    private static final String KEY_CUSTOM_FIELD4 = "customField4";

    private static final String KEY_PACKAGE_ID = "packageID";
    private static final String KEY_PACKAGE_NAME = "packageName";
    private static final String KEY_TOTAL_CHANNELS = "totalChannels";
    private static final String KEY_PRICE = "price";

    private static final String KEY_PROVIDER_ID = "providerId";
    private static final String KEY_PARENT_PROVIDER_ID = "parentProviderID";
    private static final String KEY_PROVIDER_NAME = "providerName";
    private static final String KEY_AREA_NAME = "areaName";

    private static final String KEY_TYPE_ID = "typeID";
    private static final String KEY_TYPE_NAME = "typeName";

    private static final String KEY_CUSTOMER_ID = "customerID";
    private static final String KEY_NAME_BANGLA = "nameBangla";
    private static final String KEY_NAME_ENGLISH = "nameEnglish";
    private static final String KEY_CUSTOMER_NUMBER = "customerNumber";
    private static final String KEY_MOBILE = "mobile";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_ALT_CONTACT_NUMBER = "altContactNumber";
    private static final String KEY_FIRST_CONNECTION_DATE ="firstConnectionDate";

    private static final String KEY_ID = "_id";

    private static final String CREATE_TABLE_PACKAGE = "CREATE TABLE " + TABLE_PACKAGE +" (" +
            KEY_ID +" INTEGER PRIMARY KEY,"+
            KEY_PACKAGE_NAME +" TEXT NOT NULL," +
            KEY_TOTAL_CHANNELS +" INTEGER NOT NULL,"+
            KEY_PRICE +" REAL NOT NULL,"+
            KEY_CUSTOM_FIELD1 +" TEXT," +
            KEY_CUSTOM_FIELD2 +" TEXT," +
            KEY_CUSTOM_FIELD3 +" TEXT," +
            KEY_CUSTOM_FIELD4 +" TEXT," +
            KEY_CREATED_ON +" DATETIME,"+
            KEY_UPDATED_ON +" DATETIME,"+
            KEY_CREATED_BY + " INTEGER,"+
            KEY_UPDATED_BY +" INTEGER"+ ")";

    private static final String CREATE_TABLE_PROVIDER = "CREATE TABLE " + TABLE_PROVIDER +" (" +
            KEY_ID +" INTEGER PRIMARY KEY,"+
            KEY_PARENT_PROVIDER_ID +" INTEGER NOT NULL," +
            KEY_PROVIDER_NAME +" TEXT NOT NULL,"+
            KEY_AREA_NAME +" TEXT NOT NULL,"+
            KEY_CUSTOM_FIELD1 +" TEXT," +
            KEY_CUSTOM_FIELD2 +" TEXT," +
            KEY_CUSTOM_FIELD3 +" TEXT," +
            KEY_CUSTOM_FIELD4 +" TEXT," +
            KEY_CREATED_ON +" DATETIME,"+
            KEY_UPDATED_ON +" DATETIME,"+
            KEY_CREATED_BY + " INTEGER,"+
            KEY_UPDATED_BY +" INTEGER"+ ")";

    private static final String CREATE_TABLE_CUSTOMER_TYPE = "CREATE TABLE " + TABLE_CUSTOMER_TYPE +" ( " +
            KEY_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            KEY_TYPE_NAME +" TEXT NOT NULL, "+
            KEY_CREATED_ON +" DATETIME, "+
            KEY_UPDATED_ON +" DATETIME, "+
            KEY_CREATED_BY + " INTEGER, "+
            KEY_UPDATED_BY +" INTEGER )";

    private static final String CREATE_TABLE_CUSTOMER_INFO = "CREATE TABLE " + TABLE_CUSTOMER_INFO +" (" +
            KEY_ID +" INTEGER PRIMARY KEY,"+
            KEY_PROVIDER_ID +" INTEGER NOT NULL," +
            KEY_NAME_BANGLA +" TEXT,"+
            KEY_NAME_ENGLISH +" TEXT NOT NULL,"+
            KEY_CUSTOMER_NUMBER +" TEXT NOT NULL,"+
            KEY_MOBILE +" TEXT NOT NULL,"+
            KEY_EMAIL +" EMAIL NOT NULL,"+
            KEY_ALT_CONTACT_NUMBER +"TEXT,"+
            KEY_FIRST_CONNECTION_DATE +" DATETIME,"+
            KEY_CUSTOM_FIELD1 +" TEXT," +
            KEY_CUSTOM_FIELD2 +" TEXT," +
            KEY_CUSTOM_FIELD3 +" TEXT," +
            KEY_CUSTOM_FIELD4 +" TEXT," +
            KEY_CREATED_ON +" DATETIME,"+
            KEY_UPDATED_ON +" DATETIME,"+
            KEY_CREATED_BY + " INTEGER,"+
            KEY_UPDATED_BY +" INTEGER"+ ")";

    private static final String CREATE_TABLE_CUSTOMER_PACKAGE = "CREATE TABLE " + TABLE_CUSTOMER_PACKAGE +" (" +
            KEY_ID +" INTEGER PRIMARY KEY,"+
            KEY_CUSTOMER_ID +" INTEGER NOT NULL,"+
            KEY_PACKAGE_ID + " INTEGER NOT NULL,"+
            KEY_CUSTOM_FIELD1 +" TEXT," +
            KEY_CUSTOM_FIELD2 +" TEXT," +
            KEY_CUSTOM_FIELD3 +" TEXT," +
            KEY_CUSTOM_FIELD4 +" TEXT," +
            KEY_CREATED_ON +" DATETIME,"+
            KEY_UPDATED_ON +" DATETIME,"+
            KEY_CREATED_BY + " INTEGER,"+
            KEY_UPDATED_BY +" INTEGER"+ ")";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.e("DB","onCreate");
        db.execSQL(CREATE_TABLE_CUSTOMER_INFO);
        db.execSQL(CREATE_TABLE_CUSTOMER_PACKAGE);
        db.execSQL(CREATE_TABLE_CUSTOMER_TYPE);
        db.execSQL(CREATE_TABLE_PACKAGE);
        db.execSQL(CREATE_TABLE_PROVIDER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.e(TAG,"onUpgrade");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CUSTOMER_PACKAGE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROVIDER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CUSTOMER_TYPE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CUSTOMER_INFO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PACKAGE);

        onCreate(db);
    }

    public long insertCustomerType(CustomerType customerType){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_TYPE_NAME,customerType.getTypeName());
        values.put(KEY_CREATED_ON,getDateTime());

         long result =db.insert(TABLE_CUSTOMER_TYPE,null,values);
        closeDB();
        return result;
        }

    public long insertPackage(Package aPackage){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_PACKAGE_NAME, aPackage.getPackageName());
        values.put(KEY_PRICE,aPackage.getPrice());
        values.put(KEY_TOTAL_CHANNELS,aPackage.getTotalChannels());
        values.put(KEY_CREATED_ON, getDateTime());

        long r = db.insert(TABLE_PACKAGE,null,values);
        closeDB();
        return r;
    }

    public long insertProvider(Provider provider){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_PROVIDER_NAME, provider.getProviderName());
        values.put(KEY_AREA_NAME, provider.getAreaName());
        values.put(KEY_PARENT_PROVIDER_ID, provider.getParentProviderId());
        values.put(KEY_CREATED_ON, getDateTime());

        long r = db.insert(TABLE_PROVIDER,null,values);
        closeDB();
        return r;
    }

    public  long insertCustomerInformation(CustomerInformation customerInformation){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_PROVIDER_ID, customerInformation.getProvider().getProviderId());
        values.put(KEY_NAME_ENGLISH, customerInformation.getNameEnglish());
        values.put(KEY_CUSTOMER_NUMBER, customerInformation.getCoustomerNumber());
        values.put(KEY_MOBILE, customerInformation.getMobile());
        values.put(KEY_EMAIL,customerInformation.getEmail());
        values.put(KEY_ALT_CONTACT_NUMBER, customerInformation.getAltContactNumber());
        values.put(KEY_FIRST_CONNECTION_DATE, customerInformation.getFirstConectionDate());
        values.put(KEY_UPDATED_ON, getDateTime());

        long r = db.insert(TABLE_CUSTOMER_INFO,null,values);
        closeDB();
        return r;
    }

    private void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }

    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
}
