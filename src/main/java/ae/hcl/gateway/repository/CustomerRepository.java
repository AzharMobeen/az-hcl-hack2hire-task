package ae.hcl.gateway.repository;

import ae.hcl.gateway.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByUsername(String username);
}