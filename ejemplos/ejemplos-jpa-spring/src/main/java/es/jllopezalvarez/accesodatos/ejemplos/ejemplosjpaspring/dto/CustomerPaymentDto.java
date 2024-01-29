package es.jllopezalvarez.accesodatos.ejemplos.ejemplosjpaspring.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Timestamp;


public interface CustomerPaymentDto {
    Timestamp getPaymentDate();
    double getAmount();
}
