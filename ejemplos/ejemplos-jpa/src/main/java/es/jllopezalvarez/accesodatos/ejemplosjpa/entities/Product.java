package es.jllopezalvarez.accesodatos.ejemplosjpa.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "productos")
public class Product {
    // Mirar como usar esto enb Oracle
    // @SequenceGenerator(name = "SEQ_PRODUCT")


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID productUuid;
    @Column(length = 100)
    private String name;
    @Column(length = 2000)
    private String description;

}
