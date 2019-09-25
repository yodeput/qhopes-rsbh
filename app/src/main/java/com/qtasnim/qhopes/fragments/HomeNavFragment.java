package com.qtasnim.qhopes.fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.qtasnim.qhopes.R;
import com.qtasnim.qhopes.activities.JadwalDokterActivity;
import com.qtasnim.qhopes.activities.PendaftaranActivity;
import com.qtasnim.qhopes.adapters.BeritaAdapterHorizontal;
import com.qtasnim.qhopes.adapters.SliderAdapter;
import com.qtasnim.qhopes.api.NetworkModule;
import com.qtasnim.qhopes.api.NetworkService;
import com.qtasnim.qhopes.models.response.Berita;
import com.qtasnim.qhopes.models.response.BeritaResponse;
import com.qtasnim.qhopes.models.response.Slider;
import com.qtasnim.qhopes.models.response.SliderResponse;
import com.qtasnim.qhopes.utils.Apps;
import com.qtasnim.qhopes.utils.CustomDialog;
import com.smarteist.autoimageslider.SliderView;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeNavFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private DatePickerDialog datePickerDialog;
    private CustomDialog cd;
    private RecyclerView.LayoutManager layoutManager;
    private Unbinder unbinder;
    private BeritaAdapterHorizontal mAdapter;
    private NetworkService mNetworkService;
    private Calendar calendar;
    private Dialog dialog;
    private int year, month, day;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.sliderView)
    SliderView sliderView;

    @OnClick(R.id.btn_dokter)
    void dokter() {
        startActivity(new Intent(getActivity(), JadwalDokterActivity.class));
        getActivity().overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
    }

    @OnClick(R.id.btn_antrian)
    void antrian() {
        dialogPilihanPasien();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_nav_home, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        initClass();
        getBerita();
        getSlider();

        return rootView;
    }

    private void initClass() {
        cd = new CustomDialog(getActivity());
        mNetworkService = NetworkModule.getClient().create(NetworkService.class);

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
    }

    private void dialogPilihanPasien() {
        if (dialog == null || !dialog.isShowing()) {
            dialog = new Dialog(getActivity(), R.style.DialogSlideAnim);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.dialog_pilihan_pasien);
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
                        dialog.dismiss();
                    }
                    return true;
                }
            });
            Button pasien_lama = dialog.findViewById(R.id.btn_pasien_lama);
            Button pasien_baru = dialog.findViewById(R.id.btn_pasien_baru);

            pasien_baru.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    cd.toastWarning("Silahkan Datang ke Rumah Sakit");
                }
            });
            pasien_lama.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                    dialogRM();
                }
            });
        }

    }

    private void dialogRM() {
        if (dialog == null || !dialog.isShowing()) {
            dialog = new Dialog(getActivity(), R.style.DialogSlideAnim);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.view_dialog_medrek_form1);
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
                        dialog.dismiss();
                    }
                    return true;
                }
            });
            EditText editNoRm = (EditText) dialog.findViewById(R.id.editNoRm);
            EditText editTanggal = (EditText) dialog.findViewById(R.id.editTanggal);
            Button confirmMedrek = dialog.findViewById(R.id.btn_confirm_medrek);

            editTanggal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    datePickerDialog = DatePickerDialog.newInstance(new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                            HomeNavFragment.this.year = year;
                            HomeNavFragment.this.month = monthOfYear + 1;
                            HomeNavFragment.this.day = dayOfMonth;
                            String tgl = Integer.toString(dayOfMonth);
                            String bulan = Apps.getMonthNameByInt(monthOfYear + 1);
                            String tahun = Integer.toString(year);
                            editTanggal.setText(tgl + " " + bulan + " " + tahun);
                        }
                    }, year, month, day);
                    datePickerDialog.setThemeDark(false);
                    datePickerDialog.setAccentColor(getResources().getColor(R.color.colorPrimaryDark));
                    datePickerDialog.showYearPickerFirst(true);
                    datePickerDialog.setTitle("Pilih Tanggal Lahir");
                    datePickerDialog.show(getActivity().getFragmentManager(), "Tanggal Lagir");

                }
            });

            confirmMedrek.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String NoMedrek = editNoRm.getText().toString();
                    String NoTanggalLahir = editTanggal.getText().toString();

                    if (TextUtils.isEmpty(NoMedrek) || TextUtils.isEmpty(NoTanggalLahir)) {
                        cd.toastWarning("Lengkapi data yang diperlukan");
                    } else {

                        Intent konfirmasiMedrek = new Intent(getActivity(), PendaftaranActivity.class);
                        startActivity(konfirmasiMedrek);

                    }

                }
            });
        }
    }

    private void getBerita() {
        Call<BeritaResponse> beritaReq = mNetworkService.getBerita();
        beritaReq.enqueue(new Callback<BeritaResponse>() {
            @Override
            public void onResponse(Call<BeritaResponse> call, Response<BeritaResponse> response) {
                if (response.isSuccessful()) {
                    generateBeritaView(response.body().getData());
                }
            }

            @Override
            public void onFailure(Call<BeritaResponse> call, Throwable t) {

            }
        });
    }

    private void getSlider() {
        Call<SliderResponse> sliderReq = mNetworkService.getSlider();
        sliderReq.enqueue(new Callback<SliderResponse>() {
            @Override
            public void onResponse(Call<SliderResponse> call, Response<SliderResponse> response) {
                if (response.isSuccessful()) {
                    generateSliderView(response.body().getData());
                }
            }

            @Override
            public void onFailure(Call<SliderResponse> call, Throwable t) {

            }
        });
    }

    private void generateBeritaView(List<Berita> beritaList) {
        mAdapter = new BeritaAdapterHorizontal(beritaList);
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new BeritaAdapterHorizontal(beritaList);
        mAdapter.setOnItemClickListener(new BeritaAdapterHorizontal.OnItemClickListener() {
            @Override
            public void onItemClick(View view, Berita obj, int position) {
                Log.e("berita click", obj.getTitle());
            }
        });
        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }


    private void generateSliderView(List<Slider> sliderList) {
        SliderAdapter sliderAdapter = new SliderAdapter(sliderList);
        sliderAdapter.setOnItemClickListener(new SliderAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, Slider obj, int position) {
                Log.e("slider click", obj.getDesc());
            }
        });
        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorSelectedColor(getActivity().getResources().getColor(R.color.colorPrimary));
        sliderView.setIndicatorUnselectedColor(getActivity().getResources().getColor(R.color.white));
        sliderView.setScrollTimeInSec(4); //set scroll delay in seconds :
        sliderView.startAutoCycle();
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
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onDestroy() {
        unbinder.unbind();
        super.onDestroy();

    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
