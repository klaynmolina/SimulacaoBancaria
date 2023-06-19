package com.SimulacaoTransacaoBancaria.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErrorsController {

	// IMPLEMENTAÇÃO TEMPORÁRIA DE TRATAMENTO DE ERROS

	@GetMapping("/")
	public ModelAndView erroInicial() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("erros-geral/erro-pagina-inicial");
		return mv;
	}

	@GetMapping("/cadastro-cliente-concluido")
	public ModelAndView erroCadastro() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("erros-geral/autenticacao-necessaria");
		return mv;
	}

	@GetMapping("/abertura-conta-concluida")
	public ModelAndView erroConta() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("erros-geral/autenticacao-necessaria");
		return mv;
	}

	@GetMapping("/selecionar-emissor")
	public ModelAndView erroEmissor() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("erros-geral/autenticacao-necessaria");
		return mv;
	}

	@GetMapping("/selecionar-receptor")
	public ModelAndView erroReceptor() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("erros-geral/autenticacao-necessaria");
		return mv;
	}

	@GetMapping("/selecionar-valor")
	public ModelAndView erroValor() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("erros-geral/autenticacao-necessaria");
		return mv;
	}

	@GetMapping("/metodo-pagamento-pix")
	public ModelAndView erroPix() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("erros-geral/autenticacao-necessaria");
		return mv;
	}

	@GetMapping("/metodo-pagamento-ted")
	public ModelAndView erroTed() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("erros-geral/autenticacao-necessaria");
		return mv;
	}

	@GetMapping("/metodo-pagamento-doc")
	public ModelAndView erroDoc() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("erros-geral/autenticacao-necessaria");
		return mv;
	}

	@GetMapping("/transferencia-concluida")
	public ModelAndView erroConcluir() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("erros-geral/autenticacao-necessaria");
		return mv;
	}

}
