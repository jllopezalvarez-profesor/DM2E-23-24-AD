package es.jllopezalvarez.accesodatos.ejemplosjpa.repositories;


import es.jllopezalvarez.accesodatos.ejemplosjpa.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer > {
}
