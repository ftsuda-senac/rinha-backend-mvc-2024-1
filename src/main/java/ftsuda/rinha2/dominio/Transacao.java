package ftsuda.rinha2.dominio;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(indexes = @Index(name = "idx_cliente_realizada", columnList = "cliente_id, realizada_em"))
@Getter
@Setter
public class Transacao {

	@Id
	@SequenceGenerator(name = "seq_transacao", allocationSize = 1000, initialValue = 1)
	@GeneratedValue(generator = "seq_transacao")
	private Long id;

	@Embedded
	@JsonUnwrapped
	private TransacaoInfo info;

	@Column(name = "realizada_em")
	@JsonProperty("realizada_em")
	private LocalDateTime realizadaEm;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cliente_id", foreignKey = @ForeignKey(name = "fk_transacao__cliente_id"))
	private Cliente cliente;

}
