package es.jllopezalvarez.accesodatos.ejemplos.ejemplosjpaspring.repositories;

import es.jllopezalvarez.accesodatos.ejemplos.ejemplosjpaspring.entities.InventoryItem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface InventoryRepository extends Repository<InventoryItem, Integer> {
    @Query("select item from InventoryItem item " +
            "where item.filmId = :filmId and item.storeId = :storeId " +
            "and not exists (select rental.rentalId from Rental rental where rental.inventoryId = item.inventoryId and rental.returnDate is null )")
    Collection<InventoryItem> findNotRentedCopies(@Param("filmId") int filmId, @Param("storeId") int storeId);
}

/*
 * select i.inventory_id
 * from inventory i
 * where
 * i.film_id = @film_id and i.store_id = @store_id
 * and not exists(select rental.inventory_id from rental where rental.inventory_id = i.inventory_id and rental.return_date is null);
 */
