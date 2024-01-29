package es.jllopezalvarez.accesodatos.ejemplos.ejemplosjpaspring.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FilmDto {
    private int filmId;
    private String title;
    private int releaseYear;
}
