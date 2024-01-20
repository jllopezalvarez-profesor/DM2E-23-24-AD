package es.jllopezalvarez.accesodatos.ejemplosjpa.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name="categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryId;
    private String name;
    private String description;
    @OneToMany(fetch = FetchType.)
    private List<Product> products;


    Math.PI;

}
