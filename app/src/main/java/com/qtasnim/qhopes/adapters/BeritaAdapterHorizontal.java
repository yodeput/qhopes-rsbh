package com.qtasnim.qhopes.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.qtasnim.qhopes.R;
import com.qtasnim.qhopes.models.response.Berita;
import java.util.List;

public class BeritaAdapterHorizontal extends RecyclerView.Adapter<BeritaAdapterHorizontal.MyViewHolder> {

    private List<Berita> mData;
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View view, Berita obj, int position);
    }

    public void setOnItemClickListener(OnItemClickListener mItemClickListener) {
        this.mOnItemClickListener = mItemClickListener;
    }

    public BeritaAdapterHorizontal(List<Berita> mData) {
        this.mData = mData;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_item_menu_berita3, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Berita model = mData.get(position);
        holder.mJudul.setText(model.getTitle());
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .error(R.drawable.ic_broken_image);
        Glide.with(holder.mFoto)
                .load(model.getImage())
                .apply(options)
                .into(holder.mFoto);
        holder.mKonten.setText(model.getContent());
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnItemClickListener == null) return;
               mOnItemClickListener.onItemClick(view, model,position);
            }
        });
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        private View view;
        private TextView mJudul, mKonten;
        private ImageView mFoto;

        public MyViewHolder(View itemView) {
            super(itemView);

            mJudul = itemView.findViewById(R.id.txt_title);
            mFoto = itemView.findViewById(R.id.img_thumb);
            mKonten = itemView.findViewById(R.id.txt_description);
            this.view = itemView;
        }
    }
}