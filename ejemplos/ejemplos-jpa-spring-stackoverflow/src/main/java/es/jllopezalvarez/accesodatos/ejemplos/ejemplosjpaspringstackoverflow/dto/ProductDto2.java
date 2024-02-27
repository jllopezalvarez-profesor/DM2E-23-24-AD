package es.jllopezalvarez.accesodatos.ejemplos.ejemplosjpaspringstackoverflow.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductDto2 {
    private int productId;
    private String productName;
    List<CategoryDto2> categories;

}
