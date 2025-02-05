package com.dk.dao;

import com.dk.modelo.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoDao extends JpaRepository<Producto,Long> {
    @Query("""
            SELECT p
            FROM Producto p
            WHERE p.nombre = :name

""")
    public Producto getProductoByName(String name);
}
