package es.jllopezalvarez.accesodatos.ejemplos.ejemplosjpaspring.repositories;

import es.jllopezalvarez.accesodatos.ejemplos.ejemplosjpaspring.dto.AddressDto;
import es.jllopezalvarez.accesodatos.ejemplos.ejemplosjpaspring.entities.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface StoreRepository extends JpaRepository<Store, Byte> {


    @Query("select new es.jllopezalvarez.accesodatos.ejemplos.ejemplosjpaspring.dto.AddressDto(" +
            "s.address.addressId, s.address.postalCode,  s.address.city.city, " +
            "s.address.city.country.country ) from Store s")
    Collection<AddressDto> findAllAddressesDto();
}
