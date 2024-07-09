package org.example.customerspringbatch.config;

import org.example.customerspringbatch.entity.Customer;
import org.springframework.batch.item.ItemProcessor;

public class CustomCustomerProcessor implements ItemProcessor<Customer, Customer> {
    @Override
    public Customer process(Customer customer) throws Exception {
        if(customer.getCountry().equals("United States")) {
            System.out.println(customer.getFirstName() + " " + customer.getLastName());
            return customer;
        }else{
            return null;
        }
    }
}
