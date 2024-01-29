package es.jllopezalvarez.accesodatos.ejemplos.ejemplosjpaspringstackoverflow.repositories;

import es.jllopezalvarez.accesodatos.ejemplos.ejemplosjpaspringstackoverflow.entities.Category;
import es.jllopezalvarez.accesodatos.ejemplos.ejemplosjpaspringstackoverflow.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
