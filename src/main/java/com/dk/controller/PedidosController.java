package com.dk.controller;

import com.dk.modelo.Pedido;
import com.dk.modelo.dto.CrearPedidoDTO;
import com.dk.modelo.dto.PedidoDTO;
import com.dk.servicio.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedido")
public class PedidosController {


    @Autowired private PedidoService pedidoService;


    @GetMapping("/pedidos")
    public ResponseEntity<?> getPedidos(){
        List<Pedido> lista = pedidoService.getPedidos();

        return ResponseEntity.ok(lista.stream().map(p -> new PedidoDTO(p)));
    }

    @PostMapping("/carga")
    public ResponseEntity<?> crearPedido(@RequestBody CrearPedidoDTO pedido){
        System.out.println("=====================================");
        System.out.println(pedido.getDescripcion());
        System.out.println(pedido);
        Pedido miPedido = pedidoService.crear(pedido.aModelo());
        System.out.println(miPedido.getDescripcion());
        System.out.println(miPedido.getDescripcion());
        return ResponseEntity.ok(new PedidoDTO(miPedido));
    }
}
