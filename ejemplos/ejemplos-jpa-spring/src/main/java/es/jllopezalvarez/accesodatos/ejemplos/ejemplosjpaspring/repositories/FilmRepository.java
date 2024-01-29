package es.jllopezalvarez.accesodatos.ejemplos.ejemplosjpaspring.repositories;

import es.jllopezalvarez.accesodatos.ejemplos.ejemplosjpaspring.dto.FilmDto;
import es.jllopezalvarez.accesodatos.ejemplos.ejemplosjpaspring.entities.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.Optional;

public interface FilmRepository extends JpaRepository<Film, Short> {
    Collection<Film> findByFilmIdInOrderByTitle(Collection<Short> ids);

    Collection<Film> findByFilmIdInAndTitleContainingOrderByTitle(Collection<Short> ids, String busqueda);

    Collection<FilmDto> findByLanguageLanguageId(int languageId);

    @Query("select new  es.jllopezalvarez.accesodatos.ejemplos.ejemplosjpaspring.dto.FilmDto(f.filmId, f.title, f.releaseYear) from Film f where f.filmId = :filmId")
    Optional<FilmDto> findFilmDtoById(@Param("filmId") int filmId);
}
