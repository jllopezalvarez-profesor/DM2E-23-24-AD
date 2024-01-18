package es.jllopezalvarez.accesodatos.ejemplos.ejemplosspringdata.ejemplocrudrepository.repositories;

import es.jllopezalvarez.accesodatos.ejemplos.ejemplosspringdata.ejemplocrudrepository.entities.Actor;
import org.springframework.data.repository.CrudRepository;

public interface ActorRepository extends CrudRepository<Actor, Short> {
}
