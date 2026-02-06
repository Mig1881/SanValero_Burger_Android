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
        // 1. Recuperamos la hamburguesa de esta posición
        Burger burger = burgerList.get(position);

        // 2. Pintamos los datos
        holder.tvName.setText(burger.getNombre());
        holder.tvIngredients.setText(burger.getIngredientes());

        // Formateamos el precio para que quede bonito
        String precioFormateado = String.format("%.2f €", burger.getPrecio());
        holder.tvPrice.setText(precioFormateado);

        // 3. Lógica de la imagen (mantengo la tuya que está perfecta)
        String imageUrl = burger.getImagenURL();
        if (imageUrl != null && !imageUrl.startsWith("http")) {
            imageUrl = "http://10.0.2.2:8080" + imageUrl;
        }

        Glide.with(holder.itemView.getContext())
                .load(imageUrl)
                .placeholder(R.drawable.ic_burger)
                .error(R.drawable.ic_burger)
                .into(holder.ivImage);

        // --- 4. NUEVO: CLICK PARA IR AL DETALLE ---
        holder.itemView.setOnClickListener(v -> {
            // Obtenemos el contexto desde la vista para poder lanzar el Intent
            Context context = holder.itemView.getContext();

            Intent intent = new Intent(context, BurgerDetailView.class);
            intent.putExtra("burger_id", burger.getId()); // ¡Pasamos el ID clave!
            context.startActivity(intent);
        });
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