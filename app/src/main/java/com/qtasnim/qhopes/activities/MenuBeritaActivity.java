package com.qtasnim.qhopes.activities;

import android.app.Dialog;
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
import com.qtasnim.qhopes.adapters.BeritaAdapter;
import com.qtasnim.qhopes.models.BeritaModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;


public class MenuBeritaActivity extends AppCompatActivity {

    private ArrayList<BeritaModel> mModelData = new ArrayList<>();

    private View.OnClickListener onItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            int position = viewHolder.getAdapterPosition();
            // viewHolder.getItemId();
            // viewHolder.getItemViewType();
            // viewHolder.itemView;
            BeritaModel mBeritaModel = mModelData.get(position);
            setDialog(mBeritaModel);

        }
    };

    private void setDialog(BeritaModel currentModel) {

        final Dialog mDialog = new Dialog(MenuBeritaActivity.this);
        mDialog.setContentView(R.layout.view_dialog_menu_hariini);

        TextView mDialogPoliklinik = mDialog.findViewById(R.id.tv_dialog_poliklinik);
        TextView mDialogDokter = mDialog.findViewById(R.id.tv_dialog_dokter);
        TextView mJamPraktek = mDialog.findViewById(R.id.tv_dialog_jampraktek);
        TextView mPasienTerdaftar = mDialog.findViewById(R.id.tv_dialog_pasien_terdaftar);
        TextView mCheckin = mDialog.findViewById(R.id.tv_dialog_checkin_pasien);
        TextView mPasienTerlayani = mDialog.findViewById(R.id.tv_dialog_pasien_terlayani);
        TextView mKuotaPasien = mDialog.findViewById(R.id.tv_dialog_kuota_pasien);


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
                //TODO: set intent jumpactivity to pendaftaran activity form
//                Intent jumpActivity = new Intent(MenuHariIniActivity.this, PendaftaranNavFragment.class);
//                startActivity(jumpActivity);
//                finish();
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

        RecyclerView mRecyclerView = findViewById(R.id.rv_menu_berita);
        BeritaAdapter recyclerViewAdapter = new BeritaAdapter(mModelData);

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
        Objects.requireNonNull(getSupportActionBar()).setTitle(getString(R.string.tbtn_menu_berita));
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
        mModelData.add(new BeritaModel("Pelatihan Pemadaman Kebakaran","15/05/2019","foto_berita","RS. Bhakti Husada Cikarang melakukan pelatihan pemadam kebakaran terhadap semua staff dan karyawan yang dilaksanakn setiap tahun dan bekerja sama dengan tim pemadam kebakaran Kab. Bekasi"));
        mModelData.add(new BeritaModel("ISPA","10/10/17","foto_berita2","ISPA adalah kepanjanngan dari Infeksi Saluran Pernafasan Akut yang berarti terjadinya infeksi yang parah pada bagian sinus, tenggorokan, saluran udara, atau paru-paru. Kondisi ini menyebabkan fungsi pernafasan menjadi terganggu. Jika tidak segera ditangani, ISPA dapat menyebar ke seluruh sistem pernafasan tubuh bahkan dapat menyebabkan kematian."));
        BeritaAdapter mAdapter = new BeritaAdapter(mModelData);

        mAdapter.notifyDataSetChanged();
    }
    
    private void setContent() {
        //setActionBar();
        setRecyclerView();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_berita);
        setContent();
    }
}