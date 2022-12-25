package ru.gb.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.model.Customer;
import ru.gb.repository.CustomerRepository;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;
    @Autowired
    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }

    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    public Optional<Customer> findByNumber(String number) {
        return customerRepository.findByPhoneNumber(number);
    }

    public List<Customer> findByNameLike (String name) {
        return customerRepository.findByNameContaining(name);
    }

    public Optional<Customer> findOldest() {
    return customerRepository.findOldest();
    }
}
