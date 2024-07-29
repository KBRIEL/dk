package com.dk.dao;

import com.dk.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<Usuario,Long> {

    public Usuario getUsuarioByEmail(String email);

    @Query("""
            SELECT COUNT(*) AS exists 
            FROM Usuario 
            WHERE email = :email
            """)
    public int cantEmails(@Param("email") String email);

    public Usuario getUsuarioByName(String name);

}
