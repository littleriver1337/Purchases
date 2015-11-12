package com.theironyard;

import javax.persistence.*;

/**
 * Created by MattBrown on 11/11/15.
 */
@Entity
public class Purchase {
    @Id
    @GeneratedValue
    Integer id;

    String date;
    String creditCard;
    String cvv;
    String category;

    @ManyToOne
    Customer customer;//extends to Customer...pulls whole customer object and gives us our CUSTOMER ID

    public Purchase(){

    }
}
