package com.qtasnim.qhopes.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.qtasnim.qhopes.R;
import com.qtasnim.qhopes.models.BeritaModel;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class BeritaAdapterHorizontal extends RecyclerView.Adapter<BeritaAdapterHorizontal.MyViewHolder> {

    private ArrayList<BeritaModel> mData;
    private View.OnClickListener mOnItemClickListener;

    public BeritaAdapterHorizontal(ArrayList<BeritaModel> mData) {
        this.mData = mData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_item_menu_berita3, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        BeritaModel currentModel = mData.get(position);
        holder.mJudul.setText(currentModel.getJudul());
        holder.mFoto.setImageResource(getResId(currentModel.getFoto(),R.drawable.class));
        holder.mFoto.setScaleType(ImageView.ScaleType.CENTER_CROP);
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

        private TextView mJudul, mKonten;
        private ImageView mFoto;

        public MyViewHolder(View itemView) {
            super(itemView);

            mJudul = itemView.findViewById(R.id.txt_title);
            mFoto = itemView.findViewById(R.id.img_thumb);
            mKonten = itemView.findViewById(R.id.txt_description);

            itemView.setTag(this);
            itemView.setOnClickListener(mOnItemClickListener);
        }
    }
}