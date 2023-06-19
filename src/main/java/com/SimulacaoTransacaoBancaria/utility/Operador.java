package com.SimulacaoTransacaoBancaria.utility;

import com.SimulacaoTransacaoBancaria.model.Conta;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Operador {

	private Conta contaEmissor;
	private Conta contaReceptor;
	private Double valorTransacao;

	public String exibirValorTransacao() {
		return String.format("%.2f", valorTransacao);
	}

	@Override
	public String toString() {
		return String.format("Conta Emissor: %s || Conta Receptor: %s || Valor Transação: %f", contaEmissor,
				contaReceptor, valorTransacao);
	}

}
