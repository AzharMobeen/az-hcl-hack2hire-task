package ae.hcl.gateway.service.impl;

import ae.hcl.gateway.entity.Customer;
import ae.hcl.gateway.repository.CustomerRepository;
import ae.hcl.gateway.exception.CustomRuntimeException;
import ae.hcl.gateway.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Azhar Mobeen
 * @since 8/6/2022
 */

@RequiredArgsConstructor
@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService, UserDetailsService {

    private final CustomerRepository customerRepository;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) {
        log.info("loadUserByUsername called username {}", username);
        Customer customer = customerRepository.findByUsername(username);
        if (customer == null) {
            throw new CustomRuntimeException("Invalid customer credentials", "Provided username/password invalid",
                    HttpStatus.UNAUTHORIZED);
        }
        User user = new User(customer.getUsername(), customer.getPassword(), new ArrayList<>());
        log.info("loadUserByUsername response {}", user);
        return user;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Customer> fetchAllCustomer() {
        log.info("Fetch All Customers");
        List<Customer> customers = customerRepository.findAll();
        log.info("Fetch All Customers response :: {}", customers);
        return customers;
    }
    @Transactional
    @Override
    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }
}