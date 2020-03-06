package com.thrymr.farmersmarket.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.thrymr.farmersmarket.R;

public class CropListAdapter extends RecyclerView.Adapter<CropListAdapter.CropListViewHolder> {
    private Context context;
    public CropListAdapter(Context context){
        this.context=context;
    }

    @NonNull
    @Override
    public CropListAdapter.CropListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CropListViewHolder(LayoutInflater.from(context).inflate(R.layout.item_crop,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CropListAdapter.CropListViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class CropListViewHolder extends RecyclerView.ViewHolder{

        public CropListViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
