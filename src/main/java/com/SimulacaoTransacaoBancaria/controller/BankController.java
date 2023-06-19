package com.SimulacaoTransacaoBancaria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.SimulacaoTransacaoBancaria.model.Cliente;
import com.SimulacaoTransacaoBancaria.model.Conta;
import com.SimulacaoTransacaoBancaria.service.ClienteService;
import com.SimulacaoTransacaoBancaria.service.ContaService;
import com.SimulacaoTransacaoBancaria.utility.ImportClientesContas;
import com.SimulacaoTransacaoBancaria.utility.Operador;
import com.SimulacaoTransacaoBancaria.utility.Validacao;

@Controller
public class BankController {

	@Autowired
	private ClienteService serviceCliente;

	@Autowired
	private ContaService serviceConta;

	private Operador operador = new Operador();

//	ACESSO PÁGINA INICIAL 
	@GetMapping("/pagina-inicial")
	public ModelAndView paginaInicial() {
		ModelAndView mv = new ModelAndView();
		// PREENCHER BANCO DE DADOS
		List<Cliente> clientes = serviceCliente.readAll();
		List<Conta> contas = serviceConta.readAll();
		if (clientes.isEmpty() && contas.isEmpty()) {
			ImportClientesContas.importar(serviceCliente, serviceConta);
		}
		mv.addObject("cliente", new Cliente());
		mv.setViewName("pagina-inicial/index");
		return mv;
	}

//	ACESSO CADASTRAR CLIENTE
	@GetMapping("/cadastrar-cliente")
	public ModelAndView acessarCadastrarCliente(Cliente cliente) {
		ModelAndView mv = new ModelAndView();
		// PREENCHER BANCO DE DADOS
		List<Cliente> clientes = serviceCliente.readAll();
		List<Conta> contas = serviceConta.readAll();
		if (clientes.isEmpty() && contas.isEmpty()) {
			ImportClientesContas.importar(serviceCliente, serviceConta);
		}
		mv.setViewName("cliente/cadastrar-cliente");
		mv.addObject("cliente", new Cliente());
		return mv;
	}

//	CADASTRAR CLIENTE
	@PostMapping("cadastro-cliente-concluido")
	public ModelAndView cadastrarCliente(Cliente cliente) {
		ModelAndView mv = new ModelAndView();

		Boolean cpfExiste = Validacao.validarCpf(cliente, serviceCliente);
		if (cpfExiste.equals(true)) {
			mv.setViewName("cliente/erros/cpf-duplicado");
		} else {
			Boolean senhaRequisitos = Validacao.validarSenhaRequisitos(cliente);
			if (senhaRequisitos.equals(false)) {
				mv.setViewName("cliente/erros/senha-requisitos");
			} else {
				Conta conta = new Conta(serviceCliente.create(cliente));
				serviceConta.create(conta);
				Cliente clienteCadastro = Validacao.buscarCliente(cliente, serviceCliente);
				clienteCadastro.getContas().add(conta);
				serviceCliente.update(clienteCadastro.getId(), clienteCadastro);
				mv.setViewName("cliente/cadastro-cliente-concluido");
			}
		}
		return mv;
	}

//	ACESSO ABRIR CONTA
	@GetMapping("/abrir-conta")
	public ModelAndView acessarAberturaConta(Cliente cliente) {
		ModelAndView mv = new ModelAndView();
		// PREENCHER BANCO DE DADOS
		List<Cliente> clientes = serviceCliente.readAll();
		List<Conta> contas = serviceConta.readAll();
		if (clientes.isEmpty() && contas.isEmpty()) {
			ImportClientesContas.importar(serviceCliente, serviceConta);
		}
		mv.setViewName("conta/abrir-conta");
		mv.addObject("cliente", new Cliente());
		return mv;
	}

//	ABRIR CONTA
	@PostMapping("abertura-conta-concluida")
	public ModelAndView abrirConta(Cliente cliente) {
		ModelAndView mv = new ModelAndView();
		Boolean cpfCadastrado = Validacao.validarCpf(cliente, serviceCliente);
		if (cpfCadastrado.equals(false)) {
			mv.setViewName("conta/erros/abrir-conta-cpf-nao-cadastrado");
			return mv;
		} else {
			Boolean senhaValida = Validacao.validarSenha(cliente, serviceCliente);
			if (senhaValida.equals(false)) {
				mv.setViewName("conta/erros/abrir-conta-senha-incorreta");
				return mv;
			}
			Cliente clienteCadastrado = Validacao.buscarCliente(cliente, serviceCliente);
			Conta conta = new Conta(clienteCadastrado);
			serviceConta.create(conta);
			clienteCadastrado.getContas().add(conta);
			serviceCliente.update(clienteCadastrado.getId(), clienteCadastrado);
			mv.setViewName("conta/abertura-conta-concluida");
			return mv;
		}
	}

//	ACESSO LOGIN	
	@GetMapping("/login")
	public ModelAndView login(Cliente cliente) {
		ModelAndView mv = new ModelAndView();
		// PREENCHER BANCO DE DADOS
		List<Cliente> clientes = serviceCliente.readAll();
		List<Conta> contas = serviceConta.readAll();
		if (clientes.isEmpty() && contas.isEmpty()) {
			ImportClientesContas.importar(serviceCliente, serviceConta);
		}
		mv.setViewName("login/login");
		mv.addObject("cliente", new Cliente());
		return mv;
	}

// 	AUTENTICAR LOGIN
	@PostMapping("/selecionar-emissor")
	public ModelAndView exibirContas(Cliente cliente) {
		ModelAndView mv = new ModelAndView();

//	 	LOGIN		
		mv.addObject("cliente", cliente);
		Boolean validarCpfDigitado = Validacao.validarCpf(cliente, serviceCliente);
		if (validarCpfDigitado.equals(false)) {
			mv.setViewName("login/erros/cpf-nao-cadastrado");
			return mv;
		}
		Boolean validarSenhaDigitada = Validacao.validarSenha(cliente, serviceCliente);
		if (validarSenhaDigitada.equals(false)) {
			mv.setViewName("login/erros/senha-incorreta");
			return mv;
		}

//	 	ATRIBUIR EMISSOR E CONTAS
		Cliente clienteCadastro = Validacao.buscarCliente(cliente, serviceCliente);
		List<Conta> contasCliente = clienteCadastro.getContas();

//		VINCULAR EMISSOR DA TRANSAÇÃO
		mv.addObject("cliente", clienteCadastro);
		mv.addObject("contasCliente", contasCliente);

//	 	SELECIONAR CONTA EMISSOR
		mv.setViewName("transacao/selecionar-emissor");
		return mv;
	}

// 	SELECIONAR CONTA RECEPTOR
	@GetMapping("/emissorSelecionado/{numeroConta}")
	public ModelAndView selecionarEmissor(@PathVariable("numeroConta") Integer numeroContaEmissor) {
		ModelAndView mv = new ModelAndView();
		Conta emissor = Validacao.buscarConta(numeroContaEmissor, serviceConta);
		operador.setContaEmissor(emissor);
		operador.setContaReceptor(null);
		mv.addObject(operador);
		mv.addObject(emissor);
		mv.setViewName("transacao/selecionar-receptor");
		return mv;
	}

//	VALIDAR CONTA RECEPTOR 
	@PostMapping("/selecionar-receptor")
	public ModelAndView selecionarReceptor(Operador contaReceptor) {
		ModelAndView mv = new ModelAndView();
		mv.addObject(operador);
		Conta emissor = Validacao.buscarConta(operador.getContaEmissor().getNumeroConta(), serviceConta);
		Conta receptor = Validacao.buscarConta(contaReceptor.getContaReceptor().getNumeroConta(), serviceConta);
		Boolean receptorExiste = Validacao.validarConta(receptor, serviceConta);
		if (receptorExiste.equals(false)) {
			mv.setViewName("transacao/erros/conta-inexistente");
			return mv;
		}
		if (emissor.equals(receptor)) {
			mv.setViewName("transacao/erros/contas-iguais");
			return mv;
		}
		operador.setContaEmissor(emissor);
		operador.setContaReceptor(receptor);
		operador.setValorTransacao(null);
		mv.addObject(emissor);
		mv.addObject(receptor);
		mv.setViewName("transacao/selecionar-valor");
		return mv;
	}

// 	SELECIONAR VALOR 	
	@PostMapping("/selecionar-valor")
	public ModelAndView selecionarValor(Operador valorTransacao) {
		ModelAndView mv = new ModelAndView();
		mv.addObject(operador);
		Double valorTransferencia = valorTransacao.getValorTransacao();
		if (valorTransferencia <= 0) {
			mv.setViewName("transacao/erros/valor-invalido");
			return mv;
		}
		operador.setValorTransacao(valorTransferencia);
		mv.setViewName("transacao/metodo-pagamento");
		return mv;
	}

	@PostMapping("/metodo-pagamento-pix")
	public ModelAndView validarTransferenciaPix() {
		ModelAndView mv = new ModelAndView();
		mv.addObject(operador);
		mv.setViewName("transacao/metodo-pagamento");
		Double valorTransferencia = operador.getValorTransacao();
		if (valorTransferencia > 5000) {
			mv.setViewName("transacao/erros/limite-pix");
			return mv;
		} else {
			operador.getContaEmissor().transferir(operador.getContaReceptor(), valorTransferencia);
			mv.setViewName("transacao/confirmar-transferencia");
			return mv;
		}
	}

	@PostMapping("/metodo-pagamento-ted")
	public ModelAndView validarTransferenciaTed() {
		ModelAndView mv = new ModelAndView();
		mv.addObject(operador);
		mv.setViewName("transacao/metodo-pagamento");
		Double valorTransferencia = operador.getValorTransacao();
		if (valorTransferencia < 5000) {
			mv.setViewName("transacao/erros/limite-min-ted");
			return mv;
		}
		if (valorTransferencia > 10000) {
			mv.setViewName("transacao/erros/limite-max-ted");
			return mv;
		} else {
			operador.getContaEmissor().transferir(operador.getContaReceptor(), valorTransferencia);
			mv.setViewName("transacao/confirmar-transferencia");
			return mv;
		}
	}

	@PostMapping("/metodo-pagamento-doc")
	public ModelAndView validarTransferenciaDoc() {
		ModelAndView mv = new ModelAndView();
		mv.addObject(operador);
		mv.setViewName("transacao/metodo-pagamento");
		Double valorTransferencia = operador.getValorTransacao();
		if (valorTransferencia < 10000) {
			mv.setViewName("transacao/erros/limite-doc");
			return mv;
		} else {
			operador.getContaEmissor().transferir(operador.getContaReceptor(), valorTransferencia);
			mv.setViewName("transacao/confirmar-transferencia");
			return mv;
		}
	}

	@PostMapping("/transferencia-concluida")
	public ModelAndView validarTransferencia() {
		ModelAndView mv = new ModelAndView();
		mv.addObject(operador);
		Conta emissor = operador.getContaEmissor();
		Conta receptor = operador.getContaReceptor();
		serviceConta.update(emissor.getId(), emissor);
		serviceConta.update(receptor.getId(), receptor);
		mv.setViewName("transacao/transferencia-concluida");
		return mv;
	}

}
