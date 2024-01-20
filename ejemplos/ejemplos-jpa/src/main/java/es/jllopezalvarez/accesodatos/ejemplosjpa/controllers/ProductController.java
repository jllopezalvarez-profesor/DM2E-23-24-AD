package es.jllopezalvarez.accesodatos.ejemplosjpa.controllers;

import es.jllopezalvarez.accesodatos.ejemplosjpa.entities.Product;
import es.jllopezalvarez.accesodatos.ejemplosjpa.repositories.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/product")
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAll(){
        return ResponseEntity.ok(productRepository.findAll());
    }


     @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable int id){
        Optional<Product> product = productRepository.findById(id);

        if( product.isEmpty()){
            return ResponseEntity.notFound().build();
        }

         System.out.printf("Producto encontrado: %d\n", product.get().getProductId());
         System.out.printf("Categoría: %s\n", product.get().getCategory().getName());
        return ResponseEntity.ok(product.get());
    }

}
