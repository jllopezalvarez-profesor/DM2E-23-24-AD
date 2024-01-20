package es.jllopezalvarez.accesodatos.ejemplos.ejemplosjpaspring.repositories;

import es.jllopezalvarez.accesodatos.ejemplos.ejemplosjpaspring.entities.Actor;
import org.springframework.data.repository.CrudRepository;

public interface ActorRepository extends CrudRepository<Actor, Short> {
}
