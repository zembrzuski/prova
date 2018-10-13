package com.serasa.baseb.appb.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Data
public class Asset { // bens materiais. nao sei se o nome em ingles está certo.

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String description;
    private BigDecimal value;

    public Asset() {
    }

    public Asset(String description, double value) {
        this.description = description;
        this.value = new BigDecimal(value);
    }
}
