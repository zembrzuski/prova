package com.serasa.baseb.appb.entity;

import javax.persistence.*;
import java.util.List;

public class CreditScoreInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private Integer age;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Asset> assets;

    @OneToOne
    private Address address;

    @OneToOne
    private Income income;

}
