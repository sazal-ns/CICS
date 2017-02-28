package com.sazal.siddiqui.cics;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.afollestad.materialdialogs.MaterialDialog;
import com.sazal.siddiqui.cics.DBHelper.DBHelper;
import com.sazal.siddiqui.cics.model.CustomerInformation;
import com.sazal.siddiqui.cics.model.CustomerXPackage;
import com.sazal.siddiqui.cics.model.Package;
import com.sazal.siddiqui.cics.model.Provider;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CXPFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CXPFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CXPFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private Spinner pakSpinner, cusSpinner;
    DBHelper dbHelper;

    private Package provider;
    private CustomerInformation customerInformation;

    public CXPFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CXPFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CXPFragment newInstance(String param1, String param2) {
        CXPFragment fragment = new CXPFragment();
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
        View view = inflater.inflate(R.layout.fragment_cx, container, false);
        pakSpinner = (Spinner) view.findViewById(R.id.packageSpinner);
        cusSpinner = (Spinner) view.findViewById(R.id.customerSpinner);

        dbHelper = new DBHelper(getContext());

        final List<CustomerInformation> allCustomerInfo = dbHelper.getAllCustomerInfo();


        String[] nameList=new String[allCustomerInfo.size()];

        for(int i=0;i<allCustomerInfo.size();i++){
            nameList[i]=allCustomerInfo.get(i).getNameEnglish(); //create array of name
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, nameList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cusSpinner.setAdapter(dataAdapter);

        final List<Package> packages = dbHelper.getAllPackage();

        final String[] pakList=new String[packages.size()];

        for(int i=0;i<packages.size();i++){
            pakList[i]=packages.get(i).getPackageName(); //create array of name
        }

        ArrayAdapter<String> dataAdapterPak = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, pakList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pakSpinner.setAdapter(dataAdapterPak);

        pakSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                provider = packages.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        cusSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                customerInformation = allCustomerInfo.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Button save = (Button) view.findViewById(R.id.saveButton);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomerXPackage customerXPackage = new CustomerXPackage();
                customerXPackage.setaPackage(provider);
                customerXPackage.setCustomer(customerInformation);

                long l = dbHelper.insertCustomerXPackage(customerXPackage);

                if (l!=-1) new MaterialDialog.Builder(getContext())
                        .title("Result")
                        .content("Data Save")
                        .show();
                else new MaterialDialog.Builder(getContext())
                        .title("Result")
                        .content("Error On Data Save")
                        .show();
            }
        });

        return view;
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
