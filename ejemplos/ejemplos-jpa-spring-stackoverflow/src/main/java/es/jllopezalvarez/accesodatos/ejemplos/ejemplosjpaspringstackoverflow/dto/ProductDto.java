package es.jllopezalvarez.accesodatos.ejemplos.ejemplosjpaspringstackoverflow.dto;

import es.jllopezalvarez.accesodatos.ejemplos.ejemplosjpaspringstackoverflow.entities.Category;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

public interface ProductDto {
    int getProductId();
    String getProductName();
    List<CategoryDto> getCategories();

}
