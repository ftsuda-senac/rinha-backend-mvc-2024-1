package ftsuda.rinha2;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import ftsuda.rinha2.dominio.Cliente;
import ftsuda.rinha2.dominio.repository.ClienteRepository;
import ftsuda.rinha2.dominio.repository.TransacaoRepository;

@SpringBootApplication
public class Rinha2MvcApplication implements CommandLineRunner {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private TransacaoRepository transacaoRepository;

	@Value("${spring.profiles.active:}")
	private String activeProfile;

	@Value("${spring.profiles.include:}")
	private String includedProfile;

	public static void main(String[] args) {
		SpringApplication.run(Rinha2MvcApplication.class, args);
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {
		if ("principal".equals(includedProfile) || "dev".equals(activeProfile)) {

			transacaoRepository.deleteAll();
			clienteRepository.flush();

			clienteRepository.deleteAll();
			clienteRepository.flush();

		// @formatter:off
		clienteRepository.saveAll(List.of(
			new Cliente(1, 1000_00, 0),
			new Cliente(2, 800_00, 0),
			new Cliente(3, 10000_00, 0),
			new Cliente(4, 100000_00, 0),
			new Cliente(5, 5000_00, 0))
		);
		// @formatter:on
		}
	}

}
