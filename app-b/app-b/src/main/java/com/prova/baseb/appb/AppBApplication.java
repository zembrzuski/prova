package com.prova.baseb.appb;

import com.google.common.collect.Lists;
import com.prova.baseb.appb.entity.Address;
import com.prova.baseb.appb.entity.Asset;
import com.prova.baseb.appb.entity.Income;
import com.prova.baseb.appb.entity.Score;
import com.prova.baseb.appb.repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class AppBApplication implements CommandLineRunner {

	@Autowired
	private ScoreRepository repo;

	public static void main(String[] args) {
		SpringApplication.run(AppBApplication.class, args);
	}

	@Override
	public void run(String... args) {
		// populating some information on database at startup.

		repo.deleteAll();

		Score score1 = Score.builder()
				.age(32)
				.assets(Lists.newArrayList(
						new Asset("casa", 1224223.3),
						new Asset("apartamento", 1534.34)))
				.income(new Income("salario", 1050.5))
				.address(new Address("bento", "poa", "rs", "br", "123"))
				.build();

		Score score2 = Score.builder()
				.age(55)
				.assets(Lists.newArrayList(new Asset("casa", 55.3)))
				.income(new Income("salario", 56.5))
				.address(new Address("bento", "poa", "rs", "br", "123"))
				.build();

		Score score3 = Score.builder()
				.age(12)
				.income(new Income("mesada", 10000))
				.address(new Address("bento", "sp", "rs", "br", "124"))
				.build();

		Score score4 = Score.builder()
				.age(43)
				.income(new Income("dividendos", 5524.34))
				.address(new Address("wall street", "sp", "rs", "br", "124"))
				.build();

		Score score5 = Score.builder()
				.age(88)
				.income(new Income("dividendos", 55343424.34))
				.address(new Address("wall street", "sp", "rs", "br", "124"))
				.build();

		repo.save(score1);
		repo.save(score2);
		repo.save(score3);
		repo.save(score4);
		repo.save(score5);
	}
}
