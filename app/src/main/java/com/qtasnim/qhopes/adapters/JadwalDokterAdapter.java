package com.qtasnim.qhopes.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.qtasnim.qhopes.R;
import com.qtasnim.qhopes.models.response.JadwalDokter;

import java.util.ArrayList;
import java.util.List;


public class JadwalDokterAdapter extends RecyclerView.Adapter<JadwalDokterAdapter.MyViewHolder> implements Filterable {

    private List<JadwalDokter> jadwalList;
    private List<JadwalDokter> jadwalListFiltered;
    private Context context;
    private DokterListListener listener;
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;

    public JadwalDokterAdapter(Context context, List<JadwalDokter> jadwalList, DokterListListener listener) {
        this.context = context;
        this.listener = listener;
        this.jadwalList = jadwalList;
        this.jadwalListFiltered = jadwalList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_jadwal_dokter, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {
        JadwalDokter dokter = jadwalListFiltered.get(listPosition);
        holder.txt_dokter.setText(dokter.getNamaDokter());

    }

    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    jadwalListFiltered = jadwalList;
                } else {
                    List<JadwalDokter> filteredList = new ArrayList<>();
                    for (JadwalDokter row : jadwalList) {

                        if (row.getNamaDokter().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }

                    jadwalListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = jadwalListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                jadwalListFiltered = (ArrayList<JadwalDokter>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public int getItemViewType(int position) {
        return jadwalListFiltered.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    @Override
    public int getItemCount() {
        return jadwalListFiltered == null ? 0 : jadwalListFiltered.size();
    }

    public void add(JadwalDokter response) {
        jadwalListFiltered.add(response);
        notifyItemInserted(jadwalListFiltered.size() - 1);
    }

    public void clear() {
        while (getItemCount() > 0) {
            remove(getItem(0));
        }
    }

    JadwalDokter getItem(int position) {
        return jadwalListFiltered.get(position);
    }

    private void remove(JadwalDokter postItems) {
        int position = jadwalListFiltered.indexOf(postItems);
        if (position > -1) {
            jadwalListFiltered.remove(position);
            notifyItemRemoved(position);
        }
    }

    public interface DokterListListener {
        void onDokterSelected(JadwalDokter JadwalDokter);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txt_dokter;
        CardView cvItem;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.txt_dokter = (TextView) itemView.findViewById(R.id.txt_dokter);
            this.cvItem = (CardView) itemView.findViewById(R.id.lytItem);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onDokterSelected(jadwalListFiltered.get(getAdapterPosition()));
                }
            });
        }
    }
}
