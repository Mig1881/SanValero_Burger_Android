package com.svalero.sv_burger_android.domain;


public class FoodTruck {
    private Long id;
    private String nombre;
    private String telefono;
    private String email;
    private String descripcion;
    private Boolean opcionEnvios;
    private Float valoracion;

    public FoodTruck() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Boolean getOpcionEnvios() { return opcionEnvios; }
    public void setOpcionEnvios(Boolean opcionEnvios) { this.opcionEnvios = opcionEnvios; }

    public Float getValoracion() { return valoracion; }
    public void setValoracion(Float valoracion) { this.valoracion = valoracion; }
}
