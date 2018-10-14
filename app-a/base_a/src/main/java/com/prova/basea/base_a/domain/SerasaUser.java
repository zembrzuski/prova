package com.prova.basea.base_a.domain;


import com.prova.basea.base_a.crypto.StringCryptoConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(indexes = {
        @Index(name = "user_cpf", columnList = "cpf"),
        @Index(name = "user_name", columnList = "name")
})
public class SerasaUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String cpf;

    @Convert(converter = StringCryptoConverter.class)
    private String name;

    @Convert(converter = StringCryptoConverter.class)
    private String address;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Debt> debts;

}
