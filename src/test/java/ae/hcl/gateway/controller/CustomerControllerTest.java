package ae.hcl.gateway.controller;

import ae.hcl.gateway.service.impl.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Azhar Mobeen
 * @since 8/6/2022
 */
class CustomerControllerTest {

    @InjectMocks
    private CustomerController customerController;

    @Mock
    private CustomerServiceImpl customerService;

    @BeforeEach
    void beforeEach() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllUser() {
        assertDoesNotThrow(() -> customerController.getAllUser());
    }
}