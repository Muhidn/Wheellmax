package com.example.wheelmax77;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CarViewHolder extends RecyclerView.ViewHolder {

    ImageView imageView;
    TextView name, price;
    public CarViewHolder(@NonNull View itemView) {
        super(itemView);

        imageView = itemView.findViewById(R.id.imageview);
        name = itemView.findViewById(R.id.name);
        price = itemView.findViewById(R.id.price);
    }
}
