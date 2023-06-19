package com.SimulacaoTransacaoBancaria.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "Tabela_Contas")
@Entity
public class Conta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

	private Integer agencia;

	@Column(unique = true)
	private Integer numeroConta;

	private Double saldo;

	public Conta(Cliente cliente) {
		this.cliente = cliente;
		this.agencia = (int) (Math.random() * (4321 - 1234) + 1234);
		this.numeroConta = (int) (Math.random() * (654321 - 123456) + 123456);
		this.saldo = 0.0;
	}

	public void transferir(Conta destinatario, double valor) {
		this.saldo = this.saldo - valor;
		destinatario.saldo = destinatario.saldo + valor;
	}

	public String exibirContaSaldo() {
		return String.format("Conta %d • Saldo R$ %.2f", numeroConta, saldo);
	}

	public String exibirSaldo() {
		return String.format("R$ %.2f", saldo);
	}

	public String selectConta() {
		return String.format("Conta: %s • Saldo R$ %.2f", numeroConta, saldo);
	}

	@Override
	public int hashCode() {
		return Objects.hash(numeroConta);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Conta other = (Conta) obj;
		return Objects.equals(numeroConta, other.numeroConta);
	}

	@Override
	public String toString() {
		return String.format("Id: %s || Agência: %s || Conta: %s || Saldo: R$%.2f", id, agencia, numeroConta, saldo);
	}

}
