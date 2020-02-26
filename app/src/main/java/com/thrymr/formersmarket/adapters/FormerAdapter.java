package com.thrymr.formersmarket.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.thrymr.formersmarket.Model.Photos;
import com.thrymr.formersmarket.R;

import java.util.ArrayList;

public class FormerAdapter extends RecyclerView.Adapter<FormerAdapter.FormerViewHolder> {
    private Context context;
    private ArrayList<Photos> photosList;

    public FormerAdapter(Context context, ArrayList<Photos> photosArrayList) {
        this.context=context;
        photosList=photosArrayList;
    }

    @NonNull
    @Override
    public FormerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_former, parent, false);
        return new FormerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FormerViewHolder holder, int position) {
        Photos photo = photosList.get(position);
        holder.imgFormer.setImageResource(photo.getFormerImage());
    }

    @Override
    public int getItemCount() {
        return photosList.size();
    }

    public class FormerViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgFormer;
        public FormerViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFormer=itemView.findViewById(R.id.imv_former);
        }
    }
}
