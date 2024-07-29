package com.dk.modelo.dto;

import com.dk.modelo.Usuario;

public class UserDTO {

    private String name;
    private String email;
    private Usuario user;

    public UserDTO(Usuario user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Usuario aModelo(){
        return this.user;
    }


}
