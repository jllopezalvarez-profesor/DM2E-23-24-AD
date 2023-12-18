import es.jllopezalvarez.accesodatos.ejemplos.ejemplosjpabasicos.entities.Category;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;


public class Program {



    public static void main(String[] args) {
        try (EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(Constants.SAKILA_PERSISTENCE_UNIT);
             EntityManager entityManager = entityManagerFactory.createEntityManager()) {

            EntityTransaction transaction = entityManager.getTransaction();
            try {
                transaction.begin();

//                Category category = new Category();
//                category.setName("Nueva categoría");
//                entityManager.persist(category);
//                System.out.println(category);



               Category c = entityManager.find(Category.class, 33);
               entityManager.remove(c);

                transaction.commit();
            } finally {
                if (transaction.isActive()) {
                    transaction.rollback();
                }
            }
        }


    }


}
