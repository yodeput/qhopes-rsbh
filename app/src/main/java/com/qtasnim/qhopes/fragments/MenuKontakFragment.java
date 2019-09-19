package com.qtasnim.qhopes.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.qtasnim.qhopes.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MenuKontakFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MenuKontakFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MenuKontakFragment extends Fragment {
    Button btn_menu_info_todaftar;
    TextView mTvTitleKontak,mTvAlamatKontak, mTvEmailKontak, mTvPhone1Kontak, mTvPhone2Kontak;



    private OnFragmentInteractionListener mListener;

    public MenuKontakFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MenuInfoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MenuKontakFragment newInstance(String param1, String param2) {
        MenuKontakFragment fragment = new MenuKontakFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.activity_menu_kontak, container, false);
        mTvTitleKontak = rootView.findViewById(R.id.tv_title_kontak);
        mTvTitleKontak.setText("Contact Us");
        mTvAlamatKontak = rootView.findViewById(R.id.tv_alamat_kontak);
        mTvAlamatKontak.setText("Jl. RE. Martadinata Bypass Cikaran");
        mTvEmailKontak = rootView.findViewById(R.id.tv_email_kontak);
        mTvEmailKontak.setText("admin@rsbhaktihusada.com");
        mTvPhone1Kontak = rootView.findViewById(R.id.tv_phone1_kontak);
        mTvPhone1Kontak.setText("+62 21 890 0530");
        mTvPhone2Kontak = rootView.findViewById(R.id.tv_phone2_kontak);
        mTvPhone2Kontak.setText("+62 21 890 0570");


        return rootView;
    }


    public void btn_menu_info_todaftar_onClick()
    {
        btn_menu_info_todaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PendaftaranNavFragment fm = new PendaftaranNavFragment();


                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fl_container,fm);
                fragmentTransaction.commit();
            }
        });

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
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
         //   throw new RuntimeException(context.toString()
        //            + " must implement OnFragmentInteractionListener");
        }
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
