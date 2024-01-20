package es.jllopezalvarez.accesodatos.ejemplosjpa.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.ManyToAny;

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
    @ColumnDefault("random_uuid()")
    private UUID productUuid;
    @Column(length = 100)
    private String name;
    @Column(length = 2000)
    private String description;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    //@JsonIgnore
    private Category category;

}
