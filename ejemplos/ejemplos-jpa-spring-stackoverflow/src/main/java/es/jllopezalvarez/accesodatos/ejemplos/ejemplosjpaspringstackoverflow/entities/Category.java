package es.jllopezalvarez.accesodatos.ejemplos.ejemplosjpaspringstackoverflow.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

@Entity
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "categories")
public class Category {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryId;
    private String categoryName;

    @ManyToMany(mappedBy = "categories")
    private List<Product> products;
}
