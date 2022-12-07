package com.projeto.restapi.database;

import com.projeto.restapi.entidades.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioCliente extends JpaRepository<Cliente, Long> {
    
}
