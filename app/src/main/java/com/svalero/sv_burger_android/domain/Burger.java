package com.svalero.sv_burger_android.domain;

public class Burger {
    private Long id;
    private String nombre;
    private String ingredientes;
    private Float precio;
    private String fechaCreacion;
    private boolean opcionVegana;
    private String imagenURL; 


    public Burger() {}

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getIngredientes() { return ingredientes; }
    public void setIngredientes(String ingredientes) { this.ingredientes = ingredientes; }
    public Float getPrecio() { return precio; }
    public void setPrecio(Float precio) { this.precio = precio; }
    public String getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(String fechaCreacion) { this.fechaCreacion = fechaCreacion; }
    public boolean isOpcionVegana() { return opcionVegana; }
    public void setOpcionVegana(boolean opcionVegana) { this.opcionVegana = opcionVegana; }
    public String getImagenURL() { return imagenURL; }
    public void setImagenURL(String imagenURL) { this.imagenURL = imagenURL; }
}