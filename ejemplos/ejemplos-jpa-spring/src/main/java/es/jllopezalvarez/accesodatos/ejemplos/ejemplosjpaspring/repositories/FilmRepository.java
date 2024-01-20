package es.jllopezalvarez.accesodatos.ejemplos.ejemplosjpaspring.repositories;

import es.jllopezalvarez.accesodatos.ejemplos.ejemplosjpaspring.entities.Film;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface FilmRepository extends JpaRepository<Film, Short> {
    Collection<Film> findByFilmIdInOrderByTitle(Collection<Short> ids);

    Collection<Film> findByFilmIdInAndTitleContainingOrderByTitle(Collection<Short> ids, String busqueda);
}
