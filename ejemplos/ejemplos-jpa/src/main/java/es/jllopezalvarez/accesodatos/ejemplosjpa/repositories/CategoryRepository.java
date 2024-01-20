package es.jllopezalvarez.accesodatos.ejemplosjpa.repositories;

import es.jllopezalvarez.accesodatos.ejemplosjpa.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
