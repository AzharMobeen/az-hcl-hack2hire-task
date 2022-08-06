package ae.hcl.gateway.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

/**
 * @author Azhar Mobeen
 * @since 8/6/2022
 */

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue
    private Long customerId;
    private String username;
    private String password;

    @Transient
    private Collection<String> roles;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Customer customer = (Customer) o;
        return customerId != null && Objects.equals(customerId, customer.customerId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}