package com.example.testprojectmvvm.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testprojectmvvm.R;
import com.example.testprojectmvvm.models.UserEntrys;

import java.util.ArrayList;
import java.util.List;

public  class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private List<UserEntrys> listUserEntrys = new ArrayList<>();
    private Context context;

    public RecyclerViewAdapter(Context context, List<UserEntrys> listUserEntrys) {
        this.context = context;
        this.listUserEntrys = listUserEntrys;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder    (@NonNull ViewHolder holder, int position) {
        holder.textUserNote.setText(listUserEntrys.get(position).getText());
    }

    @Override
    public int getItemCount() {
        return listUserEntrys.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textUserNote;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textUserNote = itemView.findViewById(R.id.textView);
        }

        @Override
        public void onClick(View v) {
        }
    }
}
