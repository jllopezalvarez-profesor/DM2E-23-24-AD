package es.jllopezalvarez.accesodatos.ejemplos.ejemplosspringdata.ejemplocrudrepository.repositories;

import es.jllopezalvarez.accesodatos.ejemplos.ejemplosspringdata.ejemplocrudrepository.entities.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.util.Streamable;

import java.util.Collection;

public interface FilmRepository extends JpaRepository<Film, Short> {
    Collection<Film> findByFilmIdInOrderByTitle(Collection<Short> ids);
    Collection<Film> findByFilmIdInAndTitleContainingOrderByTitle(Collection<Short> ids, String busqueda);
}
