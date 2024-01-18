package es.jllopezalvarez.accesodatos.ejemplos.ejemplosspringdata.ejemplocrudrepository.repositories;

import es.jllopezalvarez.accesodatos.ejemplos.ejemplosspringdata.ejemplocrudrepository.entities.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Short> {
}
