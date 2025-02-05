package com.dk.servicio.impl;

import com.dk.dao.PedidoDao;
import com.dk.dao.ProductoDao;
import com.dk.modelo.Pedido;
import com.dk.modelo.Producto;
import com.dk.servicio.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    private PedidoDao dao;

    @Override
    public Pedido crear(Pedido pedido) {
        return dao.save(pedido);
    }

    @Override
    public Pedido update(Pedido pedido) {
        return dao.save(pedido);
    }

    @Override
    public Pedido getPedidoById(Long id) {
        return dao.findById(id).get();
    }

    @Override
    public void Delete(Long id) {
        dao.delete(this.getPedidoById(id));

    }

    @Override
    public List<Pedido> getPedidos() {
        return dao.findAll();
    }
}
