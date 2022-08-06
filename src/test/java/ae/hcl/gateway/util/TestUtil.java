package ae.hcl.gateway.util;

import ae.hcl.gateway.entity.Customer;

/**
 * @author Azhar Mobeen
 * @since 8/6/2022
 */
public class TestUtil {
    public static Customer buildCustomer() {
        return new Customer(1L, "azhar", "1234", null);
    }
}
