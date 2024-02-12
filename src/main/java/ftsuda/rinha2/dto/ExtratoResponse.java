package ftsuda.rinha2.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import ftsuda.rinha2.dominio.Cliente;
import ftsuda.rinha2.dominio.Transacao;
import lombok.Getter;

@Getter
public class ExtratoResponse {

	private final Saldo saldo;

	@JsonProperty("ultimas_transacoes")
	private final List<Transacao> transacoes;

	public ExtratoResponse(Cliente cli, List<Transacao> transacoes) {
		this.saldo = new Saldo(cli.getSaldo(), LocalDateTime.now(), cli.getLimite());
		this.transacoes = transacoes.size() > 10 ? transacoes.subList(0, 10) : transacoes;
	}

	private record Saldo(int total, @JsonProperty("data_extrato") LocalDateTime dataExtrato, int limite) {

	}

}
