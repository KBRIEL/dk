package com.dk.controller;

import com.dk.modelo.Producto;
import com.dk.modelo.dto.ProductoDTO;
import com.dk.servicio.ProductoService;
import com.dk.servicio.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@RestController
@CrossOrigin
@Validated
@RequestMapping("/product")
public class ProductoController {

    @Autowired
    private ProductoService productoService;



    @GetMapping("/products")
    public  ResponseEntity<?> getProductos(){
        List<Producto> lista = productoService.getProductos();

        return ResponseEntity.ok(lista.stream().map(p -> new ProductoDTO(p)));
    }

    @GetMapping("/{name}")
    public ResponseEntity<?> getProductoByName(@PathVariable String name){

        try{
            return ResponseEntity.ok(new ProductoDTO(productoService.getProductoByNombre(name)));
        }catch (HttpClientErrorException.NotFound e){
            e.getMessage();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado el producto con el nombre : " + name);
        }
    }
}
