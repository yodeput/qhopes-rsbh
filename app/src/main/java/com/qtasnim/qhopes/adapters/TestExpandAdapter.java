package com.qtasnim.qhopes.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.qtasnim.qhopes.R;
import com.qtasnim.qhopes.adapters.viewholder.PoliViewHolder;
import com.qtasnim.qhopes.adapters.viewholder.DokterViewHolder;
import com.qtasnim.qhopes.models.DokterGroup;
import com.qtasnim.qhopes.models.response.Berita;
import com.qtasnim.qhopes.models.response.JadwalDokter;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.ArrayList;
import java.util.List;

public class TestExpandAdapter extends ExpandableRecyclerViewAdapter<PoliViewHolder, DokterViewHolder>  {

    private OnChildClickListener mOnChildClickListener;
    private List<JadwalDokter> jadwalDokters = new ArrayList<>();

    public interface OnChildClickListener {
        void onItemClick(View view, JadwalDokter obj, int position);
    }

    public void setOnChildClickListener(OnChildClickListener mOnChildClickListener) {
        this.mOnChildClickListener = mOnChildClickListener;
    }


    public TestExpandAdapter(List<? extends ExpandableGroup> groups,  List<JadwalDokter> jadwalDokters) {
        super(groups);
    }


    @Override
    public PoliViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_poli, parent, false);
        return new PoliViewHolder(view);
    }

    @Override
    public DokterViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_jadwal_dokter, parent, false);
        return new DokterViewHolder(view);
    }

    @Override
    public void onBindChildViewHolder(DokterViewHolder holder, int flatPosition, ExpandableGroup group,
                                      int childIndex) {
        jadwalDokters = ((DokterGroup) group).getItems();
        final JadwalDokter dokter = jadwalDokters.get(childIndex);

        holder.setData(dokter);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnChildClickListener == null) return;
                mOnChildClickListener.onItemClick(view, dokter,childIndex);
            }
        });
    }

    @Override
    public void onBindGroupViewHolder(PoliViewHolder holder, int flatPosition,
                                      ExpandableGroup group) {
        holder.setTitle(group);
    }
}