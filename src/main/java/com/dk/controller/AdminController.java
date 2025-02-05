package com.dk.controller;


import com.dk.modelo.Usuario;
import com.dk.modelo.dto.UserDTO;
import com.dk.servicio.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;
    @GetMapping("/admin")
    public String soloAdmin(){
        return "soy administrador";
    }


    @PostMapping("/User/{id}")
    public ResponseEntity<?> getUsuario(@PathVariable Long id){
        try{
            Usuario user = userService.getUser(id);
            return  ResponseEntity.ok(new UserDTO(user));
        }catch (HttpClientErrorException.BadRequest b){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado el usuario con el id : " + id);
        }

    }

    @GetMapping("/user/users")
    public List<UserDTO> getUsuarios(){
        List<UserDTO> users = userService.getAll().stream().map(u-> new UserDTO(u)).collect(Collectors.toList());
        return  users;
    }

    @GetMapping("/user/{name}")
    public ResponseEntity<?> getUserByName(@PathVariable String name){

        try{
            return ResponseEntity.ok(new UserDTO(userService.getUsuarioByName(name)));
        }catch (HttpClientErrorException.NotFound e){
            e.getMessage();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado el usuario con el nombre : " + name);
        }
    }

}
