package com.thrymr.farmersmarket.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.thrymr.farmersmarket.Model.Photos;
import com.thrymr.farmersmarket.R;

import java.util.ArrayList;

public class FarmerAdapter extends RecyclerView.Adapter<FarmerAdapter.FormerViewHolder> {
    private Context context;
    private ArrayList<Photos> photosList;

    public FarmerAdapter(Context context, ArrayList<Photos> photosArrayList) {
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
        holder.imgPerson.setImageResource(photo.getFormerImage());
        holder.txtName.setText(photo.getName());
    }

    @Override
    public int getItemCount() {
        return photosList.size();
    }

    public class FormerViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgPerson;
        private TextView txtName;
        public FormerViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPerson =itemView.findViewById(R.id.imv_person);
            txtName =itemView.findViewById(R.id.txt_name);

        }
    }
}
