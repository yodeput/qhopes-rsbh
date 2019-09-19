package com.qtasnim.qhopes.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.qtasnim.qhopes.R;
import com.qtasnim.qhopes.adapters.BeritaAdapter;
import com.qtasnim.qhopes.models.BeritaModel;

import java.util.ArrayList;


public class MenuBeritaFragment extends Fragment {

    private ArrayList<BeritaModel> mModelData = new ArrayList<>();

    private View.OnClickListener onItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            int position = viewHolder.getAdapterPosition();
            // viewHolder.getItemId();
            // viewHolder.getItemViewType();
            // viewHolder.itemView;
            BeritaModel mAppointmentModel = mModelData.get(position);
            setDialog(mAppointmentModel);

        }
    };


    private void setDialog(BeritaModel currentModel) {

        final Dialog mDialog = new Dialog(getContext());
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.view_item_menu_berita, container, false);
        View vRV = inflater.inflate(R.layout.fragment_menu_berita,container,false);

        RecyclerView mRecyclerView = vRV.findViewById(R.id.rv_menu_berita);
        BeritaAdapter recyclerViewAdapter = new BeritaAdapter(mModelData);
        initData();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(recyclerViewAdapter);
        recyclerViewAdapter.setOnItemClickListener(onItemClickListener);


        return rootView;
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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}