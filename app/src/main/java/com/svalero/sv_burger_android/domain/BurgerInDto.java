package com.svalero.sv_burger_android.domain;

public class BurgerInDto {
    private String nombre;
    private String ingredientes;
    private float precio;
    private boolean opcionVegana;
    private long foodTruckId;
    // OJO: En Spring Boot es byte[], pero en JSON viaja como String (Base64).
    // Spring Boot lo convierte automáticamente.
    private String imagen;

    public BurgerInDto() {}

    public BurgerInDto(String nombre, String ingredientes, float precio, boolean opcionVegana, long foodTruckId, String imagen) {
        this.nombre = nombre;
        this.ingredientes = ingredientes;
        this.precio = precio;
        this.opcionVegana = opcionVegana;
        this.foodTruckId = foodTruckId;
        this.imagen = imagen;
    }

    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getIngredientes() { return ingredientes; }
    public void setIngredientes(String ingredientes) { this.ingredientes = ingredientes; }

    public float getPrecio() { return precio; }
    public void setPrecio(float precio) { this.precio = precio; }

    public boolean isOpcionVegana() { return opcionVegana; }
    public void setOpcionVegana(boolean opcionVegana) { this.opcionVegana = opcionVegana; }

    public long getFoodTruckId() { return foodTruckId; }
    public void setFoodTruckId(long foodTruckId) { this.foodTruckId = foodTruckId; }

    public String getImagen() { return imagen; }
    public void setImagen(String imagen) { this.imagen = imagen; }
}