package com.serasa.baseb.appb.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String street;
    private String city;
    private String state;
    private String country;
    private String zipcode;

    public Address() {
    }

    public Address(String street, String city, String state, String country, String zipcode) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zipcode = zipcode;
    }

    public String asCsv() {
        return street + ',' + city + "," + state + "," + country + zipcode;
    }
}
