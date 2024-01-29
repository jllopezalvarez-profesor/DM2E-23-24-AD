package es.jllopezalvarez.accesodatos.ejemplos.ejemplosjpaspringstackoverflow.controllers;

import es.jllopezalvarez.accesodatos.ejemplos.ejemplosjpaspringstackoverflow.dto.ProductDto;
import es.jllopezalvarez.accesodatos.ejemplos.ejemplosjpaspringstackoverflow.entities.Product;
import es.jllopezalvarez.accesodatos.ejemplos.ejemplosjpaspringstackoverflow.repositories.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public ResponseEntity<List<Product>> findAll(){
        List<Product> productos = productRepository.findAll();

        System.out.println(productos.size());

        return ResponseEntity.ok(productos);
    }
    @GetMapping("dto")
    public ResponseEntity<List<ProductDto>> findAllDto(){
        return ResponseEntity.ok(productRepository.findAllDto());
    }
}
