package com.serasa.baseb.appb.controller;

import com.serasa.baseb.appb.entity.Score;
import com.serasa.baseb.appb.repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Function;

@RestController
@RequestMapping("/score")
public class ScoreController {

    @Autowired
    private ScoreRepository repository;

    @GetMapping
    public ResponseEntity<List<Score>> getAll(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size) {

        Page<Score> all = repository.findAll(PageRequest.of(page, size, Sort.Direction.DESC, "updatedAt"));
        return ResponseEntity.ok(all.getContent());
    }

    @GetMapping("/income")
    public ResponseEntity<List<Score>> getByIncome(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size,
            @RequestParam float income) {

        List<Score> result = repository.findByIncome_value(new BigDecimal(income));

        return ResponseEntity.ok(result);
    }

}
