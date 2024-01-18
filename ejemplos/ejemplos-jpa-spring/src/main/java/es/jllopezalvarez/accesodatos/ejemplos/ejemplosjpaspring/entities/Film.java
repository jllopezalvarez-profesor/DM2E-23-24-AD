package es.jllopezalvarez.accesodatos.ejemplos.ejemplosspringdata.ejemplocrudrepository.entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Film {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "film_id", nullable = false)
    @EqualsAndHashCode.Include
    private short filmId;
    @Basic
    @Column(name = "title", nullable = false, length = 128)
    private String title;
    @Basic
    @Column(name = "description", nullable = true, length = -1)
    private String description;
    @Basic
    @Column(name = "release_year", nullable = true, columnDefinition = "year")
    private int releaseYear;
    //    @Basic
//    @Column(name = "language_id", nullable = false)
//    private byte languageId;
//    @Basic
//    @Column(name = "original_language_id", nullable = true)
//    private Byte originalLanguageId;
    @Basic
    @Column(name = "rental_duration", nullable = false)
    private byte rentalDuration;
    @Basic
    @Column(name = "rental_rate", nullable = false, precision = 2)
    private BigDecimal rentalRate;
    @Basic
    @Column(name = "length", nullable = true)
    private Short length;
    @Basic
    @Column(name = "replacement_cost", nullable = false, precision = 2)
    private BigDecimal replacementCost;
    @Basic
    @Column(name = "rating", nullable = true, columnDefinition = "enum ('G', 'PG', 'PG-13', 'R', 'NC-17') default 'G'")
    private String rating;
    @Basic
    @Column(name = "special_features", nullable = true, columnDefinition = "set ('Trailers', 'Commentaries', 'Deleted Scenes', 'Behind the Scenes')")
    private String specialFeatures;
    @Basic
    @Column(name = "last_update", nullable = false)
    private Timestamp lastUpdate;
    @ManyToOne
    @JoinColumn(name = "language_id", referencedColumnName = "language_id", nullable = false)
    private Language language;
    @ManyToOne
    @JoinColumn(name = "original_language_id", referencedColumnName = "language_id")
    private Language originalLanguage;
    @OneToMany(mappedBy = "film")
    private Collection<InventoryItem> inventoryItems;
    @ManyToMany(mappedBy = "films")
    private Collection<Actor> actors;
    @ManyToMany
    @JoinTable(name = "film_category", joinColumns = @JoinColumn(name = "film_id", referencedColumnName = "film_id", nullable = false), inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "category_id", nullable = false))
    private Collection<Category> categories;
}
