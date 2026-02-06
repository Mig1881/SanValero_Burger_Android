package com.svalero.sv_burger_android.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.svalero.sv_burger_android.R;
import com.svalero.sv_burger_android.domain.FoodTruck;

import java.util.List;

public class FoodTruckAdapter extends RecyclerView.Adapter<FoodTruckAdapter.FoodTruckViewHolder> {

    private List<FoodTruck> foodTruckList;

    public FoodTruckAdapter(List<FoodTruck> foodTruckList) {
        this.foodTruckList = foodTruckList;
    }

    @NonNull
    @Override
    public FoodTruckViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_food_truck, parent, false);
        return new FoodTruckViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodTruckViewHolder holder, int position) {
        FoodTruck truck = foodTruckList.get(position);

        // Asignamos datos a la vista
        holder.tvName.setText(truck.getNombre());
        holder.tvDescription.setText(truck.getDescripcion());
        holder.tvPhone.setText("📞 " + truck.getTelefono());

        // Formateamos la valoración (ej: 8.5)
        if (truck.getValoracion() != null) {
            holder.tvRating.setText("⭐ " + truck.getValoracion());
        } else {
            holder.tvRating.setText("⭐ -");
        }
        // CLIC EN LA TARJETA
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), com.svalero.sv_burger_android.view.FoodTruckDetailView.class);
            intent.putExtra("id", truck.getId());
            intent.putExtra("name", truck.getNombre());
            intent.putExtra("description", truck.getDescripcion());
            intent.putExtra("phone", truck.getTelefono());
            intent.putExtra("email", truck.getEmail());
            // Ojo con los nulls en floats
            intent.putExtra("rating", truck.getValoracion() != null ? truck.getValoracion() : 0f);

            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return foodTruckList.size();
    }

    public static class FoodTruckViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvDescription, tvPhone, tvRating;

        public FoodTruckViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvTruckName);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvPhone = itemView.findViewById(R.id.tvPhone);
            tvRating = itemView.findViewById(R.id.tvRating);
        }
    }
}
