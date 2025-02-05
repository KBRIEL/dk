package com.dk.servicio;

import com.dk.modelo.Producto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductoService {

     Producto crear(Producto producto);
     Producto update(Producto producto);
     Producto getProductoById(Long id);
     void Delete(Long id);


     List<Producto> getProductos();

     Producto getProductoByNombre(String name);
}
