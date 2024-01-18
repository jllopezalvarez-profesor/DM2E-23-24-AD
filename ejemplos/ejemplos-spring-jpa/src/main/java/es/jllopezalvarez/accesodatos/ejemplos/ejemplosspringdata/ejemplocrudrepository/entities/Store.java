package es.jllopezalvarez.accesodatos.ejemplos.ejemplosspringdata.ejemplocrudrepository.entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Store {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "store_id", nullable = false)
    @EqualsAndHashCode.Include
    private byte storeId;
    @Basic
    @Column(name = "manager_staff_id", nullable = false)
    private byte managerStaffId;
    @Basic
    @Column(name = "address_id", nullable = false)
    private short addressId;
    @Basic
    @Column(name = "last_update", nullable = false)
    private Timestamp lastUpdate;
    @OneToMany(mappedBy = "store")
    private Collection<Customer> customers;
    @OneToMany(mappedBy = "store")
    private Collection<InventoryItem> inventory;
    @OneToMany(mappedBy = "store")
    private Collection<StaffMember> staff;
    @ManyToOne
    @JoinColumn(name = "manager_staff_id", referencedColumnName = "staff_id", nullable = false, insertable = false, updatable = false)
    private StaffMember manager;
    @ManyToOne
    @JoinColumn(name = "address_id", referencedColumnName = "address_id", nullable = false, insertable = false, updatable = false)
    private Address address;
}
