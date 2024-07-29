package com.dk.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long cliente;
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private ArrayList<Producto> productos ;
    private Double adelanto = 0.0;
    private LocalDate emitido = LocalDate.now();
    private LocalDate entrega;
    private boolean envio = false;
    private double costoDeEnvio = 0.0;
    private double precio= 0.0;
    private String ubicacionDeEntrega = "";

    public Pedido() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCliente() {
        return cliente;
    }

    public void setCliente(Long cliente) {
        this.cliente = cliente;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }

    public Double getAdelanto() {
        return adelanto;
    }

    public void setAdelanto(Double adelanto) {
        this.adelanto = adelanto;
    }

    public LocalDate getEmitido() {
        return emitido;
    }

    public void setEmitido(LocalDate emitido) {
        this.emitido = emitido;
    }

    public LocalDate getEntrega() {
        return entrega;
    }

    public void setEntrega(LocalDate entrega) {
        this.entrega = entrega;
    }

    public boolean isEnvio() {
        return envio;
    }

    public void setEnvio(boolean envio) {
        this.envio = envio;
    }

    public double getCostoDeEnvio() {
        return costoDeEnvio;
    }

    public void setCostoDeEnvio(double costoDeEnvio) {
        this.costoDeEnvio = costoDeEnvio;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Double calculoDelCosto(){
        double sumaPrecios =productos.stream().mapToDouble(producto -> producto.getPrecio() * producto.getCantidad()).sum();
        return sumaPrecios + costoDeEnvio - adelanto;
    }
}
