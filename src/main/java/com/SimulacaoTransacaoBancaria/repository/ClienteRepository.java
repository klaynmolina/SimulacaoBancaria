package com.SimulacaoTransacaoBancaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SimulacaoTransacaoBancaria.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
