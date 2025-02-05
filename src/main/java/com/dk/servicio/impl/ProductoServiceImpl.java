package com.dk.servicio.impl;

import com.dk.dao.ProductoDao;
import com.dk.modelo.Producto;
import com.dk.servicio.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoDao dao;

    @Override
    public Producto crear(Producto producto) {
        return dao.save(producto);
    }

    @Override
    public Producto update(Producto producto) {
        return dao.save(producto);
    }

    @Override
    public Producto getProductoById(Long id) {
        return dao.findById(id).get();
    }

    @Override
    public void Delete(Long id) {
        dao.delete(this.getProductoById(id));

    }

    @Override
    public List<Producto> getProductos() {
        return dao.findAll();
    }

    @Override
    public Producto getProductoByNombre(String name) {
        return dao.getProductoByName(name);
    }
}
