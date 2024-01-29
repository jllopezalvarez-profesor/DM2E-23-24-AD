package es.jllopezalvarez.accesodatos.ejemplos.ejemplosjpaspringstackoverflow.entities;

import es.jllopezalvarez.accesodatos.ejemplos.ejemplosjpaspringstackoverflow.dto.ProductDto;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

@Entity
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "products")
public class Product {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;
    private String productName;

    @ManyToMany
    @JoinTable(name = "categories_products", joinColumns = {@JoinColumn(name = "category_id")}, inverseJoinColumns = {@JoinColumn(name = "product_id")})
    private List<Category> categories;

}
