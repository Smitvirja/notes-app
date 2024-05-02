package com.example.notes_app.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.notes_app.Models.Notes;
import com.example.notes_app.R;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class recayclerviewadapter extends RecyclerView.Adapter<recayclerviewadapter.noteviewholder> {

    private List<Notes> notes;

    public recayclerviewadapter() {
        this.notes = new ArrayList<>();
    }

    @NonNull
    @Override
    public noteviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new noteviewholder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.card,
                        parent, false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull noteviewholder holder, int position) {
        holder.setnote(
                notes.get(position)
        );
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public void setNotes(List<Notes> notes) {
        this.notes = notes;
        notifyDataSetChanged();
    }

    static class noteviewholder extends RecyclerView.ViewHolder {
        TextView viewtitle, viewsubtitle, viewdate,viewnote;
        LinearLayout notesContainer;

        private int getRandomColor() {
            int[] colorArray = itemView.getResources().getIntArray(R.array.card_colors);
            Random random = new Random();
            return colorArray[random.nextInt(colorArray.length)];
        }

        public noteviewholder(@NonNull View itemView) {
            super(itemView);
            viewtitle = itemView.findViewById(R.id.viewtitle);
            viewsubtitle = itemView.findViewById(R.id.viewsub);
            viewdate = itemView.findViewById(R.id.viewdate);
            viewnote = itemView.findViewById(R.id.note);
            notesContainer = itemView.findViewById(R.id.layoutnote);
        }

        void setnote(Notes notes) {
            viewtitle.setText(notes.getTitle());

            if (notes.getSubtitle().trim().isEmpty()) {
                viewsubtitle.setVisibility(View.GONE);
            } else {
                viewsubtitle.setText(notes.getSubtitle());

            }
            viewdate.setText(notes.getDate());
            viewnote.setText(notes.getNote());
            // Set the background color dynamically

        }
    }
}
