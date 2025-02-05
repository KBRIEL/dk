package com.dk.servicio.impl;


import com.dk.dao.*;
import com.dk.modelo.Usuario;
import com.dk.servicio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) {
        return null;
    }

    @Override
    public Usuario register(Usuario user){

        userDao.save(user);

        return user;
    }
    @Override
    public Usuario crear(Usuario user){
        return userDao.save(user);
    };

    @Override
    public Usuario getUser(Long id) {
        Usuario user=userDao.findById(id).get();
        return user;
    }

    @Override
    public List<Usuario> getAll() {
        return userDao.findAll();
    }

    @Override
    public Usuario getUserByEmail(String email) {
        return userDao.getUsuarioByEmail(email);
    }

    @Override
    public Usuario getUsuarioByName(String name) {
        return userDao.getUsuarioByName(name);
    }

    @Override
    public int cantEmailsExist(String email){
      return userDao.cantEmails(email) ;
    };
}

