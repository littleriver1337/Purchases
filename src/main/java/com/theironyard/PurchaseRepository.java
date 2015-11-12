package com.theironyard;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by MattBrown on 11/11/15.
 */
public interface PurchaseRepository extends CrudRepository<Purchase, Integer>{
    List<Purchase> findByCategory(String category);
    List<Purchase> findByCreditCard(String creditCard);
}
