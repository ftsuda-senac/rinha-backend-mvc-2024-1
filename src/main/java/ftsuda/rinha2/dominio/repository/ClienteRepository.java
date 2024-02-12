package ftsuda.rinha2.dominio.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import ftsuda.rinha2.dominio.Cliente;
import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query("SELECT c FROM Cliente c  WHERE c.id = :clienteId")
	@QueryHints(@QueryHint(name = "jakarta.persistence.lock.timeout", value = "10000"))
	Optional<Cliente> findByIdWithLock(Integer clienteId);

//	@Query(value = """
//			SELECT cli.id, cli.limite, cli.saldo, tr.valor, tr.tipo, tr.descricao, tr.realizada_em
//			FROM cliente cli
//			INNER JOIN transacao tr ON tr.cliente_id = cli.id
//			ORDER BY tr.realizada_em DESC
//			LIMIT 10
//			""", nativeQuery = true)
	@Query("SELECT c FROM Cliente c LEFT JOIN FETCH c.transacoes t WHERE c.id = :clienteId ORDER BY t.realizadaEm DESC")
	Optional<Cliente> findExtratoById(Integer clienteId);

}
