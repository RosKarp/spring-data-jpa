package ru.gb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.gb.model.Customer;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByPhoneNumber(String number);
    List<Customer> findByNameContaining(String name);
    @Query("select c from Customer c where c.age = (select max(c2.age) from Customer c2)")
    Optional<Customer> findOldest();



}
