package com.qtasnim.qhopes.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.balysv.materialripple.MaterialRippleLayout;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.qtasnim.qhopes.R;
import com.qtasnim.qhopes.models.response.Berita;
import java.util.List;

public class BeritaAdapterHorizontal extends RecyclerView.Adapter<BeritaAdapterHorizontal.MyViewHolder> {

    private Context context;
    private List<Berita> beritaList;
    private BeritaListListener listener;

    public BeritaAdapterHorizontal(Context context, List<Berita> beritaList, BeritaListListener listener) {
        this.context = context;
        this.listener = listener;
        this.beritaList = beritaList;
    }

    public interface BeritaListListener {
        void onBeritaSelected(Berita berita);
    }

    public void setOnItemClickListener(BeritaListListener mItemClickListener) {
        this.listener = mItemClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_item_menu_berita3, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Berita model = beritaList.get(position);
        holder.mJudul.setText(model.getTitle());
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .error(R.drawable.ic_broken_image);
        Glide.with(holder.mFoto)
                .load(model.getImage())
                .apply(options)
                .into(holder.mFoto);
        holder.mKonten.setText(model.getContent());
        holder.rpBerita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onBeritaSelected(beritaList.get(position));
            }
        });
    }


    @Override
    public int getItemCount() {
        return beritaList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        private View view;
        private TextView mJudul, mKonten;
        private ImageView mFoto;
        private CardView cvBerita;
        private RelativeLayout rlBerita;
        private MaterialRippleLayout rpBerita;

        public MyViewHolder(View itemView) {
            super(itemView);

            mJudul = itemView.findViewById(R.id.txt_title);
            mFoto = itemView.findViewById(R.id.img_thumb);
            mKonten = itemView.findViewById(R.id.txt_description);
            cvBerita = itemView.findViewById(R.id.cvBerita);
            rlBerita = itemView.findViewById(R.id.rlBerita);
            rpBerita = itemView.findViewById(R.id.rpBerita);
            view = itemView;
        }
    }
}