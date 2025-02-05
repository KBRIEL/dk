package com.dk.servicio;

import com.dk.modelo.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    Usuario crear(Usuario user);
    UserDetails loadUserByUsername(String username);

    Usuario register(Usuario user);

    Usuario getUser(Long id);

    List<Usuario> getAll();

    Usuario getUserByEmail(String email);

    Usuario getUsuarioByName(String name);

    int cantEmailsExist(String email);
}
