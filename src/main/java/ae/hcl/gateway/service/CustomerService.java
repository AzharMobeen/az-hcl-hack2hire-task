package ae.hcl.gateway.service;

import ae.hcl.gateway.entity.Customer;

import java.util.List;

/**
 * @author Azhar Mobeen
 * @since 8/6/2022
 */
public interface CustomerService {


    List<Customer> fetchAllCustomer();

    void saveCustomer(Customer customer);
}
