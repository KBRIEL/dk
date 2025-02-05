package com.dk.modelo.dto;

import com.dk.modelo.Pedido;
import com.dk.modelo.Producto;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;

@AllArgsConstructor
public class CrearPedidoDTO {
    private Pedido pedido;
    private Long id;
    private Long cliente;
    private String descripcion;
    private ArrayList<Producto> productos ;
    private Double adelanto = 0.0;
    private LocalDate emitido ;
    private LocalDate entrega;
    private boolean envio ;
    private double costoDeEnvio;
    private double precio;
    private String ubicacionDeEntrega ;

/*    public CrearPedidoDTO(Pedido pedido) {
        this.pedido = pedido;
        id = pedido.getId();
        cliente = pedido.getCliente();
        descripcion = pedido.getDescripcion();
        productos = pedido.getProductos() ;
        adelanto = pedido.getAdelanto() ;
        emitido = pedido.getEmitido() ;
        entrega = pedido.getEntrega();
        envio = pedido.isEnvio() ;
        costoDeEnvio = pedido.getCostoDeEnvio();
        precio = pedido.getPrecio();
        ubicacionDeEntrega = pedido.getDescripcion() ;
    }*/

    public Long getId() {
        return id;
    }

    public Long getCliente() {
        return cliente;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public Double getAdelanto() {
        return adelanto;
    }

    public LocalDate getEmitido() {
        return emitido;
    }

    public LocalDate getEntrega() {
        return entrega;
    }

    public boolean isEnvio() {
        return envio;
    }

    public double getCostoDeEnvio() {
        return costoDeEnvio;
    }

    public double getPrecio() {
        return precio;
    }

    public String getUbicacionDeEntrega() {
        return ubicacionDeEntrega;
    }
    public Pedido aModelo(){
        return pedido;
    }
}
