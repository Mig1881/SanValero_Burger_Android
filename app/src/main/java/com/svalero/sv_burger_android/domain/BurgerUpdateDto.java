package com.svalero.sv_burger_android.domain;

import com.google.gson.annotations.SerializedName;

public class BurgerUpdateDto {

    @SerializedName("nombre")
    private String nombre;
    @SerializedName("ingredientes")
    private String ingredientes;
    @SerializedName("precio")
    private float precio;
    @SerializedName("opcionVegana")
    private boolean opcionVegana;

    // --- CAMBIO RADICAL: Usamos byte[] directamente ---
    @SerializedName("imagen")
    private byte[] imagen;
    // --------------------------------------------------

    public BurgerUpdateDto(String nombre, String ingredientes, float precio, boolean opcionVegana, byte[] imagen) {
        this.nombre = nombre;
        this.ingredientes = ingredientes;
        this.precio = precio;
        this.opcionVegana = opcionVegana;
        this.imagen = imagen;
    }
}