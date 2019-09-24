package com.qtasnim.qhopes.activities;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.qtasnim.qhopes.R;
import com.qtasnim.qhopes.adapters.MenuHariiniAdapter;
import com.qtasnim.qhopes.models.MenuHariiniModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;


public class MenuHariIniActivity extends AppCompatActivity {

    private ArrayList<MenuHariiniModel> mModelData = new ArrayList<>();
    Dialog dialog;

    private View.OnClickListener onItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            int position = viewHolder.getAdapterPosition();
            // viewHolder.getItemId();
            // viewHolder.getItemViewType();
            // viewHolder.itemView;
            MenuHariiniModel mMenuHariiniModel = mModelData.get(position);
            setDialog(mMenuHariiniModel);

        }
    };

    private void setDialog(MenuHariiniModel currentModel) {

        final Dialog mDialog = new Dialog(MenuHariIniActivity.this);
        mDialog.setContentView(R.layout.view_dialog_menu_hariini);

        TextView mDialogPoliklinik = mDialog.findViewById(R.id.tv_dialog_poliklinik);
        TextView mDialogDokter = mDialog.findViewById(R.id.tv_dialog_dokter);
        TextView mJamPraktek = mDialog.findViewById(R.id.tv_dialog_jampraktek);
        TextView mPasienTerdaftar = mDialog.findViewById(R.id.tv_dialog_pasien_terdaftar);
        TextView mCheckin = mDialog.findViewById(R.id.tv_dialog_checkin_pasien);
        TextView mPasienTerlayani = mDialog.findViewById(R.id.tv_dialog_pasien_terlayani);
        TextView mKuotaPasien = mDialog.findViewById(R.id.tv_dialog_kuota_pasien);

        mDialogPoliklinik.setText(currentModel.getNama_poliklinik());
        mDialogDokter.setText(String.format("Nama Dokter     : %s", currentModel.getNama_dokter()));
        mJamPraktek.setText(String.format("Jam Praktek      : %s", currentModel.getJam_praktek()));
        mPasienTerdaftar.setText(String.format("Jumlah Pasien  : %s Pasien", currentModel.getPasien_terdaftar()));
        mCheckin.setText(String.format("Belum Checkin  : %s Pasien", currentModel.getPasien_checkin()));
        mPasienTerlayani.setText(String.format("Terlayani             : %s Pasien", currentModel.getPasien_terlayani()));
        mKuotaPasien.setText(String.format("Kuota                   : %s Pasien", currentModel.getKuota_pasien()));

        Button mBtnDialogKembali = mDialog.findViewById(R.id.btn_dialog_kembali);
        Button mBtnDialogDaftar = mDialog.findViewById(R.id.btn_dialog_daftar);

        mBtnDialogKembali.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mDialog.cancel();
            }
        });

        mBtnDialogDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent jumpActivity = new Intent(MenuHariIniActivity.this, NavPendaftaranFormActivity.class);
                startActivity(jumpActivity);
            }
        });

        mDialog.show();
    }
    private void getCurrentTime() {
        TextView tvCurrentDateTime = findViewById(R.id.tv_curent_update_menu_hariini);
        tvCurrentDateTime.setText(String.format("Update : %s", new SimpleDateFormat(
                "EEEE, dd MMM yyyy - hh:mm",
                new Locale("in", "ID"))
                .format(new Date())));
    }

    private void setRecyclerView() {

        RecyclerView mRecyclerView = findViewById(R.id.recycler_menu_hariini);
        MenuHariiniAdapter recyclerViewAdapter = new MenuHariiniAdapter(mModelData);

        initData();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(recyclerViewAdapter);
        recyclerViewAdapter.setOnItemClickListener(onItemClickListener);

    }

//    private void setDynamiceRecycler(){
////
//////         Helper class for creating swipe to dismiss and drag and drop
//////         functionality.
////        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper
////                .SimpleCallback(
////                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT |
////                        ItemTouchHelper.DOWN | ItemTouchHelper.UP,
////                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
////            /**
////             * Defines the drag and drop functionality.
////             *
////             * @param recyclerView The RecyclerView that contains the list items
////             * @param viewHolder The SportsViewHolder that is being moved
////             * @param target The SportsViewHolder that you are switching the
////             *               original one with.
////             * @return true if the item was moved, false otherwise
////             */
////            @Override
////            public boolean onMove(RecyclerView recyclerView,
////                                  RecyclerView.ViewHolder viewHolder,
////                                  RecyclerView.ViewHolder target) {
////                // Get the from and to positions.
////                int from = viewHolder.getAdapterPosition();
////                int to = target.getAdapterPosition();
////
////                // Swap the items and notify the adapter.
////                Collections.swap(mModelData, from, to);
////                mAdapter.notifyItemMoved(from, to);
////                return true;
////            }
////
////            /**
////             * Defines the swipe to dismiss functionality.
////             *
////             * @param viewHolder The viewholder being swiped.
////             * @param direction The direction it is swiped in.
////             */
////            @Override
////            public void onSwiped(RecyclerView.ViewHolder viewHolder,
////                                 int direction) {
////                // Remove the item from the dataset.
////                mModelData.remove(viewHolder.getAdapterPosition());
////                // Notify the adapter.
////                mAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
////            }
////        });
////
////        // Attach the helper to the RecyclerView.
////        helper.attachToRecyclerView(mRecyclerView);
//    }

    private void setActionBar() {
        Objects.requireNonNull(getSupportActionBar()).show();
        Objects.requireNonNull(getSupportActionBar()).setTitle(getString(R.string.title_dokter));
        ArrayList<View> textViews = new ArrayList<>();
        getWindow().getDecorView().findViewsWithText(textViews, getTitle(), View.FIND_VIEWS_WITH_TEXT);
        if (textViews.size() > 0) {
            AppCompatTextView appCompatTextView = null;
            if (textViews.size() == 1) {
                appCompatTextView = (AppCompatTextView) textViews.get(0);
            } else {
                for (View v : textViews) {
                    if (v.getParent() instanceof Toolbar) {
                        appCompatTextView = (AppCompatTextView) v;
                        break;
                    }
                }
            }
            if (appCompatTextView != null) {
                ViewGroup.LayoutParams params = appCompatTextView.getLayoutParams();
                params.width = ViewGroup.LayoutParams.MATCH_PARENT;
                appCompatTextView.setLayoutParams(params);
                appCompatTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //                // Create new fragment and transaction
//                Fragment newFragment = new HomeNavFragment();
//                // consider using Java coding conventions (upper first char class names!!!)
//                FragmentTransaction transaction = getFragmentManager().beginTransaction();
//
//                // Replace whatever is in the fragment_container view with this fragment,
//                // and add the transaction to the back stack
//                transaction.replace(R.id.containerViewPager, newFragment);
//                transaction.addToBackStack(null);
//
//                // Commit the transaction
//                transaction.commit();


        return super.onOptionsItemSelected(item);
    }

    private void initData() {

        mModelData.add(new MenuHariiniModel("Fisiotherapy", "Widodo Amd. Fis", "07:30", "4", "3", "0", "0"));
        mModelData.add(new MenuHariiniModel("Internist", "Bambang Eko Wahyono, dr.Sp. PD", "08:00", "1", "0", "0", "45"));
        mModelData.add(new MenuHariiniModel("Anak", "Taufiqur Rahman,dr. Spa", "08:00", "28", "28", "0", "0"));
        mModelData.add(new MenuHariiniModel("Syaraf", "Dhimas Hantoko, dr.Sp.S", "08:00", "49", "40", "0", "1"));
        mModelData.add(new MenuHariiniModel("Bedah Syaraf", "Suhariyanto, dr. Sp.BS", "08:00", "13", "11", "0", "0"));
        mModelData.add(new MenuHariiniModel("Jantung", "Laksmi Pramushinta, dr, Sp.JP", "08:30", "44", "25", "0", "0"));
        mModelData.add(new MenuHariiniModel("Internist", "Fajar Admayana, dr,Sp.PD", "08:00", "32", "27", "0", "0"));
        mModelData.add(new MenuHariiniModel("Gigi", "Agustina, drg", "09:00", "3", "2", "0", "0"));
        mModelData.add(new MenuHariiniModel("Bedah", "Chrisna Budi Satria, dr, Sp. B", "09:00", "14", "13", "0", "0"));
        mModelData.add(new MenuHariiniModel("Kandungan", "Supratiko, dr, Sp. OG(K)", "09:00", "0", "0", "0", "5"));
        mModelData.add(new MenuHariiniModel("Urologi", "Randa Halfan, dr, Sp.U", "09:00", "25", "9", "0", "0"));
        mModelData.add(new MenuHariiniModel("Paru", "Ganis Tjahyono, dr.SP.P", "10:00", "29", "12", "0", "4"));
        mModelData.add(new MenuHariiniModel("Mata", "Kartini, dr SpM", "10:00", "18", "10", "0", "0"));
        mModelData.add(new MenuHariiniModel("Rehab Medis", "Zakir Iskandar, dr. Sp.RM", "10:00", "15", "13", "0", "3"));
        mModelData.add(new MenuHariiniModel("Bedah Ortopedi", "Abdur Rahman Yusuf H, dr. SpOT", "12:00", "24", "0", "0", "5"));
        mModelData.add(new MenuHariiniModel("Kandungan", "Trimayanta Olfah, dr, SpOG", "12:00", "7", "0", "0", "1"));
        mModelData.add(new MenuHariiniModel("T H T", "Hari Purnomo, dr. Sp.THT", "13:00", "4", "0", "0", "1"));
        mModelData.add(new MenuHariiniModel("Kulit dan Kelamin", "Enik Sri Hartati, dr.SpKK", "14:00", "0", "0", "0", "22"));
        mModelData.add(new MenuHariiniModel("Paru", "Lilis Asfaroh, dr Sp.P", "14:00", "0", "0", "0", "5"));
        mModelData.add(new MenuHariiniModel("Gigi", "Agus Syaifuddin Setiawan, drg", "15:00", "0", "0", "0", "1"));
        mModelData.add(new MenuHariiniModel("Urologi", "Rochmad Yasin,dr.Sp.U", "16:00", "0", "0", "0", "3"));
        mModelData.add(new MenuHariiniModel("Syaraf", "Irawan S, dr.Sp.S", "16:00", "0", "0", "0", "26"));
        mModelData.add(new MenuHariiniModel("Bedah Kepala Leher", "Sahudi, dr. Sp.BKL", "16:00", "0", "0", "0", "9"));
        mModelData.add(new MenuHariiniModel("Internist", "Eko Budi Santoso, dr Sp.PD", "18:00", "1", "0", "0", "15"));
        mModelData.add(new MenuHariiniModel("Jantung", "Mochammad Basori, Sp.JP", "18:30", "0", "0", "0", "25"));
        MenuHariiniAdapter mAdapter = new MenuHariiniAdapter(mModelData);

        mAdapter.notifyDataSetChanged();
    }
    
    private void setContent() {

        //setActionBar();
        getCurrentTime();
        setRecyclerView();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_hariini);
        setContent();
    }
}