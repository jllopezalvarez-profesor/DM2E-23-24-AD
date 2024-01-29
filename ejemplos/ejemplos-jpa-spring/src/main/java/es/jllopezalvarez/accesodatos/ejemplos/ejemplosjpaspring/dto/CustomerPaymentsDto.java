package es.jllopezalvarez.accesodatos.ejemplos.ejemplosjpaspring.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

public interface CustomerPaymentsDto {
    int getCustomerId();
    String getFirstName();
    String getLastName();

    Collection<CustomerPaymentDto> getPayments();
}
