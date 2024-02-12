package ftsuda.rinha2;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonMappingException;

import ftsuda.rinha2.dominio.TransacaoInfo;
import ftsuda.rinha2.dto.ExtratoResponse;
import ftsuda.rinha2.dto.TransacaoResponse;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AppController {

	private final AppService service;

	@PostMapping("/clientes/{clienteId}/transacoes")
	public ResponseEntity<?> doTransacao(@PathVariable Integer clienteId, @RequestBody TransacaoInfo tInfo) {
		TransacaoResponse resp = service.doTransacao(clienteId, tInfo);
		return ResponseEntity.ok(resp);
	}

	@GetMapping("/clientes/{clienteId}/extrato")
	public ResponseEntity<?> findExtrato(@PathVariable Integer clienteId) {
		ExtratoResponse resp = service.findExtrato(clienteId);
		return ResponseEntity.ok(resp);
	}

	@ExceptionHandler(JsonMappingException.class)
	public ResponseEntity<?> handleJacksonError(JsonMappingException ex) {
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<?> handleValidationError(ConstraintViolationException ex) {
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
	}

}
