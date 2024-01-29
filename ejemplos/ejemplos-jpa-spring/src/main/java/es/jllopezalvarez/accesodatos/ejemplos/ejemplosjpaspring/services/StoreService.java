package es.jllopezalvarez.accesodatos.ejemplos.ejemplosjpaspring.services;

import es.jllopezalvarez.accesodatos.ejemplos.ejemplosjpaspring.dto.AddressDto;
import es.jllopezalvarez.accesodatos.ejemplos.ejemplosjpaspring.repositories.StoreRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class StoreService {
    private final StoreRepository storeRepository;

    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    public Collection<AddressDto> findAllAddressesDto() {
        return storeRepository.findAllAddressesDto();
    }
}
