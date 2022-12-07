package com.projeto.restapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.restapi.models.Cliente;

public interface RepositorioCliente extends JpaRepository<Cliente, Long> {

}
