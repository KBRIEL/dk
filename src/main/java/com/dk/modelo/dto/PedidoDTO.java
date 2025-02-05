package com.dk.modelo.dto;

import com.dk.modelo.Pedido;



public class PedidoDTO {

    private Pedido pedido;
    private Long cliente;
    private String descripcion;

    public PedidoDTO(Pedido pedido) {

        this.pedido = pedido;
    }

    public Long getCliente() {
        return cliente;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
