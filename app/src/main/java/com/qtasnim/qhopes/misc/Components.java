package com.qtasnim.qhopes.misc;

import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

public class Components {

//    public void setActionBar(AppCompatActivity Activity){
//        // Inflate the layout for this fragment
//        ((AppCompatActivity)getActivity()).getSupportActionBar().show();
//        // Set title bar
//        ((MainActivity) getActivity())nbbn
//                .setActionBarTitle(getString(R.string.title_diagnosis));
//    }

    public void centerTitle() {
        ArrayList<View> textViews = new ArrayList<>();

        if(textViews.size() > 0) {
            AppCompatTextView appCompatTextView = null;
            if(textViews.size() == 1) {
                appCompatTextView = (AppCompatTextView) textViews.get(0);
            } else {
                for(View v : textViews) {
                    if(v.getParent() instanceof Toolbar) {
                        appCompatTextView = (AppCompatTextView) v;
                        break;
                    }
                }
            }

            if(appCompatTextView != null) {
                ViewGroup.LayoutParams params = appCompatTextView.getLayoutParams();
                params.width = ViewGroup.LayoutParams.MATCH_PARENT;
                appCompatTextView.setLayoutParams(params);
                appCompatTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            }
        }
    }

}
