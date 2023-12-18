package es.jllopezalvarez.accesodatos.ejemplos.ejemplosjpabasicos.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;
import java.util.Objects;

@Getter
@Setter
@ToString
@Entity
@Table(name = "city")
public class City {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "city_id", nullable = false)
    private short cityId;
    @Basic
    @Column(name = "city", nullable = false, length = 50)
    private String city;
//    @Basic
//    @Column(name = "country_id", nullable = false, updatable = false, insertable = false)
//    private short countryId;
    @Basic
    @Column(name = "last_update", nullable = false)
    private Timestamp lastUpdate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id", referencedColumnName = "country_id", nullable = false)
    private Country country;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city1 = (City) o;
        return cityId == city1.cityId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cityId);
    }
}
