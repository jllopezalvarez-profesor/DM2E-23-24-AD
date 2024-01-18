package es.jllopezalvarez.accesodatos.ejemplos.ejemplosspringdata.ejemplocrudrepository.services;

import es.jllopezalvarez.accesodatos.ejemplos.ejemplosspringdata.ejemplocrudrepository.entities.Film;
import es.jllopezalvarez.accesodatos.ejemplos.ejemplosspringdata.ejemplocrudrepository.repositories.FilmRepository;
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

}
