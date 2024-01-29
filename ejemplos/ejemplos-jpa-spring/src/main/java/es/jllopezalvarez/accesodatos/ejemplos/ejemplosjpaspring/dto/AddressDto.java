package es.jllopezalvarez.accesodatos.ejemplos.ejemplosjpaspring.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AddressDto {
    private int addressId;
    private String postalCode;
    private String city;
    private String country;
}
