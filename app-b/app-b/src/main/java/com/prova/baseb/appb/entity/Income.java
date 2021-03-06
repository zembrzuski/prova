package com.prova.baseb.appb.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Income {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String description;
    private Double value;

    public Income() {
    }

    public Income(String description, double value) {
        this.description = description;
        this.value = value;
    }

    public String asCsv() {
        return description + "," + value.toString();
    }
}
