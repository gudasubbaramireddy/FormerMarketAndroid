package com.thrymr.farmersmarket.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.thrymr.farmersmarket.R;

public class SelectedFarmersAdapter extends RecyclerView.Adapter<SelectedFarmersAdapter.SelectedFarmersViewHolder> {
    private Context context;
    public SelectedFarmersAdapter(Context context){
        this.context=context;
    }
    @NonNull
    @Override
    public SelectedFarmersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SelectedFarmersViewHolder(LayoutInflater.from(context).inflate(R.layout.item_former,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull SelectedFarmersViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class SelectedFarmersViewHolder extends RecyclerView.ViewHolder{

        public SelectedFarmersViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
