package com.SimulacaoTransacaoBancaria.utility;

import com.SimulacaoTransacaoBancaria.model.Cliente;
import com.SimulacaoTransacaoBancaria.model.Conta;
import com.SimulacaoTransacaoBancaria.service.ClienteService;
import com.SimulacaoTransacaoBancaria.service.ContaService;

public class ImportClientesContas {

	public static void importar(ClienteService serviceCliente, ContaService serviceConta) {
		Cliente cliente1 = new Cliente("Caio Vicente Sebastião Ribeiro", "17664918847", "Caio@2023");
		serviceConta.create(new Conta(serviceCliente.create(cliente1)));

		Cliente cliente2 = new Cliente("Carolina Caroline da Silva Pinto", "27803188206", "Carolina#2023");
		serviceConta.create(new Conta(serviceCliente.create(cliente2)));

		Cliente cliente3 = new Cliente("Filipe Daniel Pedro Henrique Nunes", "34065272700", "Filipe!2023");
		serviceConta.create(new Conta(serviceCliente.create(cliente3)));

		Cliente cliente4 = new Cliente("Igor Mateus Tiago Rezende", "41237811996", "Igor&2023");
		serviceConta.create(new Conta(serviceCliente.create(cliente4)));

		Cliente cliente5 = new Cliente("Tomás Márcio Theo Gomes", "53417019834", "Tomas*2023");
		serviceConta.create(new Conta(serviceCliente.create(cliente5)));

		Cliente cliente6 = new Cliente("Roberto Yago Ferreira", "61065693303", "Roberto-2023");
		serviceConta.create(new Conta(serviceCliente.create(cliente6)));

		Cliente cliente7 = new Cliente("Erick Mário Giovanni da Cruz", "73470543763", "Erick%2023");
		serviceConta.create(new Conta(serviceCliente.create(cliente7)));

		Cliente cliente8 = new Cliente("Isis Olivia Carvalho", "83995533590", "Isis.2023");
		serviceConta.create(new Conta(serviceCliente.create(cliente8)));

		Cliente cliente9 = new Cliente("Lorena Mariah Nogueira", "90152531742", "Lorena?2023");
		serviceConta.create(new Conta(serviceCliente.create(cliente9)));

		Cliente cliente10 = new Cliente("Pietra Tatiane Mariana Figueiredo", "03552510672", "Pietra+2023");
		serviceConta.create(new Conta(serviceCliente.create(cliente10)));
	}
}
