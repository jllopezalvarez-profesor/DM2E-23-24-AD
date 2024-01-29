package es.jllopezalvarez.accesodatos.ejemplos.ejemplosjpaspringstackoverflow;

import es.jllopezalvarez.accesodatos.ejemplos.ejemplosjpaspringstackoverflow.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EjemplosJpaSpringStackoverflowApplication implements CommandLineRunner {

	private final ProductRepository productRepository;

	public EjemplosJpaSpringStackoverflowApplication(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(EjemplosJpaSpringStackoverflowApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		productRepository.findAll().forEach(System.out::println);
	}
}
