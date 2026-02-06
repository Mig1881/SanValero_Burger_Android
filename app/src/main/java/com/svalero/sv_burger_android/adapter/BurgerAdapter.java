package com.svalero.sv_burger_android.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.svalero.sv_burger_android.R;
import com.svalero.sv_burger_android.domain.Burger;
import com.svalero.sv_burger_android.view.BurgerDetailView;

import java.util.List;

public class BurgerAdapter extends RecyclerView.Adapter<BurgerAdapter.BurgerViewHolder> {

    private List<Burger> burgerList;

    public BurgerAdapter(List<Burger> burgerList) {
        this.burgerList = burgerList;
    }

    @NonNull
    @Override
    public BurgerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_burger, parent, false);
        return new BurgerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BurgerViewHolder holder, int position) {
        Burger burger = burgerList.get(position);

        holder.tvName.setText(burger.getNombre());
        holder.tvIngredients.setText(burger.getIngredientes());

        if (burger.getFoodTruck() != null) {
            holder.tvFoodTruck.setText("🚚 " + burger.getFoodTruck().getNombre());
            holder.tvFoodTruck.setVisibility(View.VISIBLE);
        } else {
            holder.tvFoodTruck.setVisibility(View.GONE);
        }

        String precioFormateado = String.format("%.2f €", burger.getPrecio());
        holder.tvPrice.setText(precioFormateado);

        String imageUrl = burger.getImagenURL();
        if (imageUrl != null && !imageUrl.startsWith("http")) {
            imageUrl = "http://10.0.2.2:8080" + imageUrl;
        }

        Glide.with(holder.itemView.getContext())
                .load(imageUrl)
                .diskCacheStrategy(com.bumptech.glide.load.engine.DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .placeholder(R.drawable.ic_burger)
                .error(R.drawable.ic_burger)
                .into(holder.ivImage);

        holder.itemView.setOnClickListener(v -> {
            Context context = holder.itemView.getContext();
            Intent intent = new Intent(context, BurgerDetailView.class);
            intent.putExtra("burger_id", burger.getId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() { return burgerList.size(); }

    static class BurgerViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvIngredients, tvPrice;
        TextView tvFoodTruck;
        ImageView ivImage;

        public BurgerViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvBurgerName);
            // Referenciamos el nuevo ID del XML
            tvFoodTruck = itemView.findViewById(R.id.tvFoodTruckName);
            tvIngredients = itemView.findViewById(R.id.tvBurgerIngredients);
            tvPrice = itemView.findViewById(R.id.tvBurgerPrice);
            ivImage = itemView.findViewById(R.id.ivBurgerImage);
        }
    }
}