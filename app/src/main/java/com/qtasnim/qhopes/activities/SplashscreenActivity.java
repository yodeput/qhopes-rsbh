package com.qtasnim.qhopes.activities;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.motion.widget.MotionLayout;

import com.google.android.material.textfield.TextInputEditText;
import com.qtasnim.qhopes.R;
import com.qtasnim.qhopes.utils.CustomDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SplashscreenActivity extends AppCompatActivity {
    private CustomDialog cd;
    private Dialog dialog = null;
    private int waktuLoading = 1000;

    @BindView(R.id.img_logo_rotate)
    ImageView img_rotate;
    @BindView(R.id.img_logo)
    ImageView img_logo;
    @BindView(R.id.motionLayout)
    MotionLayout motionLayout;

    @OnClick(R.id.btn_pasien) void pasien(){
        cd.show_p_Dialog();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                cd.hide_p_Dialog();
                Intent jumpActivity= new Intent(SplashscreenActivity.this, MainActivityPasien.class);
                startActivity(jumpActivity);
                finish();
            }
        }, 1000);
    }

    @OnClick(R.id.btn_dokter) void dokter(){
        dialogLogin();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splashscreen);
        ButterKnife.bind(this);
        initClass();
        initView();

    }
    private void initClass(){
        cd = new CustomDialog(this);
    }
    private void initView(){
        RotateAnimation rotate = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(waktuLoading);
        rotate.setInterpolator(new LinearInterpolator());
        img_rotate.startAnimation(rotate);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                motionLayout.loadLayoutDescription(R.xml.scene_startup);
                motionLayout.setTransition(R.id.start, R.id.end);
                motionLayout.transitionToStart();
                motionLayout.transitionToEnd();
            }
        }, waktuLoading+250);
    }

    private void dialogLogin() {
        if (dialog == null) {
            dialog = new Dialog(this);
            dialog.setContentView(R.layout.view_dialog_dokter_form);
            TextInputEditText mInputNipDokter = dialog.findViewById(R.id.input_nip_dokter_form);
            TextInputEditText mInputPwdDokter = dialog.findViewById(R.id.input_sandi_dokter_form);
            mInputNipDokter.requestFocus();
            mInputNipDokter.setFocusableInTouchMode(true);
            showKeyboard();
            AppCompatButton mBtnLoginDokter = dialog.findViewById(R.id.btn_login_dokter);
            TextView tvRegister = dialog.findViewById(R.id.tv_register_dokter);
            tvRegister.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    closeKeyboard();
                    Intent intent = new Intent(SplashscreenActivity.this, RegisterDokterActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
            mBtnLoginDokter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final String isNip = mInputNipDokter.getText().toString();
                    if (isNip.matches("12345")) {
                        closeKeyboard();
                        //showGreetingAlert();
                        dialog.dismiss();
                    } else {
                        //showNipAlert();
                    }
                }
            });
            dialog.setCancelable(true);
            dialog.setCanceledOnTouchOutside(true);
            dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    closeKeyboard();
                    dialog.dismiss();
                }
            });
            dialog.show();
        }
    }

    public void showKeyboard(){
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }

    public void closeKeyboard(){
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
    }
}
