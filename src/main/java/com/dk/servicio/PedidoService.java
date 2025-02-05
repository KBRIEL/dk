package com.dk.servicio;

import com.dk.modelo.Pedido;
import com.dk.modelo.Producto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PedidoService {

    Pedido crear(Pedido pedido);
    Pedido update(Pedido pedido);
    Pedido getPedidoById(Long id);
    void Delete(Long id);

    List<Pedido> getPedidos();
}
