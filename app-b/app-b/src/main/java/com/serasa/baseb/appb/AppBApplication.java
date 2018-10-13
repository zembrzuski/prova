package com.serasa.baseb.appb;

import com.google.common.collect.Lists;
import com.serasa.baseb.appb.entity.Asset;
import com.serasa.baseb.appb.entity.Income;
import com.serasa.baseb.appb.entity.ScoreInformation;
import com.serasa.baseb.appb.repository.ScoreInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class AppBApplication implements CommandLineRunner {

	@Autowired
	private ScoreInformationRepository repo;

	public static void main(String[] args) {
		SpringApplication.run(AppBApplication.class, args);
	}

	@Override
	public void run(String... args) {
		// poputating some information on database at startup.
		ScoreInformation score1 = ScoreInformation.builder()
				.age(32)
				.assets(Lists.newArrayList(new Asset("casa", 123.3), new Asset("ap", 15)))
				.income(new Income("salario", 15.5))
				.build();

		ScoreInformation score2 = ScoreInformation.builder()
				.age(55)
				.assets(Lists.newArrayList(new Asset("casa", 55.3)))
				.income(new Income("salario", 56.5))
				.build();

		ScoreInformation score3 = ScoreInformation.builder()
				.age(12)
				.income(new Income("mesada", 10000))
				.build();

		repo.save(score1);
		repo.save(score2);
		repo.save(score3);
	}
}
