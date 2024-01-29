package es.jllopezalvarez.accesodatos.ejemplos.ejemplosjpaspring.services;

import es.jllopezalvarez.accesodatos.ejemplos.ejemplosjpaspring.dto.CustomerPaymentsDto;
import es.jllopezalvarez.accesodatos.ejemplos.ejemplosjpaspring.repositories.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Transactional
    public Collection<CustomerPaymentsDto> findPaymentsByCustomerIdIn(Collection<Short> ids){
        return customerRepository.findPaymentsByCustomerIdIn(ids);
    }
}
