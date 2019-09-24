package com.qtasnim.qhopes.adapters.viewholder;

import android.view.View;
import android.widget.TextView;

import com.qtasnim.qhopes.R;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

public class PoliViewHolder extends GroupViewHolder {

        private TextView title;

        public PoliViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.txt_poli);
        }

        public void setTitle(ExpandableGroup group) {
            title.setText(group.getTitle());
        }
}
