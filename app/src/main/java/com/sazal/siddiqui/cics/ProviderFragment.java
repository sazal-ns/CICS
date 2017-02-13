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

import com.sazal.siddiqui.cics.DBHelper.DBHelper;
import com.sazal.siddiqui.cics.model.Package;
import com.sazal.siddiqui.cics.model.Provider;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProviderFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProviderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProviderFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private EditText prividerIDNameEditText, nameEditText2,areaEditText;
    private Button resultButton;
    private TextView resultTextView;

    private OnFragmentInteractionListener mListener;

    public ProviderFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProviderFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProviderFragment newInstance(String param1, String param2) {
        ProviderFragment fragment = new ProviderFragment();
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
        View view = inflater.inflate(R.layout.fragment_, container, false);
        prividerIDNameEditText = (EditText) view.findViewById(R.id.providerIDNameEditText);
        nameEditText2 = (EditText) view.findViewById(R.id.nameEditText2);
        areaEditText = (EditText) view.findViewById(R.id.areaEditText);
        resultTextView = (TextView) view.findViewById(R.id.resultTextView);

        resultButton = (Button) view.findViewById(R.id.saveButton);
        resultButton.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                Provider provider = new Provider();
                                                provider.setProviderId(Integer.parseInt(prividerIDNameEditText.getText().toString().trim()));
                                                provider.setProviderName(nameEditText2.getText().toString().trim());
                                                provider.setAreaName(areaEditText.getText().toString().trim());

                                                DBHelper dbHelper = new DBHelper(getContext());
                                                long l = dbHelper.insertProvider(provider);

                                                if (l!=-1) resultTextView.setText("Data Save");
                                                else resultTextView.setText("Error on Data Save");
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
