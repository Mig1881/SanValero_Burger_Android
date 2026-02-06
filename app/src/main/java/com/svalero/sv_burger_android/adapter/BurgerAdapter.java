package com.svalero.sv_burger_android.adapter;

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
        holder.tvPrice.setText(burger.getPrecio() + " €");

        // --- ARREGLO PARA GLIDE ---
        String imageUrl = burger.getImagenURL();

        // Si la URL viene incompleta (sin http), se lo ponemos nosotros
        if (imageUrl != null && !imageUrl.startsWith("http")) {
            // Añadimos la IP del emulador (10.0.2.2) o la IP de tu PC si usas móvil real
            // IMPORTANTE: Asegúrate de que el puerto (8080) es el correcto de tu Spring Boot
            imageUrl = "http://10.0.2.2:8080" + imageUrl;
        }

        // Ahora 'imageUrl' ya es una dirección completa de internet
        Glide.with(holder.itemView.getContext())
                .load(imageUrl)
                .placeholder(R.drawable.ic_burger)
                .error(R.drawable.ic_burger)
                .into(holder.ivImage);
    }

    @Override
    public int getItemCount() { return burgerList.size(); }

    static class BurgerViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvIngredients, tvPrice;
        ImageView ivImage;

        public BurgerViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvBurgerName);
            tvIngredients = itemView.findViewById(R.id.tvBurgerIngredients);
            tvPrice = itemView.findViewById(R.id.tvBurgerPrice);
            ivImage = itemView.findViewById(R.id.ivBurgerImage);
        }
    }
}
