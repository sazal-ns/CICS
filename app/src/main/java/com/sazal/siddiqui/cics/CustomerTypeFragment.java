package com.sazal.siddiqui.cics;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.sazal.siddiqui.cics.DBHelper.DBHelper;
import com.sazal.siddiqui.cics.model.CustomerType;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CustomerTypeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CustomerTypeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CustomerTypeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private EditText typeNameEditText;
    private Button saveButton, cleanButton;
    private DBHelper db;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public CustomerTypeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CustomerTypeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CustomerTypeFragment newInstance(String param1, String param2) {
        CustomerTypeFragment fragment = new CustomerTypeFragment();
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
        View view = inflater.inflate(R.layout.fragment_customer_type, container, false);
        typeNameEditText = (EditText) view.findViewById(R.id.typeNameEditText);
        saveButton = (Button) view.findViewById(R.id.saveButton);
        cleanButton = (Button) view.findViewById(R.id.clearButton);

        db = new DBHelper(getContext());
        Log.e("ddbb",db.getDatabaseName());

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomerType customerType = new CustomerType();
                customerType.setTypeName(typeNameEditText.getText().toString().trim());

               long result= db.insertCustomerType(customerType);

                if (result!=-1) new MaterialDialog.Builder(getContext())
                        .title("Result")
                        .content("Data Save")
                        .show();
                else new MaterialDialog.Builder(getContext())
                        .title("Result")
                        .content("Error On Data Save")
                        .show();
                saveButton.setEnabled(false);
                cleanButton.setEnabled(true);
            }
        });

        cleanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                typeNameEditText.setText(null);
                saveButton.setEnabled(true);
                cleanButton.setEnabled(false);
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
