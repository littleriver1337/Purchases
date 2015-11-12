package com.theironyard;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by MattBrown on 11/11/15.
 */
public interface CustomerRepository extends CrudRepository<Customer, Integer> {
    Customer findOneByName(String username);
}
