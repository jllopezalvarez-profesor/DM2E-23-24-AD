package es.jllopezalvarez.accesodatos.ejemplos.ejemplosjpaspringstackoverflow.controllers;

import es.jllopezalvarez.accesodatos.ejemplos.ejemplosjpaspringstackoverflow.dto.ProductDto;
import es.jllopezalvarez.accesodatos.ejemplos.ejemplosjpaspringstackoverflow.dto.ProductDto2;
import es.jllopezalvarez.accesodatos.ejemplos.ejemplosjpaspringstackoverflow.entities.Product;
import es.jllopezalvarez.accesodatos.ejemplos.ejemplosjpaspringstackoverflow.repositories.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public ProductController(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
        List<Product> productos = productRepository.findAll();

        System.out.println(productos.size());

        return ResponseEntity.ok(productos);
    }

    @GetMapping("dto")
    public ResponseEntity<List<ProductDto>> findAllDto() {
        return ResponseEntity.ok(productRepository.findAllDto());
    }


    @GetMapping("dto/paged/{pageNumber}/{pageSize}")
    public ResponseEntity<List<ProductDto>> findAllDto(@PathVariable int pageNumber, @PathVariable int pageSize) {
        return ResponseEntity.ok(productRepository.findAllDto(PageRequest.of(pageNumber, pageSize)).getContent());
    }


    @GetMapping("dto2")
    public ResponseEntity<List<ProductDto2>> findAllDto2() {
        List<Product> productos = productRepository.findAll();

        List<ProductDto2> productosDto = productos.stream().map(p->modelMapper.map(p, ProductDto2.class)).toList();
        return ResponseEntity.ok(productosDto);
    }



}
