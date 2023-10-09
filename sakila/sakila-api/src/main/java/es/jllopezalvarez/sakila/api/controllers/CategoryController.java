package es.jllopezalvarez.sakila.api.controllers;

import es.jllopezalvarez.sakila.api.data.CategoryData;
import es.jllopezalvarez.sakila.api.models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;



@RestController
@RequestMapping("category")
public class CategoryController {
    
    private final CategoryData categoryData;

    public CategoryController(CategoryData categoryData) {
        this.categoryData = categoryData;
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAll() {
        return ResponseEntity.ok(categoryData.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Category> getById(@PathVariable int id) {
       Optional<Category> category = categoryData.getById(id);
        if (category.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(category.get());
    }


    @PostMapping
    public ResponseEntity<Category> create(@RequestBody Category category) {
        Category  newCategory = categoryData.create(category);
        // TODO: obtener la URL del recurso creado, para devolverla al cliente.
        return ResponseEntity.created(null).body(newCategory);
    }

    @PutMapping
    public ResponseEntity<Category> update(@RequestBody Category category) {
        Category  updatedCategory = categoryData.update(category);
        return ResponseEntity.ok(updatedCategory);
    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable int id) {
        categoryData.delete(id);
    }
}
