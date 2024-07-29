package com.dk.modelo;

import jakarta.persistence.*;


@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Column(name = "email", unique = true)
    private String email;
    @Enumerated(EnumType.STRING)
    private Role role;
    private String password;

    public Usuario(String name, String email,Role rol, String password) {

        this.name = name;
        this.email = email;
        this.role = rol;
        this.password =password;
    }
    // Constructor por defecto
    public Usuario() {
    }
    //---------Setters and Getters---------------

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRol() { return role;  }

    public void setRole(Role role) { this.role = role; }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
