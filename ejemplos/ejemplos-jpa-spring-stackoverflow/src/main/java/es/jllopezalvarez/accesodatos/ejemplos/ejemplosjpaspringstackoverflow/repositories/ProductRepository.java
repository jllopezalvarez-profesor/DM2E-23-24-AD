package es.jllopezalvarez.accesodatos.ejemplos.ejemplosjpaspringstackoverflow.repositories;

import es.jllopezalvarez.accesodatos.ejemplos.ejemplosjpaspringstackoverflow.dto.ProductDto;
import es.jllopezalvarez.accesodatos.ejemplos.ejemplosjpaspringstackoverflow.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("select p from Product p join p.categories c")
    List<ProductDto> findAllDto();
}
