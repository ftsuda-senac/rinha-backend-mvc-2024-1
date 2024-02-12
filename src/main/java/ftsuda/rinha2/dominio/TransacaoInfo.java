package ftsuda.rinha2.dominio;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class TransacaoInfo {

	@NotNull
	private Integer valor;

	@NotBlank
	@Pattern(regexp = "[cd]")
	private String tipo;

	@NotBlank
	@Size(max = 10)
	private String descricao;

}
