package es.jllopezalvarez.sakila.api.controllers;

import es.jllopezalvarez.sakila.api.data.CategoryData;
import es.jllopezalvarez.sakila.api.models.Category;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("category")
public class CategoryController {
    @GetMapping
    public ResponseEntity<List<Category>> getAll() {
        return ResponseEntity.ok(CategoryData.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Category> getById(@PathVariable int id) {
        Optional<Category> category = CategoryData.getById(id);
        if (category.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(category.get());
    }


    @PostMapping
    public ResponseEntity<Category> create(@RequestBody Category category) {
        Category  newCategory = CategoryData.create(category);
        // TODO: obtener la URL del recurso creado, para devolverla al cliente.
        return ResponseEntity.created(null).body(newCategory);
    }

    @PutMapping
    public ResponseEntity<Category> update(@RequestBody Category category) {
        Category  updatedCategory = CategoryData.update(category);
        return ResponseEntity.ok(updatedCategory);
    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable int id) {
        CategoryData.delete(id);
    }
}
