package com.qtasnim.qhopes.fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.qtasnim.qhopes.R;

public class PendaftaranNavFragment extends Fragment {

    Dialog dialog;
    private OnFragmentInteractionListener mListener;
    Button mConfirm;

    public PendaftaranNavFragment() {
        // Required empty public constructor
    }

    public static PendaftaranNavFragment newInstance(String param1, String param2) {
        PendaftaranNavFragment fragment = new PendaftaranNavFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.activity_pendaftaran_form, container, false);


        mConfirm = rootView.findViewById(R.id.btn_confirm_pendaftaran);
        mConfirmOnClick();


        return rootView;
    }

    private void mConfirmOnClick() {
        mConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button mBtnOke;
                dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.view_dialog_confirm_pendaftaran);
                mBtnOke = dialog.findViewById(R.id.btn_oke_pendaftaran);
                mBtnOke.setOnClickListener(new View.OnClickListener(){
                    public void onClick (View v){

                        dialog.dismiss();
                    }
                });
                dialog.setCancelable(true);
                dialog.setCanceledOnTouchOutside(true);
                dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {

                    }
                });
                dialog.show();
            }
        });
    }


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

        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
