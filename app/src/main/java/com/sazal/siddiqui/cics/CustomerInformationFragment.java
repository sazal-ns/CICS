package com.sazal.siddiqui.cics;

import android.app.DatePickerDialog;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.sazal.siddiqui.cics.DBHelper.DBHelper;
import com.sazal.siddiqui.cics.model.CustomerInformation;
import com.sazal.siddiqui.cics.model.Provider;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CustomerInformationFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CustomerInformationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CustomerInformationFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private EditText providerIdEditText, nameEnglishEditText, customerNumberEditText, mobileEditText, emailEditText, altContactNumberEditText, firstConectionDateEditText;
    private Button saveButton, clearButton;
    private Spinner spinner;
    private Provider provider;

    private Calendar calendar= Calendar.getInstance();

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public CustomerInformationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CustomerInformationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CustomerInformationFragment newInstance(String param1, String param2) {
        CustomerInformationFragment fragment = new CustomerInformationFragment();
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
        View view = inflater.inflate(R.layout.fragment_customer_information, container, false);
        spinner = (Spinner) view.findViewById(R.id.providerSpinner);
        nameEnglishEditText = (EditText) view.findViewById(R.id.nameEnglishEditText);
        customerNumberEditText = (EditText) view.findViewById(R.id.customerNumberEditText);
        mobileEditText = (EditText) view.findViewById(R.id.mobileEditText);
        emailEditText = (EditText) view.findViewById(R.id.emailEditText);
        altContactNumberEditText = (EditText) view.findViewById(R.id.altContactNumberEditText);
        firstConectionDateEditText = (EditText) view.findViewById(R.id.firstConectionDateEditText);

        firstConectionDateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getContext(), dateSetListener, calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        saveButton = (Button) view.findViewById(R.id.saveButton);
        clearButton = (Button)view.findViewById(R.id.clearButton);

        final DBHelper dbHelper = new DBHelper(getContext());
        final List<Provider> providerList = dbHelper.getAllPovider();
        String[] nameList=new String[providerList.size()];

        for(int i=0;i<providerList.size();i++){
            nameList[i]=providerList.get(i).getProviderName(); //create array of name
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, nameList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                provider = providerList.get(position);
                //Toast.makeText(getContext(),provider.getProviderName(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomerInformation customerInformation = new CustomerInformation();
                customerInformation.setProvider(provider);
                customerInformation.setNameEnglish(nameEnglishEditText.getText().toString());
                customerInformation.setCoustomerNumber(customerNumberEditText.getText().toString());
                customerInformation.setMobile(mobileEditText.getText().toString());
                customerInformation.setEmail(emailEditText.getText().toString());
                customerInformation.setAltContactNumber(altContactNumberEditText.getText().toString());
                customerInformation.setFirstConectionDate(firstConectionDateEditText.getText().toString());

                long l = dbHelper.insertCustomerInformation(customerInformation);

                if (l!=-1) new MaterialDialog.Builder(getContext())
                        .title("Result")
                        .content("Data Save")
                        .show();
                else new MaterialDialog.Builder(getContext())
                        .title("Result")
                        .content("Error On Data Save")
                        .show();
                saveButton.setEnabled(true);
                clearButton.setEnabled(true);

            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinner.setPrompt("Select Provider");
                nameEnglishEditText.setText(null);
                customerNumberEditText.setText(null);
                mobileEditText.setText(null);
                emailEditText.setText(null);
                altContactNumberEditText.setText(null);
                firstConectionDateEditText.setText(null);
                saveButton.setEnabled(true);
                clearButton.setEnabled(false);

            }
        });


        return view;
    }

    DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                firstConectionDateEditText.setText(formatDate(calendar.getTime()));
        }
    };

    public String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
        String hireDate = sdf.format(date);
        return hireDate;
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
