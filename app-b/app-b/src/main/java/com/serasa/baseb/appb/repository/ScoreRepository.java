package com.serasa.baseb.appb.repository;

import com.serasa.baseb.appb.entity.Score;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface ScoreRepository extends JpaRepository<Score, Long> {

    List<Score> findByIncome_value(BigDecimal value);

}
