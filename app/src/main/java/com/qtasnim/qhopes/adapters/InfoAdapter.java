package com.qtasnim.qhopes.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.qtasnim.qhopes.R;
import com.qtasnim.qhopes.models.InfoModel;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class InfoAdapter extends RecyclerView.Adapter<InfoAdapter.MyViewHolder> {

    private ArrayList<InfoModel> mData;
    private View.OnClickListener mOnItemClickListener;

    public InfoAdapter(ArrayList<InfoModel> mData) {
        this.mData = mData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_item_menu_info, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        InfoModel currentModel = mData.get(position);
        holder.mJudul.setText(currentModel.getJudul());
        holder.mTanggalTerbit.setText(currentModel.getTanggalTerbit());
        holder.mFoto.setImageResource(getResId(currentModel.getFoto(),R.drawable.class));
        holder.mKonten.setText(currentModel.getKonten());
    }

    public static int getResId(String resName, Class<?> c) {

        try {
            Field idField = c.getDeclaredField(resName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    //TODO: Step 2 of 4: Assign itemClickListener to your local View.OnClickListener variable
    public void setOnItemClickListener(View.OnClickListener itemClickListener) {
        mOnItemClickListener = itemClickListener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView mJudul, mTanggalTerbit, mKonten;
        private ImageView mFoto;

        public MyViewHolder(View itemView) {
            super(itemView);

            mJudul = itemView.findViewById(R.id.tv_judul_info);
            mTanggalTerbit = itemView.findViewById(R.id.tv_tanggalterbit_info);
            mFoto = itemView.findViewById(R.id.iv_foto_info);
            mKonten = itemView.findViewById(R.id.tv_konten_info);

            itemView.setTag(this);
            itemView.setOnClickListener(mOnItemClickListener);
        }
    }
}