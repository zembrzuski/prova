package com.serasa.baseb.appb.controller;

import com.serasa.baseb.appb.entity.Score;
import com.serasa.baseb.appb.repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/score")
public class ScoreController {

    @Autowired
    private ScoreRepository repository;

    @Autowired
    private Serializer serializer;

    @GetMapping
    public void getAll(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size,
            @RequestParam(name = "type", required = false, defaultValue = "json") String serializationType,
            HttpServletResponse response) throws IOException {

        Page<Score> all = repository.findAll(PageRequest.of(page, size));
        serializer.writeResponse(serializationType, all.getContent(), response);
    }

    @GetMapping("/income")
    public void getByIncome(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size,
            @RequestParam float income,
            @RequestParam(name = "type", required = false, defaultValue = "json") String serializationType,
            HttpServletResponse response) throws IOException {

        List<Score> result = repository.findByIncome_value(income);

        serializer.writeResponse(serializationType, result, response);
    }

    @GetMapping("/zipcode")
    public void getByZipCode(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size,
            @RequestParam String zipcode,
            @RequestParam(name = "type", required = false, defaultValue = "json") String serializationType,
            HttpServletResponse response) throws IOException {

        PageRequest pageRequest = PageRequest.of(page, size);
        List<Score> result = repository.findByAddress_zipcode(zipcode, pageRequest);
        serializer.writeResponse(serializationType, result, response);
    }


}
