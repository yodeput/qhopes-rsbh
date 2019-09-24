package com.qtasnim.qhopes.adapters.viewholder;

import android.view.View;
import android.widget.TextView;

import com.qtasnim.qhopes.R;
import com.qtasnim.qhopes.models.response.JadwalDokter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

public class DokterViewHolder extends ChildViewHolder {

        private TextView txt_dokter,txt_jamPraktek,txt_daftar,txt_kuota,txt_terlayani,txt_antri;

        public DokterViewHolder(View itemView) {
            super(itemView);
            txt_dokter = itemView.findViewById(R.id.txt_dokter);
            txt_jamPraktek = itemView.findViewById(R.id.txt_jamPraktek);
            txt_daftar = itemView.findViewById(R.id.txt_daftar);
            txt_kuota = itemView.findViewById(R.id.txt_kuota);
            txt_terlayani = itemView.findViewById(R.id.txt_terlayani);
            txt_antri = itemView.findViewById(R.id.txt_antri);

        }

        public void setData(JadwalDokter dokter) {
            txt_dokter.setText(dokter.getNamaDokter());
            txt_jamPraktek.setText(dokter.getJadwal());
            txt_daftar.setText(dokter.getDaftar());
            txt_kuota.setText(dokter.getKuota());
            txt_terlayani.setText(dokter.getTerlayani());
            txt_antri.setText(dokter.getAntri());


        }
}
