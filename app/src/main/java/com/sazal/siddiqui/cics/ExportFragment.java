package com.sazal.siddiqui.cics;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.sazal.siddiqui.cics.DBHelper.DBHelper;
import com.sazal.siddiqui.cics.model.Package;
import com.sazal.siddiqui.cics.model.Provider;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ExportFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ExportFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ExportFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    DBHelper dbHelper;
    SQLiteDatabase database;

    private OnFragmentInteractionListener mListener;

    public ExportFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ExportFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ExportFragment newInstance(String param1, String param2) {
        ExportFragment fragment = new ExportFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_export, container, false);

        dbHelper = new DBHelper(getContext());

        String query = "SELECT * FROM customerInfo";

        database = dbHelper.getReadableDatabase();
        final Cursor cursor = database.rawQuery(query, null);

        Button button = (Button) view.findViewById(R.id.exportToEl);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                export(cursor);
            }
        });

        return view;
    }

    private void export(Cursor cursor) {
        final String fileName = "sazal.xls";

        //Saving file in external storage
        File sdCard = Environment.getExternalStorageDirectory();
        File directory = new File(sdCard.getAbsolutePath() + "/ms.todo");

        //create directory if not exist
        if(!directory.isDirectory()){
            directory.mkdirs();
        }

        //file path
        File file = new File(directory, fileName);

        WorkbookSettings wbSettings = new WorkbookSettings();
        wbSettings.setLocale(new Locale("en", "EN"));
        WritableWorkbook workbook;

        try {
            workbook = Workbook.createWorkbook(file, wbSettings);
            //Excel sheet name. 0 represents first sheet
            WritableSheet sheet = workbook.createSheet("userList", 0);

            try {
                sheet.addCell(new Label(0, 0, "NAME_ENGLISH")); // column and row
                sheet.addCell(new Label(1, 0, "EMAIL"));
                sheet.addCell(new Label(2, 0, "MOBILE")); // column and row
                sheet.addCell(new Label(3, 0, "CUSTOMER_NUMBER"));
                sheet.addCell(new Label(4, 0, "provider"));
                sheet.addCell(new Label(5, 0, "aPackage"));
                if (cursor.moveToFirst()) {
                    do {
                        String name = cursor.getString(cursor.getColumnIndex(DBHelper.KEY_NAME_ENGLISH));
                        String email = cursor.getString(cursor.getColumnIndex(DBHelper.KEY_EMAIL));
                        String mobile = cursor.getString(cursor.getColumnIndex(DBHelper.KEY_MOBILE));
                        String number = cursor.getString(cursor.getColumnIndex(DBHelper.KEY_CUSTOMER_NUMBER));

                        SQLiteDatabase database1 = dbHelper.getReadableDatabase();
                        Cursor prov = database1.rawQuery("SELECT * FROM "+ DBHelper.TABLE_PROVIDER+" WHERE "+DBHelper.KEY_ID+" = "
                                +cursor.getInt(cursor.getColumnIndex(DBHelper.KEY_PROVIDER_ID)),null);
                        prov.moveToFirst();
                       // provider.setAreaName(prov.getString(prov.getColumnIndex(DBHelper.KEY_AREA_NAME)));
                       String provider = prov.getString(prov.getColumnIndex(DBHelper.KEY_PROVIDER_NAME));


                        SQLiteDatabase database2 = dbHelper.getReadableDatabase();
                        Cursor pak = database2.rawQuery("SELECT * FROM "+ DBHelper.TABLE_CUSTOMER_PACKAGE+" WHERE "+DBHelper.KEY_CUSTOMER_ID+" = "
                                +cursor.getInt(cursor.getColumnIndex(DBHelper.KEY_ID)),null);

                        pak.moveToFirst();
                        int ij = pak.getInt(pak.getColumnIndex(DBHelper.KEY_ID));

                        Cursor pak1 = database.rawQuery("SELECT * FROM "+ DBHelper.TABLE_PACKAGE+" WHERE "+DBHelper.KEY_ID+" = "+ String.valueOf(ij),null);
                        pak1.moveToFirst();
                        String paka =pak1.getString(pak1.getColumnIndex(DBHelper.KEY_PACKAGE_NAME));

                        int i = cursor.getPosition() + 1;
                        sheet.addCell(new Label(0, i, name));
                        sheet.addCell(new Label(1, i, email));
                        sheet.addCell(new Label(2, i, mobile));
                        sheet.addCell(new Label(3, i, number));
                        sheet.addCell(new Label(4, i, provider));
                        sheet.addCell(new Label(5, i, paka));
                    } while (cursor.moveToNext());
                }
                //closing cursor
                cursor.close();
            } catch (RowsExceededException e) {
                e.printStackTrace();
            } catch (WriteException e) {
                e.printStackTrace();
            }
            workbook.write();
            try {
                workbook.close();
            } catch (WriteException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
