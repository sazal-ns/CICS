package com.sazal.siddiqui.cics;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.sazal.siddiqui.cics.DBHelper.DBHelper;
import com.sazal.siddiqui.cics.model.Package;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PackageFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PackageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PackageFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private EditText pakageNameEditText, totalChannelsEditText2,priceEditText;
    private Button resultButton, clearButton;

    private OnFragmentInteractionListener mListener;

    public PackageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PackageFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PackageFragment newInstance(String param1, String param2) {
        PackageFragment fragment = new PackageFragment();
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
        View view = inflater.inflate(R.layout.fragment_package, container, false);

        pakageNameEditText = (EditText) view.findViewById(R.id.pakageNameEditText);
        priceEditText = (EditText) view.findViewById(R.id.priceEditText);
        totalChannelsEditText2 = (EditText) view.findViewById(R.id.totalChannelsEditText2);
        clearButton =(Button) view.findViewById(R.id.clearButton);

        resultButton = (Button) view.findViewById(R.id.saveButton);
        resultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Package aPackage = new Package();
                aPackage.setPackageName(pakageNameEditText.getText().toString().trim());
                aPackage.setPrice(Double.parseDouble(priceEditText.getText().toString().trim()));
                aPackage.setTotalChannels(Integer.parseInt(totalChannelsEditText2.getText().toString().trim()));

                DBHelper dbHelper = new DBHelper(getContext());
                long l = dbHelper.insertPackage(aPackage);

                if (l!=-1) new MaterialDialog.Builder(getContext())
                        .title("Result")
                        .content("Data Save")
                        .show();
                else new MaterialDialog.Builder(getContext())
                        .title("Result")
                        .content("Error On Data Save")
                        .show();
                resultButton.setEnabled(false);
                clearButton.setEnabled(true);
            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pakageNameEditText.setText(null);
                priceEditText.setText(null);
                totalChannelsEditText2.setText(null);
                resultButton.setEnabled(true);
                clearButton.setEnabled(false);

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
