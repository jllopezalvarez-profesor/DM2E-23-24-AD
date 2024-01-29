package es.jllopezalvarez.accesodatos.ejemplos.ejemplosjpaspring.repositories;

import es.jllopezalvarez.accesodatos.ejemplos.ejemplosjpaspring.dto.CustomerPaymentsDto;
import es.jllopezalvarez.accesodatos.ejemplos.ejemplosjpaspring.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface CustomerRepository extends JpaRepository<Customer, Short> {
    @Query("select c from Customer c  join fetch c.payments p where c.customerId in :customerIds")
    Collection<CustomerPaymentsDto> findPaymentsByCustomerIdIn(@Param("customerIds") Collection<Short> customerIds);



}
