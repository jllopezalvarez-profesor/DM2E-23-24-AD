package es.jllopezalvarez.accesodatos.ejemplos.ejemplosjpaspring.services;

import es.jllopezalvarez.accesodatos.ejemplos.ejemplosjpaspring.entities.Actor;
import es.jllopezalvarez.accesodatos.ejemplos.ejemplosjpaspring.repositories.ActorRepository;
import org.springframework.stereotype.Service;

@Service
public class ActorService {
    private final ActorRepository actorRepository;

    public ActorService(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    public long count() {
        return actorRepository.count();
    }

    public Iterable<Actor> findAll() {
        return actorRepository.findAll();
    }


}
