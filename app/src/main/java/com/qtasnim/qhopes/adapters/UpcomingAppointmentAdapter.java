package com.qtasnim.qhopes.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.qtasnim.qhopes.R;
import com.qtasnim.qhopes.models.UpcomingAppointmentModel;

import java.util.ArrayList;

public class UpcomingAppointmentAdapter extends RecyclerView.Adapter<UpcomingAppointmentAdapter.MyViewHolder> {

    private ArrayList<UpcomingAppointmentModel> mAppointmentModel;
    private View.OnClickListener mOnItemClickListener;

    public UpcomingAppointmentAdapter(ArrayList<UpcomingAppointmentModel> mAppointmentModel) {
        this.mAppointmentModel = mAppointmentModel;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_item_nav_appointment, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        UpcomingAppointmentModel currentModel = mAppointmentModel.get(position);
        holder.mNamaPoliklinik.setText(currentModel.getPoliklinik());
        holder.mNamaDokter.setText(currentModel.getDokter());
        holder.mTanggal.setText(currentModel.getTanggal());
        holder.mJam.setText(currentModel.getJam());
    }

    @Override
    public int getItemCount() {
        return mAppointmentModel.size();
    }

    //TODO: Step 2 of 4: Assign itemClickListener to your local View.OnClickListener variable
    public void setOnItemClickListener(View.OnClickListener itemClickListener) {
        mOnItemClickListener = itemClickListener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView
                mNamaDokter,
                mNamaPoliklinik,
                mTanggal,
                mJam;

        public MyViewHolder(View itemView) {
            super(itemView);
            mNamaPoliklinik = itemView.findViewById(R.id.tv_nama_poliklink);
            mNamaDokter = itemView.findViewById(R.id.tv_nama_dokter);
            mJam = itemView.findViewById(R.id.tv_jam_appoinment);
            mTanggal = itemView.findViewById(R.id.tv_tanggal_appoinment);
            itemView.setTag(this);
            itemView.setOnClickListener(mOnItemClickListener);
        }
    }
}