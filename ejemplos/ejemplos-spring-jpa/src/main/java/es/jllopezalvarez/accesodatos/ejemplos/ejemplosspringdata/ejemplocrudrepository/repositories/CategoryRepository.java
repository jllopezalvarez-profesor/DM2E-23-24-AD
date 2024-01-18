package es.jllopezalvarez.accesodatos.ejemplos.ejemplosspringdata.ejemplocrudrepository.repositories;

import es.jllopezalvarez.accesodatos.ejemplos.ejemplosspringdata.ejemplocrudrepository.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends JpaRepository<Category, Byte> {
}
