package com.sazal.siddiqui.cics;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.sazal.siddiqui.cics.DBHelper.DBHelper;
import com.sazal.siddiqui.cics.model.CustomerType;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListFragment newInstance(String param1, String param2) {
        ListFragment fragment = new ListFragment();
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
        View v =  inflater.inflate(R.layout.fragment_list, container, false);
        ListView  list = (ListView) v.findViewById(R.id.list);
        CustomList adp = new CustomList(new DBHelper(getContext()).getAllCustomerType(), getActivity());
        list.setAdapter(adp);


        return v;
    }

   /* private void showCustomerType() {

       *//* List<CustomerType> customerTypes = new DBHelper(getContext()).getAllCustomerType();*//*
        *//*String[] name = new String[customerTypes.size()];
        StringBuilder builder = new StringBuilder();
        builder.append("ID"+" ---> "+"Name\n");

        int i=0;
        for (CustomerType customerType : customerTypes){
            name[i++] = customerType.getTypeName();
            builder.append(customerType.getTypeId()+" --> "+customerType.getTypeName()+"\n");
        }
*//*

       *//* CustomList customList = new CustomList(customerTypes, getActivity());
        list.setAdapter(customList);
        customList.notifyDataSetChanged();*//*
        *//*new MaterialDialog.Builder(getContext())
                .title("All Customer Types")
                .items(name)
                .show();*//*
        *//*boolean wrapInScrollView = true;
        new MaterialDialog.Builder(getContext())
                .title("All Customer Types")
                .customView(R.layout.list_row, wrapInScrollView)
                .show();*//*

    }*/

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
