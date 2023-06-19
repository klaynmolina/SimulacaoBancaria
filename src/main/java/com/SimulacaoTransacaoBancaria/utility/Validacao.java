package com.SimulacaoTransacaoBancaria.utility;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.SimulacaoTransacaoBancaria.model.Cliente;
import com.SimulacaoTransacaoBancaria.model.Conta;
import com.SimulacaoTransacaoBancaria.service.ClienteService;
import com.SimulacaoTransacaoBancaria.service.ContaService;

public class Validacao {

	public static Boolean validarSenhaRequisitos(Cliente cliente) {
		String regex = "^(?=.*[a-z])(?=." + "*[A-Z])(?=.*\\d)" + "(?=.*[-+_!@#$%^&*., ?]).+$";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(cliente.getSenha());
		if (m.matches()) {
			return true;
		} else {
			return false;
		}
	}

	public static Boolean validarCpf(Cliente cliente, ClienteService serviceCliente) {
		List<Cliente> bancoDados = serviceCliente.readAll();
		String cpfDigitado = cliente.getCpf();
		for (int i = 0; i < bancoDados.size(); i++) {
			String cpfCadastrado = bancoDados.get(i).getCpf();
			if (cpfDigitado.equals(cpfCadastrado)) {
				return true;
			}
		}
		return false;
	}

	public static Boolean validarSenha(Cliente cliente, ClienteService serviceCliente) {
		List<Cliente> bancoDados = serviceCliente.readAll();
		Long id = 0L;
		String cpfDigitado = cliente.getCpf();
		for (Cliente clienteCadastrado : bancoDados) {
			if (cpfDigitado.equals(clienteCadastrado.getCpf())) {
				id = clienteCadastrado.getId();
			}
		}
		String senhaDigitada = cliente.getSenha();
		String senhaCadastrada = serviceCliente.read(id).getSenha();
		if (senhaDigitada.equals(senhaCadastrada)) {
			return true;
		} else {
			return false;
		}
	}

	public static Cliente buscarCliente(Cliente cliente, ClienteService serviceCliente) {
		List<Cliente> bancoDados = serviceCliente.readAll();
		Long id = 0L;
		for (int i = 0; i < bancoDados.size(); i++) {
			String cpfDigitado = cliente.getCpf();
			String cpfCadastrado = bancoDados.get(i).getCpf();
			if (cpfDigitado.equals(cpfCadastrado)) {
				id = bancoDados.get(i).getId();
			}
		}
		return serviceCliente.read(id);
	}

	public static Conta buscarConta(Integer numeroConta, ContaService serviceConta) {
		List<Conta> listaContas = serviceConta.readAll();
		Conta conta = null;
		for (int i = 0; i < listaContas.size(); i++) {
			Integer numeroContaCadastrada = listaContas.get(i).getNumeroConta();
			Boolean testeNumeroConta = numeroConta.equals(numeroContaCadastrada);
			if (testeNumeroConta.equals(true)) {
				conta = listaContas.get(i);
			}
		}
		return conta;
	}

	public static Boolean validarConta(Conta contaReceptor, ContaService serviceConta) {
		List<Conta> contasCadastradas = serviceConta.readAll();
		if (!contasCadastradas.contains(contaReceptor)) {
			return false;
		}
		return true;
	}
	
}
