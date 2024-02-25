import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public enum utils {
    ;
static EntityManager entityManager (){
    EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
   return factory.createEntityManager();

}
}
