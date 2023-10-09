package es.jllopezalvarez.sakila.api.dto;

import java.util.List;

public class UpdateActorFilmDto {
    int actorId;
    List<Integer> filmIds;

    public UpdateActorFilmDto(int actorId, List<Integer> filmIds) {
        this.actorId = actorId;
        this.filmIds = filmIds;
    }

    public int getActorId() {
        return actorId;
    }

    public void setActorId(int actorId) {
        this.actorId = actorId;
    }

    public List<Integer> getFilmIds() {
        return filmIds;
    }

    public void setFilmIds(List<Integer> filmIds) {
        this.filmIds = filmIds;
    }

    @Override
    public String toString() {
        return "UpdateActorFilmDto{" +
                "actorId=" + actorId +
                ", filmIds=" + filmIds +
                '}';
    }
}
