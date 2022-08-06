package ae.hcl.gateway.controller;

import ae.hcl.gateway.entity.Customer;
import ae.hcl.gateway.exception.ErrorMessage;
import ae.hcl.gateway.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.math.BigDecimal;
import java.util.List;

import static ae.hcl.gateway.constant.Constants.BASE_URL;

/**
 * @author Azhar Mobeen
 * @since 8/6/2022
 */

@RequiredArgsConstructor
@RequestMapping(BASE_URL)
@RestController
@SecurityRequirement(name = "securitySchema")
public class CustomerController {

    private final CustomerService customerService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "success", content = {@Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = BigDecimal.class)
            )}),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = {@Content(
                    schema = @Schema(implementation = ErrorMessage.class)
            )}),
            @ApiResponse(responseCode = "404", description = "NOT FOUND", content = {@Content(
                    schema = @Schema(implementation = ErrorMessage.class)
            )}),
            @ApiResponse(responseCode = "406", description = "NOT ACCEPTABLE", content = {@Content(
                    schema = @Schema(implementation = ErrorMessage.class)
            )})
    })
    @Operation(summary = "Fetch all Customer")
    @GetMapping
    public ResponseEntity<List<Customer>> getAllUser() {
        List<Customer> users = customerService.fetchAllCustomer();
        return ResponseEntity.ok().body(users);
    }
}