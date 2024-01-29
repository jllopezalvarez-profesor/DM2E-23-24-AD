package es.jllopezalvarez.accesodatos.ejemplos.ejemplosjpaspring.services;

import es.jllopezalvarez.accesodatos.ejemplos.ejemplosjpaspring.dto.FilmDto;
import es.jllopezalvarez.accesodatos.ejemplos.ejemplosjpaspring.entities.Film;
import es.jllopezalvarez.accesodatos.ejemplos.ejemplosjpaspring.repositories.FilmRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class FilmService {
    private final FilmRepository filmRepository;

    public FilmService(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    public Optional<Film> findById(short id) {
        return filmRepository.findById(id);
    }

    public Film findReferenceById(short id) {
        return filmRepository.getReferenceById(id);
    }

    public void save(Film film) {
        filmRepository.save(film);
    }

    public Collection<Film> findById(Collection<Short> ids) {
        return filmRepository.findByFilmIdInOrderByTitle(ids);
    }

    public Collection<Film> findByIdAndTitle(Collection<Short> ids, String busqueda) {
        return filmRepository.findByFilmIdInAndTitleContainingOrderByTitle(ids, busqueda);
    }

    public Page<Film> findAll(Pageable page) {
        return filmRepository.findAll(page);
    }

    public Collection<FilmDto> findFilmDtoByLanguageId(int languageId) {
        return filmRepository.findByLanguageLanguageId(languageId);
    }

    public Optional<FilmDto> findFilmDtoById(int filmId) {
        return filmRepository.findFilmDtoById(filmId);
    }
}
