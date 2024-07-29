package com.dk.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductoController {

    @GetMapping("/products")
    public String getProductos(){
        return "Mis productos";
    }
}
