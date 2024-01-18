package es.jllopezalvarez.accesodatos.ejemplos.ejemplosspringdata.ejemplocrudrepository.services;

import es.jllopezalvarez.accesodatos.ejemplos.ejemplosspringdata.ejemplocrudrepository.entities.Category;
import es.jllopezalvarez.accesodatos.ejemplos.ejemplosspringdata.ejemplocrudrepository.repositories.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public long count() {
        return categoryRepository.count();
    }

    public Iterable<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Optional<Category> findById(byte id) {
        return categoryRepository.findById(id);
    }

    public Category findReferenceById(byte id) {
        return categoryRepository.getReferenceById(id);
    }

    public void deleteById(byte categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    public void deleteUnusedCategories() {
        var categories = this.findAll();
        for (Category category : categories) {
            // Esta llamada a getFilms falla si no tenemos activa la propiedad
            // "spring.jpa.properties.hibernate.enable_lazy_load_no_trans"
            // Esto pasa porque la relación entre categorías y películas es "lazy".
            // Esto significa que las películas asociadas a la categoría no se recuperan
            // de la BD hasta que se llama a getFilms(). Pero como el entityManager que
            // se usa en el repositorio ha cerrado la conexión, no se pueden recuperar.
            // Al fijar a true la propiedad anteriormente indicada, se abrirá una conexión y
            // transacción para recuperar los datos.
            // No es el enfoque recomendado. Lo ideal es hacer este tipo de operaciones de forma
            // transaccional, diseñando la aplicación para que se ejecuten todas las peticiones
            // bajo la misma sesión, o trayendo todos los datos (no hacer lazy).
            if (category.getFilms().isEmpty()) {
                System.out.printf("Borrando categoría %d porque no tiene películas asociadas.\n", category.getCategoryId());
                this.deleteById(category.getCategoryId());
            }
        }
    }

    @Transactional
    public void deleteUnusedCategoriesTransactional() {
        var categories = this.findAll();
        for (Category category : categories) {
            // Esta llamada a getFilms falla si no tenemos activa la propiedad

            if (category.getFilms().isEmpty()) {
                System.out.printf("Borrando categoría %d porque no tiene películas asociadas.\n", category.getCategoryId());
                this.deleteById(category.getCategoryId());

                throw new RuntimeException("Error forzado");
            }
        }
    }


    public Category save(Category category) {
        return categoryRepository.save(category);
    }
}
