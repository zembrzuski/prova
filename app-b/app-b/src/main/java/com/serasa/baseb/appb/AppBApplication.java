package com.serasa.baseb.appb;

import com.google.common.collect.Lists;
import com.serasa.baseb.appb.entity.Address;
import com.serasa.baseb.appb.entity.Asset;
import com.serasa.baseb.appb.entity.Income;
import com.serasa.baseb.appb.entity.Score;
import com.serasa.baseb.appb.repository.ScoreRepository;
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
				.assets(Lists.newArrayList(new Asset("casa", 123.3), new Asset("ap", 15)))
				.income(new Income("salario", 15.5))
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

		repo.save(score1);
		repo.save(score2);
		repo.save(score3);
	}
}
