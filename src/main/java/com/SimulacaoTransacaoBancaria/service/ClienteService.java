package com.SimulacaoTransacaoBancaria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SimulacaoTransacaoBancaria.model.Cliente;
import com.SimulacaoTransacaoBancaria.model.Conta;
import com.SimulacaoTransacaoBancaria.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;

	public Cliente create(Cliente cliente) {
		return repository.save(cliente);
	}

	public Cliente read(Long id) {
		return repository.findById(id).get();
	}

	public List<Cliente> readAll() {
		return repository.findAll();
	}

	public List<Conta> readAllAccounts(Long id) {
		return read(id).getContas();
	}

	public Cliente update(Long id, Cliente atualizar) {
		Cliente atualizado = repository.findById(id).get();
		atualizado.setNome(atualizar.getNome());
		atualizado.setCpf(atualizar.getCpf());
		atualizado.setSenha(atualizar.getSenha());
		return repository.save(atualizado);
	}

	public void delete(Long id) {
		repository.deleteById(id);
	}

}
