package es.jllopezalvarez.accesodatos.ejemplos.ejemplosjpaspring.entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Language {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "language_id", nullable = false)
    @EqualsAndHashCode.Include
    private byte languageId;
    @Basic
    @Column(name = "name", nullable = false, length = 20, columnDefinition = "char")
    private String name;
    @Basic
    @Column(name = "last_update", nullable = false)
    private Timestamp lastUpdate;
}
