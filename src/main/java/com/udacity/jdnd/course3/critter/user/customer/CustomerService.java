package com.udacity.jdnd.course3.critter.user.customer;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class CustomerService {

    private final CustomerRepository repository;

    public Customer save(Customer customer) {
        return repository.save(customer);
    }

    public List<Customer> findAll() {
        List<Customer> customerList = repository.findAll();
        if (customerList.size() == 0) throw new IllegalStateException("There is no customer yet");
        else return customerList;
    }

    public Customer getById(Long id) {
        return repository.findById(id).orElseThrow(() -> {throw new IllegalStateException(
                "No such customer with id: " + id
        );});
    }
}
