package com.qtasnim.qhopes.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qtasnim.qhopes.R;
import com.qtasnim.qhopes.models.response.Berita;
import com.qtasnim.qhopes.models.response.Slider;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.List;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.SliderAdapterVH> {

    private List<Slider> sliderList;
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View view, Slider obj, int position);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mOnItemClickListener = mItemClickListener;
    }


    public SliderAdapter(List<Slider> sliderList) {
        this.sliderList = sliderList;
    }


    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {

        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_slider, null);
        return new SliderAdapterVH(inflate);
    }

    @Override
    public void onBindViewHolder(SliderAdapterVH holder, int position) {
        Slider model = sliderList.get(position);
        holder.txtSlider.setText(model.getDesc());
        Glide.with(holder.itemView)
                .load(model.getImage())
                .centerCrop()
                .into(holder.imgSlider);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnItemClickListener == null) return;
                mOnItemClickListener.onItemClick(view, model, position);
            }
        });
    }

    @Override
    public int getCount() {
        //slider view count could be dynamic size
        return 4;
    }

    class SliderAdapterVH extends SliderViewAdapter.ViewHolder {

        View itemView;
        ImageView imgSlider;
        TextView txtSlider;

        public SliderAdapterVH(View itemView) {
            super(itemView);
            imgSlider = itemView.findViewById(R.id.imgSlider);
            txtSlider = itemView.findViewById(R.id.txtSlider);
            this.itemView = itemView;
        }
    }
}