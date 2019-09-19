package com.qtasnim.qhopes.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.qtasnim.qhopes.R;
import com.qtasnim.qhopes.models.MenuHariiniModel;

import java.util.ArrayList;

public class MenuHariiniAdapter extends RecyclerView.Adapter<MenuHariiniAdapter.MyViewHolder> {

    private ArrayList<MenuHariiniModel> mMenuHariiniModel;
    private View.OnClickListener mOnItemClickListener;

    public MenuHariiniAdapter(ArrayList<MenuHariiniModel> mMenuHariiniModel) {
        this.mMenuHariiniModel = mMenuHariiniModel;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_item_menu_hariini, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        MenuHariiniModel currentModel = mMenuHariiniModel.get(position);
        holder.mNamaPoliklinik.setText(currentModel.getNama_poliklinik());
        holder.mNamaDokter.setText(currentModel.getNama_dokter());
        holder.mKuotaPasien.setText("Kuota  : "+currentModel.getKuota_pasien()+" Pasien");
        holder.mPasienTerlayani.setText("Terlayani : "+currentModel.getPasien_terlayani()+" Pasien");
        holder.mPasienTerdaftar.setText("Daftar : "+currentModel.getPasien_terdaftar()+" Pasien");
        holder.mPasienMengantri.setText("Antri         : 0 Pasien");
        holder.mJamPraktek.setText(currentModel.getJam_praktek());
//        holder.mCheckin.setText(currentModel.getPasien_checkin());
    }

    @Override
    public int getItemCount() {
        return mMenuHariiniModel.size();
    }

    //TODO: Step 2 of 4: Assign itemClickListener to your local View.OnClickListener variable
    public void setOnItemClickListener(View.OnClickListener itemClickListener) {
        mOnItemClickListener = itemClickListener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView
                mNamaDokter,
                mNamaPoliklinik,
                mKuotaPasien,
                mPasienTerlayani,
                mPasienTerdaftar,
                mPasienMengantri,
                mJamPraktek,
                mCheckin;

        public MyViewHolder(View itemView) {
            super(itemView);
            mNamaPoliklinik = itemView.findViewById(R.id.tv_nama_poliklink);
            mNamaDokter = itemView.findViewById(R.id.tv_nama_dokter);
            mKuotaPasien = itemView.findViewById(R.id.tv_kuota_pasien);
            mPasienTerlayani = itemView.findViewById(R.id.tv_pasien_terlayani);
            mPasienTerdaftar = itemView.findViewById(R.id.tv_pasien_terdaftar);
            mPasienMengantri = itemView.findViewById(R.id.tv_pasien_mengantri);
            mJamPraktek = itemView.findViewById(R.id.tv_jam_praktek);

            //TODO: Step 3 of 4: setTag() as current view holder along with
            // setOnClickListener() as your local View.OnClickListener variable.
            // You can set the same mOnItemClickListener on multiple views if required
            // and later differentiate those clicks using view's id.
            itemView.setTag(this);
            itemView.setOnClickListener(mOnItemClickListener);
        }
    }
}