package es.jllopezalvarez.accesodatos.ejemplos.ejemplosjpaspring.repositories;

import es.jllopezalvarez.accesodatos.ejemplos.ejemplosjpaspring.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface CategoryRepository extends JpaRepository<Category, Byte> {

    @Query(value = "select c from Category c")
    Collection<Category> getAllJpql();

    @Query(value = "select * from category", nativeQuery = true)
    Collection<Category> getAllSql();

    @Query("select c from Category c where c.name like :searchString")
    Collection<Category> searchWithLikeJpql(@Param("searchString") String search);

    @Query(value = "select * from category where name like :searchString", nativeQuery = true)
    Collection<Category> searchWithLikeSql(@Param("searchString") String search);
    

}
