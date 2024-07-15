package com.example.wheelmax77;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CarAdapter extends RecyclerView.Adapter<CarViewHolder> {
    Context context;
    List<Car> cars;

    public CarAdapter(Context context, List<Car> cars) {
        this.context = context;
        this.cars = cars;
    }



    @NonNull
    @Override
    public CarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new  CarViewHolder(LayoutInflater.from(context).inflate(R.layout.car_view,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull CarViewHolder holder, int position) {
            holder.name.setText(cars.get(position).getName());
            holder.price.setText(cars.get(position).getPrice());
            holder.imageView.setImageResource(cars.get(position).getImageUrl());
    }

    @Override
    public int getItemCount() {
        return cars.size();
    }

    public void filterList(List<Car> filteredList) {

    }
}
