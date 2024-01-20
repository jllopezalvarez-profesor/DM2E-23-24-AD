package es.jllopezalvarez.accesodatos.ejemplos.ejemplosjpaspring.entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Collection;

@Entity
@Table(name = "inventory")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class InventoryItem {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "inventory_id", nullable = false)
    @EqualsAndHashCode.Include
    private int inventoryId;
    @Basic
    @Column(name = "film_id", nullable = false)
    private short filmId;
    @Basic
    @Column(name = "store_id", nullable = false)
    private byte storeId;
    @Basic
    @Column(name = "last_update", nullable = false)
    private Timestamp lastUpdate;
    @ManyToOne
    @JoinColumn(name = "film_id", referencedColumnName = "film_id", nullable = false, insertable = false, updatable = false)
    private Film film;
    @ManyToOne
    @JoinColumn(name = "store_id", referencedColumnName = "store_id", nullable = false, insertable = false, updatable = false)
    private Store store;
    @OneToMany(mappedBy = "inventoryItem")
    private Collection<Rental> rentals;
}
