package es.jllopezalvarez.accesodatos.ejemplos.ejemplosjpaspring;

import es.jllopezalvarez.accesodatos.ejemplos.ejemplosjpaspring.dto.AddressDto;
import es.jllopezalvarez.accesodatos.ejemplos.ejemplosjpaspring.dto.CustomerPaymentsDto;
import es.jllopezalvarez.accesodatos.ejemplos.ejemplosjpaspring.dto.FilmDto;
import es.jllopezalvarez.accesodatos.ejemplos.ejemplosjpaspring.entities.Category;
import es.jllopezalvarez.accesodatos.ejemplos.ejemplosjpaspring.entities.Film;
import es.jllopezalvarez.accesodatos.ejemplos.ejemplosjpaspring.entities.InventoryItem;
import es.jllopezalvarez.accesodatos.ejemplos.ejemplosjpaspring.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.*;

// Al implementar CommandLineRunner se ejecuta el método run de
// la clase tras haberse inicializado la aplicación Spring
@SpringBootApplication
public class EjemplosJpaSpring implements CommandLineRunner {


    private Scanner scanner = new Scanner(System.in);

    private final ActorService actorService;
    private final FilmService filmService;
    private final CategoryService categoryService;
    private final InventoryService inventoryService;
    private final StoreService storeService;
    private final CustomerService customerService;

    public EjemplosJpaSpring(ActorService actorService, CategoryService categoryService, FilmService filmService, InventoryService inventoryService, StoreService storeService, CustomerService customerService) {
        this.actorService = actorService;
        this.categoryService = categoryService;
        this.filmService = filmService;
        this.inventoryService = inventoryService;
        this.storeService = storeService;
        this.customerService = customerService;
    }

    public static void main(String[] args) {
        SpringApplication.run(EjemplosJpaSpring.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
//        ejemplo01ConsultaRepo();
//        ejemplo02InsercionRepo();
//        ejemplo03BorradoRepo();
//        ejemplo04BorradoRepoTransaccional();
//        ejemplo05InsercionRepoRelacionadas();
//        ejemplo06DerivedQueries();
//        ejemplo07BuscarCopiasNoAlquiladas();
//        ejemplo08ConsultasPersonalizadas();
//        ejemplo09ConsultasPersonalizadasConParametros();
//        ejemplo10ConsultaPaginada();
//        ejemplo11DtoConsultaDerivada();
//        ejemplo12DtoJPQLConOptional();
        ejemplo13DtoJPQLConJoin();
//        ejemplo14DtoConRelaciones();
    }


    private void ejemplo01ConsultaRepo() {
        System.out.println("Método ejemplo01ConsultaRepo: Lee, usando los métodos count() de los repositorios, la cantidad de categorías y de actores");
        System.out.printf("Hay %d categorías\n", categoryService.count());
        System.out.printf("Hay %d actores\n", actorService.count());
        System.out.println("Fin de ejemplo01ConsultaRepo");
    }

    private void ejemplo02InsercionRepo() {
        // Ojo, este ejemplo puede agotar los valores identity en la tabla de categorías (solo admite 128).
        // Para restaurarlos:
        // Borrar las categorías no utilizadas:
        // delete category where category_id not in (select category_id from film_category)
        // Restaurar el valor de identity. Esto se tiene que hacer un usuario con suficientes permisos (root, p.ej.)
        // alter table category auto_increment = 1
        System.out.println("Método ejemplo02InsercionRepo: Inserta en categorías 10 categorías, sin relacionarlas con películas.");
        for (int i = 1; i <= 10; i++) {
            Category category = new Category();
            category.setName(String.format("Categoría de prueba %d", i));
            categoryService.save(category);
            System.out.printf("Insertada categoría con id %d\n", category.getCategoryId());
        }
        System.out.println("Fin de ejemplo02InsercionRepo");
    }

    private void ejemplo03BorradoRepo() {
        System.out.println("Método ejemplo03BorradoRepo: Elimina del repo de categorías todas aquellas que no están relacionadas con películas.");
        // Esta llamada a deleteUnusedCategories falla si no tenemos activa la propiedad
        // "spring.jpa.properties.hibernate.enable_lazy_load_no_trans". Ver el método para
        // una explicación de por qué falla.
        categoryService.deleteUnusedCategories();
        System.out.println("Fin de ejemplo03BorradoRepo");
    }

    public void ejemplo04BorradoRepoTransaccional() {
        System.out.println("Método ejemplo04BorradoRepoTransaccional: Elimina del repo de categorías todas aquellas que no están relacionadas con películas, pero con la anotación @Transactional.");
        // Esta llamada no falla porque el método deleteUnusedCategoriesTransactional es transaccional.
        // Aunque "spring.jpa.properties.hibernate.enable_lazy_load_no_trans" sea false.
        // Al ser transaccional, mantiene la sesión abierta hasta que termina.
        try {
            categoryService.deleteUnusedCategoriesTransactional();
        } catch (Exception e) {
            System.out.printf("Error al eliminar categorías: %s", e.getMessage());

        }
        System.out.println("Fin de ejemplo04BorradoRepoTransaccional");
    }

    private void ejemplo05InsercionRepoRelacionadas() {
        System.out.println("Método ejemplo05InsercionRepoRelacionadas: Establece una relación entre películas y categorías.");
        // Primero entre película 1 y categoría 1 añadiendo la categoría a la película
        // Esto falla la segunda vez que se realiza porque duplicamos la clave primaria
        // de la tabla film_category.
        Optional<Film> filmOne = filmService.findById((short) 1);
        Category categoryOne = categoryService.findReferenceById((byte) 1);
        filmOne.get().getCategories().add(categoryOne);
        filmService.save(filmOne.get());
        // Igual entre película 2 y categoría 2 añadiendo la película a la categoría
        // Esto no sirve porque el EntityManager en las relaciones solo actualiza las
        // entidades del lado "propietario". En este caso la relación entre Film y Category
        // se ha definido Film como propietario de la relación (tiene la anotación @JoinColumns)
        // y Category como la relación inversa (tiene el atributo mappedBy).
        Optional<Category> categoryTwo = categoryService.findById((byte) 2);
        Optional<Film> filmTwo = filmService.findById((short) 2);
        categoryTwo.get().getFilms().add(filmTwo.get());
        categoryService.save(categoryTwo.get());
        System.out.println("Fin de ejemplo05InsercionRepoRelacionadas");
    }

    private void ejemplo06DerivedQueries() {
        System.out.println("Método ejemplo06DerivedQueries: Usa métodos de repositorio definidos con la convención de nombres para consultas derivadas.");
        var films = filmService.findById(List.of((short) 2, (short) 3, (short) 4, (short) 5));
        for (Film f : films) {
            System.out.printf("%s - %s\n", f.getFilmId(), f.getTitle());

        }
        System.out.println("-".repeat(10));
        films = filmService.findByIdAndTitle(List.of((short) 2, (short) 3, (short) 4, (short) 5), "o");
        for (Film f : films) {
            System.out.printf("%s - %s\n", f.getFilmId(), f.getTitle());

        }

        System.out.println("Fin de ejemplo06DerivedQueries");
    }

    private void ejemplo07BuscarCopiasNoAlquiladas() {
        System.out.println("Método ejemplo07BuscarCopiasNoAlquiladas: Busca copias no alquiladas de una película en una tienda.");
        var copies = inventoryService.findNotRentedCopies(1, 2);
        for (InventoryItem item : copies) {
            System.out.printf("%d\n", item.getInventoryId());
        }
        System.out.println("Fin de ejemplo07BuscarCopiasNoAlquiladas");
    }

    private void ejemplo08ConsultasPersonalizadas() {
        System.out.println("Método ejemplo08ConsultasPersonalizadas: Lee categorías usando JPQL y SQL.");
        System.out.println("Recuperando todas las categorías con JPQL");
        Collection<Category> categoriesJpql = categoryService.getAllJpql();
        System.out.printf("Se han recuperado %d categorías.\n", categoriesJpql.size());
        System.out.println("Recuperando todas las categorías con SQL");
        Collection<Category> categoriesSql = categoryService.getAllSql();
        System.out.printf("Se han recuperado %d categorías.\n", categoriesSql.size());
        System.out.println("Fin de ejemplo08ConsultasPersonalizadas");
    }

    private void ejemplo09ConsultasPersonalizadasConParametros() {
        System.out.println("Método ejemplo09ConsultasPersonalizadasConParametros: Lee categorías usando JPQL y SQL, pasado parámetros a las consultas.");
        System.out.println("Recuperando todas las categorías con JPQL");
        Collection<Category> categoriesJpql = categoryService.searchWithLikeJpql("%i%");
        System.out.printf("Se han recuperado %d categorías.\n", categoriesJpql.size());
        System.out.println("Recuperando todas las categorías con SQL");
        Collection<Category> categoriesSql = categoryService.searchWithLikeSql("%u%");
        System.out.printf("Se han recuperado %d categorías.\n", categoriesSql.size());
        System.out.println("Fin de ejemplo09ConsultasPersonalizadasConParametros");
    }

    private void ejemplo10ConsultaPaginada() {
        System.out.println("Método ejemplo10ConsultaPaginada: Lee películas de 17 en 17, ordenadas por título ascendente.");

        // Las páginas se inician en 0, como los índices de arrays
        int initialPageNumber = 0;
        int registrosPorPagina = 58;

        // Una de muchas posibles alternativas. Hay varias sobrecargas de pageRequest.of.
        Pageable pageRequest = PageRequest.of(initialPageNumber, registrosPorPagina, Sort.Direction.ASC, "title", "releaseYear");
        Page<Film> page = filmService.findAll(pageRequest);

        // Mostramos los datos de la página
        showFilmsPage(page);

        // Saltamos a la última página
        page = filmService.findAll(pageRequest.withPage(page.getTotalPages() - 1));

        // Mostramos los datos de la página
        showFilmsPage(page);


        System.out.println("Fin de ejemplo10ConsultaPaginada");
    }

    private void showFilmsPage(Page<Film> page) {
        System.out.println("-".repeat(100));
        System.out.printf("Mostrando %s registros de un total de %s.\n", page.getNumberOfElements(), page.getTotalElements());
        // Como las páginas se numeran desde 0, sumamos 1 al mostrar el número de página.
        System.out.printf("Mostrando página %s de %s.\n", page.getNumber() + 1, page.getTotalPages());
        List<Film> films = page.toList();
        Film primerRegistro = films.get(0);
        Film ultimoRegistro = films.get(films.size() - 1);
        System.out.printf("Primer registro de la página: %d - %s - %s\n", primerRegistro.getFilmId(), primerRegistro.getTitle(), primerRegistro.getReleaseYear());
        System.out.printf("Último registro de la página: %d - %s - %s\n", ultimoRegistro.getFilmId(), ultimoRegistro.getTitle(), ultimoRegistro.getReleaseYear());
        // Para recorrer todos los elementos de la página:
//        for (Film film : page.toList()) {
//            System.out.printf("%d - %s - %s\n", film.getFilmId(), film.getTitle(), film.getReleaseYear());
//        }
        System.out.println("-".repeat(100));
    }

    private void ejemplo11DtoConsultaDerivada() {
        System.out.println("Método ejemplo11DtoConsultaDerivada: Lee sólo algunos datos de Film, usando clase como DTO, sin recuperar aquellos que no necesite.");
        System.out.println("Si se configura la propiedad spring.jpa.show-sql = true, se podrá ver que no se piden más columnas de las necesarias.");

        // Llamamos al servicio que busca detalles de películas, pero sólo devuelve un DTO
        int languageId = 1;
        Collection<FilmDto> films = filmService.findFilmDtoByLanguageId(languageId);

        // Mostramos los datos
        films.forEach(f -> System.out.printf("%d - %s - %d\n", f.getFilmId(), f.getTitle(), f.getReleaseYear()));

        System.out.println("Fin de ejemplo11DtoConsultaDerivada");
    }

    private void ejemplo12DtoJPQLConOptional() {
        System.out.println("Método ejemplo12DtoJPQLConOptional: Lee sólo algunos datos de Film , sin recuperar aquellos que no necesite.");
        System.out.println("Es una búsqueda de una película por Id, lo que puede devolver o un solo dto o ninguno, y por eso se usa optional");
        System.out.println("Si se configura la propiedad spring.jpa.show-sql = true, se podrá ver que no se piden más columnas de las necesarias.");

        // Pedimos el id de la película al usuario
        System.out.print("Introduce el id de una película: ");
        int filmId = scanner.nextInt();

        // Buscamos el DTO
        Optional<FilmDto> film = filmService.findFilmDtoById(filmId);

        // Comprobamos is hemos encontado la película y mostramos los datos
        if (film.isEmpty()) {
            System.out.printf("Película con id %d no encontrada.\n", filmId);
        } else {
            System.out.printf("Película con id %d encontrada: %s.\n", filmId, film.get().getTitle());
        }

        System.out.println("Fin de ejemplo12DtoJPQLConOptional");
    }


    private void ejemplo13DtoJPQLConJoin() {
        System.out.println("Método ejemplo13DtoJPQLConJoin: Lee DTO de direcciones de tiendas, pero incluye en el DTO información de ciudad y país.");
        System.out.println("Si se configura la propiedad spring.jpa.show-sql = true, se podrá ver que no se piden más columnas de las necesarias.");

        Collection<AddressDto> addresses = storeService.findAllAddressesDto();

        addresses.forEach(a -> System.out.printf("%d - %s - %s - %s\n", a.getAddressId(), a.getPostalCode(), a.getCity(), a.getCountry()));

        System.out.println("Fin de ejemplo13DtoJPQLConJoin");
    }

    private void ejemplo14DtoConRelaciones() {
        System.out.println("Método ejemplo14DtoConRelaciones: Lee DTO de clientes y los pagos que realizan.");
        System.out.println("Si se configura la propiedad spring.jpa.show-sql = true, se podrá ver que no se piden más columnas de las necesarias.");

        Short[] ids = {1, 2, 3, 4};
        Collection<CustomerPaymentsDto> customersPayments = customerService.findPaymentsByCustomerIdIn(Arrays.asList(ids));

        customersPayments.forEach(this::showPayments);

        System.out.println("Fin de ejemplo14DtoConRelaciones");
    }

    private void showPayments(CustomerPaymentsDto customerPaymentsDto) {
        System.out.printf("%s - %s %s\n", customerPaymentsDto.getCustomerId(), customerPaymentsDto.getFirstName(), customerPaymentsDto.getLastName());
        customerPaymentsDto.getPayments().forEach(cp-> System.out.printf("\t%s - %s\n", cp.getPaymentDate(), cp.getAmount()));
    }


}
