package es.jllopezalvarez.accesodatos.ejemplos.ejemplosspringdata.ejemplocrudrepository.services;

import es.jllopezalvarez.accesodatos.ejemplos.ejemplosspringdata.ejemplocrudrepository.entities.InventoryItem;
import es.jllopezalvarez.accesodatos.ejemplos.ejemplosspringdata.ejemplocrudrepository.repositories.InventoryRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class InventoryService {
    private final InventoryRepository inventoryRepository;


    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public Collection<InventoryItem> findNotRentedCopies(int filmId, int storeId){
        return inventoryRepository.findNotRentedCopies(filmId, storeId);
    }
}
