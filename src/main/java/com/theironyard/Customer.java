package com.theironyard;

import javax.persistence.*;

import java.util.List;

/**
 * Created by MattBrown on 11/11/15.
 */
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue
    Integer id;
    String name;
    String email;

    @OneToMany(mappedBy = "customer")
    List<Purchase> purchase;

    public Customer(){

    }
}

