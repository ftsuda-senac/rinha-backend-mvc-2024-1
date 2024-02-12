package ftsuda.rinha2.dominio;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Cliente {

	@Id
	private Integer id;
	
	@Version
	private Integer version;

	private int limite;

	private int saldo;

	@OneToMany(mappedBy = "cliente")
	@OrderBy("realizadaEm DESC")
	private Set<Transacao> transacoes;

	public Cliente() {

	}

	public Cliente(Integer id, int limite, int saldo) {
		this.id = id;
		this.limite = limite;
		this.saldo = saldo;
	}

}
