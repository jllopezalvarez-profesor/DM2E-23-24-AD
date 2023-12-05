package es.jllopezalvarez.accesodatos.ejemplosjpa.controllers;

import es.jllopezalvarez.accesodatos.ejemplosjpa.entities.Product;
import es.jllopezalvarez.accesodatos.ejemplosjpa.repositories.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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



}
