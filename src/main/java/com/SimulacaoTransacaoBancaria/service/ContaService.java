package com.SimulacaoTransacaoBancaria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SimulacaoTransacaoBancaria.model.Conta;
import com.SimulacaoTransacaoBancaria.repository.ContaRepository;

@Service
public class ContaService {

	@Autowired
	private ContaRepository repository;

	public Conta create(Conta conta) {
		return repository.save(conta);
	}

	public Conta read(Long id) {
		return repository.findById(id).get();
	}

	public List<Conta> readAll() {
		return repository.findAll();
	}

	public Conta update(Long id, Conta atualizar) {
		Conta atualizado = repository.findById(id).get();
		atualizado.setCliente(atualizar.getCliente());
		atualizado.setAgencia(atualizar.getAgencia());
		atualizado.setNumeroConta(atualizar.getNumeroConta());
		atualizado.setSaldo(atualizar.getSaldo());
		return repository.save(atualizado);
	}

	public void delete(Long id) {
		repository.deleteById(id);
	}

}
