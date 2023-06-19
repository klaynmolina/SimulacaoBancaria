package com.SimulacaoTransacaoBancaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SimulacaoTransacaoBancaria.model.Conta;

public interface ContaRepository extends JpaRepository<Conta, Long> {

}
