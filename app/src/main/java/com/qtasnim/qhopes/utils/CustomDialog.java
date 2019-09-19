package com.qtasnim.qhopes.utils;


/*
 * ******************************************************
 *  * Copyright (c) 2019. All Rights Reserved
 *  * Unauthorized copying of this file, via any medium is strictly prohibited
 *  * Proprietary and confidential
 *  * Created by: Yogi Dewansyah<yodeput@gmail.com>
 *  ******************************************************
 */

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.Application;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.text.Html;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;

import com.balysv.materialripple.MaterialRippleLayout;
import com.qtasnim.qhopes.R;


public class CustomDialog extends Application {
    Context mContext;
    Dialog p_dialog = null;
    Dialog dialog = null;
    Handler handler;
    PrefManager pref;

    public CustomDialog(Context context) {
        this.mContext = context;
        init_p_dialog();
        handler = new Handler();
        pref = new PrefManager(mContext);
    }

    final String GetString(int string) {
        return mContext.getString(string);
    }

    ///TOAST
    public void toastWarning(String msg) {
        //FancyToast.makeText(mContext, msg, FancyToast.LENGTH_LONG, FancyToast.WARNING, false).show();

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.toast_custom, null);
        TextView text = (TextView) layout.findViewById(R.id.text);
        text.setTextColor(Color.WHITE);
        text.setText(msg);
        CardView lyt_card = (CardView) layout.findViewById(R.id.lyt_card);
        lyt_card.setCardBackgroundColor(mContext.getResources().getColor(R.color.yellow_800));

        Toast toast = new Toast(mContext);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }

    public void toastError(String msg) {
        //FancyToast.makeText(mContext, msg, FancyToast.LENGTH_LONG, FancyToast.ERROR, false).show();
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.toast_custom, null);
        TextView text = (TextView) layout.findViewById(R.id.text);
        text.setTextColor(Color.WHITE);
        text.setText(msg);
        CardView lyt_card = (CardView) layout.findViewById(R.id.lyt_card);
        lyt_card.setCardBackgroundColor(mContext.getResources().getColor(R.color.red_500));

        Toast toast = new Toast(mContext);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }

    public void toastSuccess(String msg) {
        //FancyToast.makeText(mContext, msg, FancyToast.LENGTH_LONG, FancyToast.SUCCESS, false).show();
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.toast_custom, null);
        TextView text = (TextView) layout.findViewById(R.id.text);
        text.setTextColor(Color.WHITE);
        text.setText(msg);
        CardView lyt_card = (CardView) layout.findViewById(R.id.lyt_card);
        lyt_card.setCardBackgroundColor(mContext.getResources().getColor(R.color.green_700));

        Toast toast = new Toast(mContext);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }

    public void init_p_dialog() {
        if (p_dialog == null || !p_dialog.isShowing()) {
            p_dialog = new Dialog(mContext, R.style.DialogSlideAnim);
            p_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // b// efore
            p_dialog.setContentView(R.layout.dialog_progress);
            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
            lp.copyFrom(p_dialog.getWindow().getAttributes());
            lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            lp.gravity = Gravity.CENTER_HORIZONTAL;
            p_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            p_dialog.getWindow().setAttributes(lp);
            ProgressBar progressBar = (ProgressBar) p_dialog.findViewById(R.id.progress);
          /*  ChasingDots mChasingDotsDrawable = new ChasingDots();
            mChasingDotsDrawable.setColor(mContext.getResources().getColor(R.color.colorPrimaryDark));
        progressBar.setIndeterminateDrawable(mChasingDotsDrawable);*/
        }
    }

    public void show_p_Dialog() {
        if (!p_dialog.isShowing())
            p_dialog.show();
    }

    public void hide_p_Dialog() {
        if (p_dialog.isShowing())
            p_dialog.dismiss();
    }

    public void oneButtonDialog(String message, int img_header, int color_header) {
        if (dialog == null || !dialog.isShowing()) {
            dialog = new Dialog(mContext, R.style.DialogSlideAnim);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // b// efore
            dialog.setContentView(R.layout.dialog_one_button);
            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
            lp.copyFrom(dialog.getWindow().getAttributes());
            lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            lp.gravity = Gravity.CENTER_HORIZONTAL;
            dialog.show();
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.getWindow().setAttributes(lp);


            dialog.setCanceledOnTouchOutside(false);
            dialog.setOnKeyListener(new Dialog.OnKeyListener() {

                @Override
                public boolean onKey(DialogInterface arg0, int keyCode,
                                     KeyEvent event) {
                    // TODO Auto-generated method stub
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                    }
                    return true;
                }
            });
            Drawable img = mContext.getResources().getDrawable(img_header);
            int color = mContext.getResources().getColor(color_header);

            ((LinearLayout) dialog.findViewById(R.id.header_dialog)).setBackgroundColor(color);
            ((ImageView) dialog.findViewById(R.id.img_dialog)).setImageDrawable(img);
            ((TextView) dialog.findViewById(R.id.txt_message)).setText(Html.fromHtml(message));
            ((Button) dialog.findViewById(R.id.bt_positive)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
        }
    }

    public void oneButtonDialog_finish(String message, int img_header, int color_header) {
        if (dialog == null || !dialog.isShowing()) {
            dialog = new Dialog(mContext, R.style.DialogSlideAnim);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // b// efore
            dialog.setContentView(R.layout.dialog_one_button);
            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
            lp.copyFrom(dialog.getWindow().getAttributes());
            lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            lp.gravity = Gravity.CENTER_HORIZONTAL;
            dialog.show();
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.getWindow().setAttributes(lp);


            dialog.setCanceledOnTouchOutside(false);
            dialog.setOnKeyListener(new Dialog.OnKeyListener() {

                @Override
                public boolean onKey(DialogInterface arg0, int keyCode,
                                     KeyEvent event) {
                    // TODO Auto-generated method stub
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                    }
                    return true;
                }
            });
            Drawable img = mContext.getResources().getDrawable(img_header);
            int color = mContext.getResources().getColor(color_header);

            ((LinearLayout) dialog.findViewById(R.id.header_dialog)).setBackgroundColor(color);
            ((ImageView) dialog.findViewById(R.id.img_dialog)).setImageDrawable(img);
            ((TextView) dialog.findViewById(R.id.txt_message)).setText(message);
            ((Button) dialog.findViewById(R.id.bt_positive)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    ((Activity) mContext).finish();
                }
            });
        }
    }



}


