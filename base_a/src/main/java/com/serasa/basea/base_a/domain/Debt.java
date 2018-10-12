package com.serasa.basea.base_a.domain;

import com.serasa.basea.base_a.crypto.StringCryptoConverter;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Debt {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Convert(converter = StringCryptoConverter.class)
    private String description;

    @Convert(converter = StringCryptoConverter.class)
    private String value;

    @Convert(converter = StringCryptoConverter.class)
    private String active;

}
