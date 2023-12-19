package es.jllopezalvarez.accesodatos.ejemplos.ejemplosjpabasicos.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

@Getter
@Setter
@ToString
@Entity
@Table(name = "country")
public class Country {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "country_id", nullable = false)
    private short countryId;
    @Basic
    @Column(name = "country", nullable = false, length = 50)
    private String country;
    @Basic
    @Column(name = "last_update", nullable = false, insertable = false, updatable = false)
    private Timestamp lastUpdate;
    @OneToMany(mappedBy = "country")
    private Collection<City> cities;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return countryId == country.countryId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(countryId);
    }

}
