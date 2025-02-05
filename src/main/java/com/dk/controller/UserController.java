package com.dk.controller;

import com.dk.modelo.Security.RegisterRequest;
import com.dk.modelo.Usuario;
import com.dk.modelo.dto.UserDTO;
import com.dk.servicio.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/{id}")
    public ResponseEntity<?>  getUsuario(@PathVariable Long id){
        try{
            Usuario user = userService.getUser(id);
            return  ResponseEntity.ok(new UserDTO(user));
        }catch (HttpClientErrorException.BadRequest b){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado el usuario con el id : " + id);
        }

    }

    @GetMapping("/users")
    public List<UserDTO> getUsuarios(){

        return  userService.getAll().stream().map(u-> new UserDTO(u)).collect(Collectors.toList());
    }

    @GetMapping("/{name}")
    public ResponseEntity<?> getUserByName(@PathVariable String name){

        try{
            return ResponseEntity.ok(new UserDTO(userService.getUsuarioByName(name)));
        }catch (HttpClientErrorException.NotFound e){
            e.getMessage();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado el usuario con el nombre : " + name);
        }
    }


}
