package ru.gb.service;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.gb.model.Customer;
import ru.gb.repository.CustomerRepository;

@Component
public class CustomerDataGenerator {

    private CustomerRepository customerRepository;

    @Autowired
    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
@EventListener(ApplicationReadyEvent.class)
    public void generateDataOnStartup() {
        Faker faker = new Faker();

        for(int i = 0; i < 10; i++){
            Customer customer = new Customer();
            customer.setName(faker.name().fullName());
            customer.setPhoneNumber(faker.number().digits(6));
            customer.setAge(faker.number().numberBetween(20, 60));
            customerRepository.save(customer);
        }
    }


}
