package es.jllopezalvarez.accesodatos.ejemplos.ejemplosjpaspringstackoverflow.configurtion;

import es.jllopezalvarez.accesodatos.ejemplos.ejemplosjpaspringstackoverflow.dto.ProductDto2;
import es.jllopezalvarez.accesodatos.ejemplos.ejemplosjpaspringstackoverflow.entities.Product;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {
    @Bean
    public ModelMapper getMapper(){
        ModelMapper modelMapper = new ModelMapper();
//        modelMapper.typeMap(Product.class, ProductDto2.class).addMappings(mapper -> {
//            mapper.map(Product::getProductId, ProductDto2::setProductId);
//            mapper.map(Product::getCategories, VoteDto::setOption);
//        });
//        modelMapper.typeMap(Option.class, OptionDto.class).addMappings(mapper -> {
//            mapper.map(Vote::getVoteId, VoteDto::setVoteId);
//            mapper.map(Vote::getOption, VoteDto::setOption);
//        });
        return modelMapper;
    }
}
