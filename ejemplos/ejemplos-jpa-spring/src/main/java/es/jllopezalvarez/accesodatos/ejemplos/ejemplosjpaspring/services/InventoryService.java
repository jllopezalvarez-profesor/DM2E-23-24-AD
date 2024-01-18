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

    /**
     * Método para encontrar las copias de una película disponibles (no alquiladas) en una tienda.
     *
     * @param filmId  Id de la película que queremos buscar copias
     * @param storeId Id de la tienda en la que queremos buscar copias
     * @return Una colección de elementos de inventario con las copias disponibles para alquiler
     */
    public Collection<InventoryItem> findNotRentedCopies(int filmId, int storeId) {
        return inventoryRepository.findNotRentedCopies(filmId, storeId);
    }
}
