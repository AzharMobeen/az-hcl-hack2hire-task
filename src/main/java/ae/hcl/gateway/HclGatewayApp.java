package ae.hcl.gateway;

import ae.hcl.gateway.entity.Customer;
import ae.hcl.gateway.service.CustomerService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@OpenAPIDefinition(info = @Info(title = "HCL BANKING APP", version = "1.0",
        description = "It's a simple HCL Banking Gateway service to handle UI requests/response"))
@EnableJpaAuditing
@SecurityScheme(name = "securitySchema", scheme = "bearer", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)
@SpringBootApplication
public class HclGatewayApp {

    public static void main(String[] args) {
        SpringApplication.run(HclGatewayApp.class, args);
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner runner(CustomerService customerService) {
        return args -> {
            customerService.saveCustomer(new Customer(null, "azhar",
                    passwordEncoder().encode("1234"), new ArrayList<>()));
            customerService.saveCustomer(new Customer(null, "ali",
                    passwordEncoder().encode("1234"), new ArrayList<>()));
            customerService.saveCustomer(new Customer(null, "umaima",
                    passwordEncoder().encode("1234"), new ArrayList<>()));
            List<Customer> users = customerService.fetchAllCustomer();
            users.forEach(user -> log.info("User details ::: {}", user));
        };
    }
}