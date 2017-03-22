package com.sazal.siddiqui.cics.DBHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.sazal.siddiqui.cics.model.CustomerInformation;
import com.sazal.siddiqui.cics.model.CustomerType;
import com.sazal.siddiqui.cics.model.CustomerXPackage;
import com.sazal.siddiqui.cics.model.Package;
import com.sazal.siddiqui.cics.model.Provider;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by sazal on 2017-02-12.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final String TAG = "DBHelper";

    private static final int DATABASE_VERSION =1;
    private static final String DATABASE_NAME = "CICS";

    public static final String TABLE_PACKAGE = "package";
    public static final String TABLE_PROVIDER ="provider";
    private static final String TABLE_CUSTOMER_TYPE ="customerType";
    private static final String TABLE_CUSTOMER_INFO = "customerInfo";
    public static final String TABLE_CUSTOMER_PACKAGE = "customerXpackage";

    private static final String KEY_CREATED_ON = "createdOn";
    private static final String KEY_CREATED_BY = "createdBy";
    private static final String KEY_UPDATED_ON = "updatedON";
    private static final String KEY_UPDATED_BY = "updatedBy";

    private static final String KEY_CUSTOM_FIELD1 = "customField1";
    private static final String KEY_CUSTOM_FIELD2 = "customField2";
    private static final String KEY_CUSTOM_FIELD3 = "customField3";
    private static final String KEY_CUSTOM_FIELD4 = "customField4";

    public static final String KEY_PACKAGE_ID = "packageID";
    public static final String KEY_PACKAGE_NAME = "packageName";
    private static final String KEY_TOTAL_CHANNELS = "totalChannels";
    private static final String KEY_PRICE = "price";

    public static final String KEY_PROVIDER_ID = "providerId";
    private static final String KEY_PARENT_PROVIDER_ID = "parentProviderID";
    public static final String KEY_PROVIDER_NAME = "providerName";
    public static final String KEY_AREA_NAME = "areaName";

    private static final String KEY_TYPE_ID = "typeID";
    private static final String KEY_TYPE_NAME = "typeName";

    public static final String KEY_CUSTOMER_ID = "customerID";
    private static final String KEY_NAME_BANGLA = "nameBangla";
    public static final String KEY_NAME_ENGLISH = "nameEnglish";
    public static final String KEY_CUSTOMER_NUMBER = "customerNumber";
    public static final String KEY_MOBILE = "mobile";
    public static final String KEY_EMAIL = "email";
    private static final String KEY_ALT_CONTACT_NUMBER = "altContactNumber";
    private static final String KEY_FIRST_CONNECTION_DATE ="firstConnectionDate";

    public static final String KEY_ID = "_id";

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
            KEY_ID +" INTEGER PRIMARY KEY, "+
            KEY_PROVIDER_ID +" INTEGER NOT NULL, " +
            KEY_NAME_BANGLA +" TEXT, "+
            KEY_NAME_ENGLISH +" TEXT NOT NULL, "+
            KEY_CUSTOMER_NUMBER +" TEXT NOT NULL, "+
            KEY_MOBILE +" TEXT NOT NULL, "+
            KEY_EMAIL +" EMAIL NOT NULL, "+
            KEY_ALT_CONTACT_NUMBER +" TEXT, "+
            KEY_FIRST_CONNECTION_DATE +" DATETIME, "+
            KEY_CUSTOM_FIELD1 +" TEXT, " +
            KEY_CUSTOM_FIELD2 +" TEXT, " +
            KEY_CUSTOM_FIELD3 +" TEXT, " +
            KEY_CUSTOM_FIELD4 +" TEXT, " +
            KEY_CREATED_ON +" DATETIME, "+
            KEY_UPDATED_ON +" DATETIME, "+
            KEY_CREATED_BY + " INTEGER, "+
            KEY_UPDATED_BY +" INTEGER )";

    private static final String CREATE_TABLE_CUSTOMER_PACKAGE = "CREATE TABLE " + TABLE_CUSTOMER_PACKAGE +" (" +
            KEY_ID +" INTEGER PRIMARY KEY, "+
            KEY_CUSTOMER_ID +" INTEGER NOT NULL, "+
            KEY_PACKAGE_ID + " INTEGER NOT NULL, "+
            KEY_CUSTOM_FIELD1 +" TEXT, " +
            KEY_CUSTOM_FIELD2 +" TEXT, " +
            KEY_CUSTOM_FIELD3 +" TEXT, " +
            KEY_CUSTOM_FIELD4 +" TEXT, " +
            KEY_CREATED_ON +" DATETIME, "+
            KEY_UPDATED_ON +" DATETIME, "+
            KEY_CREATED_BY + " INTEGER, "+
            KEY_UPDATED_BY +" INTEGER )";


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

    public List<CustomerType> getAllCustomerType(){
        List<CustomerType> customerTypes = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_CUSTOMER_TYPE;

        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(query, null);

        if (cursor.moveToFirst()){
            do {
                CustomerType customerType = new CustomerType();
                customerType.setTypeId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
                customerType.setTypeName(cursor.getString(cursor.getColumnIndex(KEY_TYPE_NAME)));

                customerTypes.add(customerType);
            }while (cursor.moveToNext());
        }

        return customerTypes;
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

    public List<Package> getAllPackage(){
        List<Package> customerTypes = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_PACKAGE;

        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(query, null);

        if (cursor.moveToFirst()){
            do {
                Package customerType = new Package();
                customerType.setPackageId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
                customerType.setPackageName(cursor.getString(cursor.getColumnIndex(KEY_PACKAGE_NAME)));
                customerType.setTotalChannels(cursor.getInt(cursor.getColumnIndex(KEY_TOTAL_CHANNELS)));
                customerType.setPrice(cursor.getDouble(cursor.getColumnIndex(KEY_PRICE)));

                customerTypes.add(customerType);
            }while (cursor.moveToNext());
        }

        return customerTypes;
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

    public List<Provider> getAllPovider(){
        List<Provider> customerTypes = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_PROVIDER;

        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(query, null);

        if (cursor.moveToFirst()){
            do {
                Provider customerType = new Provider();
                customerType.setProviderId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
                customerType.setProviderName(cursor.getString(cursor.getColumnIndex(KEY_PROVIDER_NAME)));
                customerType.setAreaName(cursor.getString(cursor.getColumnIndex(KEY_AREA_NAME)));

                customerTypes.add(customerType);
            }while (cursor.moveToNext());
        }

        return customerTypes;
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

    public List<CustomerInformation> getAllCustomerInfo(){
        List<CustomerInformation> customerTypes = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_CUSTOMER_INFO;

        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(query, null);

        if (cursor.moveToFirst()){
            do {
                CustomerInformation customerType = new CustomerInformation();
                customerType.setCoustomerId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
                customerType.setNameEnglish(cursor.getString(cursor.getColumnIndex(KEY_NAME_ENGLISH)));
                customerType.setEmail(cursor.getString(cursor.getColumnIndex(KEY_EMAIL)));
                customerType.setMobile(cursor.getString(cursor.getColumnIndex(KEY_MOBILE)));
                customerType.setCoustomerNumber(cursor.getString(cursor.getColumnIndex(KEY_CUSTOMER_NUMBER)));

                Cursor prov = database.rawQuery("SELECT * FROM "+ TABLE_PROVIDER+" WHERE "+KEY_ID+" = "
                        +cursor.getInt(cursor.getColumnIndex(KEY_PROVIDER_ID)),null);
                Provider provider = new Provider();
                provider.setProviderId(prov.getInt(prov.getColumnIndex(KEY_ID)));
                provider.setAreaName(prov.getString(prov.getColumnIndex(KEY_AREA_NAME)));
                provider.setProviderName(prov.getString(prov.getColumnIndex(KEY_PROVIDER_NAME)));
                customerType.setProvider(provider);



                Cursor pak = database.rawQuery("SELECT * FROM "+ TABLE_CUSTOMER_PACKAGE+" WHERE "+KEY_CUSTOMER_ID+" = "
                        +cursor.getInt(cursor.getColumnIndex(KEY_ID)),null);

                int i = pak.getInt(pak.getColumnIndex(KEY_ID));

                Cursor pak1 = database.rawQuery("SELECT * FROM "+ TABLE_PACKAGE+" WHERE "+KEY_ID+" = "+ String.valueOf(i),null);
                Package aPackage = new Package();
                aPackage.setPackageName(pak1.getString(pak1.getColumnIndex(KEY_PACKAGE_NAME)));
                customerType.setaPackage(aPackage);

                customerTypes.add(customerType);
            }while (cursor.moveToNext());
        }

        return customerTypes;
    }

    public List<CustomerInformation> getAllCustomerInfoN(){
        List<CustomerInformation> customerTypes = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_CUSTOMER_INFO;

        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(query, null);

        if (cursor.moveToFirst()){
            do {
                CustomerInformation customerType = new CustomerInformation();
                customerType.setCoustomerId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
                customerType.setNameEnglish(cursor.getString(cursor.getColumnIndex(KEY_NAME_ENGLISH)));
                customerType.setEmail(cursor.getString(cursor.getColumnIndex(KEY_EMAIL)));
                customerType.setMobile(cursor.getString(cursor.getColumnIndex(KEY_MOBILE)));
                customerType.setCoustomerNumber(cursor.getString(cursor.getColumnIndex(KEY_CUSTOMER_NUMBER)));


                customerTypes.add(customerType);
            }while (cursor.moveToNext());
        }

        return customerTypes;
    }

    public  long insertCustomerXPackage(CustomerXPackage  customerInformation){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_CUSTOMER_ID, customerInformation.getCustomer().getCoustomerId());
        values.put(KEY_PACKAGE_ID, customerInformation.getaPackage().getPackageId());
        values.put(KEY_UPDATED_ON, getDateTime());

        long r = db.insert(TABLE_CUSTOMER_PACKAGE,null,values);
        closeDB();
        return r;
    }

    public List<CustomerXPackage> getAllCXP(){
        List<CustomerXPackage> customerTypes = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_CUSTOMER_PACKAGE;

        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(query, null);

        if (cursor.moveToFirst()){
            do {
                CustomerXPackage customerType = new CustomerXPackage();
                customerType.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));

                int j = cursor.getInt(cursor.getColumnIndex(KEY_CUSTOMER_ID));
                Log.e("customer id", String.valueOf(j));
                SQLiteDatabase database1 = this.getReadableDatabase();
                Cursor pak = database1.rawQuery("SELECT * FROM "+ TABLE_CUSTOMER_INFO+" WHERE "+KEY_ID+" = "+j,null);
                pak.moveToFirst();
                CustomerInformation aPackag = new CustomerInformation();
                aPackag.setNameEnglish(pak.getString(pak.getColumnIndex(KEY_NAME_ENGLISH)));
                j = pak.getInt(pak.getColumnIndex(KEY_PROVIDER_ID));
                Cursor pak2 = database1.rawQuery("SELECT * FROM "+ TABLE_PROVIDER+" WHERE "+KEY_ID+" = "+j,null);
                pak2.moveToFirst();
                Provider provider = new Provider();
                provider.setProviderName(pak2.getString(pak2.getColumnIndex(KEY_PROVIDER_NAME)));
                aPackag.setProvider(provider);
                customerType.setCustomer(aPackag);
                database1.close();

                int i = cursor.getInt(cursor.getColumnIndex(KEY_PACKAGE_ID));
Log.e("package id", String.valueOf(i));
                SQLiteDatabase database2 = this.getReadableDatabase();
                Cursor pak1 = database2.rawQuery("SELECT * FROM "+ TABLE_PACKAGE+" WHERE "+KEY_ID+" = "+ i,null);
                Package aPackage = new Package();
                pak1.moveToFirst();
                aPackage.setPackageName(pak1.getString(pak1.getColumnIndex(KEY_PACKAGE_NAME)));
                customerType.setaPackage(aPackage);
                database2.close();

                customerTypes.add(customerType);
            }while (cursor.moveToNext());
        }

        return customerTypes;
    }


    private void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }

    public String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
}
