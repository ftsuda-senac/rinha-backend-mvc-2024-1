package ftsuda.rinha2;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ResponseStatusException;

import ftsuda.rinha2.dominio.Cliente;
import ftsuda.rinha2.dominio.Transacao;
import ftsuda.rinha2.dominio.TransacaoInfo;
import ftsuda.rinha2.dominio.repository.ClienteRepository;
import ftsuda.rinha2.dominio.repository.TransacaoRepository;
import ftsuda.rinha2.dto.ExtratoResponse;
import ftsuda.rinha2.dto.TransacaoResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@Validated
@RequiredArgsConstructor
public class AppService {

	private final TransacaoRepository transacaoRepository;

	private final ClienteRepository clienteRepository;

	// @Transactional(isolation = Isolation.SERIALIZABLE)
	@Transactional
	public TransacaoResponse doTransacao(Integer clienteId, @Valid TransacaoInfo tInfo) {

		Cliente c = clienteRepository.findByIdWithLock(clienteId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		switch (tInfo.getTipo().toLowerCase()) {
		case "c" -> {
			// Operacao de crédito
			c.setSaldo(c.getSaldo() + tInfo.getValor());
		}
		case "d" -> {
			// Operação de débito
			int limite = c.getLimite();
			int valTemp = c.getSaldo() - tInfo.getValor();

			if (valTemp < -limite) {
				throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
			}
			c.setSaldo(valTemp);
		}
		}
		clienteRepository.save(c);

		Transacao t = new Transacao();
		t.setCliente(c);
		t.setInfo(tInfo);
		t.setRealizadaEm(LocalDateTime.now());
		transacaoRepository.save(t);

		return new TransacaoResponse(c.getLimite(), c.getSaldo());
	}

	@Transactional(readOnly = true)
	public ExtratoResponse findExtrato(Integer clienteId) {
//		Cliente cli = clienteRepository.findExtratoById(clienteId)
//				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
//		return new ExtratoResponse(cli, new ArrayList<>(cli.getTransacoes()));

		Cliente cli = clienteRepository.findById(clienteId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		List<Transacao> transacoes = transacaoRepository.findTop10ByCliente_IdOrderByRealizadaEmDesc(clienteId);
		return new ExtratoResponse(cli, transacoes);
	}

}
