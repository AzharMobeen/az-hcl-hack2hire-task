package ae.hcl.gateway.service.impl;

import ae.hcl.gateway.entity.Customer;
import ae.hcl.gateway.exception.CustomRuntimeException;
import ae.hcl.gateway.repository.CustomerRepository;
import ae.hcl.gateway.util.TestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

/**
 * @author Azhar Mobeen
 * @since 8/6/2022
 */
class CustomerServiceImplTest {

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Mock
    private CustomerRepository customerRepository;

    @BeforeEach
    void beforeEach() {
        MockitoAnnotations.openMocks(this);
    }

    @DisplayName("LoadUserByUsername Test cases")
    @Nested
    class LoadUserByUsernameTest {

        @Test
        void loadUserByUsernameNegative() {
            Mockito.when(customerRepository.findByUsername(any())).thenReturn(null);
            assertThrows(CustomRuntimeException.class, () -> customerService.loadUserByUsername(""),
                    "Invalid customer credentials");
        }

        @Test
        void loadUserByUsernameSuccess() {
            Customer customer = TestUtil.buildCustomer();
            Mockito.when(customerRepository.findByUsername(any())).thenReturn(customer);
            assertDoesNotThrow( () -> customerService.loadUserByUsername(""));
        }
    }

    @Nested
    class FetchAllCustomerTest {

        @Test
        void fetchAllCustomer() {
            Mockito.when(customerRepository.findAll()).thenReturn(null);
            assertDoesNotThrow( () -> customerService.fetchAllCustomer());
        }
    }

    @Test
    void saveCustomer() {
        customerService.saveCustomer(any());
    }
}