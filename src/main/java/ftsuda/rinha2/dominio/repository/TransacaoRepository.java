package ftsuda.rinha2.dominio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ftsuda.rinha2.dominio.Transacao;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {

	List<Transacao> findTop10ByCliente_IdOrderByRealizadaEmDesc(Integer clientId);

}
