package es.jllopezalvarez.accesodatos.ejemplos.ejemplosjpaspringstackoverflow.controllers;

import es.jllopezalvarez.accesodatos.ejemplos.ejemplosjpaspringstackoverflow.entities.Category;
import es.jllopezalvarez.accesodatos.ejemplos.ejemplosjpaspringstackoverflow.repositories.CategoryRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryRepository categoryRepository;


    public CategoryController(CategoryRepository categoryRepository) {

        this.categoryRepository = categoryRepository;
    }

    @GetMapping
    public ResponseEntity<List<Category>> findAll() {
        return ResponseEntity.ok(categoryRepository.findAll());
    }


}
