package com.sazal.siddiqui.cics.DBHelper;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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

    private static final String KEY_ID = "id";

    private static final String CREATE_TABLE_PACKAGE = "CREATE TABLE " + TABLE_PACKAGE +"(" +
            KEY_PACKAGE_ID +"INTEGER PRIMARY KEY,"+
            KEY_PACKAGE_NAME +"TEXT," +
            KEY_TOTAL_CHANNELS +"INTEGER,"+
            KEY_PRICE +"DOUBLE,"+
            KEY_CUSTOM_FIELD1 +"TEXT," +
            KEY_CUSTOM_FIELD2 +"TEXT," +
            KEY_CUSTOM_FIELD3 +"TEXT," +
            KEY_CUSTOM_FIELD4 +"TEXT," +
            KEY_CREATED_ON +"DATETIME,"+
            KEY_UPDATED_ON +"DATETIME,"+
            KEY_CREATED_BY + "INTEGER,"+
            KEY_UPDATED_BY +"INTEGER"+ ")";

    private static final String CREATE_TABLE_PROVIDER = "CREATE TABLE " + TABLE_PROVIDER +"(" +
            KEY_PROVIDER_ID +"INTEGER PRIMARY KEY,"+
            KEY_PARENT_PROVIDER_ID +"INTEGER," +
            KEY_PROVIDER_NAME +"TEXT,"+
            KEY_AREA_NAME +"TEXT,"+
            KEY_CUSTOM_FIELD1 +"TEXT," +
            KEY_CUSTOM_FIELD2 +"TEXT," +
            KEY_CUSTOM_FIELD3 +"TEXT," +
            KEY_CUSTOM_FIELD4 +"TEXT," +
            KEY_CREATED_ON +"DATETIME,"+
            KEY_UPDATED_ON +"DATETIME,"+
            KEY_CREATED_BY + "INTEGER,"+
            KEY_UPDATED_BY +"INTEGER"+ ")";

    private static final String CREATE_TABLE_CUSTOMER_TYPE = "CREATE TABLE " + TABLE_CUSTOMER_TYPE +"(" +
            KEY_TYPE_ID +"INTEGER PRIMARY KEY,"+
            KEY_TYPE_NAME +"TEXT,"+
            KEY_CREATED_ON +"DATETIME,"+
            KEY_UPDATED_ON +"DATETIME,"+
            KEY_CREATED_BY + "INTEGER,"+
            KEY_UPDATED_BY +"INTEGER"+ ")";

    private static final String CREATE_TABLE_CUSTOMER_INFO = "CREATE TABLE " + TABLE_CUSTOMER_INFO +"(" +
            KEY_CUSTOMER_ID +"INTEGER PRIMARY KEY,"+
            KEY_PROVIDER_ID +"INTEGER," +
            KEY_NAME_BANGLA +"TEXT,"+
            KEY_NAME_ENGLISH +"TEXT,"+
            KEY_CUSTOMER_NUMBER +"TEXT,"+
            KEY_MOBILE +"TEXT,"+
            KEY_EMAIL +"EMAIL,"+
            KEY_ALT_CONTACT_NUMBER +"TEXT,"+
            KEY_FIRST_CONNECTION_DATE +"DATETIME,"+
            KEY_CUSTOM_FIELD1 +"TEXT," +
            KEY_CUSTOM_FIELD2 +"TEXT," +
            KEY_CUSTOM_FIELD3 +"TEXT," +
            KEY_CUSTOM_FIELD4 +"TEXT," +
            KEY_CREATED_ON +"DATETIME,"+
            KEY_UPDATED_ON +"DATETIME,"+
            KEY_CREATED_BY + "INTEGER,"+
            KEY_UPDATED_BY +"INTEGER"+ ")";

    private static final String CREATE_TABLE_CUSTOMER_PACKAGE = "CREATE TABLE " + TABLE_CUSTOMER_PACKAGE +"(" +
            KEY_ID +"INTEGER PRIMARY KEY,"+
            KEY_CUSTOMER_ID +"INTEGER,"+
            KEY_PACKAGE_ID + " 0+,"+
            KEY_CUSTOM_FIELD1 +"TEXT," +
            KEY_CUSTOM_FIELD2 +"TEXT," +
            KEY_CUSTOM_FIELD3 +"TEXT," +
            KEY_CUSTOM_FIELD4 +"TEXT," +
            KEY_CREATED_ON +"DATETIME,"+
            KEY_UPDATED_ON +"DATETIME,"+
            KEY_CREATED_BY + "INTEGER,"+
            KEY_UPDATED_BY +"INTEGER"+ ")";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_CUSTOMER_INFO);
        db.execSQL(CREATE_TABLE_CUSTOMER_PACKAGE);
        db.execSQL(CREATE_TABLE_CUSTOMER_TYPE);
        db.execSQL(CREATE_TABLE_PACKAGE);
        db.execSQL(CREATE_TABLE_PROVIDER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CUSTOMER_PACKAGE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROVIDER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CUSTOMER_TYPE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CUSTOMER_INFO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PACKAGE);

        onCreate(db);
    }
}
