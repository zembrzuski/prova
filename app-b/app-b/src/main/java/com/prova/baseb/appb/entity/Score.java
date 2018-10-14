package com.prova.baseb.appb.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private Integer age;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Asset> assets;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Address address;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Income income;

    public String asCsv() {
        return age + "," + address.asCsv() + "," + income.asCsv() + "," + assetsAsCsv(assets);
    }

    private String assetsAsCsv(List<Asset> assets) {
        return assets.stream()
                .map(Asset::asCsv)
                .collect(Collectors.joining(","));
    }

}
