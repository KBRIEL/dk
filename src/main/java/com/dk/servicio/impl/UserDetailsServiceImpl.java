package com.dk.servicio.impl;

import com.dk.dao.UserDao;
import com.dk.modelo.Security.UserDetailsImpl;
import com.dk.modelo.Usuario;
import com.dk.servicio.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import static java.rmi.server.LogStream.log;

@Component
public class UserDetailsServiceImpl implements UserDetailsService{

    private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
    @Autowired
    private UserService service;
    @Autowired
    private UserDao repository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        try{
            Usuario user =repository.getUsuarioByEmail(email);

            return new UserDetailsImpl(user);
        }catch (UsernameNotFoundException e){
            log("el usuario con "+ email+" no existe");
            e.getMessage();
            return null;
        }


    }
}
