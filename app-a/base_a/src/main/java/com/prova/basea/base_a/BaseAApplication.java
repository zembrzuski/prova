package com.prova.basea.base_a;

import com.google.common.collect.Sets;
import com.prova.basea.base_a.domain.Debt;
import com.prova.basea.base_a.domain.SerasaUser;
import com.prova.basea.base_a.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BaseAApplication implements CommandLineRunner {

	@Autowired
	private UserRepository repo;

	public static void main(String[] args) {
		SpringApplication.run(BaseAApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		// populating some information

		repo.deleteAll();

		SerasaUser user1 = SerasaUser.builder()
				.cpf("12345678901")
				.name("Walter White")
				.address("Avenida White")
				.debts(Sets.newHashSet(
						new Debt("house", "12343433.23", "true"),
						new Debt("car", "1234.23", "false")
				))
				.build();

		SerasaUser user2 = SerasaUser.builder()
				.cpf("50000012300")
				.name("Saul Goodman")
				.address("Atrás da pedicure")
				.debts(Sets.newHashSet(
						new Debt("whisky", "1.23", "true"),
						new Debt("chocolate", "23.23", "true"),
						new Debt("telefone", "1.2", "true"),
						new Debt("faculdade", "3.2", "true"),
						new Debt("pao", "1.23", "true"),
						new Debt("queijo", "2.5", "true"),
						new Debt("presunto", "3.7", "true")
				))
				.build();

		SerasaUser user3 = SerasaUser.builder()
				.cpf("55555555555")
				.name("Gus Fring")
				.address("Los Pollos Hermanos, 123")
				.build();

		SerasaUser user4 = SerasaUser.builder()
				.cpf("40050060022")
				.name("Skyler White")
				.address("Avenida White")
				.build();

		SerasaUser user5 = SerasaUser.builder()
				.cpf("40050060022")
				.name("Jesse Pinkman")
				.address("Com os pais")
				.debts(Sets.newHashSet(
						new Debt("bala", "4943.11", "true"),
						new Debt("doce", "43.11", "true"),
						new Debt("crystal", "3.11", "true"),
						new Debt("cachorro quente", "2.50", "true")
				))
				.build();

		repo.save(user1);
		repo.save(user2);
		repo.save(user3);
		repo.save(user4);
		repo.save(user5);

	}
}
