package com.dk.modeloTest;

import com.dk.dao.UserDao;
import com.dk.modelo.Role;
import com.dk.modelo.Usuario;
import com.dk.servicio.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class UsuarioTest {

    @Autowired
    private UserDao userDao ;
    @Autowired
    private UserService userService;
    private Usuario user;
private List<Usuario> lista = new ArrayList<Usuario>();

    @BeforeEach
    private void prepare(){
        lista = userService.getAll();
        user = new Usuario("matu","matu@email.com", Role.USER,"matu");

    }
    @Test
    public void crearUsuario(){
        // assertTrue(lista.isEmpty());
       // assertTrue(userDao.findById(1L).isPresent());
        assertEquals(4, userService.crear(user).getId());
       // Usuario usuarioTest = userService.crear(user);

       // assertEquals(usuarioTest.getEmail(),user.getEmail());
    }

}
